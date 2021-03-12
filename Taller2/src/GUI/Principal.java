package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Excepciones.FechaIncorrectaException;
import Excepciones.VentaVaciaException;
import Excepciones.ViandaVaciaException;
import GUI.Controladores.*;
import ValueObjects.VOVenta;
import ValueObjects.VOVianda;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JPanel pnlInicio;
	private static boolean conectado;
	private static ControladorIngresarVianda contrIngresarVianda;
	private static ControladorGuardarCambios contrGuardarCambios;
	private static ControladorIniciarVenta contrIniciarVenta;
	private static ControladorListarVentas contrListarVentas;
	private static ControladorViandasVenta contrViandasVenta;
	private static Principal ventana = null;
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Conectar();
			}
		});
	}
	
	private static void Conectar() {
		try {
			contrIngresarVianda = new ControladorIngresarVianda();
			contrGuardarCambios = new ControladorGuardarCambios();
			contrIniciarVenta = new ControladorIniciarVenta();
			contrListarVentas = new ControladorListarVentas();
			contrViandasVenta = new ControladorViandasVenta();
			conectado = true;

			if(ventana == null) {
				ventana = new Principal();
				ventana.setLocationRelativeTo(null);
				ventana.setVisible(true);
			}
		} catch (Exception ex) {
			conectado = false;
			if(ventana == null) {
				ventana = new Principal();
				ventana.setLocationRelativeTo(null);
				ventana.setVisible(true);
			}
			JOptionPane.showMessageDialog(ventana, "No se pudo conectar al servidor.");
		}
	}

	public Principal() {
		
		setBackground(Color.DARK_GRAY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 537);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		Inicio();
		
		for (Component comp : contentPane.getComponents()) {
			System.out.println(comp.getName());
		}
	}
	
	private void Inicio() {
		pnlInicio = new JPanel();
		pnlInicio.setBackground(Color.DARK_GRAY);
		contentPane.add(pnlInicio, "<-- Volver al inicio");
		pnlInicio.setLayout(null);
		pnlInicio.setName("<-- Volver al inicio");
		JPanel pnlIngresar = new JPanel();
		JLabel lblAltas = new JLabel("Altas");
		lblAltas.setForeground(Color.WHITE);
		lblAltas.setOpaque(true);
		lblAltas.setBackground(Color.DARK_GRAY);
		lblAltas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAltas.setBounds(10, -7, 175, 32);
		pnlIngresar.add(lblAltas);
		pnlIngresar.setBounds(87, 89, 195, 137);
		pnlIngresar.setBackground(Color.GRAY);
		pnlInicio.add(pnlIngresar);
		pnlIngresar.setLayout(null);
		JButton btnIngresarVianda = new JButton("Ingresar vianda");
		btnIngresarVianda.setBounds(10, 36, 175, 23);
		pnlIngresar.add(btnIngresarVianda);
		JButton btnIniciarVenta = new JButton("Iniciar venta");
		btnIniciarVenta.setBounds(10, 70, 175, 23);
		pnlIngresar.add(btnIniciarVenta);
		JButton btnViandaAVenta = new JButton("Agregar vianda a venta");
		btnViandaAVenta.setBounds(10, 104, 175, 23);
		pnlIngresar.add(btnViandaAVenta);
		Panel pnlListado = new Panel();
		pnlListado.setBounds(87, 250, 402, 110);
		pnlListado.setLayout(null);
		pnlListado.setBackground(Color.GRAY);
		pnlInicio.add(pnlListado);
		JLabel lblListados = new JLabel("Listados");
		lblListados.setOpaque(true);
		lblListados.setHorizontalTextPosition(SwingConstants.CENTER);
		lblListados.setHorizontalAlignment(SwingConstants.CENTER);
		lblListados.setForeground(Color.WHITE);
		lblListados.setBackground(Color.DARK_GRAY);
		lblListados.setBounds(10, -8, 383, 32);
		pnlListado.add(lblListados);
		JButton btnListarViandas = new JButton("Listar viandas");
		btnListarViandas.setBounds(141, 36, 121, 23);
		pnlListado.add(btnListarViandas);
		JButton btnListarVentas = new JButton("Listar ventas");
		btnListarVentas.setBounds(10, 36, 121, 23);
		pnlListado.add(btnListarVentas);
		JButton btnDetallarVianda = new JButton("Detallar vianda");
		btnDetallarVianda.setBounds(272, 36, 121, 23);
		pnlListado.add(btnDetallarVianda);
		JButton btnVentasPorDescripcion = new JButton("Ventas por descripcion");
		btnVentasPorDescripcion.setBounds(43, 70, 151, 23);
		pnlListado.add(btnVentasPorDescripcion);
		JButton btnViandasDeVenta = new JButton("Viandas de venta");
		btnViandasDeVenta.setBounds(204, 70, 171, 23);
		pnlListado.add(btnViandasDeVenta);
		Panel pnlBajas = new Panel();
		pnlBajas.setBounds(294, 89, 195, 137);
		pnlBajas.setLayout(null);
		pnlBajas.setBackground(Color.GRAY);
		pnlInicio.add(pnlBajas);
		JLabel lblBajas = new JLabel("Bajas");
		lblBajas.setOpaque(true);
		lblBajas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBajas.setHorizontalAlignment(SwingConstants.CENTER);
		lblBajas.setForeground(Color.WHITE);
		lblBajas.setBackground(Color.DARK_GRAY);
		lblBajas.setBounds(10, -7, 175, 32);
		pnlBajas.add(lblBajas);
		JButton btnEliminarViandaVenta = new JButton("Eliminar vianda de venta");
		btnEliminarViandaVenta.setBounds(10, 85, 175, 23);
		pnlBajas.add(btnEliminarViandaVenta);
		JButton btnFinalizarVenta = new JButton("Finalizar venta");
		btnFinalizarVenta.setBounds(10, 51, 175, 23);
		pnlBajas.add(btnFinalizarVenta);
		Panel pnlServidor = new Panel();
		pnlServidor.setBounds(501, 144, 175, 194);
		pnlServidor.setLayout(null);
		pnlServidor.setBackground(Color.GRAY);
		pnlInicio.add(pnlServidor);
		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setOpaque(true);
		lblServidor.setHorizontalTextPosition(SwingConstants.CENTER);
		lblServidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblServidor.setForeground(Color.WHITE);
		lblServidor.setBackground(Color.DARK_GRAY);
		lblServidor.setBounds(10, -7, 155, 32);
		pnlServidor.add(lblServidor);
		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(conectado)
						contrGuardarCambios.GuardarCambios();
					else
						JOptionPane.showMessageDialog(ventana, "Antes debe conectarse al servidor.");
				}catch(IOException ex) {
					JOptionPane.showMessageDialog(ventana, "Error al acceder al servidor.");
					conectado = false;
					contentPane.remove(pnlInicio);
					Inicio();
				}
			}
		});
		btnGuardarCambios.setBounds(10, 161, 155, 23);
		pnlServidor.add(btnGuardarCambios);
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("cfg/config.properties"));
		} catch (IOException ex) {
			
		}
		
		String portValue = prop.getProperty("SERVER_PORT");
		String ipValue = prop.getProperty("SERVER_IP");
		
		if(ipValue == null)
			ipValue = "no encontrada";
		if(portValue == null)
			portValue = "no encontrado";
		
		JLabel lblIP = new JLabel("Direccion: " + ipValue);
		lblIP.setBounds(10, 40, 155, 14);
		pnlServidor.add(lblIP);
		
		JLabel lblPuerto = new JLabel("Puerto: " + portValue);
		lblPuerto.setBounds(10, 66, 155, 14);
		pnlServidor.add(lblPuerto);
		
		JLabel lblEstado = new JLabel("Conectado");
		lblEstado.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEstado.setBounds(24, 102, 101, 14);
		pnlServidor.add(lblEstado);
		
		JLabel lblEstadoIcono = new JLabel("\u25A0");
		lblEstadoIcono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEstadoIcono.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEstadoIcono.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoIcono.setBounds(5, 100, 21, 14);
		
		if(conectado) {
			lblEstado.setText("Conectado");
			lblEstadoIcono.setForeground(Color.GREEN);
		}else {
			lblEstado.setText("Desconectado");
			lblEstadoIcono.setForeground(Color.RED);
		}
		
		pnlServidor.add(lblEstadoIcono);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.WAIT_CURSOR);
				Conectar();
				setCursor(Cursor.DEFAULT_CURSOR);
				if(conectado) {
					lblEstado.setText("Conectado");
					lblEstadoIcono.setForeground(Color.GREEN);
				}else {
					lblEstado.setText("Desconectado");
					lblEstadoIcono.setForeground(Color.RED);
				}
			}
		});
		btnConectar.setBounds(10, 127, 155, 23);
		pnlServidor.add(btnConectar);
		
		btnIngresarVianda.addActionListener(Navegacion);
		btnIniciarVenta.addActionListener(Navegacion);
		btnViandaAVenta.addActionListener(Navegacion);
		btnListarViandas.addActionListener(Navegacion);
		btnListarVentas.addActionListener(Navegacion);
		btnDetallarVianda.addActionListener(Navegacion);
		btnVentasPorDescripcion.addActionListener(Navegacion);
		btnViandasDeVenta.addActionListener(Navegacion);
		btnEliminarViandaVenta.addActionListener(Navegacion);
		btnFinalizarVenta.addActionListener(Navegacion);
	}
	
	private void IngresarViandas() {
		JPanel pnlIngresarViandas = new JPanel();
		pnlIngresarViandas.setBackground(Color.DARK_GRAY);
		contentPane.add(pnlIngresarViandas, "Ingresar vianda");
		pnlIngresarViandas.setLayout(null);
		pnlIngresarViandas.setName("Ingresar vianda");
		JButton btnVolver = new JButton("<-- Volver al inicio");
		btnVolver.setBounds(10, 11, 137, 23);
		pnlIngresarViandas.add(btnVolver);
		
		
		JTextField txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!((e.getKeyChar()<='9' && e.getKeyChar()>='0') || (e.getKeyChar()<='Z' && e.getKeyChar()>='A') || (e.getKeyChar()<='z' && e.getKeyChar()>='a')))
						e.consume();
			}
		});
		txtCodigo.setBounds(321, 134, 118, 20);
		pnlIngresarViandas.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JTextField txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				if(e.getKeyChar() == '.' && txtPrecio.getText().contains("."))
					e.consume();
				else if((e.getKeyChar() > '9' || e.getKeyChar() < '0') && e.getKeyChar() != '.')
					e.consume();
			}
		});
		
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(321, 243, 118, 20);
		pnlIngresarViandas.add(txtPrecio);
		
		JRadioButton rdbtnVegetariana = new JRadioButton("Vegetariana");
		rdbtnVegetariana.setBackground(Color.WHITE);
		
		rdbtnVegetariana.setForeground(Color.BLACK);
		rdbtnVegetariana.setBounds(322, 274, 117, 20);
		pnlIngresarViandas.add(rdbtnVegetariana);
		
		JRadioButton rdbtnOvolactea = new JRadioButton("Ovol\u00E1ctea");
		rdbtnOvolactea.setBackground(Color.WHITE);
		rdbtnOvolactea.setVisible(false);
		rdbtnOvolactea.setForeground(Color.BLACK);
		rdbtnOvolactea.setBounds(322, 306, 117, 20);
		pnlIngresarViandas.add(rdbtnOvolactea);
		
		JTextPane txtDescAdicional = new JTextPane();
		txtDescAdicional.setVisible(false);
		txtDescAdicional.setBounds(321, 337, 118, 67);
		pnlIngresarViandas.add(txtDescAdicional);
		
		JTextPane txtDescripcion = new JTextPane();
		txtDescripcion.setBounds(321, 165, 118, 67);
		pnlIngresarViandas.add(txtDescripcion);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setBackground(Color.BLACK);
		btnIngresar.setBounds(321, 305, 118, 23);
		pnlIngresarViandas.add(btnIngresar);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setBounds(265, 137, 46, 14);
		pnlIngresarViandas.add(lblCodigo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setBounds(237, 165, 74, 14);
		pnlIngresarViandas.add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrecio.setForeground(Color.WHITE);
		lblPrecio.setBounds(265, 246, 46, 14);
		pnlIngresarViandas.add(lblPrecio);
		
		JLabel lblDescAdicional = new JLabel("Descripcion adicional");
		lblDescAdicional.setVisible(false);
		lblDescAdicional.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescAdicional.setForeground(Color.WHITE);
		lblDescAdicional.setBounds(160, 337, 151, 14);
		pnlIngresarViandas.add(lblDescAdicional);
		
		JLabel lblIngresarVianda = new JLabel("Ingresar vianda");
		lblIngresarVianda.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresarVianda.setForeground(Color.WHITE);
		lblIngresarVianda.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblIngresarVianda.setBounds(237, 34, 283, 49);
		pnlIngresarViandas.add(lblIngresarVianda);
		
		JLabel lblErrCodigo = new JLabel("New label");
		lblErrCodigo.setForeground(Color.RED);
		lblErrCodigo.setBounds(449, 137, 302, 14);
		lblErrCodigo.setVisible(false);
		pnlIngresarViandas.add(lblErrCodigo);
		
		JLabel lblErrDescripcion = new JLabel("Campo obligatorio");
		lblErrDescripcion.setForeground(Color.RED);
		lblErrDescripcion.setBounds(449, 165, 218, 14);
		lblErrDescripcion.setVisible(false);
		pnlIngresarViandas.add(lblErrDescripcion);
		
		JLabel lblErrPrecio = new JLabel("Campo obligatorio");
		lblErrPrecio.setForeground(Color.RED);
		lblErrPrecio.setBounds(449, 246, 218, 14);
		lblErrPrecio.setVisible(false);
		pnlIngresarViandas.add(lblErrPrecio);
		
		JLabel lblErrDescAdicional = new JLabel("Campo obligatorio");
		lblErrDescAdicional.setForeground(Color.RED);
		lblErrDescAdicional.setBounds(449, 337, 218, 14);
		lblErrDescAdicional.setVisible(false);
		pnlIngresarViandas.add(lblErrDescAdicional);
		
		btnVolver.addActionListener(Navegacion);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio();
				contentPane.remove(pnlIngresarViandas);
			}
		});
		
		rdbtnVegetariana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnVegetariana.isSelected()) {
					rdbtnOvolactea.setVisible(true);
					rdbtnOvolactea.setSelected(false);
					txtDescAdicional.setVisible(true);
					lblDescAdicional.setVisible(true);
					btnIngresar.setLocation(321, 415);
				}else {
					rdbtnOvolactea.setVisible(false);
					txtDescAdicional.setVisible(false);
					lblDescAdicional.setVisible(false);
					lblErrDescAdicional.setVisible(false);
					btnIngresar.setLocation(321, 305);
				}
			}
		});

		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String errores="";
				try {
					if(rdbtnVegetariana.isSelected())
						errores = contrIngresarVianda.IngresarVegetariana(txtCodigo.getText(), txtDescripcion.getText(), txtPrecio.getText(), rdbtnOvolactea.isSelected(), txtDescAdicional.getText());
					else
						errores = contrIngresarVianda.IngresarVianda(txtCodigo.getText(), txtDescripcion.getText(), txtPrecio.getText());
					
					if(errores.isEmpty()) {
						contentPane.remove(pnlIngresarViandas);
						IngresarViandas();
						CardLayout card = (CardLayout)contentPane.getLayout();
						card.show(contentPane, "Ingresar vianda");
					}else if(errores.contains("Codigo")) {
						lblErrCodigo.setText("Campo obligatorio.");
						lblErrCodigo.setVisible(true);
					}else if(errores.contains("Existe")) {
						lblErrCodigo.setText("Ya existe una vianda con el codigo ingresado.");
						lblErrCodigo.setVisible(true);
					}else
						lblErrCodigo.setVisible(false);
					
					if(errores.contains("Descripcion")) {
						lblErrDescripcion.setVisible(true);
					}else
						lblErrDescripcion.setVisible(false);
					
					if(errores.contains("Precio")) {
						lblErrPrecio.setVisible(true);
					}else
						lblErrPrecio.setVisible(false);
					
					if(errores.contains("descAdicional")) {
						lblErrDescAdicional.setVisible(true);
					}else
						lblErrDescAdicional.setVisible(false);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(ventana, "Error al acceder al servidor.");
					conectado = false;
					contentPane.remove(pnlIngresarViandas);
					contentPane.remove(pnlInicio);
					Inicio();
				}
					
			}
		});
		
	}
	
	private void IniciarVenta() {
		JPanel pnlIniciarVenta = new JPanel();
		pnlIniciarVenta.setBackground(Color.DARK_GRAY);
		contentPane.add(pnlIniciarVenta, "Iniciar venta");
		pnlIniciarVenta.setLayout(null);
		pnlIniciarVenta.setName("Iniciar venta");
		JButton btnVolver_1 = new JButton("<-- Volver al inicio");
		btnVolver_1.setBounds(10, 11, 137, 23);
		pnlIniciarVenta.add(btnVolver_1);
		
		btnVolver_1.addActionListener(Navegacion);
		btnVolver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio();
				contentPane.remove(pnlIniciarVenta);
			}
		});
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDireccion.setBounds(244, 156, 67, 14);
		pnlIniciarVenta.add(lblDireccion);
		
		JTextField txtDireccion;
		txtDireccion = new JTextField();
		txtDireccion.setBounds(322, 153, 124, 20);
		pnlIniciarVenta.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblErrDireccion = new JLabel("Campo obligatorio");
		lblErrDireccion.setVisible(false);
		lblErrDireccion.setForeground(Color.RED);
		lblErrDireccion.setBounds(456, 156, 152, 14);
		pnlIniciarVenta.add(lblErrDireccion);
		
		JButton btnIngresar_1 = new JButton("Ingresar");
		btnIngresar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String errores = contrIniciarVenta.IniciarVenta(txtDireccion.getText());
					if(errores.isEmpty()) {
						JOptionPane.showMessageDialog(ventana, "Venta ingresada con éxito.");
						IniciarVenta();
						contentPane.remove(pnlIniciarVenta);
					}else if(errores.contains("Direccion"))
						lblErrDireccion.setVisible(true);
					else
						lblErrDireccion.setVisible(false);
				} catch (FechaIncorrectaException ex) {
					JOptionPane.showMessageDialog(ventana, ex.getMessage());
				} catch(IOException | VentaVaciaException ex) {
					JOptionPane.showMessageDialog(ventana, "Error al acceder al servidor.");
					conectado = false;
					contentPane.remove(pnlIniciarVenta);
					contentPane.remove(pnlInicio);
					Inicio();
				}
			}
		});
		btnIngresar_1.setBounds(315, 184, 89, 23);
		pnlIniciarVenta.add(btnIngresar_1);
	}
	
	private void ViandaAVenta() {
		JPanel pnlViandaAVenta = new JPanel();
		pnlViandaAVenta.setBackground(Color.DARK_GRAY);
		contentPane.add(pnlViandaAVenta, "Agregar vianda a venta");
		pnlViandaAVenta.setLayout(null);
		pnlViandaAVenta.setName("Agregar vianda a venta");
		JButton btnVolver = new JButton("<-- Volver al inicio");
		btnVolver.setBounds(10, 11, 137, 23);
		pnlViandaAVenta.add(btnVolver);
		
		JComboBox<VOVenta> cmbxVenta = new JComboBox<VOVenta>();
		cmbxVenta.setBounds(254, 121, 200, 20);
		try {
			contrViandasVenta.CargarVentas(cmbxVenta);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		pnlViandaAVenta.add(cmbxVenta);
		
		JSpinner spnCantidad = new JSpinner();
		spnCantidad.setModel(new SpinnerNumberModel(0, 0, 30, 1));
		spnCantidad.setBounds(254, 294, 51, 20);
		pnlViandaAVenta.add(spnCantidad);
		
		JTextPane txtDescAdicional = new JTextPane();
		txtDescAdicional.setBounds(254, 214, 200, 56);
		pnlViandaAVenta.add(txtDescAdicional);
		
		JComboBox<VOVianda> cmbxVianda = new JComboBox<VOVianda>();
		cmbxVianda.setBounds(254, 168, 200, 20);
		
		try {
			contrViandasVenta.CargarViandas(cmbxVianda);
		} catch (Exception ex) {
			
		}
	
		pnlViandaAVenta.add(cmbxVianda);
		
		JButton btnAgregar = new JButton("Agregar");
		
		btnAgregar.setBounds(311, 340, 89, 23);
		pnlViandaAVenta.add(btnAgregar);
		
		JLabel lblVenta = new JLabel("Venta");
		lblVenta.setForeground(Color.WHITE);
		lblVenta.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVenta.setBounds(127, 124, 117, 14);
		pnlViandaAVenta.add(lblVenta);
		
		JLabel lblVianda = new JLabel("Vianda");
		lblVianda.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVianda.setForeground(Color.WHITE);
		lblVianda.setBounds(127, 171, 117, 14);
		pnlViandaAVenta.add(lblVianda);
		
		JLabel lblObservacion = new JLabel("Observaci\u00F3n");
		lblObservacion.setHorizontalAlignment(SwingConstants.TRAILING);
		lblObservacion.setForeground(Color.WHITE);
		lblObservacion.setBounds(127, 214, 117, 14);
		pnlViandaAVenta.add(lblObservacion);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCantidad.setForeground(Color.WHITE);
		lblCantidad.setBounds(127, 297, 117, 14);
		pnlViandaAVenta.add(lblCantidad);
		
		JLabel lblErrVenta = new JLabel("Campo obligatorio");
		lblErrVenta.setVisible(false);
		lblErrVenta.setForeground(Color.RED);
		lblErrVenta.setBounds(464, 124, 193, 14);
		pnlViandaAVenta.add(lblErrVenta);
		
		JLabel lblErrVianda = new JLabel("Campo obligatorio");
		lblErrVianda.setVisible(false);
		lblErrVianda.setForeground(Color.RED);
		lblErrVianda.setBounds(464, 171, 193, 14);
		pnlViandaAVenta.add(lblErrVianda);
		
		JLabel lblErrObservacion = new JLabel("Campo obligatorio");
		lblErrObservacion.setVisible(false);
		lblErrObservacion.setForeground(Color.RED);
		lblErrObservacion.setBounds(464, 214, 193, 14);
		pnlViandaAVenta.add(lblErrObservacion);
		btnVolver.addActionListener(Navegacion);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio();
				contentPane.remove(pnlViandaAVenta);
			}
		});
		cmbxVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VOVenta venta = ((VOVenta)((JComboBox) e.getSource()).getSelectedItem());
				try {
					contrViandasVenta.CargarCantidad(spnCantidad, venta.getCodigo());
				} catch (RemoteException ex) {
					
				}
			}
		});
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String errores = contrViandasVenta.IngresarViandaVenta(cmbxVenta.getSelectedItem(), cmbxVianda.getSelectedItem(), txtDescAdicional.getText(), (Integer)spnCantidad.getValue());
					
					if(errores.contains("Venta"))
						lblErrVenta.setVisible(true);
					else
						lblErrVenta.setVisible(false);
					
					if(errores.contains("Vianda"))
						lblErrVianda.setVisible(true);
					else
						lblErrVianda.setVisible(false);
					
					if(errores.contains("Observacion"))
						lblErrObservacion.setVisible(true);
					else
						lblErrObservacion.setVisible(false);
					
					if(errores.isEmpty()) {
						ViandaAVenta();
						contentPane.remove(pnlViandaAVenta);
					}
					
				} catch (Exception ex) {
					
				}
			}
		});
	}
		
	private void ListarVentas() {
		JPanel pnlListarVentas = new JPanel();
		pnlListarVentas.setBackground(Color.DARK_GRAY);
		contentPane.add(pnlListarVentas, "Listar ventas");
		pnlListarVentas.setName("Listar ventas");
		JButton btnVolver = new JButton("<-- Volver al inicio");
		
		JScrollPane scrlVentas = new JScrollPane();
		GroupLayout gl_pnlListarVentas = new GroupLayout(pnlListarVentas);
		gl_pnlListarVentas.setHorizontalGroup(
			gl_pnlListarVentas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlListarVentas.createSequentialGroup()
					.addGroup(gl_pnlListarVentas.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlListarVentas.createSequentialGroup()
							.addGap(10)
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlListarVentas.createSequentialGroup()
							.addGap(29)
							.addComponent(scrlVentas, GroupLayout.PREFERRED_SIZE, 699, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_pnlListarVentas.setVerticalGroup(
			gl_pnlListarVentas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlListarVentas.createSequentialGroup()
					.addGap(11)
					.addComponent(btnVolver)
					.addGap(18)
					.addComponent(scrlVentas, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		

		JTable tblVentas;
		tblVentas = new JTable();
		tblVentas.setRequestFocusEnabled(false);
		tblVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblVentas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Numero", "Fecha", "Hora", "Direccion", "Monto ($)", "Estado"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Float.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblVentas.getColumnModel().getColumn(0).setResizable(false);
		tblVentas.getColumnModel().getColumn(1).setResizable(false);
		tblVentas.getColumnModel().getColumn(2).setResizable(false);
		tblVentas.getColumnModel().getColumn(3).setResizable(false);
		tblVentas.getColumnModel().getColumn(4).setResizable(false);
		tblVentas.getColumnModel().getColumn(5).setResizable(false);
		
		try {
			contrListarVentas.CargarVentas(tblVentas);
		} catch (Exception ex) {
		}
		
		scrlVentas.setViewportView(tblVentas);
		pnlListarVentas.setLayout(gl_pnlListarVentas);
		
		btnVolver.addActionListener(Navegacion);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio();
				contentPane.remove(pnlListarVentas);
			}
		});
	}
	
	private void ViandasDeVenta() {
		JPanel pnlViandasDeVenta = new JPanel();
		pnlViandasDeVenta.setBackground(Color.DARK_GRAY);
		contentPane.add(pnlViandasDeVenta, "Viandas de venta");
		pnlViandasDeVenta.setName("Viandas de venta");
		JButton btnVolver = new JButton("<-- Volver al inicio");
		
		JComboBox cmbxVenta = new JComboBox();
		
		try {
			contrViandasVenta.CargarVentas(cmbxVenta);
		} catch (RemoteException ex) {
			
		}
		
			
			JScrollPane scrlViandas = new JScrollPane();
			
			JLabel lblVenta_1 = new JLabel("Venta");
			lblVenta_1.setForeground(Color.WHITE);
			GroupLayout gl_pnlViandasDeVenta = new GroupLayout(pnlViandasDeVenta);
			gl_pnlViandasDeVenta.setHorizontalGroup(
				gl_pnlViandasDeVenta.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlViandasDeVenta.createSequentialGroup()
						.addGroup(gl_pnlViandasDeVenta.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_pnlViandasDeVenta.createSequentialGroup()
								.addGap(10)
								.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_pnlViandasDeVenta.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrlViandas, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE))
							.addGroup(gl_pnlViandasDeVenta.createSequentialGroup()
								.addGap(58)
								.addComponent(lblVenta_1)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(cmbxVenta, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			gl_pnlViandasDeVenta.setVerticalGroup(
				gl_pnlViandasDeVenta.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlViandasDeVenta.createSequentialGroup()
						.addGap(11)
						.addComponent(btnVolver)
						.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
						.addGroup(gl_pnlViandasDeVenta.createParallelGroup(Alignment.BASELINE)
							.addComponent(cmbxVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblVenta_1))
						.addGap(36)
						.addComponent(scrlViandas, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			
			JTable tblViandas = new JTable();;
			 
			tblViandas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Codigo", "Descripcion", "Precio unit. ($)", "Cant. Unidades", "Observacion"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, Float.class, Integer.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tblViandas.getColumnModel().getColumn(0).setResizable(false);
			tblViandas.getColumnModel().getColumn(1).setResizable(false);
			tblViandas.getColumnModel().getColumn(2).setResizable(false);
			tblViandas.getColumnModel().getColumn(3).setResizable(false);
			tblViandas.getColumnModel().getColumn(4).setResizable(false);
			scrlViandas.setViewportView(tblViandas);
			pnlViandasDeVenta.setLayout(gl_pnlViandasDeVenta);
		
		cmbxVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					VOVenta venta = ((VOVenta)((JComboBox) e.getSource()).getSelectedItem());
					contrViandasVenta.CargarViandas(tblViandas, venta.getCodigo());
				} catch (Exception ex) {
				}
			}
		});
		
		btnVolver.addActionListener(Navegacion);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio();
				contentPane.remove(pnlViandasDeVenta);
			}
		});
	}
	 
	private void EliminarVianda() {
		JPanel pnlEliminarVianda = new JPanel();
		pnlEliminarVianda.setBackground(Color.DARK_GRAY);
		contentPane.add(pnlEliminarVianda, "Eliminar vianda de venta");
		pnlEliminarVianda.setLayout(null);
		pnlEliminarVianda.setName("Eliminar vianda de venta");
		JButton btnVolver = new JButton("<-- Volver al inicio");
		btnVolver.setBounds(10, 11, 137, 23);
		pnlEliminarVianda.add(btnVolver);
		btnVolver.addActionListener(Navegacion);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio();
				contentPane.remove(pnlEliminarVianda);
			}
		});
	}
	
	private void FinalizarVenta() {
		
		JPanel pnlFinalizarVenta = new JPanel();pnlFinalizarVenta.setBackground(Color.DARK_GRAY);
		contentPane.add(pnlFinalizarVenta, "Finalizar venta");
		pnlFinalizarVenta.setLayout(null);
		pnlFinalizarVenta.setName("Finalizar venta");
		JButton btnVolver = new JButton("<-- Volver al inicio");
		btnVolver.setBounds(10, 11, 137, 23);
		pnlFinalizarVenta.add(btnVolver);
		btnVolver.addActionListener(Navegacion);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio();
				contentPane.remove(pnlFinalizarVenta);
			}
		});
	}
	
	private ActionListener Navegacion = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(conectado) {
				String opcion = ((JButton)e.getSource()).getText();
				switch(opcion) {
				case "Ingresar vianda":
					IngresarViandas();
					contentPane.remove(pnlInicio);
					break;
				case "Iniciar venta":
					IniciarVenta();
					contentPane.remove(pnlInicio);
					break;
				case "Agregar vianda a venta":
					ViandaAVenta();
					contentPane.remove(pnlInicio);
					break;
				case "Listar ventas":
					ListarVentas();
					contentPane.remove(pnlInicio);
					break;
				case "Viandas de venta":
					ViandasDeVenta();
					contentPane.remove(pnlInicio);
					break;
				case "Eliminar vianda de venta":
					EliminarVianda();
					contentPane.remove(pnlInicio);
					break;
				case "Finalizar venta":
					FinalizarVenta();
					contentPane.remove(pnlInicio);
					break;
				}
			}else
				JOptionPane.showMessageDialog(ventana, "Antes debe conectarse al servidor.");
		}
	};
	
}
