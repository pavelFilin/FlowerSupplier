<#import "../parts/profilecontent.ftlh" as pf>

<@pf.profile>
    <div class="container">
        <div class="row shadow-lg bg-white rounded" style="width: 130%" >
            <table class="table table-bordered table-hover" style="width: 140%">
                <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Дата</th>
                    <th>Количество</th>
                    <th>Цена</th>
                    <th>Телефон</th>
                    <th>Адрес</th>
                    <th>Цена</th>
                    <th>Способ оплаты</th>
                    <th>Статус</th>
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
