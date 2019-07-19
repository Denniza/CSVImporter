<#import "parts/common.ftl" as c>
<@c.page>
    <p><a href="/main">На главную страницу</a></p>
    <table  border=4>
        <caption>Events For Last Hour</caption>
        <tr>
            <th>ssoId</th><th>form_id</th>
        </tr>
    <#list events as event>
        <tr align="center">
            <td>${event.getSsoid()}</td>
            <td>${event.getFormId()}</td>
        </tr>
    </#list>
    </table>

</@c.page>