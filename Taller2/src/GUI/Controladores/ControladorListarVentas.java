package GUI.Controladores;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Conexion.IControladora;
import ValueObjects.VOVenta;

public class ControladorListarVentas {
	IControladora contr;

	public ControladorListarVentas() throws Exception {
		
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
	
	public void CargarVentas(JTable tbl) throws RemoteException {
		for (VOVenta venta : contr.getVentas()) {
			String estado;
			if(venta.isPendiente())
				estado = "Pendiente";
			else
				estado = "Finalizada";
			Object[] fila = {
					venta.getCodigo(),
					venta.getFechaString(),
					venta.getHoraString(),
					venta.getDireccion(),
					venta.getMonto(),
					estado
			};
			((DefaultTableModel)tbl.getModel()).addRow(fila);
		}
	}
}
