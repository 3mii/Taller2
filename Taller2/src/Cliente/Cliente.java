package Cliente;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.util.Properties;

import GUI.*;
import RMI.IControladora;

public class Cliente {

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("cfg/config.properties"));
			
			String portValue = prop.getProperty("SERVER_PORT");
			String ipValue = prop.getProperty("SERVER_IP");
			String protocolValue = prop.getProperty("PROTOCOL");
			
			IControladora contr = (IControladora) Naming.lookup(protocolValue + "://"+ ipValue + ":" + portValue + "//Controladora");		
			
			Principal pri = new Principal(contr); 
			pri.setVisible(true);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}