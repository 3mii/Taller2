package Excepciones;

public class LimiteDeViandasException extends Exception{
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public LimiteDeViandasException(String msg) {
		mensaje = msg;
	}
	
	@Override
	public String getMessage() {
		return mensaje;
	}
}
