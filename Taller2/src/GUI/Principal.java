package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Excepciones.ViandaVaciaException;
import Excepciones.ViandaYaExisteException;
import GUI.Controladores.*;

import javax.swing.SwingConstants;
import java.awt.Color;
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

public class Principal extends JFrame {

	private JPanel contentPane;
	private static boolean conectado;
	private static ControladorIngresarVianda contrIngresarVianda;
	private static ControladorGuardarCambios contrGuardarCambios;
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
		
		IngresarViandas();
		
		IniciarVenta();
		
		ViandaAVenta();
		
		ListarVentas();
		
		ViandasDeVenta();
		 
		EliminarVianda();
		
		FinalizarVenta();
	}
	
	private void Inicio() {
		
		JPanel pnlInicio = new JPanel();
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
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(ventana, ex.getMessage());
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
				if(rdbtnVegetariana.isSelected()) {
					try {
						errores = contrIngresarVianda.IngresarVegetariana(txtCodigo.getText(), txtDescripcion.getText(), txtPrecio.getText(), rdbtnOvolactea.isSelected(), txtDescAdicional.getText());
					} catch (Exception ex) {
						errores = ex.getMessage();
					}
				}else {
					try {
						errores = contrIngresarVianda.IngresarVianda(txtCodigo.getText(), txtDescripcion.getText(), txtPrecio.getText());
					} catch (RemoteException | ViandaVaciaException | ViandaYaExisteException ex) {
						errores = ex.getMessage();
					}
				}
				
				if(errores.contains("Codigo")) {
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
				
				System.out.println(errores);
				if(errores.isEmpty()) {
					txtCodigo.setText("");
					txtDescripcion.setText("");
					txtPrecio.setText("");
					rdbtnOvolactea.setSelected(false);
					txtDescAdicional.setText("");
					JOptionPane.showMessageDialog(pnlIngresarViandas, "Vianda ingresada con éxito.");
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
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDireccion.setBounds(131, 125, 46, 14);
		pnlIniciarVenta.add(lblDireccion);
		
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
		btnVolver.addActionListener(Navegacion);
	}

	private void ListarVentas() {
		JPanel pnlListarVentas = new JPanel();
		pnlListarVentas.setBackground(Color.DARK_GRAY);
		contentPane.add(pnlListarVentas, "Listar ventas");
		pnlListarVentas.setLayout(null);
		pnlListarVentas.setName("Listar ventas");
		JButton btnVolver = new JButton("<-- Volver al inicio");
		btnVolver.setBounds(10, 11, 137, 23);
		pnlListarVentas.add(btnVolver);
		btnVolver.addActionListener(Navegacion);
	}
	
	private void ViandasDeVenta() {
		JPanel pnlViandasDeVenta = new JPanel();
		pnlViandasDeVenta.setBackground(Color.DARK_GRAY);
		contentPane.add(pnlViandasDeVenta, "Viandas de venta");
		pnlViandasDeVenta.setLayout(null);
		pnlViandasDeVenta.setName("Viandas de venta");
		JButton btnVolver = new JButton("<-- Volver al inicio");
		btnVolver.setBounds(10, 11, 137, 23);
		pnlViandasDeVenta.add(btnVolver);
		btnVolver.addActionListener(Navegacion);
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
	}
	
	private void FinalizarVenta() {
		JPanel pnlFinalizarVenta = new JPanel();
		pnlFinalizarVenta.setBackground(Color.DARK_GRAY);
		contentPane.add(pnlFinalizarVenta, "Finalizar venta");
		pnlFinalizarVenta.setLayout(null);
		pnlFinalizarVenta.setName("Finalizar venta");
		JButton btnVolver = new JButton("<-- Volver al inicio");
		btnVolver.setBounds(10, 11, 137, 23);
		pnlFinalizarVenta.add(btnVolver);
		btnVolver.addActionListener(Navegacion);
	}
	
	private ActionListener Navegacion = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(conectado) {
				CardLayout card = (CardLayout)contentPane.getLayout();
				card.show(contentPane, ((JButton)e.getSource()).getText());
			}else
				JOptionPane.showMessageDialog(ventana, "Antes debe conectarse al servidor.");
		}
	};
	
}

