<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container content mt-5">
        <div class="row">
            <div class="col-md-4">
                <div class="list-group">
                    <#list categories as category>
                        <a class="list-group-item" href="/catalog/productlist/${category.id}">${category.title}</a>
                    </#list>
                </div>
            </div>
            <div class="col-md-8 products">
                <div class="row">
                    <div class="col-sm-4">
                        <#list products as product>
                            <div class="product">
                                <div class="product-img">
                                    <a href="/product/${product.getId()}"><img src="/img/${product.photo?if_exists}" alt=""></a>
                                </div>
                                <p class="product-title">
                                    <a href="/product/${product.getId()}">${product.getName()}</a>
                                </p>
                                <p class="product-desc">${product.getDescription()}</p>
                                <p class="product-price">Цена: €${product.getPrice()}</p>
                                <form action="/cart/add/" method="POST" class="col-12">
                                    <input type="hidden" name="productId" value="${product.id}"/>
                                    <input type="hidden" name="quantity" value="1"/>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button type="submit" class="btn btn-green text-white btn-lg btn-block">Купить</button>
                                </form>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
            <#--        <div class="row mt-5">-->
            <#--            <#list categories as category>-->
            <#--                <div class="col-12 col-sm-6 col-md-4 col-lg-3 mb-3">-->
            <#--                    <div class="card text-white border-success mb-3">-->
            <#--                        <div class="card-body box">-->
            <#--                            <a class="badge badge-success" href="/catalog/productlist/${category.id}">${category.title}</a>-->
            <#--                        </div>-->
            <#--                    </div>-->
            <#--                </div>-->
            <#--            </#list>-->
            <#--        </div>-->
        </div>
    </div>
</@c.page>