<%@ page import="mineria.datos.Registro" %>



<div class="fieldcontain ${hasErrors(bean: registroNuevo, field: 'valorA', 'error')} ">
    <label for="valorA">
        <g:message code="registro.valorA.label" default="${configuracion?.columnaA}"/>

    </label>
    <g:textField name="valorNuevoA" value="${registroNuevo?.valorA}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: registroNuevo, field: 'valorB', 'error')} required">
    <label for="valorB">
        <g:message code="registro.valorB.label" default="${configuracion?.columnaB}"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="number" name="valorNuevoB" required="" value="${fieldValue(bean: registroNuevo, field: 'valorB')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: registroNuevo, field: 'valorC', 'error')} ">
    <label for="valorC">
        <g:message code="registro.valorC.label" default="${configuracion?.columnaC}"/>

    </label>
    <g:field type="number" name="valorNuevoC" value="${fieldValue(bean: registroNuevo, field: 'valorC')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: registroNuevo, field: 'valorD', 'error')} ">
    <label for="valorD">
        <g:message code="registro.valorD.label" default="${configuracion?.columnaD}"/>

    </label>
    <g:textField name="valorNuevoD" value="${registroNuevo?.valorD}"/>
</div>
<div class="fieldcontain">
    <label for="valorK">
        <g:message code="Valor de K"/>

    </label>
    <g:textField name="valorK" value="${valorK}"/>
</div>

