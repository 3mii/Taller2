package ValueObjects;

import java.io.Serializable;

public class VOVianda_Venta implements Serializable{
	
	private static final long serialVersionUID = 2338997393421459720L;
	private VOVianda vianda;
    private int cantidad;
    private String observacion;
	
    public VOVianda_Venta() {
	}

	public VOVianda_Venta(VOVianda vianda, int cantidad, String observacion) {
		this.vianda = vianda;
		this.cantidad = cantidad;
		this.observacion = observacion;
	}

	public VOVianda getVianda() {
		return vianda;
	}

	public void setVianda(VOVianda vianda) {
		this.vianda = vianda;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
    
    
}
