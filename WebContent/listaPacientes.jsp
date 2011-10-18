<%@page import="util.FechaUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.HojaClinica" %>
<%@ page import="entidades.Especialidad" %>
<%@ page import="entidades.Medico" %>
<%@ page import="util.Constantes" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Hospital Cl&iacute;nico PUCV</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>


<!--[if IE 5]>

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


 <script>
  $(document).ready(function() {
	
	  $("#dialogoDiagnostico").dialog(
	    		{ 
	    			autoOpen: false,
	    			draggable: false,
	    			modal: true,
	    			position: 'center',
	    			resizable: false,
	    			height:300,
	    			width:450}		
	    
	    );
	  
	  $("#dialogoMedico").dialog(
	    		{ 
	    			autoOpen: false,
	    			draggable: false,
	    			modal: true,
	    			position: 'center',
	    			resizable: false,
	    			height:200,
	    			width:450}		
	    
	    );
	  
	  $("#dialogoDarAlta").dialog(
	    		{ 
	    			autoOpen: false,
	    			draggable: false,
	    			modal: true,
	    			position: 'center',
	    			resizable: false,
	    			height:150,
	    			width:450}		
	    
	    );
	  
	  comprobarErrores();
  });
 
  function darAlta(idHojaClinica){
		$('#idHojaClinica_alta').val(idHojaClinica);
		$('#dialogoDarAlta').dialog('open');
  	}
  
  function realizarDiagnostico(idHojaClinica){
		$('#idHojaClinica_diagnostico').val(idHojaClinica);
		$('#dialogoDiagnostico').dialog('open');
	}
  
  function asignarMedico(idHojaClinica){
		$('#idHojaClinica_medico').val(idHojaClinica);
		$('#dialogoMedico').dialog('open');
	}
  
  function comprobarErrores(){
	  errores_diagnostico="${errores_en_diagnostico}";
	  
	  if(errores_diagnostico){
	 	 idHojaClinica="${idHojaClinica}";
	 	$('#idHojaClinica_diagnostico').val(idHojaClinica);
		$('#dialogoDiagnostico').dialog('open');
	  }
  }
  
  function cargarMedicoEspecialista(idEspecialidad){
	
	  //especialidad 1 Cardiolgia
	  if(idEspecialidad==1 ){
		  $('#medicos_especialistas').append('<option value="1" selected="selected">Dr. Gregory House</option>');
	  }
	  
	  //especialidad 2 Endocrinologia
	  if(idEspecialidad==2 ){
		  $('#medicos_especialistas').append('<option value="2" selected="selected">Dr. Nick Riviera</option>');
		  $('#medicos_especialistas').append('<option value="3" selected="selected">Dr. Fox Mulder</option>');
	  }

  }
  
  </script>
</head>


<body>

<div id="dialogoDarAlta" title="Dar Alta al paciente" style="display: none;">
	<form action="/tareaN1/HojaClinicaServlet" method="post">
		<p>¿Est&aacute; seguro que desea dar de Alta a este paciente?</p>
		<div class="botonera">
			<input type="hidden" value="" name="idHojaClinica" id="idHojaClinica_alta"/>
			<input type="submit"  name="accion" id="submitDirectorio" value="<%=Constantes.BTN_DAR_ALTA%>"/>
			<input type="button" onclick="$('#dialogoDarAlta').dialog('close');" value="Cancelar"/>
		</div>
	</form>
</div>




<div id="dialogoDiagnostico" title="Realizar Diagnostico" style="display: none;">
	<form action="/tareaN1/HojaClinicaServlet" method="post">
		<table class="formulario">
			<tr>
				<th>(*) Diagnostico:</th>
				<td>
					<textarea id="diagnostico" name="diagnostico"></textarea>
					<c:if test="${not empty error_diagnostico}">
						<br/><span class="msj-campo-error">${error_diagnostico}</span>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>(*) Tratamiento:</th>
				<td>
					<textarea id="tratamiento" name="tratamiento"></textarea>
					<c:if test="${not empty error_tratamiento}">
						<br/><span class="msj-campo-error">${error_tratamiento}</span>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>(*) Ingresar paciente (encamar):</th>
				<td>
					<input type="checkbox" name="encamar" value="true"/>Si
					<input type="checkbox" name="encamar" value="false"/>No
					<c:if test="${not empty error_encamar}">
						<br/><span class="msj-campo-error">${error_encamar}</span>
					</c:if>
				</td>
			</tr>
		</table>
		<p>(*) Campos obligatorios</p>
		<div class="botonera">
			<input type="hidden" value="" name="idHojaClinica" id="idHojaClinica_diagnostico"/>
			<input type="submit"  name="accion" id="submitDirectorio" value="<%=Constantes.BTN_INGRESAR_DIAGNOSTICO%>"/>
			<input type="button" onclick="$('#dialogoDiagnostico').dialog('close');" value="Cancelar"/>
		</div>
	</form>
</div>

<div id="dialogoMedico" title="Asignar Medico" style="display: none;">
	<form action="/tareaN1/HojaClinicaServlet" method="post">
		<table>
			<tr>
				<th>Especialidad:</th>
				<td>
					<select id="comboEspecialidades" onchange="cargarMedicoEspecialista($('#comboEspecialidades').val());">
						<c:forEach items="${listaEspecialidades}" var="especialidad">
							<option value="${especialidad.idEspecialidad}">${especialidad.nombre}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>(*) Medico:</th>
				<td>
					<select id="medicos_especialistas" name="medico_especialista">

					</select>
				</td>
			</tr>
		</table>
		<p>(*) Campos obligatorios</p>
		<div class="botonera">
			<input type="hidden" value="" name="idHojaClinica" id="idHojaClinica_medico"/>
			<input type="submit"  name="accion" id="submitDirectorio" value="<%=Constantes.BTN_ASIGNAR_MEDICO%>"/>
			<input type="button" onclick="$('#dialogoMedico').dialog('close');" value="Cancelar"/>
		</div>
	</form>
</div>

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
            		<li>752 - Dr. Juanito Jones - M. General</li>
            		<li>123 - Dr. Frank Sinatra - M. General</li>
            		<li>789 - Dr. Gregory House - Cardiolog&iacute;a</li>
            		<li>852 - Dr. Nick Riviera - Endocrinolog&iacute;a</li>
            		<li>741	- Dr. Fox Mulder - Endocrinolog&iacute;a</li>
            	</ul>
         	</div>
        </div>
         
        <div id="mainContent">
        	<h1>Lista de Pacientes - ${medico.nombre}</h1>
            <c:if test="${not empty error}">
				<div class="msj-error">${error}</div>
			</c:if>
			<table class="tabla-datos">
				<tr>
					<th>Fecha de ingreso</th>
					<th>Numero asegurado</th>
					<th>Nombre paciente</th>
					<th>Sintomas</th>
					<th>Alta</th>
					<th class="acciones">Acciones</th>
				</tr>
		
				<c:if test="${fn:length(listaHojasClinicas) > 0}">
					<c:forEach items="${listaHojasClinicas}" var="hoja" >
						<tr>
							<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${hoja.fechaIngreso}" /></td>
							<td>${hoja.asegurado.numSeguro}</td>
							<td>${hoja.asegurado.nombre}</td>
							<td>${hoja.sintomas}</td>
							<c:choose>
								<c:when test="${not empty hoja.alta }">
									<td>
										<c:choose>
											<c:when test="${hoja.alta==true}">
											<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${hoja.fechaAlta}" />
											</c:when>
											<c:otherwise>
												NO
											</c:otherwise>
										</c:choose>
									</td>
								</c:when>
								<c:otherwise>
									<td>-</td>
								</c:otherwise>
							</c:choose>
							<td class="acciones">
								<a onclick="realizarDiagnostico(${hoja.idHojaClinica});"><%=Constantes.BTN_INGRESAR_DIAGNOSTICO%></a><br/>
								<a onclick="asignarMedico(${hoja.idHojaClinica});"><%=Constantes.BTN_ASIGNAR_MEDICO%></a><br/>
								<a onclick="darAlta(${hoja.idHojaClinica});"><%=Constantes.BTN_DAR_ALTA%></a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
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
