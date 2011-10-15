package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicios.HojaClinicaService;
import util.Constantes;
import util.Dispatcher;

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
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ServletException(ex.getMessage());
		}

	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	
	private void ingresarDiagnostico(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		try{
			
			Long idHojaClinica = Long.parseLong(request.getParameter("idHojaClinica"));
			String tratamiento= (String)request.getParameter("diagnostico");
			String diagnostico= (String)request.getParameter("tratamiento");
			boolean encamar= (request.getParameter("encamar")==null)?false:true;
		
			hojaClinicaService.ingresarDiagnostico(idHojaClinica, diagnostico, tratamiento, encamar);
			Dispatcher.ir(getServletContext(), request, response, "/ConsultaServlet");
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ServletException(ex.getMessage());
		}
	}
	
	
	
	private void darAlta(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		try{
			
			Long idHojaClinica = Long.parseLong(request.getParameter("idHojaClinica"));
			hojaClinicaService.darAlta(idHojaClinica);
			Dispatcher.ir(getServletContext(), request, response, "/ConsultaServlet");
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ServletException(ex.getMessage());
		}
	}
	
	private void nuevaHojaClinica(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		try{
	
			Long numAsegurado = Long.parseLong(request.getParameter("numSeguro"));
			String sintomas = (String) request.getParameter("sintomas");
			Date fechaIngreso = new Date();
	
			System.out.println("ingreso: "+fechaIngreso);
			
			
			hojaClinicaService.nuevaHojaClinica(numAsegurado, fechaIngreso,sintomas);
	
			request.setAttribute("hojaClinicaCreada", "ok");
			Dispatcher.ir(getServletContext(), request, response, "/index.jsp");
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ServletException(ex.getMessage());
		}
	}
	
}
