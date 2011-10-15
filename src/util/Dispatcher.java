package util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispatcher{

	
	public static void ir(ServletContext sc, HttpServletRequest request,
			HttpServletResponse response,String ruta) throws ServletException, IOException{
		
		RequestDispatcher rd = sc.getRequestDispatcher(ruta);
		rd.forward(request, response);
	}
	
}
