<#include "security.ftlh">

<div class="list-group ">
    <a href="/user/profile/" class="list-group-item list-group-item-action">Профиль</a>
    <a href="/order" class="list-group-item list-group-item-action ">Заказы</a>

    <#if isAdmin>
        <a href="/user/profile/userlist" class="list-group-item list-group-item-action admin-nav-link">Пользователи</a>
        <a href="/category" class="list-group-item list-group-item-action admin-nav-link">Категории</a>
        <a href="/product/management" class="list-group-item list-group-item-action admin-nav-link">Новый товар</a>
        <a href="/product/adminproductlist" class="list-group-item list-group-item-action admin-nav-link">Все товары</a>
        <a href="/order/list" class="list-group-item list-group-item-action admin-nav-link">Все заказы</a>
    </#if>
</div>