<%@page import="util.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Hospital Cl&iacute;nico PUCV</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" /><!--[if IE 5]>
<style type="text/css"> 
/* place css box model fixes for IE 5* in this conditional comment */
#sidebar1 { width: 230px; }
</style>
<![endif]--><!--[if IE]>
<style type="text/css"> 
/* place css fixes for all versions of IE in this conditional comment */
#sidebar1 { padding-top: 30px; }
#mainContent { zoom: 1; }
/* the above proprietary zoom property gives IE the hasLayout it needs to avoid several bugs */
</style>
<![endif]-->
</head>
<body>
<% 
String strLoginCorrecto= (String)request.getAttribute("loginCorrecto");
boolean loginCorrecto= (strLoginCorrecto == null)?true:Boolean.valueOf(strLoginCorrecto);
%>

<div id="container">
    <div id="header">
		<div class="headerTop">
        		<div class="logo">
            			<a href=""><img src="images/logo.png" alt="" width="179" height="77" /></a>Hospital Cl&iacute;nico PUCV
				<span>Tarea N&uacute;mero 1 J2EE</span>
            		</div>
      		</div>
        <div class="mainMenu">
        	<ul>
                <li><a href="index.jsp">Modo recepci&oacute;n</a></li>
                <li id="active"><a href="consulta.jsp">Modo consulta</a></li>
                <li><a href="">Arq. de la soluci&oacute;n</a></li>
                <li><a href="">Nosotros</a></li>
            </ul>
        </div>
       
    </div>

    <div class="allContent">
        
        <div id="tip">
        	<div class="tip-content">
            	<h1>Indicaciones de uso</h1>
            	Existen dos usuarios de prueba en la DB, cuyos n&uacute;meros de seguros y nombre son:
            	<ul>
            		<li>789 - Dr. Gregory House</li>
            		<li>852 - Dr. Nick Riviera</li>
            	</ul>
         	</div>
        </div>
         
        <div id="mainContent">
        	<h1>Modo consulta</h1>
        	<p>Ingresr el n&uacute;mero de colegiado para acceder a la lista de pacientes asignados.</p>
        	            
           <% if(!loginCorrecto){%>
			<div class="msj-error">No se encotr&oacute; Dr. para el n&uacute;mero de colegiado ingresado.</div>
			<%}%>
			
			<form action="/tareaN1/ConsultaServlet" method="post">
				<table class="tabla-formulario">
					<tr>
						<th>N&uacute;mero de colegiado: </th>
						<td><input type="text"  name="numColegiado" id="numColegiado"/></td>
					</tr>
				</table>
				<div class="botonera">
					<input name="accion" type="submit" value="<%=Constantes.BTN_CONSULTAR%>"/> 
				</div>
			</form>
        </div>
        <!-- end #mainContent -->
    </div>
    <br class="clearfloat" />
    <div id="footer">
    	<p>Pontificia Universidad Cat&oacute;lica de V&aacute;lparaiso</p>
    	<p>Escuela de Ingenier&iacute;a en Inform&aacute;tica</p>
    </div>
</div>
</body>
</html>
