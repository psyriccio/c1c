<#include "base.ftl">
<#assign title="preprcif"><#compress>
#Если ${condition} Тогда
<#list pos_lines as line>
    ${line}
</#list>
<#if neg_lines??>
#Иначе
<#list neg_lines as line>
    ${line}
</#list>
</#if>
#КонецЕсли</#compress>