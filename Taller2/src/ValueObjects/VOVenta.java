package ValueObjects;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class VOVenta implements Serializable{
    
	
	private static final long serialVersionUID = -6710980543736875182L;
	private int codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private String direccion;
    private boolean pendiente;
    private VOViandas_Venta viandas;
    
    public VOVenta() {
    	super();
    }
    
    public VOVenta(String direccion) {
        this.direccion = direccion;
        this.pendiente = true;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.viandas = new VOViandas_Venta();
    }
    
    public VOVenta(int codigo, LocalDate fecha, LocalTime hora, String direccion, boolean pendiente, VOViandas_Venta viandas) {
    	this.codigo = codigo;
        this.direccion = direccion;
        this.pendiente = pendiente;
        this.fecha = fecha;
        this.hora = hora;
        this.viandas = viandas;
    }
    
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public LocalTime getHora() {
		return hora;
	}
	
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public boolean isPendiente() {
		return pendiente;
	}
	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}
	
	public VOViandas_Venta getViandas() {
		return viandas;
	}
	
	public void setViandas(VOViandas_Venta viandas) {
		this.viandas = viandas;
	}

    
    
    
}
