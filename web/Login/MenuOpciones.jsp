<%-- 
    Document   : MenuOpciones
    Created on : 05/03/2015, 09:10:27 AM
    Author     : H-URBINA-M
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready(function () {
        var theme = getTheme();
        $('#div_Tree').jqxTree({theme: theme, height: '100%', width: '100%'});
        $('#div_Tree').css('visibility', 'visible');
        $('#div_Tree').jqxTree('collapseAll');
        //fn_verDocumento();
        if ('${autorizacion}' === 'true') {
            fn_mensaje();
        }
        $("#div_Notificacion").jqxNotification({width: "auto", position: "top-right", opacity: 0.9, autoOpen: false, animationOpenDelay: 800, autoClose: false, autoCloseDelay: 3000, template: "error"});
        $('#div_Notificacion').click(function () {
            $('#div_Splitter').jqxSplitter('collapse');
            var $contenidoAjax = $('#div_Contenido').html('<img src="../Imagenes/Fondos/cargando.gif">');
            $.ajax({
                type: "POST",
                url: "../DocumentosDecretados",
                data: {mode: 'docDecretado'},
                success: function (data) {
                    $contenidoAjax.html(data);
                }
            });
        });
    });

    function fn_mensaje() {
        var interval = setInterval(function () {
            $.ajax({
                type: "POST",
                url: "../VerificacionDocumentos",
                data: {mode: 'C'},
                success: function (data) {
                    if (parseInt(data) > 0) {
                        $("#div_Notificacion").jqxNotification("closeLast");
                        $('#div_Notificacion').html("USTED TIENE " + parseInt(data) + " DOCUMENTOS(S) PENDIENTE(S) DE RESPUESTA");
                        $("#div_Notificacion").jqxNotification("open");
                    }
                }
            });
        }, 100000);
    }
</script>
<div style="visibility: hidden; border: none;" id='div_Tree'>
    <ul>
        <li item-expanded='false' id="1"><span item-title="true" style="font-weight: bold; color: blue">CAMBIAR PASSWORD</span></li>
        <li item-expanded='false' id="0" ><span item-title="true" style="font-weight: bold">TRAMITE DOCUMENTARIO</span>
            <ul style='width: 350px;'>
                <%--CREA LAS OPCIONES DE MENU DEL USUARIO --%>
                <li item-expanded='false' id="NombreServlet/modo">Remisi�n de Documentos</li>
                <li item-expanded='false' id="NombreServlet/modo">Documentos Elevados</li>
                <li item-expanded='false' id="NombreServlet/modo">Bandeja de Documentos</li>
            </ul>
        </li>
        <li item-expanded='false' id="0" ><span item-title="true" style="font-weight: bold">EJEMPLO</span>
            <ul style='width: 350px;'>
                <li item-expanded='false' id="Tareas/tareas">Tareas Presupuestales</li>
            </ul>
        </li>
        <li item-expanded='false' id="2"><span item-title="true" style="font-weight: bold; color: red">SALIR</span></li>
    </ul>
</div>

<div id="div_Notificacion"></div>