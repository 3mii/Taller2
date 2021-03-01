package Excepciones;

public class ViandaNoExisteException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public ViandaNoExisteException(String msg) {
		mensaje = msg;
	}
	
	public String getMensaje() {
		return mensaje;
	}

}
