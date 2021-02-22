/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author Emi
 */
public class Vegetariana extends Vianda{
    
    private boolean ovolactea;
    private String descripcionAdicional;

    public Vegetariana(boolean ovolactea, String descripcion_adicional, String codigo, String descripcion, float precio_unitario) {
        super(codigo, descripcion, precio_unitario);
        this.ovolactea = ovolactea;
        this.descripcionAdicional = descripcion_adicional;
    }

    public boolean isOvolactea() {
        return ovolactea;
    }

    public void setOvolactea(boolean ovolactea) {
        this.ovolactea = ovolactea;
    }

    public String getDescripcionAdicional() {
        return descripcionAdicional;
    }

    public void setDescripcionAdicional(String descripcionAdicional) {
        this.descripcionAdicional = descripcionAdicional;
    }

	@Override
	public String toString() {
		return super.toString() + "]" + "Vegetariana [ovolactea=" + ovolactea + ", descripcionAdicional=" + descripcionAdicional;
	}
    
    
    
}
