package Dominio;

import java.util.TreeMap;

import Excepciones.LimiteDeViandasException;
import Excepciones.VentaNoTieneViandaException;

public class Viandas_Venta {

    private TreeMap<String, Vianda_Venta> viandas;
    private int cantidadUnidades;

    public Viandas_Venta() {
        this.viandas = new TreeMap<String, Vianda_Venta>();
        this.cantidadUnidades = 0;
    }
    
    public Viandas_Venta(TreeMap<String, Vianda_Venta> viandas, int cantidadUnidades) {
        this.viandas = viandas;
        this.cantidadUnidades = cantidadUnidades;
    }

    public TreeMap<String, Vianda_Venta> getViandas() {
		return viandas;
	}

	public void setViandas(TreeMap<String, Vianda_Venta> viandas) {
		this.viandas = viandas;
	}
	
	public int getCantidadUnidades() {
		return cantidadUnidades;
	}

	public void setCantidadUnidades(int cantidadUnidades) {
		this.cantidadUnidades = cantidadUnidades;
	}

	public void add(Vianda_Venta vianda) throws LimiteDeViandasException {
        if (this.cantidadUnidades + vianda.getCantidad() <= 30) {
            if(this.viandas.containsKey(vianda.getVianda().getCodigo())) {
	        	this.viandas.get(vianda.getVianda().getCodigo()).sumarCantidad(vianda.getCantidad());
            }else {
            	this.viandas.put(vianda.getVianda().getCodigo(), vianda);
            }
            this.cantidadUnidades+=vianda.getCantidad();
        } else {
            throw new LimiteDeViandasException("No se pudo ingresar la vianda, la cantidad supera el máximo de unidades posibles (30)");
        }
    }

    public void remove(String codigo, int cantidad) throws VentaNoTieneViandaException { 
        if(this.viandas.containsKey(codigo)){
            Vianda_Venta temp = (Vianda_Venta) this.viandas.get(codigo);
            if(temp.getCantidad() <= cantidad){
                this.viandas.remove(codigo);
                this.cantidadUnidades -= temp.getCantidad();
            }else{
                temp.restarCantidad(cantidad);
                this.cantidadUnidades -= cantidad;
            }
        } else {
            throw new VentaNoTieneViandaException("La venta seleccionada no contiene la vianda ingresada.");
        }
    }

	@Override
	public String toString() {
		return " viandas=" + viandas + ", cantidadUnidades=" + cantidadUnidades;
	}
    
    

}
