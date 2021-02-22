/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Emi
 */
public class Venta {

    private int codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private String direccion;
    private boolean pendiente;
    private Viandas_Venta viandas;
    
    public Venta(int codigo, LocalDate fecha, LocalTime hora, String direccion) {
    	this.codigo = codigo;
        this.direccion = direccion;
        this.pendiente = true;
        this.fecha = fecha;
        this.hora = hora;
        this.viandas = new Viandas_Venta();
    }

    public Venta(int codigo, String direccion) {
        this.codigo = codigo;
        this.direccion = direccion;
        this.pendiente = true;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.viandas = new Viandas_Venta();
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

    public Viandas_Venta getViandas() {
        return viandas;
    }

    public void setViandas(Viandas_Venta ventas) {
        this.viandas = ventas;
    }
    
    public void addVianda(Vianda_Venta vianda) {
    	this.viandas.add(vianda);
    }
    
    public void removeVianda(String codigo, int cantidad) {
    	this.viandas.remove(codigo, cantidad);
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
		return "Codigo=" + codigo + ", Fecha=" + getFechaString() + ", hora=" + getHoraString() + ", Direccion=" + direccion
				+ ", Pendiente=" + pendiente + ", Viandas=" + viandas;
	}

	
    
    
    
}
