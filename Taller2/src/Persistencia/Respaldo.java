package Persistencia;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ValueObjects.VOControladora;

public class Respaldo {
	
	public void save(String backupFile, VOControladora vocontroladora) throws IOException {
		FileOutputStream f = new FileOutputStream(backupFile);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(vocontroladora);
		o.close();
		f.close();
	}

	public VOControladora load(String backupFile) throws IOException, ClassNotFoundException, EOFException, FileNotFoundException {
			FileInputStream f = new FileInputStream(backupFile);
			ObjectInputStream o = new ObjectInputStream(f);
			VOControladora p = (VOControladora) o.readObject();
			o.close();
			f.close();
			return p;
	}
}
