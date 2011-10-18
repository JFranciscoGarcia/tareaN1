package excepciones;

public class LogicaNegocioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7677279620004750728L;

	public LogicaNegocioException(String mensaje){
		super(mensaje);
	}
}
