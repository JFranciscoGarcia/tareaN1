package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicios.MedicoService;
import util.Dispatcher;
import util.HojaClinicaComparator;
import entidades.HojaClinica;
import entidades.Medico;

@WebServlet("/ConsultaServlet")
public class ConsultaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MedicoService medicoService= new MedicoService();
       
  
    public ConsultaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		
			
			Integer numColegiado=null;
			
			//if(request.getSession().getAttribute("numColegiado")==null){
				//System.out.println("entre1: "+request.getParameter("numColegiado"));
				numColegiado= Integer.parseInt((String)request.getParameter("numColegiado"));
			//}else{
				//System.out.println("entre2: "+request.getSession().getAttribute("numColegiado"));
				//numColegiado=  (Integer)request.getSession().getAttribute("numColegiado");
			//}
			
			System.out.println("entre3: "+ numColegiado);
			if(numColegiado==null){
				Dispatcher.ir(getServletContext(), request, response, "/index.jsp");
				return;
			}
			
			Medico medico = medicoService.traerMedicoPorNumColegiado(numColegiado);
			
			if(medico==null){
				request.setAttribute("loginCorrecto", "false");
				Dispatcher.ir(getServletContext(), request, response, "/consulta.jsp");
			}else{
				//request.getSession().setAttribute("numColegiado",medico.getNum());
				
				List<HojaClinica> listaHojasClinicas= new ArrayList<HojaClinica>(medico.getHojaClinicas());
			
				Collections.sort(listaHojasClinicas, new HojaClinicaComparator());
				
				request.setAttribute("listaHojasClinicas", listaHojasClinicas);
				
				Dispatcher.ir(getServletContext(), request, response, "/listaPacientes.jsp");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ServletException(ex.getMessage());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
