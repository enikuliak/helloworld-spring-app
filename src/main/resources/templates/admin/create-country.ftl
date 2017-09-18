<#ftl output_format="XML" />
<#import "../layouts/main-layout.ftl" as layout />
<@layout.mainLayout "Create country" "create-country">

<#if error??>
    <div class="alert alert-danger">
        ${error}
    </div>
</#if>

<form method="post" action="/admin/create-country">
    <div class="form-group">
        <label for="iso">Iso</label>
        <input type="text" class="form-control" id="iso" placeholder="Iso" name="iso" value="${iso}">
    </div>
    <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="Country Name" value="${name}">
    </div>

    <button type="submit" class="btn btn-default" name="create" value="Create">Create</button>
    <button type="submit" class="btn btn-default" name="cancel" value="Cancel">Cancel</button>
</form>

</@layout.mainLayout>