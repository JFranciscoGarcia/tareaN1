package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaUtil {
	
	public static String formatoFechaHojaClinicaIngreso="dd/MM/yyyy HH:mm";
	public static String formatoFechaHojaClinicaAlta="dd/MM/yyyy";
	
	public static String formatearAFechaClinicaIngreso(Date date){
		SimpleDateFormat a = new SimpleDateFormat(formatoFechaHojaClinicaIngreso);
		return a.format(date);
	}
	
	public static String formatearAFechaClinicaAlta(Date date){
		SimpleDateFormat a = new SimpleDateFormat(formatoFechaHojaClinicaAlta);
		return a.format(date);
	}

}
