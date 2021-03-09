package Conexion;

import java.io.Serializable;

public class Monitor implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int cantLectores;
	private boolean escribiendo;
	
	
	public Monitor()
	{
		cantLectores = 0;
		escribiendo = false;
	}
	
	public synchronized void comienzoLectura()
	{
		if(escribiendo)									// si hay alguien escribiendo, a dormir (wait)
		{												// atrapar acá mismo la excepción de wait, sin hacer nada en el catch
			try
			{
				this.wait();
			}
			catch(InterruptedException e)
			{	}
		}
		cantLectores ++;
	}
	public synchronized void terminoLectura()
	{
		if((cantLectores - 1) == 0)						// si soy el ultimo lector que quedaba, despierto a alguien (notify)
		{
			this.notify();
		}
		cantLectores --;
	}
	public synchronized void comienzoEscritura()
	{
		if(escribiendo | cantLectores > 0)				// si hay alguien escribiendo o leyendo, a dormir (wait)
		{
			try
			{
				this.wait();
			}
			catch(InterruptedException e)
			{	}
		}
		escribiendo = true;
	}
	public synchronized void terminoEscritura()
	{
		escribiendo = false;							// despierto a alguien (notify)
		this.notify();
	}
	
}