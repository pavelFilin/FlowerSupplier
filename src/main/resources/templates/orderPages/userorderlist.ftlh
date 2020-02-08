<#import "../parts/profilecontent.ftlh" as pf>

<@pf.profile>
    <div class="container">
        <div class="row">
            <table class="table table-bordered table-hover">
                <thead class="thead-dark">
                <tr>
                    <th>Date</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Delivery Address</th>
                    <th>Pay</th>
                    <th>Pay type</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody class="table-striped">
                <#list orders as order>
                    <tr>
                        <td>${order.timeCreated?datetime}</td>
                        <td>${order.quantity}</td>
                        <td>${order.finalPrice}</td>
                        <td>${order.address}</td>
                        <td>${order.paymentStatus}</td>
                        <td>${order.paymentType}</td>
                        <td>${order.orderStatus}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@pf.profile>
