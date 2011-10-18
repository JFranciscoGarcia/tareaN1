package util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispatcher{

	
	public static void ir(ServletContext sc, HttpServletRequest request,HttpServletResponse response,String ruta){
		
		try {
			RequestDispatcher rd = sc.getRequestDispatcher(ruta);
			rd.forward(request, response);
			
		} catch (ServletException e){ 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
