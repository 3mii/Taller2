package GUI.Controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.util.Properties;

import Conexion.IControladora;
import Excepciones.FechaIncorrectaException;
import Excepciones.VentaVaciaException;
import ValueObjects.VOVenta;

public class ControladorIniciarVenta {
	
	IControladora contr;

	public ControladorIniciarVenta() throws Exception {
		
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
	
	public String IniciarVenta(String direccion) throws IOException, VentaVaciaException, FechaIncorrectaException {
		String error = "";
		try {
			if(direccion.isEmpty())
				error+="Direccion\n";
			else {
				contr.addVenta(new VOVenta(direccion));
			}
		}catch(FechaIncorrectaException ex){
			error+="Fecha\n";
			throw ex;
		}
		return error;
	}
}