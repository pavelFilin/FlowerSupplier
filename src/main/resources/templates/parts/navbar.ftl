<#include "security.ftlh">
<#import "login.ftlh" as login>
<div class="container-fluid sticky-top navbar-dark bg-dark" id="navigator">
    <nav class="navbar navbar-expand-md navbar ">
        <a href="/" class="navbar-brand">Havach</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" href="#collapseExample" role="button"
                aria-expanded="false"
                aria-controls="collapseExample">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapseExample">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/catalog">Catalog</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">Магазины</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">Новости</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">Контакты</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"></a>
                </li>
            </ul>
            <#if !known>
                <ul class="nav navbar-nav ml-auto" id="col">
                    <li class="nav-item">
                        <a class="nav-link" href="/registration"><span class="fas fa-user"></span> Sign Up</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/profile"><span class="fas fa-sign-in-alt"></span> Login</a>
                    </li>
                </ul>
            <#else>
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/user/profile"><span><i class="fas fa-user-circle"></i></span> ${email}</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-danger btn-sm mt-1 mr-2" href="/cart" id="cart-button">Cart</a>
                    </li>
                    <li class="nav-item">
                        <@login.logout/>
                    </li>
                </ul>
            </#if>

        </div>
    </nav>
</div>
