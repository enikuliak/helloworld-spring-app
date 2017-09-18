<#ftl output_format="XML" />
<#setting url_escaping_charset='UTF-8'>
<#import "../layouts/main-layout.ftl" as layout />
<@layout.mainLayout "Countries" "admin/countries">

<table class="table table-bordered">
    <thead>
    <tr>
        <th>#</th>
        <th>ISO</th>
        <th>Name</th>
        <th>
            Operation
            <a href="/admin/create-country" class="btn btn-primary">New</a>
        </th>
    </tr>
    </thead>
    <tbody>
    <#list countries as countryFromList>
        <tr>
            <td>${countryFromList?index+1}</td>
            <td>${countryFromList.getIso()}</td>
            <td>${countryFromList.getName()}</td>
            <td>
                <a href="/admin/countries/${countryFromList.getIso()?url}/edit-country">Edit</a>
                <a href="#" onClick="return deleteCountry('${countryFromList.getIso()?js_string}');">Delete</a>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

<form id="deleteForm" role="form" method="post" action="/admin/countries">
    <input type="hidden" id="deleteIsoCodeValue" name="country" value="" />
</form>

<script language="JavaScript">
    function deleteCountry(isoCode) {
      if (!confirm('Do you really want to delete country: ' + isoCode)) {
        return false;
      }
      document.getElementById('deleteIsoCodeValue').value = isoCode;
      document.getElementById('deleteForm').submit();
      return false;
    }
</script>

</@layout.mainLayout>