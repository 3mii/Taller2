package ValueObjects;

import java.io.Serializable;
import java.util.TreeMap;

public class VOViandas_Venta implements Serializable{
	
	private static final long serialVersionUID = -429295401887678273L;
	private TreeMap<String, VOVianda_Venta> viandas;
    private int cantidadUnidades;
	
    public VOViandas_Venta() {
    	super();
    	this.viandas = new TreeMap<String, VOVianda_Venta>();
        this.cantidadUnidades = 0;
	}
    
    public VOViandas_Venta(TreeMap<String, VOVianda_Venta> viandas, int cantidadUnidades) {
    	this.viandas = viandas;
    	this.cantidadUnidades = cantidadUnidades;
    }

	public TreeMap<String, VOVianda_Venta> getViandas() {
		return viandas;
	}

	public void setViandas(TreeMap<String, VOVianda_Venta> viandas) {
		this.viandas = viandas;
	}

	public int getCantidadUnidades() {
		return cantidadUnidades;
	}

	public void setCantidadUnidades(int cantidadUnidades) {
		this.cantidadUnidades = cantidadUnidades;
	}

    
}


