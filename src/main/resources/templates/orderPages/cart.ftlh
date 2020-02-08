<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container">
        <div class="row">
            <#if message??><div class="alert alert-danger" role="alert">
                ${message}
            </div></#if>
            <table class="table table-bordered table-hover admin-product-table">
                <thead class="thead-dark">
                <th></th>
                <th>Наименование</th>
                <th>Количество</th>
                <th>Цена</th>
                </thead>
                <tbody class="table-striped">
                <#list cart as cartItem>
                    <tr style="max-width: 25px">
                        <td><img class="card-img-top" style="width: 150px"
                                 src="/img/${cartItem.product.photo!}"/></td>
                        <td>${cartItem.product.name}</td>
                        <td><input type="number" class="form-control" value="${cartItem.quantity}" min="1" maxlength="100" onchange="changeCartQuantity(this, ${cartItem.product.id})"></td>
                        <td>${cartItem.product.price * cartItem.quantity}</td>
                        <td><button onclick="deleteCartItem(${cartItem.id})" class="btn btn-outline-danger" type="submit"><i
                                        class="fas fa-trash-alt" ></i></button></td>
                    </tr>
                </#list>
                <tr class="bg-info text-light">
                    <td></td>
                    <td>Итого</td>
                    <td>${totalQuantity}</td>
                    <td>${totalPrice}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <hr>
        <div class="row">
            <form style="width: 100%" action="/order" method="POST">
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="phone">Телефон</label>
                            <input name="phone" minlength="3" maxlength="15"
                                   <#if userContacts??>value="${userContacts.phone!}"</#if> type="text"
                                   class="form-control" id="phone" placeholder="+7**********">
                        </div>

                        <div class="form-group">
                            <label for="address">Адрес</label>
                            <input name="address" minlength="3" maxlength="255"
                                   <#if userContacts??>value="${userContacts.address!}"</#if> type="text"
                                   class="form-control" id="address" placeholder="Адрес">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <h3>Тип оплаты</h3>
                        <div class="row ">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" checked type="radio" name="paymentType" id="cash"
                                       value="CASH">
                                <label class="form-check-label" for="cash">Наличными</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="paymentType" id="card" value="CARD">
                                <label class="form-check-label" for="card">Картой</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="paymentType" id="inlineRadio3"
                                       value=CARD_ON_SITE>
                                <label class="form-check-label" for="inlineRadio3">Картой на месте</label>
                            </div>
                        </div>
                    </div>
                </div>
                <#if cart?? && cart?size gt 0>
                    <div class="row">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-green text-white btn-lg btn-block mt-3">Оформить заказ</button>
                    </div>
                </#if>
            </form>
        </div>
    </div>
</@c.page>