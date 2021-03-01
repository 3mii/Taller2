package Excepciones;

public class VentaVaciaException extends Exception{
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public VentaVaciaException(String msg) {
		mensaje = msg;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
