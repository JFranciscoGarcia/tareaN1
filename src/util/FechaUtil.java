package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaUtil {
	
	public static String formatoFechaHojaClinica="dd/MM/yyyy HH:mm";
	
	
	public static String formatearAFechaClinicaIngreso(Date date){
		SimpleDateFormat a = new SimpleDateFormat(formatoFechaHojaClinica);
		return a.format(date);
	}
	

}
