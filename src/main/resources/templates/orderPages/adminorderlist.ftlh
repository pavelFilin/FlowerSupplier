<#import "../parts/profilecontent.ftlh" as pf>

<@pf.profile>
    <div class="container">
        <div class="row" style="width: 130%">
            <table class="table table-bordered table-hover" style="width: 140%">
                <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Date</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>phone</th>
                    <th>Delivery Address</th>
                    <th>Pay</th>
                    <th>Pay type</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody class="table-striped">
                <#list orders as order>
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.timeCreated?datetime}</td>
                        <td>${order.quantity}</td>
                        <td>${order.finalPrice}</td>
                        <td>${order.phone}</td>
                        <td>${order.address}</td>
                        <td>${order.paymentStatus}</td>
                        <td>${order.paymentType}</td>
                        <td style="min-width: 140px"><select name="orderStatus" class="form-control"
                                    onchange="changeOrderStatus(this, ${order.getId()})">
                                <#list orderStatus as status>
                                    <option <#if status==order.getOrderStatus()> selected</#if>>${status}</option>
                                </#list>
                            </select></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@pf.profile>
