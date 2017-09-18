<#ftl output_format="XML" />
<#setting url_escaping_charset='UTF-8'>
<#import "../layouts/main-layout.ftl" as layout />
<@layout.mainLayout ("Edit country: " + iso) "edit-country">

<#if error??>
    <div class="alert alert-danger">
        ${error}
    </div>
</#if>

<form method="post" action="/admin/countries/${iso?url}/edit-country">
    <div class="form-group">
        <label for="iso">Iso</label>
        <input type="text" class="form-control" id="iso" placeholder="Iso" name="iso" value="${iso}" readonly>
    </div>
    <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="Country Name" value="${name}">
    </div>

    <button type="submit" class="btn btn-default" name="update" value="Update">Update</button>
    <button type="submit" class="btn btn-default" name="cancel" value="Cancel">Cancel</button>
</form>

</@layout.mainLayout>