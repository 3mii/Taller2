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
    private float monto;
    
    public VOVenta() {
    	super();
    }
    
    public VOVenta(String direccion) {
        this.direccion = direccion;
        this.pendiente = true;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.viandas = new VOViandas_Venta();
        this.monto = 0;
    }
    
    public VOVenta(int codigo, LocalDate fecha, LocalTime hora, String direccion, boolean pendiente, VOViandas_Venta viandas, float monto) {
    	this.codigo = codigo;
        this.direccion = direccion;
        this.pendiente = pendiente;
        this.fecha = fecha;
        this.hora = hora;
        this.viandas = viandas;
        this.monto = monto;
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
	
	public float getMonto() {
		return monto;
	}
	
	public void setHora(float monto) {
		this.monto = monto;
	}
	
	public String getFechaString() { //Temporal
        //Mostrar Fecha
    	String fechatmp = "";
        if (this.fecha.getDayOfMonth() < 10) {
            fechatmp = fechatmp + "0";
        }
        fechatmp = fechatmp + this.fecha.getDayOfMonth() + "/";
        if (this.fecha.getMonthValue() < 10) {
        	fechatmp = fechatmp + "0";
        }
        fechatmp = fechatmp + this.fecha.getMonthValue() + "/";
        fechatmp = fechatmp + this.fecha.getYear();
        return fechatmp;
    }

    public String getHoraString() { //Temporal
        //Mostrar Hora
    	String horatmp = "";
        if (this.hora.getHour() < 10) {
            horatmp = horatmp + "0";
        }
        horatmp = horatmp + this.hora.getHour() + ":";
        if (this.hora.getMinute() < 10) {
        	horatmp = horatmp + "0";
        }
        horatmp = horatmp + this.hora.getMinute();
        return horatmp;
    }

	@Override
	public String toString() {
		return codigo + " | " + direccion + " | $" + monto;
	}
	    
	
    
}
