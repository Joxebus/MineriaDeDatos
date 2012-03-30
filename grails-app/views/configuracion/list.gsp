<%@ page import="mineria.datos.Configuracion" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'configuracion.label', default: 'Configuracion')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-configuracion" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                    default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="edit" action="edit" id="${configuracion?.id}"
            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Se perderá la configuración actual ¿continuar?')}');">
            <g:message code="default.button.edit.label" default="Edit"/></g:link></li>
    </ul>
</div>

<div id="list-configuracion" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="valorA"
                              title="${configuracion?.columnaA}"/>

            <g:sortableColumn property="valorB"
                              title="${configuracion?.columnaB}"/>

            <g:sortableColumn property="valorC"
                              title="${configuracion?.columnaC}"/>

            <g:sortableColumn property="valorD"
                              title="${configuracion?.columnaD}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${registroInstanceList}" status="i" var="registroInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>${fieldValue(bean: registroInstance, field: "valorA")}</td>

                <td>${fieldValue(bean: registroInstance, field: "valorB")}</td>

                <td>${fieldValue(bean: registroInstance, field: "valorC")}</td>

                <td>${fieldValue(bean: registroInstance, field: "valorD")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${registroInstanceTotal}"/>
    </div>

    <fieldset><legend>Nuevo registro</legend>
        <g:form>
        <fieldset class="form">
            <g:render template="formRegistro"/>
        </fieldset>
        <fieldset class="buttons">
            <g:actionSubmit name="regresionLineal" action="regresionLineal"
                            value="Regresión Lineal"/>
            <g:actionSubmit name="vecinosCercanos" action="vecinosCercanos"
                            value="Vecinos Cercanos"/>
        </fieldset>
    </g:form>


    </fieldset>
</div>
</body>
</html>
