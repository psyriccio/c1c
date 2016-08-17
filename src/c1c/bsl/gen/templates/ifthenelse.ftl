<#include "base.ftl">
<#assign title="ifthenelse">
Если ${condition} Тогда
<#list pos_lines as line>
    ${line}
</#list>
<#if elseif_items??>
<#list elseif_items as key, value>
ИначЕсли ${key} Тогда
<#list value as ei_line>
    #{ei_line}
</#list>
</#list>
</#if>
<#if neg_lines??>
Иначе
<#list neg_lines as line>
    ${line}
</#list>
</#if>
КонецЕсли;
