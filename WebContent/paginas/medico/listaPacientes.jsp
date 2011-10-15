<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.HojaClinica" %>
<%@ page import="util.Constantes" %>
<%@ page import="java.util.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


 <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>
 <link href="css/principal.css" rel="stylesheet" type="text/css"/>

<title>Insert title here</title>

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
	    			height:300,
	    			width:450}		
	    
	    );
  });
  
  
  
  </script>

</head>

<% 
Set<HojaClinica> listaHojasClinicas= (Set<HojaClinica>)request.getAttribute("listaHojasClinicas");
%>

<body> 

<div id="dialogoDiagnostico" title="Relizar Diagnostico" style="display: none;">
	<form action="/tareaN1/ListaPacientesServlet" method="post">
		<table>
			<tr>
				<th>Diagnostico</th>
				<td><textarea></textarea></td>
			<tr>
			<tr>
				<th>Tratamiento</th>
				<td><textarea></textarea></td>
			<tr>
		</table>
		<input class="botonera"  type="submit"  name="accion" id="submitDirectorio" value="<%=Constantes.BTN_INGRESAR_DIAGNOSTICO%>">
	</form>
</div>

<div id="dialogoMedico" title="Asignar Medico" style="display: none;">
	<form action="/tareaN1/ListaPacientesServlet" method="post">
		<table>
			<tr>
				<th>Especialidad</th>
				<td>
					<select>
						<option>alguna</option>
						<option>alguna2</option>
					</select>
				</td>
			<tr>
			<tr>
				<th>Medico</th>
				<td>
					<select>
						<option>Doc</option>
						<option>Doc</option>
					</select>
				</td>
			<tr>
		</table>
		<input class="botonera" type="submit"  name="accion" id="submitDirectorio" value="<%=Constantes.BTN_INGRESAR_DIAGNOSTICO%>">
	</form>
</div>


Lista de Pacientes
	<table>
		<tr>
			<th>Fecha de ingreso</th>
			<th>Numero asegurado</th>
			<th>Nombre paciente</th>
			<th>Sintomas</th>
			<th>Alta</th>
			<th>Acciones</th>
		</tr>

		<%for(HojaClinica hojaClinica:listaHojasClinicas){ %>
		<tr>
			<td><%=hojaClinica.getFechaIngreso()%></td>
			<td><%=hojaClinica.getAsegurado().getNumSeguro()%></td>
			<td><%=hojaClinica.getAsegurado().getNombre()%></td>
			<td><%=hojaClinica.getSintomas()%></td>
			<% if(hojaClinica.getAlta()!=null){ %>
			<td><%= (hojaClinica.getAlta()==true)?"SI":"NO" %></td>
			<%}else{ %>
			<td>NO</td>
			<%}%>
			<td>
				<input type="submit" onclick="$('#dialogoDiagnostico').dialog('open');" name="accion" id="submitDirectorio" value="<%=Constantes.BTN_INGRESAR_DIAGNOSTICO%>">
				<input type="submit" onclick="$('#dialogoMedico').dialog('open');"   name="accion" id="submitAsignar" value="<%=Constantes.BTN_ASIGNAR_MEDICO%>">	
			</td>
		</tr>
		<%} %>
	</table>
	
</body>
</html>