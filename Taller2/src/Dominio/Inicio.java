package Dominio;

import java.io.IOException;

import ValueObjects.*;

public class Inicio {

	 
    
    public static void main(String[] args) throws IOException {
        Controladora contr = Controladora.crearControladora();
        
        Viandas_Prueba(contr);
        System.out.println("\n\n");
        Venta_Prueba(contr);
        System.out.println("\n\n");
        AViandasVenta_Prueba(contr);
        System.out.println("\n\n");
        RViandasVenta_Prueba(contr);
        System.out.println("\n\n");
        FinalizarVenta_Prueba(contr);

        System.out.println("\n\n");
        ObtenerVentas_Prueba(contr);
        
    }
    
    //Ingresar y mostrar Viandas
    public static void Viandas_Prueba(Controladora contr) {
    	System.out.println("INGRESAR VIANDAS\n+-------------------------------------------------+");
    	
      	System.out.println("(1)\nIngreso una Vianda con parámetros...");
          contr.addVianda(new VOVegetariana(false, "solo lechuga", "Ensalada", "ensalada verde", 25));
          
          System.out.println("Ingreso una Vianda con parámetros...");
          contr.addVianda(new VOVianda("Milanesa", "Con chimichurri", 70));
          
          System.out.println("Ingreso una Vianda sin parámetros..."); //Controlar excepcion
          contr.addVianda(new VOVianda());
          
          System.out.println("Muestro las viandas: ");
          contr.mostrarViandas();
      }  
    
    //Ingresar y mostrar ventas
    public static void Venta_Prueba(Controladora contr) {
    	System.out.println("INGRESAR VENTAS\n+-------------------------------------------------+");
    	
    	//Ingreso de ventas
    	System.out.println("(1)\nIngreso una nueva venta con parámetros...");
    	contr.addVenta(new VOVenta("Emilio Frugoni 426"));
    	
    	System.out.println("Ingreso una nueva venta sin parámetros...");
    	contr.addVenta(new VOVenta());
    	
    	System.out.println("Ingreso una nueva venta con parámetros...");
    	contr.addVenta(new VOVenta("Emilio Frugoni 425"));
    	
    	System.out.println("Muestro las ventas: ");
    	contr.mostrarVentas();
    	
    	
    	
    }
    
    //Ingresar viandas a ventas
    public static void AViandasVenta_Prueba(Controladora contr) {
    	System.out.println("INGRESAR VIANDAS A VENTAS\n+-------------------------------------------------+");
    	//Ingreso de viandas a ventas
    	System.out.println("Ingreso vianda de Milanesa a la venta 1");
    	contr.addViandaVenta("Milanesa", 5, "Lo que sea", 1);
    	
    	System.out.println("Ingreso vianda de Ensalada a la venta 1");
    	contr.addViandaVenta("Ensalada", 5, "Lo que sea", 1);
    	
    	System.out.println("Ingreso vianda de Milanesa a la venta 4"); //Excepción codigo venta inválido
    	contr.addViandaVenta("Milanesa", 5, "Lo que sea", 4);
    	
    	System.out.println("Ingreso vianda de Churrasco a la venta 2"); //Excepción codigo vianda inválido
    	contr.addViandaVenta("Churrasco", 5, "Lo que sea", 2);
    	
    	System.out.println("Muestro las ventas");
    	contr.mostrarVentas();
    }
    
    //Quitar viandas a ventas
    public static void RViandasVenta_Prueba(Controladora contr) {
    	System.out.println("QUITAR VIANDAS A VENTAS\n+-------------------------------------------------+");
    	
    	System.out.println("(1)");
    	contr.mostrarViandasVenta(1);
    	
    	System.out.println("\n(2)\nRemovemos 3 Milanesas de la Venta 1");
    	contr.removeViandaVenta("Milanesa", 1, 3);
    	contr.mostrarViandasVenta(1);
    	
    	System.out.println("\n(3)\nRemovemos 3 Milanesas de la Venta 1");
    	contr.removeViandaVenta("Milanesa", 1, 3);
    	contr.mostrarViandasVenta(1);
    	
    	System.out.println("\n(4)\nRemovemos 3 Milanesa de la Venta 4");
    	contr.removeViandaVenta("Milanesa", 4, 3); //Excepcion
    	contr.mostrarViandasVenta(4);
    }
       
    //Finalizar venta
    public static void FinalizarVenta_Prueba(Controladora contr) {
    	System.out.println("FINALIZAR VENTA\n+-------------------------------------------------+");
    	System.out.println("(1)\nVentas: ");
    	contr.mostrarVentasFinalizadas();
    	
    	System.out.println("\n(2)\nCambio el estado de la venta 1"); //Aquí solo debe cambiar su estado
    	contr.endVenta(1, true);
    	
    	System.out.println("Cambio el estado de la venta 2"); //Aquí debe eliminarse porque no contiene viandas
    	contr.endVenta(2, true);
    	
    	System.out.println("Cambio el estado de la venta 4"); //Excepcion
    	contr.endVenta(4, true);
    	
    	
    	System.out.println("Ventas: ");
    	contr.mostrarVentasFinalizadas();
    	
    	System.out.println("\n(3)\nFinalizo la venta 1");
    	contr.endVenta(1, false);
    	
    	System.out.println("Ventas: ");
    	contr.mostrarVentasFinalizadas();
    	
    }
    
    //Listado general de ventas
    public static void ObtenerVentas_Prueba(Controladora contr) {
    	System.out.println("LISTADO GENERAL DE VENTAS\n+-------------------------------------------------+");
    	VOVenta[] voventa = (VOVenta[]) (contr.getVentas());
    	
    }
        
        
        
        
        
        
    
    
}
