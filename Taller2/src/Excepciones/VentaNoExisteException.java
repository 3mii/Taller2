package Excepciones;

public class VentaNoExisteException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public VentaNoExisteException(String msg) {
		mensaje = msg;
	}
	
	public String getMensaje() {
		return mensaje;
	}

}
