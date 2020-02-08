<#import "../parts/common.ftlh" as c>

<#macro profile>
        <@c.page>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-3" id="profile-nav">
                <#include "userprofilenav.ftlh">
            </div>
            <div class="col-md-9" id="profile-content">
                <#nested >
            </div>
        </div>
    </div>
    </@c.page>
</#macro>