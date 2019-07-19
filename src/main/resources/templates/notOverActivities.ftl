<#import "parts/common.ftl" as c>
<@c.page>
    <p><a href="/main">На главную страницу</a></p>
    <table  border=4>
        <caption>Activities which are not over</caption>
        <tr>
            <th>ssoId</th><th>subtype</th>

        </tr>
        <#list events as event>
            <tr align="center">
                <td>${event.getSsoid()}</td>
                <td>${event.getSubtype()}</td>
            </tr>
        </#list>
    </table>

</@c.page>