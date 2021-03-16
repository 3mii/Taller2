package GUI.Controladores;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import Conexion.IControladora;
import Excepciones.VentaNoExisteException;
import Excepciones.VentaNoTieneViandaException;
import Excepciones.ViandaNoExisteException;
import ValueObjects.VOVenta;
import ValueObjects.VOVianda;
import ValueObjects.VOVianda_Venta;

public class ControladorEliminarViandasVenta {
	
	IControladora contr;

	public ControladorEliminarViandasVenta() throws Exception {
		
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
	
	public void CargarVentas(JComboBox<VOVenta> combo) throws RemoteException{
		combo.removeAllItems();
		for (VOVenta venta : contr.getVentas()) {
			if(contr.getCantidadViandasVenta(venta.getCodigo())>0 && venta.isPendiente())
				combo.addItem(venta);
		}
		combo.setSelectedIndex(-1);
	}
	
	public void CargarViandas(JComboBox<VOVianda> combo, Object obj) throws RemoteException, VentaNoExisteException{
		if(obj != null) {
			VOVenta venta = (VOVenta) obj;
			combo.removeAllItems();
			for (VOVianda_Venta vianda: contr.getViandasVenta(venta.getCodigo())){
				combo.addItem(vianda.getVianda());
			}
			combo.setSelectedIndex(-1);
		}
	}
	
	public void CargarCantidad(JSpinner spinner, Object venta, Object vianda) throws RemoteException, VentaNoExisteException {
		if(venta != null && vianda != null) {
			VOVianda_Venta[] viandas = contr.getViandasVenta(((VOVenta)venta).getCodigo());
			for (VOVianda_Venta viandaVenta : viandas) {
				if(viandaVenta.getVianda().getCodigo().equals(((VOVianda)vianda).getCodigo())) {
					spinner.setModel(new SpinnerNumberModel(1, 1, viandaVenta.getCantidad(), 1));
					break;
				}
			}
		}
	}
	
	public String EliminarViandaVenta(Object venta, Object vianda, int cantidad) throws RemoteException, ViandaNoExisteException, VentaNoExisteException, VentaNoTieneViandaException {
		String errores = "";
		
		if(venta == null)
			errores+= "Venta\n";
		if(vianda == null)
			errores+= "Vianda\n";
		
		if(errores.isEmpty())
			contr.removeViandaVenta(((VOVianda)vianda).getCodigo(), ((VOVenta)venta).getCodigo(), cantidad);
		
		return errores;
	}
}
