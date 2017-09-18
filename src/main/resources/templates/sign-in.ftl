<#ftl output_format="XML" />
<#import "layouts/main-layout.ftl" as layout />
<@layout.mainLayout "Sign In" "sign-in">

<#if error?? && error == 'invalidUsernameOrPassword'>
    <div class="alert alert-danger">
        Please enter correct username and password.
    </div>
</#if>

<form method="post" action="/sign-in?action=signin">
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" class="form-control" id="username" placeholder="Username" value="${username}">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" class="form-control" id="exampleInputPassword1" value="${password}">
    </div>
    <!--<div class="checkbox">
        <label>
            <input type="checkbox"> Check me out
        </label>
    </div>-->
    <button type="submit" class="btn btn-default">Sign in</button>
</form>

</@layout.mainLayout>