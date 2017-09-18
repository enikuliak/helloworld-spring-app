<#ftl output_format="XML" />
<#setting url_escaping_charset='UTF-8'>
    <#import "../layouts/main-layout.ftl" as layout />
    <@layout.mainLayout "Users" "admin/users">

    <div class="col-lg-12" style="margin-top: 10px;">

        <form action="/admin/users" role="form" method="get" id="searchForm">
            Query:
            <input type="text" name="query" id="query" value="${query}"/>
            <button type="submit">Search</button>
            <button type="reset" onclick="var g = document.getElementById; g('query').value='';g('searchForm').submit();">Reset</button>
        </form>

    </div>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Username</th>
            <th>Email</th>
            <th>Registration Date</th>
            <th>Operation</th>
        </tr>
        </thead>
        <tbody>
        <#list users as usersFromList>
            <tr>
                <td>${usersFromList.getId()?c}</td>
                <td>${usersFromList.getFirstName()}</td>
                <td>${usersFromList.getLastName()}</td>
                <td>${usersFromList.getUsername()}</td>
                <td>${usersFromList.getEmail()}</td>
                <td>${usersFromList.getRegistrationDateTime().format(dateTimeFormatter)}</td>
                <td>
                    <a href="/admin/users/${usersFromList.getId()?c?url}/edit-user">Edit</a>
                    <a href="#" onClick="return deleteUser('${usersFromList.getId()?c?js_string}');">Delete</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

    <div class="col-lg-12" style="margin-top: 10px;">

        <ul class="pagination">
            <#list pagination.getPages() as page>

                <li
                <#if currentPage == page>
                    class="active"
                </#if>
                <#if 0 == page>
                    class="disabled"
                </#if>
                >
                <#if 0 != page>
                    <a href="/admin/users?query=${query?url}&page=${page?c?url}">${page}</a>
                </#if>
                <#if 0 == page>
                    <a href="#" onclick="return false;">..</a>
                </#if>
                </li>

            </#list>


            <!--<li class="disabled"><a href="#">&laquo;</a></li>
            <li class="active"><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">&raquo;</a></li>-->
        </ul>

    </div>


    <form id="deleteForm" role="form" method="post" action="/admin/users">
        <input type="hidden" id="deleteIdValue" name="user" value=""/>
    </form>

    <script language="JavaScript">
    function deleteUser(id) {
      if (!confirm('Do you really want to delete user: ' + id)) {
        return false;
      }
      document.getElementById('deleteIdValue').value = id;
      document.getElementById('deleteForm').submit();
      return false;
    }
    </script>

</@layout.mainLayout>