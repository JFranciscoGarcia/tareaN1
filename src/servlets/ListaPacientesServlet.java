package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Constantes;

/**
 * Servlet implementation class ListaPacientesServlet
 */
@WebServlet("/ListaPacientesServlet")
public class ListaPacientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaPacientesServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("accion").equals(Constantes.BTN_ASIGNAR_MEDICO)){
			
		}
		
		if(request.getParameter("accion").equals(Constantes.BTN_INGRESAR_DIAGNOSTICO)){
			
		}
		
		
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
