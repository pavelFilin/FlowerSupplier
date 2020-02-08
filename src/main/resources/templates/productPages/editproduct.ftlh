<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-3 ">
                <#include "../parts/userprofilenav.ftlh"/>
            </div>
            <div class="col-md-9">
                <div class="shadow p-5">
                    <form action="/product/management/edit" method="POST" enctype="multipart/form-data">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="product-name">Name</label>
                                <input value="${product.name}" name="name" type="text" minlength="3" maxlength="50"
                                       class="form-control" id="product-name" placeholder="Name">
                            </div>
                            <div class="form-group col-md-4 offset-md-2">
                                <label for="product-price">Price</label>
                                <input value="${product.getPrice()?c}" name="price" min="1" max="1000000" type="number"
                                       class="form-control"
                                       id="product-price"
                                       placeholder="Price">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="product-price">Category</label>
                                <select name="category" class="form-control" id="product-price">
                                    <#list categories as category>
                                        <option <#if product.categoryId == category.id>selected</#if>>${category.title}</option>
                                    </#list>
                                </select>
                            </div>

                            <div class="form-group col-md-4 offset-md-2">
                                <label for="product-stock">Stock</label>
                                <input value="${product.stock}" name="stock" min="0" max="1000000" type="number"
                                       class="form-control"
                                       id="product-stock"
                                       placeholder="Stock">
                            </div>
                        </div>
                        <hr>
                        <div class="form-group">
                            <label for="product-description">Description</label>
                            <textarea name="description" minlength="1" maxlength="2048" class="form-control"
                                      id="product-description" rows="3"
                                      placeholder="Description">${product.description}</textarea>
                        </div>
                        <hr>
                        <div class="input-group">
                            <div class="custom-file">
                                <input name="file" type="file" class="custom-file-input" id="product-photo-file-input"
                                       aria-describedby="product-photo-reset">
                                <label class="custom-file-label" for="product-photo-file-input"
                                       id="product-photo-file-label">Choose
                                    file</label>
                            </div>
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" id="product-photo-reset">Reset
                                </button>
                            </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="id" value="${product.id}">
                        <button type="submit" class="btn btn-primary btn-lg btn-block mt-3">Edit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</@c.page>