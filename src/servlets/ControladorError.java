package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControladorError
 */
@WebServlet("/ControladorError")
public class ControladorError extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControladorError() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");

		if (servletName == null) {
			servletName = "Unknown";
		}
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}

		// Set response content type
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
	
		out.write("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">");
		out.write("<html>");
		out.write("<head>");
		out.write("<title>Error yyhry</title>");
		out.write("</head>");
		out.write("<body>");

	
		out.write("<div style=\"padding-left:20px; padding-top:10px; margin-left:auto; margin-right:auto; width:50%; height:200px; background-color: #FFCCFF; border-color: red; border-width: thin; border-style: solid;\">");
		out.write("<b>Nombre del Servlet: </b> " + servletName + "</br>");
		out.write("<b>Tipo de excepción: </b> " + throwable.getClass().getName()+ "</br>");
		out.write("<b>La URI request: </b> " + requestUri + "<br>");
		out.write("<b>Mensaje de la excepción: </b> " + throwable.getMessage()+ "<br>");
		out.write("<b>Codigo de estado: </b> " + statusCode);
		out.write("</div>");
		
		out.write("</body>");
		out.write("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
