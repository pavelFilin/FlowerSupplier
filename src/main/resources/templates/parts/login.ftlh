<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input class="btn badge-primary mt-1 btn-sm " type="submit" value="Выйти"/>
    </form>
</#macro>

<#macro login path isRegisterForm>
    <div id="login" class="mt-5">
        <form action="${path}"
              method="post">
            <div class="row centered-form">
                <div class="col-xs-12 col-sm-8 col-md-4 offset-sm-2 offset-md-4">
                    <div class="panel panel-default shadow p-3 mb-5 bg-white rounded">
                        <div class="panel-heading">
                            <#if isRegisterForm><h3 class="panel-title">Зарегистрироваться</h3>
                            <#else>
                                <h3 class="panel-title">Войти</h3>
                            </#if>

                        </div>
                        <#if message??>
                            <div class="alert alert-danger" role="alert">
                                ${message}
                            </div>
                        </#if>
                        <div class="panel-body">
                            <form role="form">
                                <#if isRegisterForm>
                                    <div class="row">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input type="text" name="firstName" id="first_name"
                                                       class="form-control input-sm"
                                                       <#if user??>
                                                           value="${user.firstName?if_exists}"
                                                       </#if>
                                                       placeholder="Имя">
                                            </div>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input type="text" name="lastName" id="last_name"
                                                       class="form-control input-sm"
                                                        <#if user??>
                                                            value="${user.lastName?if_exists}"
                                                        </#if>
                                                       placeholder="Фамилия">
                                            </div>
                                        </div>
                                    </div>
                                </#if>

                                <div class="form-group">
                                    <input type="email" <#if isRegisterForm>name="email" <#else >name="username"</#if>
                                           id="email" class="form-control input-sm"
                                            <#if user??>
                                                value="${user.email?if_exists}"
                                            </#if>
                                           placeholder="example@email.com">
                                </div>

                                <#if isRegisterForm>
                                    <div class="row">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input type="password" name="password" id="password"
                                                       class="form-control input-sm" placeholder="Пароль">
                                            </div>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input type="password" name="passwordConfirmation"
                                                       id="password_confirmation" class="form-control input-sm"
                                                       placeholder="Подтвердите пароль">
                                            </div>
                                        </div>
                                    </div>
                                <#else >
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-12 col-md-12">
                                            <div class="form-group">
                                                <input type="password" name="password" id="password"
                                                       class="form-control input-sm" placeholder="Пароль">
                                            </div>
                                        </div>
                                    </div>
                                </#if>
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <input class="btn btn-green btn-block log-up-button text-white" type="submit" <#if isRegisterForm>value="Зарегистрироваться" <#else>value="Войти"</#if>>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</#macro>