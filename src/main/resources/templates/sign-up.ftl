<#ftl output_format="XML" />
<#import "layouts/main-layout.ftl" as layout />
<@layout.mainLayout "Sign Up" "sign-up">




    <#if error??>
        <div class="alert alert-danger">
            ${error}
        </div>
    </#if>

    <form role="form" method="post" action="/sign-up">
        <div class="row">
            <div class="form-group col-sm-6">
                <label for="firstName"><b>First Name</b></label>
                <input type="text" class="form-control" id="firstName" name="firstName"
                       placeholder="Enter Your First Name" value="${firstName}">
            </div>

            <div class="form-group col-sm-6">
                <label for="lastName"><b>Last Name</b></label>
                <input type="text" class="form-control" id="lastName" name="lastName"
                       value="${lastName}"
                       placeholder="Enter Your Last Name">
            </div>
        </div>


        <div class="form-group ">
            <label for="username"><b>Username</b></label>
            <input type="text" class="form-control" id="username"
                   name="username" placeholder="Enter Your Username" value="${username}">
        </div>

        <div class="row">
            <div class="form-group col-sm-4">
                <label><b>Birthday</b></label>

                <select name="month">
                    <option value="0">Choose a month</option>
                    <option value="1" <#if month==1>selected</#if>>January</option>
                    <option value="2" <#if month==2>selected</#if>>February</option>
                    <option value="3" <#if month==3>selected</#if>>March</option>
                    <option value="4" <#if month==4>selected</#if>>April</option>
                    <option value="5" <#if month==5>selected</#if>>May</option>
                    <option value="6" <#if month==6>selected</#if>>June</option>
                    <option value="7" <#if month==7>selected</#if>>July</option>
                    <option value="8" <#if month==8>selected</#if>>August</option>
                    <option value="9" <#if month==9>selected</#if>>September</option>
                    <option value="10" <#if month==10>selected</#if>>October</option>
                    <option value="11" <#if month==11>selected</#if>>November</option>
                    <option value="12" <#if month==12>selected</#if>>December</option>
                </select>

            </div>

            <div class="form-group col-sm-2">
                <input type="text" class="form-control" name="day" placeholder="Day"
                    value="${(day)!}">
            </div>
            <div class="form-group col-sm-4">
                <input type="text" class="form-control" name="year" placeholder="Year"
                       value="${(year?c)!}">
            </div>
        </div>
        <div class="form-group">
            <label><b>Gender</b></label>

            <select name="gender">
                <option value="">Choose your gender</option>
                <option value="FEMALE" <#if gender=='FEMALE'>selected</#if>>Female</option>
                <option value="MALE" <#if gender=='MALE'>selected</#if>>Male</option>
                <option value="OTHER" <#if gender=='OTHER'>selected</#if>>Other</option>
            </select>

        </div>

        <div class="form-group">
            <label for="email"><b>Email</b></label>
            <input type="text" class="form-control" id="email" name="email"
                   value="${email}"
                   placeholder="Email">
        </div>

        <div class="row">
            <div class="form-group col-sm-6">
                <label><b>Country</b></label>

                <select name="country">
                    <option value="">Choose your country</option>
                    <#list countries as countryFromList>
                        <option value="${countryFromList.getIso()}"
                            <#if country == countryFromList.getIso()>selected</#if>
                            >${countryFromList.getName()}</option>
                    </#list>

                </select>
            </div>


            <div class="form-group col-sm-6">
                <label for="phone"><b>Phone</b></label>
                <input type="text" class="form-control" id="phone" name="phone"
                       value="${phone}"
                       placeholder="Enter Your Phone">
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-6">
                <label for="password"><b>Password</b></label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password">
            </div>
            <div class="form-group col-md-6">
                <label for="repeatPassword"><b>Repeat</b></label>
                <input type="password" class="form-control" id="repeatPassword" name="repeatPassword"
                       placeholder="Repeat Your Password">
            </div>
        </div>

        <button type="submit" class="btn btn-default">Sign up</button>

    </form>



</@layout.mainLayout>