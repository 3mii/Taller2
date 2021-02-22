package ValueObjects;

import java.io.Serializable;

public class VOVegetariana extends VOVianda implements Serializable{

	private static final long serialVersionUID = -5744677028424842441L;
	private boolean ovolactea;
	private String descripcionAdicional;
	
	public VOVegetariana() {
		super();
	}
	
	public VOVegetariana(boolean ovolactea, String descripcion_adicional, String codigo, String descripcion, float precio_unitario) {
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
	
}
