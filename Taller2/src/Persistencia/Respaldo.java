package Persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ValueObjects.VOControladora;

public class Respaldo {
	
	public void save(String nombreArchivo, VOControladora vocontroladora) {
		try {
			FileOutputStream f = new FileOutputStream(nombreArchivo);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(vocontroladora);
			o.close();
			f.close();
		} catch (IOException e) {
		}

	}

	public VOControladora load(String nombreArchivo) {
		try {
			FileInputStream f = new FileInputStream(nombreArchivo);
			ObjectInputStream o = new ObjectInputStream(f);
			VOControladora p = (VOControladora) o.readObject();
			o.close();
			f.close();
			return p;
		} catch (IOException | ClassNotFoundException e) {
			return null;
		}
	}
}
