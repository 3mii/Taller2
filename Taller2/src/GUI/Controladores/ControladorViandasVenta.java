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
import Excepciones.ViandaNoExisteException;
import ValueObjects.VOVenta;
import ValueObjects.VOVianda;
import ValueObjects.VOVianda_Venta;

public class ControladorViandasVenta {
	
	IControladora contr;

	public ControladorViandasVenta() throws Exception {
		
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
			if(contr.getCantidadViandasVenta(venta.getCodigo())<30 && venta.isPendiente())
				combo.addItem(venta);
		}
		combo.setSelectedIndex(-1);
	}
	
	public void CargarViandas(JComboBox<VOVianda> combo) throws RemoteException{
		combo.removeAllItems();
		for (VOVianda vianda: contr.getViandas()) {
			combo.addItem(vianda);
		}
		combo.setSelectedIndex(-1);
	}
	
	public void CargarCantidad(JSpinner spinner, int codigo) throws RemoteException {
		spinner.setModel(new SpinnerNumberModel(1, 1, 30-contr.getCantidadViandasVenta(codigo), 1));
	}
	
	public String IngresarViandaVenta(Object venta, Object vianda, String observacion, int cantidad) throws RemoteException, ViandaNoExisteException, VentaNoExisteException, LimiteDeViandasException {
		String errores = "";
		
		if(venta == null)
			errores+= "Venta\n";
		if(vianda == null)
			errores+= "Vianda\n";
		if(observacion.isEmpty())
			errores+= "Observacion\n";
		
		if(errores.isEmpty())
			contr.addViandaVenta(((VOVianda)vianda).getCodigo(), cantidad, observacion, ((VOVenta)venta).getCodigo());
		
		return errores;
	}
	
	public void CargarViandas(JTable tbl, int codVenta) throws RemoteException, VentaNoExisteException {
		DefaultTableModel dm = (DefaultTableModel) tbl.getModel();
	    dm.getDataVector().removeAllElements();
		for (VOVianda_Venta viandaVenta : contr.getViandasVenta(codVenta)) {
			Object[] fila = {
					viandaVenta.getVianda().getCodigo(),
					viandaVenta.getVianda().getDescripcion(),
					viandaVenta.getVianda().getPrecioUnitario(),
					viandaVenta.getCantidad(),
					viandaVenta.getObservacion()
			};
			((DefaultTableModel)tbl.getModel()).addRow(fila);
		}
	}
	
}
