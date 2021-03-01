package Excepciones;

public class ViandaYaExisteException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public ViandaYaExisteException(String msg) {
		mensaje = msg;
	}
	
	public String getMensaje() {
		return mensaje;
	}

}
