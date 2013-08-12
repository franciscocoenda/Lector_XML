package archivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeerPath {

	private String path;
	private InputStreamReader isr;
	private BufferedReader brd;

	/**Metodo para leer desde la consola el path donde se 
	 * localiza el archivo xml a ser leido*/
	public String leerPath(){
		
		System.out.print("Ingrese la url del archivo xml: ");

		isr = new InputStreamReader(System.in);
		brd = new BufferedReader(isr);
		try {
			path = brd.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return path;
	}
}