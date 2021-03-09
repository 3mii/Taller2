package Excepciones;

public class ViandaVaciaException extends Exception{

	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public ViandaVaciaException(String msg) {
		mensaje = msg;
	}
	
	@Override
	public String getMessage() {
		return mensaje;
	}
	
}
