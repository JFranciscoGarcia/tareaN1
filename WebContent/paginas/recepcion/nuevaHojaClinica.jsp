<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 

String hojaClinicaCreada= (request.getAttribute("hojaClinicaCreada")==null)?"":(String)request.getAttribute("hojaClinicaCreada");

%>

<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nueva Hoja Clinica</title>
</head>
<body> 

<% if(hojaClinicaCreada.equals("ok")){ %>
 
	<div style="width: 50%;background: lime;border-color: green;">
		<p>Se ha creado una nueva hoja clinica !</p>
	</div>

<%}else{%>
	<form action="/tareaN1/HojaClinica" method="post">
		<table>
			<tr>
			<th>Numero de seguro:</th>
			<td><input type="text" name="numSeguro" id="numSeguro" value=""/></td>
			</tr>
			<tr>
			<th>Sintomas:</th>
			<td><textarea rows="5" cols="20" name="sintomas" id="sintomas"></textarea></td>
			</tr>
			
		</table>
		<input type="submit" name="click" value="ingresar"> 
	</form>	
<%} %>
</body>
</html>