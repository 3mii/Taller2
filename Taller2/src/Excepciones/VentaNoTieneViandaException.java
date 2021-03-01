package Excepciones;

public class VentaNoTieneViandaException extends Exception{
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public VentaNoTieneViandaException(String msg) {
		mensaje = msg;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
