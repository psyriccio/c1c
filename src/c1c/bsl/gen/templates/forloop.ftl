<#include "base.ftl">
<#assign title="forloop">
Для ${variable} = ${from} По ${to} Цикл
<#list lines as line>
    ${line}
</#list>
КонецЦикла;