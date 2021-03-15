package GUI.Controladores;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import Conexion.IControladora;
import Excepciones.LimiteDeViandasException;
import Excepciones.VentaNoExisteException;
import Excepciones.VentaYaConfirmadaException;
import Excepciones.ViandaNoExisteException;
import ValueObjects.VOVenta;
import ValueObjects.VOVianda;
import ValueObjects.VOVianda_Venta;

public class ControladorFinalizarVenta {
	
	IControladora contr;

	public ControladorFinalizarVenta() throws Exception {
		
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
	
	public void CargarVentasPendientes(JComboBox<VOVenta> combo) throws RemoteException{
		combo.removeAllItems();
		for (VOVenta venta : contr.getVentas()) {
			if(venta.isPendiente())
				combo.addItem(venta);
		}
		combo.setSelectedIndex(-1);
	}
	
	public String FinalizarVenta(Object venta, boolean cancela, boolean confirma) throws RemoteException, VentaNoExisteException, VentaYaConfirmadaException {
		String errores = "";
		
		if(venta == null)
			errores+= "Venta\n";
		if(cancela == confirma)
			errores+= "Accion\n";
		
		if(errores.isEmpty())
			contr.endVenta(((VOVenta)venta).getCodigo(), confirma);
		
		return errores;
	}
	
}
