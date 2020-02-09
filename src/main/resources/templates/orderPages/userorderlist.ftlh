<#import "../parts/profilecontent.ftlh" as pf>

<@pf.profile>
    <div class="container">
        <div class="row shadow-lg bg-white rounded">
            <table class="table table-bordered table-hover">
                <thead class="thead-dark">
                <tr>
                    <th>Дата</th>
                    <th>Количество</th>
                    <th>Цена</th>
                    <th>Адрес доставки</th>
                    <th>Стоимость</th>
                    <th>Способ оплаты</th>
                    <th>Статус</th>
                </tr>
                </thead>
                <tbody class="table-striped">
                <#list orders as order>
                    <tr>
                        <td><a href="/order/${order.getId()}">${order.timeCreated?datetime}</a></td>
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
