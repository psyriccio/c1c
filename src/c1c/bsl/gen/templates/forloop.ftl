<#include "base.ftl">
<#assign title="forloop"><#compress>
Для ${variable} = ${from} По ${to} Цикл
<#list lines as line>
    ${line}
</#list>
КонецЦикла;</#compress>