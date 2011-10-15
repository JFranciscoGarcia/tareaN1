import java.util.Date;

import dao.AseguradoDAO;
import dao.HojaClinicaDAO;
import entidades.Asegurado;
import entidades.HojaClinica;



public class Main {

	
	
	public static void main(String argv[]){
		
		
		try{
		
		System.out.println("Comienzo");	
			
		
		AseguradoDAO aseguradoDAO = new AseguradoDAO();
		HojaClinicaDAO hojaClinicaDAO = new HojaClinicaDAO();
		
		Asegurado asegurado = aseguradoDAO.traerPorNumeroAsegurado(new Long(1));

	
		HojaClinica nuevaHojaClinica = new HojaClinica();
		nuevaHojaClinica.setFechaIngreso(new Date());
		nuevaHojaClinica.setSintomas("ahhaha");
		nuevaHojaClinica.setAsegurado(asegurado);
		
		hojaClinicaDAO.insertar(nuevaHojaClinica);
		
		System.out.println("Termino con exito");	
		
		
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
