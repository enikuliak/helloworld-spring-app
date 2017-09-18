<#ftl output_format="XML" />
<#setting url_escaping_charset='UTF-8'>
<#macro mainLayout title="Sign In" page=""><!DOCTYPE html>
    <html lang="en">
    <head>
        <title>${title}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <script src="/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </head>

    <body>
    <!-- Static navbar -->
    <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">Our application title</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li <#if page=="home">class="active"</#if>>
                        <a href="/home">Home</a></li>
                    <li <#if page=="sign-in">class="active"</#if>>
                        <a href="/sign-in">Sign in</a>
                    </li>
                    <li <#if page=="sign-up">class="active"</#if>>
                        <a href="/sign-up">Sign up</a>
                    </li>
                    <li <#if page=="contact">class="active"</#if>>
                        <a href="#contact">Contact</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li role="separator" class="divider"></li>
                            <li class="dropdown-header">Admin</li>
                            <li><a href="/admin/countries">Countries</a></li>
                            <li><a href="/admin/users">Users</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="../navbar/">Default</a></li>
                    <li class="active"><a href="./">Static top <span class="sr-only">(current)</span></a></li>
                    <li><a href="../navbar-fixed-top/">Fixed top</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>

    <div class="container">

        <h1>
            ${title}
        </h1>

        <#nested/>

        <p></p>

        <p>
            &copy; ${currentYear}
        </p>

    </div>

    </body>
    </html>
</#macro>