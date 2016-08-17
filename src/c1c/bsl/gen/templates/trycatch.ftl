<#include "base.ftl">
<#assign title="trycatch"><#compress>
Попытка
<#list lines as line>
    ${line}
</#list>
Исключение
<#if catch_lines??>
<#list catch_lines as line>
    ${line}
</#list>
</#if>
КонецПопытки;</#compress>