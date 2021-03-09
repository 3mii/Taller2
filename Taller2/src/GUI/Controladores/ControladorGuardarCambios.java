package GUI.Controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.util.Properties;

import Conexion.IControladora;

public class ControladorGuardarCambios {
	
	IControladora contr;

	public ControladorGuardarCambios() throws Exception {
		
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
	
	public void GuardarCambios() throws IOException {
		contr.save();
	}
}
