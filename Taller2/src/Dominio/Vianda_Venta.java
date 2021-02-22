package Dominio;

public class Vianda_Venta {
    private Vianda vianda;
    private int cantidad;
    private String observacion;

    public Vianda_Venta(Vianda vianda, int cantidad, String observacion) {
        this.vianda = vianda;
        this.cantidad = cantidad;
        this.observacion = observacion;
    }

    public Vianda getVianda() {
        return vianda;
    }

    public void setVianda(Vianda vianda) {
        this.vianda = vianda;
    }
   
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public void restarCantidad(int cantidad) {
        this.cantidad = this.cantidad - cantidad;
    }
    
    public void sumarCantidad(int cantidad) {
        this.cantidad = this.cantidad + cantidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

	@Override
	public String toString() {
		if (this.vianda instanceof Vegetariana) {
			Vegetariana vegetariana = (Vegetariana) vianda;
			return "  -  " + vegetariana.toString() + ", cantidad=" + cantidad + ", observacion=" + observacion;
		}else {
			return "  -  " + vianda.toString() + ", cantidad=" + cantidad + ", observacion=" + observacion;
		}
		
	}
    
    
}
