<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container ">
        <div class="row mt-5 offset-md-4 offset-sm-3 offset-2 py-3 shadow-lg bg-white rounded" style="max-width: 300px">
            <div class="col-12 ">
                <form action="/user" method="post">
                    <input class="form-control mb-2" disabled type="text" name="" value="${user.email}"/>
                    <#list rolesConst as role>
                        <div class="form-check">
                            <label>
                                <input class="form-check-input" type="checkbox"
                                       name="${role}"
                                        ${userRoles?seq_contains(role)?string("checked", "")}
                                />${role}
                            </label>
                        </div>
                    </#list>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-green text-white" type="submit">Save</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>