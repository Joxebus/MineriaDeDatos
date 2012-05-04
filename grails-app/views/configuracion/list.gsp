<%@ page import="mineria.datos.Configuracion" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'configuracion.label', default: 'Configuracion')}"/>
    <title><g:message code="configuracion.list.datos"/></title>
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
    <h1><g:message code="configuracion.list.calcular" /></h1>
    <fieldset>
        <g:form>
            <fieldset class="form">
                <g:render template="formRegistro"/>
            </fieldset>
            <fieldset class="buttons">
                <g:actionSubmit name="regresionLineal" action="regresionLineal"
                                value="Regresión Lineal"/>
                <g:actionSubmit name="vecinosCercanos" action="vecinosCercanos"
                                value="Vecinos Cercanos"/>
                <g:actionSubmit name="Inferencia Bayesiana" action="inferenciaBayesiana"
                                value="Inferencia Bayesiana"/>
            </fieldset>
        </g:form>

    </fieldset>

    <fieldset>
        <g:if test="${registroNuevo?.valorC}">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <b>
                <table>
                    <tr>

                        <td>${configuracion?.columnaA}</td>

                        <td>${configuracion?.columnaB}</td>

                        <td>${configuracion?.columnaC}</td>
                        <g:if test="${registroNuevo?.valorD}">
                        <td>${configuracion?.columnaD}</td>
                            </g:if>

                    </tr>
                        <tr>
                            <td align="center"><font color="blue">${registroNuevo?.valorA}</font></td>
                            <td align="center"><font color="blue">${registroNuevo?.valorB}</font></td>
                            <td align="center"><font color="blue">${registroNuevo?.valorC}</font></td>
                            <g:if test="${registroNuevo?.valorD}">
                            <td align="center"><font color="blue">${registroNuevo?.valorD}</font></td>
                                </g:if>
                        </tr>
                </table>
            </b>
        </g:if>
    </fieldset>

    <h1><g:message code="configuracion.list.datos" /></h1>

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

</div>
</body>
</html>
