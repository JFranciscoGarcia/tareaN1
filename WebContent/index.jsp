<%@page import="util.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    
 <% 
	String hojaClinicaCreada= (request.getAttribute("hojaClinicaCreada")==null)?"":(String)request.getAttribute("hojaClinicaCreada");
	String nombreMedicoAsignado= (request.getAttribute("nombreMedicoAsignado")==null)?null:(String)request.getAttribute("nombreMedicoAsignado");
%>
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
                <li id="active"><a href="index.jsp">Modo recepci&oacute;n</a></li>
                <li><a href="consulta.jsp">Modo consulta</a></li>
                <li><a href="arquitectura.jsp">Arq. de la soluci&oacute;n</a></li>
                <li><a href="nosotros.jsp">Nosotros</a></li>
            </ul>
        </div>
       
    </div>
    <div class="allContent">
        
        <div id="tip">
        	<div class="tip-content">
            	<h1>Indicaciones de uso</h1>
            	Existen dos usuarios de prueba en la DB, cuyos n&uacute;meros de seguros y nombre son:
            	<ul>
            		<li>123 - Francisco Garcia</li>
            		<li>456 - Yerko Chapanoff</li>
            	</ul>
         </div>
        </div>
         
    
        <div id="mainContent">
        	
            <% if(hojaClinicaCreada.equals("ok")){ %>
 
			<div class="msj-exito">
				<p>Se ha creado una nueva hoja clinica exitosamente para <br/><b><%=nombreMedicoAsignado%></b></p>
			</div>
		
			<%}else{%>
			<h1>Modo recepci&oacute;n</h1>
            <p>Ingrese el n&uacute;mero de asegurado y los s&iacute;ntomas que presenta.</p>
             <% if(request.getAttribute("error")!=null){%>
			<div class="msj-error"><%=request.getAttribute("error")%></div>
			<%}%>
			<form action="/tareaN1/HojaClinicaServlet" method="post">
				<table class="tabla-formulario">
					<tr>
						<th>(*) N&uacute;mero de seguro:</th>
						<td>
							<input type="text" name="numSeguro" id="numSeguro" value=""/>
							
							 <% if(request.getAttribute("error-numseguro")!=null){%>
								<br/><span class="msj-campo-error"><%=request.getAttribute("error-numseguro")%></span>
							<%}%>
						
						</td>
					</tr>
					<tr>
						<th>(*) Sintomas:</th>
						<td>
							<textarea rows="5" cols="20" name="sintomas" id="sintomas"></textarea>
							 <% if(request.getAttribute("error-sintomas")!=null){%>
								<br/><span class="msj-campo-error"><%=request.getAttribute("error-sintomas")%></span>
							<%}%>
						</td>
					</tr>
					 
				</table>
				<p>(*) Campos obligatorios</p>
				<div class="botonera">
					<input type="submit" name="accion" value="<%=Constantes.BTN_NUEVA_HOJA %>" /> 
				</div>
			</form>	
		<%} %>
        </div>
    </div>
    <br class="clearfloat" />
    <div id="footer">
    	<p>Pontificia Universidad Cat&oacute;lica de V&aacute;lparaiso</p>
    	<p>Escuela de Ingenier&iacute;a en Inform&aacute;tica</p>
    </div>
</div>
</body>
</html>
