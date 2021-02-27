package RMI;
import ValueObjects.*;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IControladora extends Remote{
    
    //REQUISITO 1
    public void addVianda(VOVianda vovianda) throws RemoteException;
    
    //REQUISITO 2
    public void addVenta(VOVenta voventa) throws RemoteException;
    
    //REQUISITO 3
    public void addViandaVenta(String codigoVianda, int cantidad, String observacion, int codigoVenta) throws RemoteException;
    
    //REQUISITO 4
    public void removeViandaVenta(String codigoVianda, int codigoVenta, int cantidad) throws RemoteException;
    
    //REQUISITO 5
    public void endVenta(int codigo, boolean confirma) throws RemoteException;
    
    //REQUISITO 6
    public VOVenta[] getVentas() throws RemoteException;
    
    //REQUISITO 7
    public VOVianda_Venta[] getViandasVenta(int codigo) throws RemoteException;
    
    //REQUISITO 8
    public void save() throws RemoteException;
    
    //REQUISITO 9
    public void load() throws IOException, RemoteException;
    
}
