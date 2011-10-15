package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Medico;
import excepciones.LogicaNegocioException;

import servicios.HojaClinicaService;
import util.Constantes;
import util.Dispatcher;
import util.inputTextUtil;

/**
 * Servlet implementation class HojaClinica
 */
@WebServlet("/HojaClinicaServlet")
public class HojaClinicaServlet extends HttpServlet {

	private HojaClinicaService hojaClinicaService= new HojaClinicaService();
	private static final long serialVersionUID = 1L;


	public HojaClinicaServlet() {

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			
			String accion= (String)request.getParameter("accion");
			
			if(accion.equals(Constantes.BTN_NUEVA_HOJA)){
				nuevaHojaClinica(request,response);
			}
			
			if(accion.equals(Constantes.BTN_DAR_ALTA)){
				darAlta(request,response); 
			}
			
			if(accion.equals(Constantes.BTN_INGRESAR_DIAGNOSTICO)){
				ingresarDiagnostico(request, response);
			}
			
			if(accion.equals(Constantes.BTN_ASIGNAR_MEDICO)){
				asignarMedico(request, response);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ServletException(ex.getMessage());
		}

	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	
	private void asignarMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		try{
			String idHojaClinicaStr=inputTextUtil.limpiar(request.getParameter("idHojaClinica"));
			String idMedicoEspecialistaStr=inputTextUtil.limpiar(request.getParameter("medico_especialista"));
			
			try{
				if(inputTextUtil.estaVacio(idHojaClinicaStr)){
					request.setAttribute("error", Constantes.EXCEPCION_ID_HOJA_CLINICA_NO_ENCONTRADO);
					throw new LogicaNegocioException(Constantes.EXCEPCION_ID_HOJA_CLINICA_NO_ENCONTRADO);
				}
				
				if(inputTextUtil.estaVacio(idMedicoEspecialistaStr)){
					request.setAttribute("error", Constantes.EXCEPCION_ID_MEDICO_NO_ENCONTRADO);
					throw new LogicaNegocioException(Constantes.EXCEPCION_ID_MEDICO_NO_ENCONTRADO);
				}
			
				Long idMedico = Long.parseLong(idMedicoEspecialistaStr);
				Long idHojaClinica = Long.parseLong(idHojaClinicaStr);
				
				hojaClinicaService.asignarMedico(idMedico, idHojaClinica);
				
				Dispatcher.ir(getServletContext(), request, response, "/ConsultaServlet");
				
			}catch(LogicaNegocioException ex){
				ex.printStackTrace();
				Dispatcher.ir(getServletContext(), request, response, "/ConsultaServlet");
				return;
			}

		}catch(Exception ex){
			ex.printStackTrace();
			throw new ServletException(ex.getMessage());
		}
	}
	
	
	private void ingresarDiagnostico(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		try{
			
			boolean error=false;
			String idHojaClinicaStr=inputTextUtil.limpiar(request.getParameter("idHojaClinica"));
			String tratamiento= inputTextUtil.limpiar(request.getParameter("diagnostico"));
			String diagnostico= inputTextUtil.limpiar(request.getParameter("tratamiento"));
			String encamarStr= inputTextUtil.limpiar(request.getParameter("encamar"));
			
			try{
				
				if(inputTextUtil.estaVacio(idHojaClinicaStr)){
					request.setAttribute("error", Constantes.EXCEPCION_ID_HOJA_CLINICA_NO_ENCONTRADO);
					throw new LogicaNegocioException(Constantes.EXCEPCION_ID_HOJA_CLINICA_NO_ENCONTRADO);
				}
				
				if(inputTextUtil.estaVacio(tratamiento)){
					request.setAttribute("error-tratamiento", Constantes.MSJ_DEBE_INGRESAR_VALOR);
					error=true;
				}
				
				if(inputTextUtil.estaVacio(diagnostico)){
					request.setAttribute("error-diagnostico", Constantes.MSJ_DEBE_INGRESAR_VALOR);
					error=true;
				}
				
				if(inputTextUtil.estaVacio(encamarStr)){
					request.setAttribute("error-encamar", Constantes.MSJ_DEBE_INGRESAR_VALOR);
					error=true;
				}
				
				if(error){
					request.setAttribute("idHojaClinica",idHojaClinicaStr);
					request.setAttribute("errores_diagnostico",true);
					Dispatcher.ir(getServletContext(), request, response, "/ConsultaServlet");
					return;
				}
				request.setAttribute("errores_diagnostico",false);
			
				Long idHojaClinica = Long.parseLong(idHojaClinicaStr);
				boolean encamar= Boolean.parseBoolean(encamarStr);
		
				hojaClinicaService.ingresarDiagnostico(idHojaClinica, diagnostico, tratamiento, encamar);
				Dispatcher.ir(getServletContext(), request, response, "/ConsultaServlet");
				
			}catch(LogicaNegocioException ex){
				ex.printStackTrace();
				request.setAttribute("error",ex.getMessage());
				Dispatcher.ir(getServletContext(), request, response, "/ConsultaServlet");
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ServletException(ex.getMessage());
		}
	}
	
	
	
	private void darAlta(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		try{
	
			String idHojaClinicaStr= inputTextUtil.limpiar(request.getParameter("idHojaClinica"));
			
			try{
				
				if(inputTextUtil.estaVacio(idHojaClinicaStr)){
					request.setAttribute("error", Constantes.EXCEPCION_ID_HOJA_CLINICA_NO_ENCONTRADO);
					throw new LogicaNegocioException(Constantes.EXCEPCION_ID_HOJA_CLINICA_NO_ENCONTRADO);
				}
				
				Date fechaAlta = new Date();
				Long idHojaClinica = Long.parseLong(idHojaClinicaStr);
				hojaClinicaService.darAlta(idHojaClinica,fechaAlta);
				
				Dispatcher.ir(getServletContext(), request, response, "/ConsultaServlet");
			
			
			}catch(LogicaNegocioException ex){
				ex.printStackTrace();
				request.setAttribute("error",ex.getMessage());
				Dispatcher.ir(getServletContext(), request, response, "/ConsultaServlet");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ServletException(ex.getMessage());
		}
	}
	
	private void nuevaHojaClinica(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String ruta="/index.jsp";
		
		try{
			boolean error=false;
			String numSeguro= inputTextUtil.limpiar(request.getParameter("numSeguro"));
			String sintomas = (String) request.getParameter("sintomas");
			
			if(inputTextUtil.estaVacio(numSeguro)){
				request.setAttribute("error-numseguro", Constantes.MSJ_DEBE_INGRESAR_VALOR);
				error=true;
			}
			
			if(inputTextUtil.estaVacio(sintomas)){
				request.setAttribute("error-sintomas", Constantes.MSJ_DEBE_INGRESAR_VALOR);
				error=true;
			}
			
			if(!inputTextUtil.estaVacio(numSeguro)){
				if(!inputTextUtil.esNumero(numSeguro)){
					request.setAttribute("error-numseguro", Constantes.MSJ_NO_ES_NUMERO);
					error=true;
				}
			}
			
			if(error){
				Dispatcher.ir(getServletContext(), request, response, ruta);
				return;
			}
			
			Long numAsegurado = Long.parseLong(numSeguro);
			Date fechaIngreso = new Date();

			try{
				Medico medicoAsignado= hojaClinicaService.nuevaHojaClinica(numAsegurado, fechaIngreso,sintomas);
				
				request.setAttribute("nombreMedicoAsignado", medicoAsignado.getNombre());
				request.setAttribute("hojaClinicaCreada", "ok");
				Dispatcher.ir(getServletContext(), request, response, ruta);
			
			}catch(LogicaNegocioException ex){	
				request.setAttribute("error",ex.getMessage());
				Dispatcher.ir(getServletContext(), request, response, ruta);
				return;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ServletException(ex.getMessage());
		}
	}
	
}
