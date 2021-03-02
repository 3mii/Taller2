package Dominio;
import ValueObjects.*;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;
import java.util.TreeMap;

import Excepciones.*;
import Persistencia.Respaldo;
import RMI.IControladora;

public class Controladora extends UnicastRemoteObject implements IControladora{
	
	private static final long serialVersionUID = -5457533138152917027L;
	
    private TreeMap<String, Vianda> viandas;
    private TreeMap<Integer, Venta> ventas;
    private static String portValue;
	private static String ipValue;
	private static String protocolValue;
	private static String backupFileValue;
	private static Properties prop;
    
    static {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("cfg/config.properties"));
			portValue = prop.getProperty("SERVER_PORT");
			ipValue = prop.getProperty("SERVER_IP");
			protocolValue = prop.getProperty("PROTOCOL");
			backupFileValue = prop.getProperty("BACKUP_FILE");
			LocateRegistry.createRegistry(Integer.parseInt(portValue));
			Registry reg = LocateRegistry.getRegistry(ipValue, Integer.parseInt(portValue));
		}  catch(Exception ex) {
			System.out.println(ex.getMessage());
		} 
	}
    
    public static void main(String[] args) {
		try {
			Controladora controladora = new Controladora();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
    }
    
    protected Controladora() throws ClassNotFoundException, IOException{
		super();
		this.viandas = new TreeMap<String, Vianda>();
    	this.ventas = new TreeMap<Integer ,Venta>();
    	Naming.rebind(protocolValue + "://" + ipValue + ":" + portValue + "//" + "Controladora", this);
	}
    
    //REQUISITO 1
    public void addVianda(VOVianda vovianda) throws ViandaYaExisteException, ViandaVaciaException{
    	if(vovianda.getCodigo() == null)
    		throw new ViandaVaciaException("La vianda que intenta agregar está vacía.");
    	else if(!viandas.containsKey(vovianda.getCodigo()))
    		viandas.put(vovianda.getCodigo() ,toObject(vovianda));	
    	else
    		throw new ViandaYaExisteException("Ya existe una vianda con el código ingresado.");
    }
    
    //REQUISITO 2
    public void addVenta(VOVenta voventa) throws VentaVaciaException, FechaIncorrectaException{ //ACA CONTROLAR FECHA DE INGRESO
    	if(voventa.getDireccion() == null)
    		throw new VentaVaciaException("La vianda que intenta agregar está vacía.");
    	else if (ventas.isEmpty()) {
    		voventa.setCodigo(1);
    		ventas.put(1, toObject(voventa, false));
    	}else if (voventa.getFecha().isBefore(ventas.lastEntry().getValue().getFecha()) || (voventa.getFecha().equals(ventas.lastEntry().getValue().getFecha()) && voventa.getHora().isBefore(ventas.lastEntry().getValue().getHora())))
    	    throw new FechaIncorrectaException("La fecha y hora de la venta ingresada debe ser posterior a la última.\nSe recomienda sincronizar el reloj y calendario con el servidor."); 	
    	else {
    		voventa.setCodigo(ventas.lastKey()+1);
		    ventas.put(voventa.getCodigo(), toObject(voventa, false));
    	}
    }
    
    //REQUISITO 3
    public void addViandaVenta(String codigoVianda, int cantidad, String observacion, int codigoVenta) throws ViandaNoExisteException, VentaNoExisteException, LimiteDeViandasException {
    	if(ventas.containsKey(codigoVenta)) {
	    	if(viandas.containsKey(codigoVianda))
	    		ventas.get(codigoVenta).addVianda(new Vianda_Venta(viandas.get(codigoVianda), cantidad, observacion));
	    	else
	    		throw new ViandaNoExisteException("No existe ninguna vianda con el codigo ingresado");
    	}else
    		throw new VentaNoExisteException("No existe ninguna venta con el codigo ingresado");
    }
    
    //REQUISITO 4
    public void removeViandaVenta(String codigoVianda, int codigoVenta, int cantidad) throws ViandaNoExisteException, VentaNoExisteException, VentaNoTieneViandaException {
    	if(ventas.containsKey(codigoVenta)) {
	    	if(viandas.containsKey(codigoVianda))
	    		ventas.get(codigoVenta).removeVianda(codigoVianda, cantidad);
	    	else
	    		throw new ViandaNoExisteException("No existe ninguna vianda con el codigo ingresado");
    	}else
    		throw new VentaNoExisteException("No existe ninguna venta con el codigo ingresado");
    }
    
    //REQUISITO 5
    public void endVenta(int codigo, boolean confirma) throws VentaNoExisteException, VentaYaConfirmadaException {
    	if(ventas.containsKey(codigo)) {
    		if(ventas.get(codigo).isPendiente()) {
		    	if(!confirma || ventas.get(codigo).getViandas().getViandas().isEmpty())
		    		ventas.remove(codigo);
		    	else
		    		ventas.get(codigo).setPendiente(!confirma);
    		}else
    			throw new VentaYaConfirmadaException("No se puede eliminar una venta que ya fue confirmada.");
    			
    	}else
    		throw new VentaNoExisteException("No existe ninguna venta con el codigo ingresado");
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
    public VOVianda_Venta[] getViandasVenta(int codigo) throws VentaNoExisteException{
    	if(ventas.containsKey(codigo)) {
    		int i = 0;
        	VOVianda_Venta voviandasventa[] = new VOVianda_Venta[ventas.get(codigo).getViandas().getViandas().size()];
	        	for (Vianda_Venta viandaventa : ventas.get(codigo).getViandas().getViandas().values()) {
	        		voviandasventa[i] = toValueObject(viandaventa);
	        		i++;
	    		}
        	return voviandasventa;
    	}else
    		throw new VentaNoExisteException("No existe ninguna venta con el codigo ingresado");
    }
    
    //REQUISITO 8
    public void save() throws IOException {
		VOControladora vocontroladora = toValueObject(this);
		Respaldo respaldo = new Respaldo();
		respaldo.save(backupFileValue, vocontroladora);
	}
    
  //REQUISITO 9
    public void load() throws IOException, ClassNotFoundException, RespaldoVacioException, RespaldoNoExisteException {
    	Respaldo respaldo = new Respaldo();
		VOControladora aux;
		try {
			aux = respaldo.load(backupFileValue);
			System.out.println(aux.getViandas().isEmpty() && aux.getVentas().isEmpty());
			viandas = toObject(aux).viandas;
			ventas = toObject(aux).ventas;
		}catch (EOFException ex) {
			throw new RespaldoVacioException("El archivo de respaldo " + '"' + backupFileValue + '"' + " no contiene información.");
		}catch (FileNotFoundException ex) {
			throw new RespaldoNoExisteException("El archivo de respaldo " + '"' + backupFileValue + '"' + " no se ha podido encontrar.");
		}
		
    }
    
    public VOVianda[] getViandas() {
    	int i = 0;
    	VOVianda voviandas[] = new VOVianda[viandas.size()];
    	for (Vianda vianda : viandas.values()) {
			voviandas[i] = toValueObject(vianda);
    		i++;
		}
    	return voviandas;
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
			try {
				viandastmp.add(toObject(voviandaventa));
			} catch (LimiteDeViandasException ex) {
			}
		}
    	
    	return viandastmp;
    }
    
    private Venta toObject(VOVenta voventa, boolean conViandas) {
        if(conViandas)
    		return new Venta(voventa.getCodigo(), voventa.getFecha(), voventa.getHora(), voventa.getDireccion(), toObject(voventa.getViandas()));
        else
        	return new Venta(voventa.getCodigo(), voventa.getFecha(), voventa.getHora(), voventa.getDireccion());
    }
    
    private Controladora toObject(VOControladora vocontroladora) throws RemoteException, MalformedURLException {
    		Controladora aux = this;
    		aux.ventas = new TreeMap<Integer, Venta>();
    		aux.viandas = new TreeMap<String, Vianda>();
	    	for (VOVenta venta : vocontroladora.getVentas().values()) {
				aux.ventas.put(venta.getCodigo(), toObject(venta, true));
			}
	    	for (VOVianda vianda : vocontroladora.getViandas().values()) {
				aux.viandas.put(vianda.getCodigo(), toObject(vianda));
			}
	    	return aux;
    }
}
