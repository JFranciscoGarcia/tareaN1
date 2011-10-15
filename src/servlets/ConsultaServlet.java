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

import dao.EspecialidadDAO;

import servicios.MedicoService;
import util.Constantes;
import util.Dispatcher;
import util.HojaClinicaComparator;
import entidades.Especialidad;
import entidades.HojaClinica;
import entidades.Medico;

@WebServlet("/ConsultaServlet")
public class ConsultaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MedicoService medicoService = new MedicoService();
	private EspecialidadDAO especialidadDAO = new EspecialidadDAO();

	public ConsultaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			consultarPacientes(request, response);
		
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServletException(ex.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void consultarPacientes(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Integer numColegiado = null;

		String accion= (String)request.getParameter("accion");
		
		if(accion!=null && accion.equals(Constantes.BTN_CONSULTAR)){
			numColegiado = Integer.parseInt((String) request.getParameter("numColegiado"));
		}else{
			numColegiado= (Integer)request.getSession().getAttribute("numColegiado");
		}
		

		if (numColegiado == null) {
			Dispatcher.ir(getServletContext(), request, response,"/index.jsp");
			return;
		}

		Medico medico = medicoService.traerMedicoPorNumColegiado(numColegiado);

		if (medico == null) {
			request.setAttribute("loginCorrecto", "false");
			Dispatcher.ir(getServletContext(), request, response,"/consulta.jsp");
		} else {
			request.getSession().setAttribute("numColegiado",medico.getNum());

			List<HojaClinica> listaHojasClinicas = new ArrayList<HojaClinica>(medico.getHojaClinicas());


			Collections.sort(listaHojasClinicas,new HojaClinicaComparator());

			request.setAttribute("listaHojasClinicas", listaHojasClinicas);
			request.setAttribute("listaEspecialidades",especialidadDAO.list());

			Dispatcher.ir(getServletContext(), request, response,"/listaPacientes.jsp");
		}
	}
	
}
