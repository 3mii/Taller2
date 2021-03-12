package GUI.Controladores;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Properties;

import Conexion.IControladora;
import Excepciones.ViandaVaciaException;
import Excepciones.ViandaYaExisteException;
import ValueObjects.VOVegetariana;
import ValueObjects.VOVianda;

public class ControladorIngresarVianda {
	IControladora contr;
	
	public ControladorIngresarVianda() throws Exception {
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("cfg/config.properties"));
			
			String portValue = prop.getProperty("SERVER_PORT");
			String ipValue = prop.getProperty("SERVER_IP");
			String protocolValue = prop.getProperty("PROTOCOL");
			
			contr = (IControladora) Naming.lookup(protocolValue + "://"+ ipValue + ":" + portValue + "//Controladora");		
			
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	public String IngresarVianda(String codigo, String descripcion, String precio) throws ViandaYaExisteException, RemoteException, ViandaVaciaException  {
		String errores = "";
		if(codigo.isEmpty())
			errores+= "Codigo\n";
		else if(contr.ViandaExiste(codigo))
			errores+= "Existe\n";
		
		if(descripcion.isEmpty())
			errores+= "Descripcion\n";
		
		if(precio.isEmpty())
			errores+= "Precio\n";
		
		if(errores.isEmpty())
			contr.addVianda(new VOVianda(codigo, descripcion, Float.parseFloat(precio)));
		
		return errores;
	}
	
	public String IngresarVegetariana(String codigo, String descripcion, String precio, boolean ovolactea, String descAdicional) throws RemoteException, ViandaYaExisteException, ViandaVaciaException {
		
		String errores = "";
		if(codigo.isEmpty())
			errores+= "Codigo\n";
		else if(contr.ViandaExiste(codigo))
			errores+= "Existe\n";
		
		if(descripcion.isEmpty())
			errores+= "Descripcion\n";
		
		if(precio.isEmpty())
			errores+= "Precio\n";
		
		if(descAdicional.isEmpty())
			errores+= "descAdicional\n";
		
		if(errores.isEmpty())
			contr.addVianda(new VOVegetariana(ovolactea, descAdicional, codigo, descripcion, Float.parseFloat(precio)));
		
		return errores;
	}
}