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
public class Vianda {

    private String codigo;
    private String descripcion;
    private float precioUnitario;

    public Vianda(String codigo, String descripcion, float precio_unitario) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precio_unitario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio_unitario() {
        return precioUnitario;
    }

    public void setPrecio_unitario(float precio_unitario) {
        this.precioUnitario = precio_unitario;
    }

	@Override
	public String toString() {
		return "Vianda [codigo=" + codigo + ", descripcion=" + descripcion + ", precio_unitario=" + precioUnitario
				+ "]";
	}

    
    
}
