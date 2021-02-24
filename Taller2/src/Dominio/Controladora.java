
package Dominio;
//Cambio de vida
import ValueObjects.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.TreeMap;

import Persistencia.Respaldo;

public class Controladora {
	private static Controladora controladora;
    private TreeMap<String, Vianda> viandas;
    private TreeMap<Integer, Venta> ventas;
    
    private Controladora() {
    	this.viandas = new TreeMap<String, Vianda>();
    	this.ventas = new TreeMap<Integer ,Venta>();
    }

    public static Controladora crearControladora() {
    	if (controladora == null) 
    		controladora = new Controladora();
    	return controladora; 
    }
    
    //REQUISITO 1
    public void addVianda(VOVianda vovianda){
    	viandas.put(vovianda.getCodigo() ,toObject(vovianda));	
    }
    
    //REQUISITO 2
    public void addVenta(VOVenta voventa){
    	if (ventas.isEmpty())
    		voventa.setCodigo(1);
    	else
    		voventa.setCodigo(ventas.lastKey()+1);
    	ventas.put(voventa.getCodigo(), toObject(voventa, false));
    }
    
    //REQUISITO 3
    public void addViandaVenta(String codigoVianda, int cantidad, String observacion, int codigoVenta) {
    	ventas.get(codigoVenta).addVianda(new Vianda_Venta(viandas.get(codigoVianda), cantidad, observacion));    	
    }
    
    //REQUISITO 4
    public void removeViandaVenta(String codigoVianda, int codigoVenta, int cantidad) {
    	ventas.get(codigoVenta).removeVianda(codigoVianda, cantidad);
    }
    
    //REQUISITO 5
    public void endVenta(int codigo, boolean confirma) {
    	if(!confirma || ventas.get(codigo).getViandas().getViandas().isEmpty())
    		ventas.remove(codigo);
    	else
    		ventas.get(codigo).setPendiente(!confirma);
    }
    
    //REQUISITO 6
    public VOVenta[] getVentas() {
    	int i = 0;
    	VOVenta voventas[] = new VOVenta[ventas.size()];
    	for (Venta venta : ventas.values()) {
			voventas[i] = toValueObject(venta);
			voventas[i].getViandas().setViandas(null);
    		i++;
		}
    	return voventas;
    }
    
    //REQUISITO 7
    public VOVianda_Venta[] getViandasVenta(int codigo){
    	int i = 0;
    	VOVianda_Venta voviandasventa[] = new VOVianda_Venta[ventas.size()];
    	for (Vianda_Venta viandaventa : ventas.get(codigo).getViandas().getViandas().values()) {
    		voviandasventa[i] = toValueObject(viandaventa);
    		i++;
		}
    	return voviandasventa;
    }
    
    public void save() {
    	
		VOControladora vocontroladora = toValueObject(this);
		Respaldo respaldo = new Respaldo();
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("cfg/config.properties"));
			String archivo = p.getProperty("nombreArchivo");
			System.out.println(archivo);
			respaldo.save(archivo, vocontroladora);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
    
    public void load() throws IOException {
    	Respaldo respaldo = new Respaldo();
		Properties p = new Properties();
		String nombreArchivo;
		VOControladora aux;

		try {
			p.load(new FileInputStream("cfg/config.properties"));
			nombreArchivo = p.getProperty("nombreArchivo");
			aux = respaldo.load(nombreArchivo);
			if(aux != null) {
				viandas = toObject(aux).viandas;
				ventas = toObject(aux).ventas;
			}
		} catch (FileNotFoundException ex) {
			System.out.println(ex.toString());
		}

    }
    
    //Estos metodos transforman los OBJETOS en VALUE OBJECTS.
    
    private VOVianda toValueObject(Vianda vianda) {
    	if(vianda instanceof Vegetariana) {
    		Vegetariana vegetariana = (Vegetariana) vianda;
    		return new VOVegetariana(vegetariana.isOvolactea(), vegetariana.getDescripcionAdicional(), vegetariana.getCodigo(), vegetariana.getDescripcion(), vegetariana.getPrecio_unitario());
    	}else {
    		return new VOVianda(vianda.getCodigo(), vianda.getDescripcion(), vianda.getPrecio_unitario());}
    }
    
    private VOVianda_Venta toValueObject(Vianda_Venta viandaventa) {
    	return new VOVianda_Venta(toValueObject(viandaventa.getVianda()), viandaventa.getCantidad(), viandaventa.getObservacion());
    }
    
    private VOViandas_Venta toValueObject(Viandas_Venta viandasventa) {
    	TreeMap<String, VOVianda_Venta> voviandas = new TreeMap<String, VOVianda_Venta>();
    	int cantidadUnidades = 0;
    	
    	for (Vianda_Venta viandaventa : viandasventa.getViandas().values()) {
			voviandas.put(viandaventa.getVianda().getCodigo(), toValueObject(viandaventa));
			cantidadUnidades+=viandaventa.getCantidad();
		}
    	
    	return new VOViandas_Venta(voviandas, cantidadUnidades);
    }
    
    private VOVenta toValueObject(Venta venta) {
    	return new VOVenta(venta.getCodigo(), venta.getFecha(), venta.getHora(), venta.getDireccion(), venta.isPendiente(), toValueObject(venta.getViandas()));
    }
    
    private VOControladora toValueObject(Controladora controladora) {
    	
    	VOControladora vocontroladora = new VOControladora();
    	
    	for (Venta venta : ventas.values()) {
    		vocontroladora.getVentas().put(venta.getCodigo(), toValueObject(venta));
		}
    	
    	for (Vianda vianda : viandas.values()) {
			vocontroladora.getViandas().put(vianda.getCodigo(), toValueObject(vianda));
		}
    	
    	return vocontroladora;
    }
    
    //Estos metodos transforman los VALUE OBJECTS en OBJETOS
    
    private Vianda toObject(VOVianda vovianda) {
    	if (vovianda instanceof VOVegetariana) {
    		VOVegetariana vovegetariana = (VOVegetariana) vovianda;
    		return new Vegetariana(vovegetariana.isOvolactea(), vovegetariana.getDescripcionAdicional(), vovegetariana.getCodigo(), vovegetariana.getDescripcion(), vovegetariana.getPrecioUnitario());
    	}else
    		return new Vianda(vovianda.getCodigo(), vovianda.getDescripcion(), vovianda.getPrecioUnitario());
    }
    
    private Vianda_Venta toObject(VOVianda_Venta voviandaventa) {
    	if(voviandaventa.getVianda() instanceof VOVegetariana)
    		return new Vianda_Venta(toObject((VOVegetariana) voviandaventa.getVianda()), voviandaventa.getCantidad(), voviandaventa.getObservacion());
    	else
    		return new Vianda_Venta(toObject(voviandaventa.getVianda()), voviandaventa.getCantidad(), voviandaventa.getObservacion());
    }
    
    private Viandas_Venta toObject(VOViandas_Venta voviandasventa) {
    	Viandas_Venta viandastmp= new Viandas_Venta();
    	
    	for (VOVianda_Venta voviandaventa : voviandasventa.getViandas().values()) {
			viandastmp.add(toObject(voviandaventa));
		}
    	
    	return viandastmp;
    }
    
    private Venta toObject(VOVenta voventa, boolean conViandas) {
        if(conViandas)
    		return new Venta(voventa.getCodigo(), voventa.getFecha(), voventa.getHora(), voventa.getDireccion(), toObject(voventa.getViandas()));
        else
        	return new Venta(voventa.getCodigo(), voventa.getFecha(), voventa.getHora(), voventa.getDireccion());
    }
    
    private Controladora toObject(VOControladora vocontroladora) {
    		Controladora aux = new Controladora();
    		
	    	for (VOVenta venta : vocontroladora.getVentas().values()) {
				aux.ventas.put(venta.getCodigo(), toObject(venta, true));
			}
	    	
	    	for (VOVianda vianda : vocontroladora.getViandas().values()) {
				aux.viandas.put(vianda.getCodigo(), toObject(vianda));
			}
	    	
	    	return aux;
		
    }
    
    //Funciones de prueba.
    
    public void mostrarVentas() {
    	for (Venta venta : ventas.values()) {
			System.out.println(venta.toString());
		}
    }
    
    public void mostrarViandas() {
    	for (Vianda vianda : viandas.values()) {
			if(vianda instanceof Vegetariana) {
				Vegetariana vegetariana = (Vegetariana) vianda;
				System.out.println(vegetariana.toString());
			}else
				System.out.println(vianda.toString());
		}
    }
    
    public void mostrarViandasVenta(int code) {
    	
    	for (Vianda_Venta vianda : ventas.get(code).getViandas().getViandas().values()) {
				System.out.println(vianda.toString());
		}
    }
    
}
