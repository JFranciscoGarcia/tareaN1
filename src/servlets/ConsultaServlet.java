package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicios.MedicoService;
import util.Constantes;
import util.Dispatcher;
import util.HojaClinicaComparator;
import util.inputTextUtil;
import dao.DAO;
import dao.EspecialidadDAO;
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
		String numColegiadoStr = null;
		boolean error=false;
		
		String accion= (String)request.getParameter("accion");
		
		if(accion!=null && accion.equals(Constantes.BTN_CONSULTAR)){
			numColegiadoStr=  inputTextUtil.limpiar(request.getParameter("numColegiado"));
			
			if(inputTextUtil.estaVacio(numColegiadoStr)){
				request.setAttribute("error-numcolegiado", Constantes.MSJ_DEBE_INGRESAR_NUM_COLEGIADO);
				error=true;
			}
			
			if(!inputTextUtil.estaVacio(numColegiadoStr)){
				if(!inputTextUtil.esNumero(numColegiadoStr)){
					request.setAttribute("error-numcolegiado", Constantes.MSJ_NO_ES_NUMERO);
					error=true;
				}
			}
			
			
		}else{

			numColegiadoStr= (String)request.getSession().getAttribute("numColegiado");
		}

		if(error){
			Dispatcher.ir(getServletContext(), request, response,"/consulta.jsp");
			return;
		}
		
		DAO.getSession().flush();
		DAO.getSession().clear();
		int numColegiado = Integer.parseInt(numColegiadoStr);
		Medico medico = medicoService.traerMedicoPorNumColegiado(numColegiado);
	

		if (medico == null) {
			request.setAttribute("error", Constantes.EXCEPCION_MEDICO_NO_ENCONTRADO);
			Dispatcher.ir(getServletContext(), request, response,"/consulta.jsp");
		} else {
			
			List<HojaClinica> listaHojasClinicas = new ArrayList<HojaClinica>(medico.getHojaClinicas());
			Collections.sort(listaHojasClinicas,new HojaClinicaComparator());

			request.getSession().setAttribute("listaHojasClinicas", listaHojasClinicas);
			request.getSession().setAttribute("listaEspecialidades",especialidadDAO.list());
			request.getSession().setAttribute("numColegiado",medico.getNum()+"");
			request.getSession().setAttribute("medico",medico);
			

			Dispatcher.ir(getServletContext(), request, response,"/listaPacientes.jsp");
		}
	}
	
}
