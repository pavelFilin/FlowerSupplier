<#import "../parts/common.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-3 ">
                <#include "../parts/userprofilenav.ftlh"/>
            </div>
            <div class="col-md-9">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12">
                                <h4>Your Profile</h4>
                                <hr>
                            </div>
                        </div>
                        <d  iv class="row">
                            <div class="col-md-12">
                                <form action="/user/profile" method="POST">
                                    <div class="form-group row">
                                        <label for="username" class="col-4 col-form-label">First name</label>
                                        <div class="col-8">
                                            <input id="username" name="firstName" placeholder="Username"
                                                   class="form-control here" required="required"
                                                    <#if user??>
                                                        value="${user.firstName?if_exists}"
                                                    </#if>
                                                   type="text">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="name" class="col-4 col-form-label">Last name</label>
                                        <div class="col-8">
                                            <input id="name" name="lastName" placeholder="First Name"
                                                   class="form-control here"
                                                   required="required"
                                                    <#if user??>
                                                        value="${user.lastName?if_exists}"
                                                    </#if>
                                                   type="text">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="email" class="col-4 col-form-label">Email*</label>
                                        <div class="col-8">
                                            <input id="email" disabled name=""  placeholder="Email" class="form-control here"
                                                    <#if user??>
                                                        value="${user.email?if_exists}"
                                                    </#if>
                                                   type="text">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="newpass" class="col-4 col-form-label">New Password</label>
                                        <div class="col-8">
                                            <input id="newpass" name="password" placeholder="New Password"
                                                   class="form-control here" type="text">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="phone" class="col-4 col-form-label">Phone</label>
                                        <div class="col-8">
                                            <input id="phone" name="phone"
                                                    <#if userContacts??>
                                                   value="${userContacts.phone?if_exists}"
                                                   </#if>
                                                   placeholder="phone"
                                                   class="form-control here" type="text">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="address" class="col-4 col-form-label">Address</label>
                                        <div class="col-8">
                                            <input id="address" name="address"
                                                    <#if userContacts??>
                                                   value="${userContacts.address?if_exists}"
                                                   </#if>
                                                   placeholder="Address"
                                                   class="form-control here" type="text">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="offset-4 col-8">
                                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                            <button name="submit" type="submit" class="btn btn-primary">Update My
                                                Profile
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>