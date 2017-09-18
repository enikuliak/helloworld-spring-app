<#ftl output_format="XML" />
<#setting url_escaping_charset='UTF-8'>
    <#import "../layouts/main-layout.ftl" as layout />
    <@layout.mainLayout ("Edit user: " + id?c) "edit-user">

    <#if error??>
        <div class="alert alert-danger">
            ${error}
        </div>
    </#if>

    <form method="post" action="/admin/users/${id?c?url}/edit-user">
        <div class="form-group">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" id="firstName" placeholder="First Name" name="firstName"
                   value="${firstName}">
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name"
                   value="${lastName}">
        </div>

        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="username"
                   value="${username}">
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="text" class="form-control" id="email" name="email" placeholder="Email" value="${email}">
        </div>

        <div class="form-group">
            <label for="id">ID</label>
            <input type="number" class="form-control" id="id" name="id" placeholder="ID" value="${id?c}" readonly>
        </div>

        <div class="row">
            <div class="form-group col-sm-4">
                <label>Birthday</label>
                <select name="month">
                    <option value="0">-Select-</option>
                    <option value="1"
                    <#if month==1>selected</#if>
                    >January</option>
                    <option value="2"
                    <#if month==2>selected</#if>
                    >February</option>
                    <option value="3"
                    <#if month==3>selected</#if>
                    >March</option>
                    <option value="4"
                    <#if month==4>selected</#if>
                    >April</option>
                    <option value="5"
                    <#if month==5>selected</#if>
                    >May</option>
                    <option value="6"
                    <#if month==6>selected</#if>
                    >June</option>
                    <option value="7"
                    <#if month==7>selected</#if>
                    >July</option>
                    <option value="8"
                    <#if month==8>selected</#if>
                    >August</option>
                    <option value="9"
                    <#if month==9>selected</#if>
                    >September</option>
                    <option value="10"
                    <#if month==10>selected</#if>
                    >October</option>
                    <option value="11"
                    <#if month==11>selected</#if>
                    >November</option>
                    <option value="12"
                    <#if month==12>selected</#if>
                    >December</option>
                </select>
            </div>

            <div class="form-group col-sm-2">
                <input type="text" class="form-control" value="${day}" name="day" placeholder="Day">
            </div>

            <div class="form-group col-sm-4">
                <input class="form-control" placeholder="Year" name="year" value="${(year?c)!}" type="text">
            </div>
        </div>

        <div class="form-group">
            <label>Country</label>

            <select name="country">
                <option value="">-Select-</option>
                <#list countries as countryFromList>
                    <option value="${countryFromList.getIso()}"
                    <#if countryFromList.getIso() == (country!"")>selected</#if>
                    >${countryFromList.getName()}</option>
                </#list>
            </select>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-default" name="update" value="Update">Update</button>
            <button type="submit" class="btn btn-default" name="cancel" value="Cancel">Cancel</button>
        </div>
    </form>

</@layout.mainLayout>