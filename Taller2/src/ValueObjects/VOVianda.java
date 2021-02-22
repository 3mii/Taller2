package ValueObjects;

import java.io.Serializable;

public class VOVianda implements Serializable{

	private static final long serialVersionUID = 5017733918966538922L;
	private String codigo;
	private String descripcion;
	private float precioUnitario;
	
	public VOVianda() {
	}

	public VOVianda(String codigo, String descripcion, float precioUnitario) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
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

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	
}
