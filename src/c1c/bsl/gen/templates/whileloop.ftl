<#include "base.ftl">
<#assign title="whileloop">
Пока ${condition} Цикл
<#list lines as line>
    ${line}
</#list>
КонецЦикла;