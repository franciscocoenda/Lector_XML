/**Francisco Coenda
 * 21/05/2013
 * */

//Empaquetado
package archivo;

//Importación Archivos.
import java.io.*;

public class AperturaCierre {
	
	//Declaración de variables para manipular el archivo.
	private File archivo = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	
	
	public BufferedReader abrirArchivo(){
		
		//Abrimos el archivo xml y procedemos a cargarlo en un BufferedReader
		archivo = new File("/home/ichigo/Descargas/log20130430140719.xml");
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{//Nos aseguramos de cerrar el fichero tanto si se ejecuta bien
			     //la operación, así como si surgen errores.
			try {
				if(fr != null)
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return br;
	}
}
