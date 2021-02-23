package ValueObjects;

import java.io.Serializable;
import java.util.TreeMap;

public class VOControladora implements Serializable{
	
	
	private static final long serialVersionUID = -1270219469769419630L;
	private TreeMap<String, VOVianda> viandas;
    private TreeMap<Integer, VOVenta> ventas;
    
    public VOControladora() {
    	this.viandas = new TreeMap<String, VOVianda>();
    	this.ventas = new TreeMap<Integer, VOVenta>();
    }
    
    public VOControladora(TreeMap<String, VOVianda> viandas, TreeMap<Integer, VOVenta> ventas) {
    	this.viandas = viandas;
    	this.ventas = ventas;
    }

	public TreeMap<String, VOVianda> getViandas() {
		return viandas;
	}

	public void setViandas(TreeMap<String, VOVianda> viandas) {
		this.viandas = viandas;
	}

	public TreeMap<Integer, VOVenta> getVentas() {
		return ventas;
	}

	public void setVentas(TreeMap<Integer, VOVenta> ventas) {
		this.ventas = ventas;
	}
    
}
