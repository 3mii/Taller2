package Dominio;

import java.io.IOException;

import ValueObjects.*;

public class Inicio {
//Hola Mundo
    
    public static void main(String[] args) throws IOException {
        Controladora contr = Controladora.crearControladora();
        
        
        /*
        contr.addVianda(new VOVegetariana(false, "solo lechuga", "ensalada", "ensalada verde", 25));
        
        contr.addVianda(new VOVianda("chorizo", "chorizo rojo anashei", 40));
        
        contr.addVenta(new VOVenta("Basilio Araujo 361"));
        
        contr.addViandaVenta("ensalada", 3, "2 con cebolla", 1);
        contr.addViandaVenta("chorizo", 1, "con mostaza", 1);
        
        
        contr.addVenta(new VOVenta("Braulio gaaa"));
        
        contr.addViandaVenta("ensalada", 5, "2 con cebolla",2);
        contr.addViandaVenta("chorizo", 3, "con mostaza", 2);
        
        */
        //contr.removeViandaVenta("ensalada", 1, 22);
        //contr.removeViandaVenta("chorizo", 1, 22);
        
        
        //contr.endVenta(1, true);
        
        //contr.mostrarVentas();
        
        
       // contr.save();
        
        
        contr.load();
        
        for (VOVenta venta : contr.getVentas()) {
        	System.out.println(venta.getCodigo() + " | " + venta.getDireccion() + " | " + venta.getViandas().getCantidadUnidades());
        	for (VOVianda_Venta vianda : contr.getViandasVenta(venta.getCodigo())) {
    			System.out.println(vianda.getVianda().getCodigo() + " | " + vianda.getCantidad()+ " | ");
    			if (vianda.getVianda() instanceof VOVegetariana)
    				System.out.println("Vegatariana");
    			else
    				System.out.println("Normal");
    				
    		}
		}
        
        
       
        
        
        
        
        
        
    }
    
}
