package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Excepciones.*;
import RMI.IControladora;
import ValueObjects.VOVenta;
import ValueObjects.VOVianda;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class Principal extends JFrame {

	private JPanel contentPane;

	
	public Principal(IControladora contr) throws RemoteException, IOException, ClassNotFoundException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JList list = new JList();
		contentPane.add(list, BorderLayout.CENTER);
		
		DefaultListModel lst = new DefaultListModel();
		
		
			try {
				contr.load();
				
			} catch (RespaldoVacioException ex) {
				JOptionPane.showMessageDialog(this, ex.getMensaje(), "Error", JOptionPane.WARNING_MESSAGE);
			} catch (RespaldoNoExisteException ex) {
				JOptionPane.showMessageDialog(this, ex.getMensaje(), "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			
			
		lst.addElement("Codigo | Direccion | Fecha - Hora");
        for(VOVenta venta: contr.getVentas()){
            lst.addElement(venta.getCodigo() + " | " + venta.getDireccion()+ " | "+ venta.getFechaString()+ " - " +venta.getHoraString());
        }
        list.setModel(lst);
	}

}
