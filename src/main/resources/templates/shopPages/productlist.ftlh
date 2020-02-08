<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container">
        <div class="row mt-5">
            <#list products as product>
                <div class="col-12 col-sm-6 col-md-4 col-lg-3 mb-3">
                    <div class="card border-success mb-3">
                        <img class="card-img-top" src="/img/${product.photo?if_exists}"/>
                        <div class="card-body text-center">
                            <a class="product-name-list" href="/product/${product.id}">${product.name}</a>
                            <p class="text-center product-price-list">${product.price} ₽</p>
                        </div>
                        <div class="card-footer text-muted">
                            <div class="ml-1 mb-3 row col-12">
                                <form action="/cart/add/" method="POST" class="col-12">
                                    <input type="hidden" name="productId" value="${product.id}"/>
                                    <input type="hidden" name="quantity" value="1"/>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button type="submit" class="btn btn-primary btn-lg btn-block mt-3">Pick to cart</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@c.page>