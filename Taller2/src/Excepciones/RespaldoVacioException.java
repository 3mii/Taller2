package Excepciones;

public class RespaldoVacioException extends Exception{
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public RespaldoVacioException(String msg) {
		mensaje = msg;
	}
	
	@Override
	public String getMessage() {
		return mensaje;
	}
}
