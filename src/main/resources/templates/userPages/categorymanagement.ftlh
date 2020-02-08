<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-3 ">
                <#include "../parts/userprofilenav.ftlh"/>
            </div>
            <div class="col-md-9 shadow-lg bg-white rounded pt-3">
                <#if message??>
                    <div class="alert alert-danger" role="alert">
                        ${message}
                    </div>
                </#if>
                <div class="row">
                    <div class="col-md-8">
                        <table class="table table-bordered table-hover category-display ">
                            <thead class="thead-dark">
                            <th>ID</th>
                            <th>Название</th>
                            <th>Parent ID</th>
                            <th></th>
                            </thead>
                            <tbody class="table-striped">
                            <#list categories as category>
                                <tr>
                                    <td class="text">${category.id}</td>
                                    <td>${category.title}</td>
                                    <#if category.parentId != 0>
                                        <td>${category.parentId}</td>
                                    <#else>
                                        <td></td>
                                    </#if>

                                    <td align="center">
                                        <form action="/category/${category.id}" method="POST">
                                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                            <button class="btn btn-outline-danger" type="submit"><i
                                                        class="fas fa-trash-alt"></i></button>
                                        </form>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-4">
                        <form action="/category" method="POST">
                            <h4>Добавить новую категорию</h4>
                            <div class="form-group">
                                <label for="titleCategory">Название</label>
                                <input name="title" minlength="3" maxlength="50" type="text" class="form-control"
                                       id="titleCategory" placeholder="Название">
                            </div>
                            <div class="form-group">
                                <label for="parentCategory">Parent ID</label>
                                <select name="parentId" class="form-control" id="parentCategory">
                                    <option></option>
                                    <#list categories as category>
                                        <option>${category.id} - ${category.title}</option>
                                    </#list>
                                </select>
                            </div>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <input type="submit" class="btn btn-green text-white btn-sm btn-block" value="Создать">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>