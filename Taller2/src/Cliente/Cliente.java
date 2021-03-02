package Cliente;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Properties;

import Excepciones.FechaIncorrectaException;
import Excepciones.LimiteDeViandasException;
import Excepciones.VentaNoExisteException;
import Excepciones.VentaNoTieneViandaException;
import Excepciones.VentaVaciaException;
import Excepciones.VentaYaConfirmadaException;
import Excepciones.ViandaNoExisteException;
import Excepciones.ViandaVaciaException;
import Excepciones.ViandaYaExisteException;
import GUI.*;
import RMI.IControladora;
import ValueObjects.*;

public class Cliente {

	public static void main(String[] args){
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("cfg/config.properties"));
			
			String portValue = prop.getProperty("SERVER_PORT");
			String ipValue = prop.getProperty("SERVER_IP");
			String protocolValue = prop.getProperty("PROTOCOL");
			
			IControladora contr = (IControladora) Naming.lookup(protocolValue + "://"+ ipValue + ":" + portValue + "//Controladora");		
			
			//Principal pri = new Principal(contr); 
			//pri.setVisible(true);
			
			Venta_Prueba(contr);
			System.out.println("\n\n");
			Viandas_Prueba(contr);
			System.out.println("\n\n");
			AViandasVenta_Prueba(contr);
			System.out.println("\n\n");
			RViandasVenta_Prueba(contr);
			System.out.println("\n\n");
			ZFinalizarVenta_Prueba(contr);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//Ingresar y mostrar ventas
    public static void Venta_Prueba(IControladora contr) {
    	
    	
    		
				System.out.println("INGRESAR VENTAS\n+-------------------------------------------------+");
	        	
	        	//Ingreso de ventas
	        	System.out.println("(1)\nIngreso una nueva venta con parámetros...");
	        	try {
					contr.addVenta(new VOVenta("Emilio Frugoni 425"));
				} catch (RemoteException e) {
					System.out.println(e.getMessage());
				} catch (VentaVaciaException e) {
					System.out.println(e.getMensaje());
				} catch (FechaIncorrectaException e) {
					System.out.println(e.getMensaje());
				}
				System.out.println("Ingreso una nueva venta sin parámetros...");
		    	try {
					contr.addVenta(new VOVenta());
				} catch (RemoteException e) {
					System.out.println(e.getMessage());
				} catch (VentaVaciaException e) {
					System.out.println(e.getMensaje());
				} catch (FechaIncorrectaException e) {
					System.out.println(e.getMensaje());
				}
		    	System.out.println("Ingreso una nueva venta con parámetros...");
				try {
					contr.addVenta(new VOVenta("Emilio Frugoni 426"));
				} catch (RemoteException e) {
					System.out.println(e.getMessage());
				} catch (VentaVaciaException e) {
					System.out.println(e.getMensaje());
				} catch (FechaIncorrectaException e) {
					System.out.println(e.getMensaje());
				}
			
			
	    
    	
			System.out.println("Muestro las ventas: ");
			try {
				mostrarVentas(contr);
			} catch (RemoteException e) {
				System.out.println(e.getMessage());
			} catch (VentaNoExisteException e) {
				System.out.println(e.getMensaje());
			}
    }
    
    public static void mostrarVentas(IControladora contr) throws RemoteException, VentaNoExisteException {
    	for(VOVenta venta: contr.getVentas()){
            System.out.println(venta.getCodigo() + " | " + venta.getDireccion()+ " | "+ venta.getFechaString()+ " - " +venta.getHoraString() + " | pend: " + venta.isPendiente());
            for(VOVianda_Venta vianda: contr.getViandasVenta(venta.getCodigo())) {
            	try {
            		System.out.println("	" + vianda.getVianda().getCodigo() + " | " + vianda.getCantidad());
            	}catch(NullPointerException ex) {
            		
            	}
            			
            }
        }
    }
    
  //Ingresar y mostrar Viandas
    public static void Viandas_Prueba(IControladora contr){
    	
         
        	  System.out.println("INGRESAR VIANDAS\n+-------------------------------------------------+");
          	
            	System.out.println("(1)\nIngreso una Vianda con parámetros...");
			try {
				contr.addVianda(new VOVegetariana(false, "solo lechuga", "Ensalada", "ensalada verde", 25));
			} catch (RemoteException e) {
				System.out.println(e.getMessage());
			} catch (ViandaYaExisteException e) {
				System.out.println(e.getMensaje());
			} catch (ViandaVaciaException e) {
				System.out.println(e.getMensaje());
			}
			 System.out.println("Ingreso una Vianda con parámetros...");
	          try {
				contr.addVianda(new VOVianda("Milanesa", "Con chimichurri", 70));
			} catch (RemoteException e) {
				System.out.println(e.getMessage());
			} catch (ViandaYaExisteException e) {
				System.out.println(e.getMensaje());
			} catch (ViandaVaciaException e) {
				System.out.println(e.getMensaje());
			}
	          System.out.println("Ingreso una Vianda sin parámetros..."); //Controlar excepcion
	          try {
				contr.addVianda(new VOVianda());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ViandaYaExisteException e) {
				System.out.println(e.getMensaje());
			} catch (ViandaVaciaException e) {
				System.out.println(e.getMensaje());
			}
          System.out.println("Muestro las viandas: ");
          try {
			mostrarViandas(contr);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
      }  
    
    public static void mostrarViandas(IControladora contr) throws RemoteException {
    	for(VOVianda vianda: contr.getViandas()){
            System.out.println(vianda.getCodigo() + " | " + vianda.getDescripcion() + " | "+ vianda.getPrecioUnitario());
        }
    }
    
    
  //Ingresar viandas a ventas
    public static void AViandasVenta_Prueba(IControladora contr) {
    	System.out.println("INGRESAR VIANDAS A VENTAS\n+-------------------------------------------------+");
    	//Ingreso de viandas a ventas
    	System.out.println("Ingreso vianda de Milanesa a la venta 1");
    	try {
			contr.addViandaVenta("Milanesa", 5, "Lo que sea", 1);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (ViandaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (LimiteDeViandasException e) {
			System.out.println(e.getMensaje());
		}
    	
    	System.out.println("Ingreso vianda de Ensalada a la venta 1");
    	try {
			contr.addViandaVenta("Ensalada", 5, "Lo que sea", 1);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (ViandaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (LimiteDeViandasException e) {
			System.out.println(e.getMensaje());
		}
    	
    	System.out.println("Ingreso vianda de Milanesa a la venta 4"); //Excepción codigo venta inválido
    	try {
			contr.addViandaVenta("Milanesa", 5, "Lo que sea", 4);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ViandaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (LimiteDeViandasException e) {
			System.out.println(e.getMensaje());
		}
    	
    	System.out.println("Ingreso vianda de Churrasco a la venta 2"); //Excepción codigo vianda inválido
    	try {
			contr.addViandaVenta("Churrasco", 5, "Lo que sea", 2);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (ViandaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (LimiteDeViandasException e) {
			System.out.println(e.getMensaje());
		}
    	
    	System.out.println("Muestro las ventas");
    	try {
			mostrarVentas(contr);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		}
    }
    
    //Quitar viandas a ventas
    public static void RViandasVenta_Prueba(IControladora contr) {
    	System.out.println("QUITAR VIANDAS A VENTAS\n+-------------------------------------------------+");
    	
    	System.out.println("(1)");
    	mostrarViandasVenta(1, contr);
    	
    	System.out.println("\n(2)\nRemovemos 3 Milanesas de la Venta 1");
    	try {
			contr.removeViandaVenta("Milanesa", 1, 3);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (ViandaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaNoTieneViandaException e) {
			System.out.println(e.getMensaje());
		}
    	mostrarViandasVenta(1, contr);
    	
    	System.out.println("\n(3)\nRemovemos 3 Milanesas de la Venta 1");
    	try {
			contr.removeViandaVenta("Milanesa", 1, 3);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());;
		} catch (ViandaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaNoTieneViandaException e) {
			System.out.println(e.getMensaje());
		}
    	mostrarViandasVenta(1, contr);
    	
    	System.out.println("\n(4)\nRemovemos 3 Milanesa de la Venta 4");
    	try {
			contr.removeViandaVenta("Milanesa", 4, 3);
			mostrarViandasVenta(4, contr);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (ViandaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaNoTieneViandaException e) {
			System.out.println(e.getMensaje());
		}
    	
    }
    
    public static void mostrarViandasVenta(int codigo, IControladora contr) {
    	try {
			for(VOVianda_Venta vianda: contr.getViandasVenta(codigo)){
			    System.out.println(vianda.getVianda().getCodigo() + " | " + vianda.getCantidad());
			}
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		}
    }
    
    
  //Finalizar venta
    public static void ZFinalizarVenta_Prueba(IControladora contr) {
    	System.out.println("FINALIZAR VENTA\n+-------------------------------------------------+");
    	System.out.println("(1)\nVentas: ");
    	try {
			mostrarVentas(contr);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		}
    	
    	System.out.println("\n(2)\nCambio el estado de la venta 1"); 
    	try {
			contr.endVenta(1, true);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaYaConfirmadaException e) {
			System.out.println(e.getMensaje());
		}
    	
    	System.out.println("Cambio el estado de la venta 2");
    	try {
			contr.endVenta(2, true);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaYaConfirmadaException e) {
			System.out.println(e.getMensaje());
		}
    	
    	System.out.println("elimino la venta 4"); 
    	try {
			contr.endVenta(4, false);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaYaConfirmadaException e) {
			System.out.println(e.getMensaje());
		}
    	
    	
    	System.out.println("Ventas: ");
    	try {
			mostrarVentas(contr);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		}
    	
    	System.out.println("\n(3)\nFinalizo la venta 1");
    	try {
			contr.endVenta(1, false);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		} catch (VentaYaConfirmadaException e) {
			System.out.println(e.getMensaje());
		}
    	
    	System.out.println("Ventas: ");
    	try {
			mostrarVentas(contr);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (VentaNoExisteException e) {
			System.out.println(e.getMensaje());
		}
    	
    }
}