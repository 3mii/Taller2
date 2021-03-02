package Excepciones;

public class VentaYaConfirmadaException extends Exception{
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public VentaYaConfirmadaException(String msg) {
		mensaje = msg;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
