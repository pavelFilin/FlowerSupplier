<#include "security.ftlh">

<div class="list-group ">
    <a href="/user/profile/" class="list-group-item list-group-item-action active">Dashboard</a>
    <a href="/order" class="list-group-item list-group-item-action ">Orders</a>

    <#if isAdmin>
        <a href="/user/profile/userlist" class="list-group-item list-group-item-action admin-nav-link">User Management</a>
        <a href="/category" class="list-group-item list-group-item-action admin-nav-link">Categories Management</a>
        <a href="/product/management" class="list-group-item list-group-item-action admin-nav-link">Add Product</a>
        <a href="/product/adminproductlist" class="list-group-item list-group-item-action admin-nav-link">Product List</a>
        <a href="/order/list" class="list-group-item list-group-item-action admin-nav-link">Orders List</a>
    </#if>
</div>