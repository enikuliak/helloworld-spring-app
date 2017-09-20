<#ftl output_format="XML" />
<#setting url_escaping_charset='UTF-8'>
    <#import "../layouts/main-layout.ftl" as layout />
    <@layout.mainLayout ("Edit user: " + user.getId()?c) "edit-user">

    <#if error??>
        <div class="alert alert-danger">
            ${error}
        </div>
    </#if>

    <form method="post" action="/admin/users/${user.getId()?c?url}/edit-user">
        <div class="form-group <#if errorMap['firstName']??>has-error</#if>">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" id="firstName" placeholder="First Name" name="firstName"
                   value="${user.getFirstName()}">
        </div>
        <div class="form-group <#if errorMap['lastName']??>has-error</#if>">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name"
                   value="${user.getLastName()}">
        </div>

        <div class="form-group <#if errorMap['username']??>has-error</#if>">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="username"
                   value="${user.getUsername()}">
        </div>

        <div class="form-group <#if errorMap['email']??>has-error</#if>">
            <label for="email">Email</label>
            <input type="text" class="form-control" id="email" name="email" placeholder="Email"
                   value="${user.getEmail()}">
        </div>

        <div class="form-group">
            <label for="id">ID</label>
            <input type="number" class="form-control" id="id" name="id" value="${user.getId()?c}" readonly>
        </div>

        <div class="row">
            <div class="form-group col-sm-4 <#if errorMap['month']??>has-error</#if>">
                <label>Birthday</label>
                <select name="month">
                    <option value="0">-Select-</option>
                    <option value="1"
                    <#if user.getMonth()==1>selected</#if>
                    >January</option>
                    <option value="2"
                    <#if user.getMonth()==2>selected</#if>
                    >February</option>
                    <option value="3"
                    <#if user.getMonth()==3>selected</#if>
                    >March</option>
                    <option value="4"
                    <#if user.getMonth()==4>selected</#if>
                    >April</option>
                    <option value="5"
                    <#if user.getMonth()==5>selected</#if>
                    >May</option>
                    <option value="6"
                    <#if user.getMonth()==6>selected</#if>
                    >June</option>
                    <option value="7"
                    <#if user.getMonth()==7>selected</#if>
                    >July</option>
                    <option value="8"
                    <#if user.getMonth()==8>selected</#if>
                    >August</option>
                    <option value="9"
                    <#if user.getMonth()==9>selected</#if>
                    >September</option>
                    <option value="10"
                    <#if user.getMonth()==10>selected</#if>
                    >October</option>
                    <option value="11"
                    <#if user.getMonth()==11>selected</#if>
                    >November</option>
                    <option value="12"
                    <#if user.getMonth()==12>selected</#if>
                    >December</option>
                </select>
            </div>

            <div class="form-group col-sm-2 <#if errorMap['day']??>has-error</#if>">
                <input type="text" class="form-control" value="${user.getDay()?c}" name="day" placeholder="Day">
            </div>

            <div class="form-group col-sm-4 <#if errorMap['year']??>has-error</#if>">
                <input class="form-control" placeholder="Year" name="year" value="${(user.getYear()?c)!}" type="text">
            </div>
        </div>

        <div class="form-group <#if errorMap['country']??>has-error</#if>">
            <label>Country</label>
            <select name="country">
                <option value="">-Select-</option>
                <#list countries as countryFromList>
                    <option value="${countryFromList.getIso()}"
                    <#if countryFromList.getIso() == (user.getCountry()!"")>selected</#if>
                    >${countryFromList.getName()}</option>
                </#list>
            </select>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-default" name="update" value="Update">Update</button>
            <button type="submit" class="btn btn-default" name="cancel" value="Cancel">Cancel</button>
        </div>
    </form>

    <style>
        .has-error select {
            border: solid 1px red;
        }
    </style>

</@layout.mainLayout>