<%@ page import="mineria.datos.Registro" %>
<g:if test="${flash.error}">
    <div class="errors">
        ${flash.error}
    </div>
</g:if>

<table>
    <tr>
        <td>
            <div class="fieldcontain ${hasErrors(bean: registroNuevo, field: 'valorA', 'error')} ">
                <label for="valorA">
                    <g:message code="registro.valorA.label" default="${configuracion?.columnaA}"/>
                </label>
                <br>
                <g:textField name="valorNuevoA" value="${registroNuevo?.valorA}"/>
            </div>
        </td>
        <td>
            <div class="fieldcontain ${hasErrors(bean: registroNuevo, field: 'valorB', 'error')} required">
                <label for="valorB">
                    <g:message code="registro.valorB.label" default="${configuracion?.columnaB}"/>
                    <span class="required-indicator">*</span>
                </label>
                <br>
                <g:field type="number" name="valorNuevoB" required=""
                         value="${fieldValue(bean: registroNuevo, field: 'valorB')}"/>
            </div>
        </td>
        <td>
            <div class="fieldcontain">
                <label for="valorK">
                    Valor de K / <g:message code="registro.valorC.label" default="${configuracion?.columnaC}"/>
                    <span class="required-indicator">*</span>
                </label>
                <br>
                <g:textField name="valorK" value="${valorK}"/>
            </div>

        </td>
    </tr>
    %{--<tr>

        <td>
            <div class="fieldcontain ${hasErrors(bean: registroNuevo, field: 'valorC', 'error')} ">
                <label for="valorC">
                    <g:message code="registro.valorC.label" default="${configuracion?.columnaC}"/>

                </label>
                <br>
                ${registroNuevo?.valorC}
                --}%%{--<g:field type="number" name="valorNuevoC" enable="false" value="${registroNuevo?.valorC}"/>--}%%{--
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <div class="fieldcontain ${hasErrors(bean: registroNuevo, field: 'valorD', 'error')} ">
                <label for="valorD">
                    <g:message code="registro.valorD.label" default="${configuracion?.columnaD}"/>
                </label>
                <br>
                ${registroNuevo?.valorD}
                --}%%{--<g:textField name="valorNuevoD" enable="false" value="${registroNuevo?.valorD}"/>--}%%{--
            </div>
        </td>
    </tr>--}%
</table>