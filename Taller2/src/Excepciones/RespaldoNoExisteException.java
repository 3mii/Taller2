package Excepciones;

public class RespaldoNoExisteException extends Exception{
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public RespaldoNoExisteException(String msg) {
		mensaje = msg;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
