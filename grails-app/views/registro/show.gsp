<%@ page import="mineria.datos.Registro" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'registro.label', default: 'Registro')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-registro" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-registro" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list registro">

        <g:if test="${registroInstance?.valorA}">
            <li class="fieldcontain">
                <span id="valorA-label" class="property-label"><g:message code="registro.valorA.label"
                                                                          default="Valor A"/></span>

                <span class="property-value" aria-labelledby="valorA-label"><g:fieldValue bean="${registroInstance}"
                                                                                          field="valorA"/></span>

            </li>
        </g:if>

        <g:if test="${registroInstance?.valorB}">
            <li class="fieldcontain">
                <span id="valorB-label" class="property-label"><g:message code="registro.valorB.label"
                                                                          default="Valor B"/></span>

                <span class="property-value" aria-labelledby="valorB-label"><g:fieldValue bean="${registroInstance}"
                                                                                          field="valorB"/></span>

            </li>
        </g:if>

        <g:if test="${registroInstance?.valorC}">
            <li class="fieldcontain">
                <span id="valorC-label" class="property-label"><g:message code="registro.valorC.label"
                                                                          default="Valor C"/></span>

                <span class="property-value" aria-labelledby="valorC-label"><g:fieldValue bean="${registroInstance}"
                                                                                          field="valorC"/></span>

            </li>
        </g:if>

        <g:if test="${registroInstance?.valorD}">
            <li class="fieldcontain">
                <span id="valorD-label" class="property-label"><g:message code="registro.valorD.label"
                                                                          default="Valor D"/></span>

                <span class="property-value" aria-labelledby="valorD-label"><g:fieldValue bean="${registroInstance}"
                                                                                          field="valorD"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${registroInstance?.id}"/>
            <g:link class="edit" action="edit" id="${registroInstance?.id}"><g:message code="default.button.edit.label"
                                                                                       default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
