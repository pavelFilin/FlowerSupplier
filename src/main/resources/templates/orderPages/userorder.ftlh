<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container">
        <div class="row">
            <table class="table table-bordered table-hover table-info">
                <thead class="thead-dark">
                <tr>
                    <th>Products</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Payment Status</th>
                    <th>Payment Type</th>
                    <th class="warning">Order Status</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${nameOrderItems}</td>
                    <td>${order.quantity}</td>
                    <td>${order.finalPrice}</td>
                    <td>${order.paymentStatus}</td>
                    <td>${order.paymentType}</td>
                    <td class="bg-warning">${order.orderStatus}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="row">
            <table class="table table-bordered table-hover">
                <thead class="thead-dark">
                <th></th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                </thead>
                <tbody class="table-striped">
                <#list order.orderItems as oi>
                    <tr style="max-width: 25px">
                        <td><img class="card-img-top" style="width: 150px"
                                 src="/img/${oi.product.photo!}"/></td>
                        <td>${oi.product.name}</td>
                        <td>${oi.quantity}</td>
                        <td>${oi.totalPrice}</td>
                    </tr>
                </#list>
                <tr class="bg-info text-light">
                    <td></td>
                    <td></td>
                    <td>${order.quantity}</td>
                    <td>${order.finalPrice}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <hr>
    </div>
</@c.page>