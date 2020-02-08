<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-3 ">
                <#include "../parts/userprofilenav.ftlh"/>
            </div>
            <div class="col-md-9">
                <div class="container ">
                    <div class="row">
                        <form class="form-group row" style="width: 100%">
                            <div class="col-md-6">
                                <input class="form-control" <#if filter??>value="${filter}" </#if> name="filter"
                                       placeholder="search" type="text">
                            </div>
                            <div class="col-md-5">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" <#if active??>checked</#if> type="radio"
                                           name="active" id="inlineRadio1"
                                           value="none">
                                    <label class="form-check-label" for="inlineRadio1">none</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" <#if active?? && active == "disabled">checked</#if>
                                           type="radio" name="active" id="inlineRadio2"
                                           value="disabled">
                                    <label class="form-check-label" for="inlineRadio2">disabled</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" <#if active?? && active == "active">checked</#if>
                                           type="radio" name="active" id="inlineRadio3"
                                           value="active">
                                    <label class="form-check-label" for="inlineRadio3">active</label>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <input class="btn btn-info" value="search" type="submit">
                            </div>
                        </form>
                    </div>
                    <div class="row">
                        <table class="table admin-product-table table-bordered table-hover">
                            <thead class="thead-dark">
                            <tr>
                                <th>Id</th>
                                <th>Photo</th>
                                <th>Product Name</th>
                                <th>Category Name</th>
                                <th>Stock</th>
                                <th>Active</th>
                            </tr>
                            </thead>
                            <tbody class="table-striped">
                            <#list products as product>
                                <tr>
                                    <td>${product.id}</td>
                                    <td><img style="width: 100px;" class="photo-table-product-list"
                                             src="/img/${product.photo?if_exists}"/></td>
                                    <td><a href="/product/${product.id}">${product.name}</a></td>
                                    <td>${product.categoryId} - ${product.category.title}</td>
                                    <td><input min=0, max="10000" type="number" class="form-control" value="${product.stock}" onchange="changeProductStock(this, ${product.id})"></td>
                                    <td><input <#if product.active >checked</#if>  onchange="changeProductActive(this, ${product.id})" class="form-control-input"
                                               type="checkbox"/></td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>