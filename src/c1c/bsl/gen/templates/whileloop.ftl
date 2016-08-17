<#include "base.ftl">
<#assign title="whileloop"><#compress>
Пока ${condition} Цикл
<#list lines as line>
    ${line}
</#list>
КонецЦикла;</#compress>