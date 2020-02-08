<#import "../parts/common.ftlh" as c>
<#include "../parts/security.ftlh">

<@c.page>
    <div class="container mt-5 shadow-lg bg-white rounded">
        <div class="row mt-2">
            <p class="product-name text-left pl-5 mb-0">${product.name}</p>
        </div>
        <hr>
        <div class="row">
            <div class="col-md-6">
                <img class="card-img-top" src="/img/${product.photo?if_exists}"/>
            </div>
            <div class="col-md-6 ">
                <dl class="row product-attr">
                    <dt class="col-xl-4">Цена</dt>
                    <dd class="col-xl-6">${product.price}</dd>
                    <hr>
                    <dt class="col-xl-4">На складе</dt>
                    <dd class="col-xl-6"><#if product.stock gt 0>Да<#else>Нет :(</#if></dd>
                    <dt class="col-xl-4">Описание</dt>
                    <dd class="col-xl-6">${product.description}</dd>
                </dl>
                <hr>
            </div>
            <div class="ml-1 mb-3 row col-12">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-green text-white btn-lg btn-block mt-3">Купить</button>
            </div>


            <#if isAdmin>
            <div class="ml-1 mb-3 row col-12">
                <a href="/product/management/edit/${product.id}" class="btn btn-danger text-light btn-lg btn-block mt-3">Изменить</a>
            </div>
            </#if>
        </div>
    </div>
</@c.page>