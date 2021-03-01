package RMI;
import ValueObjects.*;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import Excepciones.FechaIncorrectaException;
import Excepciones.LimiteDeViandasException;
import Excepciones.RespaldoNoExisteException;
import Excepciones.RespaldoVacioException;
import Excepciones.VentaNoExisteException;
import Excepciones.VentaNoTieneViandaException;
import Excepciones.VentaVaciaException;
import Excepciones.ViandaNoExisteException;
import Excepciones.ViandaVaciaException;
import Excepciones.ViandaYaExisteException;

public interface IControladora extends Remote{
    
    //REQUISITO 1
    public void addVianda(VOVianda vovianda) throws RemoteException, ViandaYaExisteException, ViandaVaciaException;
    
    //REQUISITO 2
    public void addVenta(VOVenta voventa) throws RemoteException, VentaVaciaException, FechaIncorrectaException;
    
    //REQUISITO 3
    public void addViandaVenta(String codigoVianda, int cantidad, String observacion, int codigoVenta) throws RemoteException, ViandaNoExisteException, VentaNoExisteException, LimiteDeViandasException;
    
    //REQUISITO 4
    public void removeViandaVenta(String codigoVianda, int codigoVenta, int cantidad) throws RemoteException, ViandaNoExisteException, VentaNoExisteException, VentaNoTieneViandaException;
    
    //REQUISITO 5
    public void endVenta(int codigo, boolean confirma) throws RemoteException, VentaNoExisteException;
    
    //REQUISITO 6
    public VOVenta[] getVentas() throws RemoteException;
    
    //REQUISITO 7
    public VOVianda_Venta[] getViandasVenta(int codigo) throws RemoteException, VentaNoExisteException;
    
    //REQUISITO 8
    public void save() throws RemoteException, IOException;
    
    //REQUISITO 9
    public void load() throws IOException, RemoteException, ClassNotFoundException, RespaldoVacioException, RespaldoNoExisteException;
    
}
