package Excepciones;

public class FechaIncorrectaException extends Exception{
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public FechaIncorrectaException(String msg) {
		mensaje = msg;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
