<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container">
        <div class="row mt-5">
            <#list categories as category>
                <div class="col-12 col-sm-6 col-md-4 col-lg-3 mb-3">
                    <div class="card text-white border-success mb-3">
                        <div class="card-body box">
                            <a class="badge badge-success" href="/catalog/productlist/${category.id}">${category.title}</a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@c.page>