<%@ page import="mineria.datos.Configuracion" %>



<div class="fieldcontain ${hasErrors(bean: configuracionInstance, field: 'archivo', 'error')} ">
    <label for="archivo">
        <g:message code="configuracion.archivo.label" default="Archivo"/>

    </label>
    <input type="file" id="archivo" name="archivo"/>
</div>

</div>

