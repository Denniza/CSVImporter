<#import "parts/common.ftl" as c>

<@c.page>
    <p><a href="/main">На главную страницу</a></p>
    <table  border=4>
        <caption>Top 5 Forms</caption>
        <tr>
            <th>formId</th>
        </tr>
        <#list events as event>
            <tr>
                <td>${event}</td>
            </tr>
        </#list>
    </table>
</@c.page>