package util;

public class inputTextUtil {

	public static boolean esNumero(String texto){
		try{
			Integer.parseInt(texto);
			return true;
			
		}catch(NumberFormatException ex){
			return false;
		}
	}
	
	public static String limpiar(String texto){
		
		if(texto==null){
			return "";
		}
		return texto.trim();
	}
	
	public static boolean estaVacio(String texto){
		
		if(texto.trim().isEmpty()){
			return true;
		}
		return false;
	}
}
