/**Francisco Coenda
 * 21/05/2013
 * */

//Empaquetado
package archivo;

//Importación Archivos.
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**Clase para controla la apertura y cierre del archivo xml a leerse*/
public class AperturaCierre {
	
	//Declaración de variables para manipular el archivo.
	private File archivo = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	private LeerPath lp = new LeerPath();
	
	/**Metodo para abrir el archivo xml a ser leido*/
	public BufferedReader abrirArchivo(){
		//Abrimos el archivo xml y procedemos a cargarlo en un BufferedReader
		archivo = new File(lp.leerPath());
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return br;
	}
	
	/**Cerramos el archivo xml*/
	public BufferedReader cerrarArchivo(BufferedReader buffer){
			try {
				if(buffer != null)
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return buffer;
	}
}
