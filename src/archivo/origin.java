/**Francisco Coenda
 * 13/05/2013
 * */

//Empaquetado
package archivo;

//Importación Archivos.
import java.io.*;
import java.util.List;
import org.jdom2.Document; // |
import org.jdom2.Element; // |\ Librerías
import org.jdom2.JDOMException; // |/ JDOM
import org.jdom2.input.SAXBuilder;


public class origin {

	public static void main(String[] args) {
		// Creación de las variables para realizar la lectura del archivo.
		cargarXml();
	}// Fin clase leer

	@SuppressWarnings("null")
	public static void cargarXml() {

		// Se crea un SAXBuilder para poder parsear el archivo
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("/home/ichigo/Descargas/log20130430140719.xml");
		// File xmlFile = new File(
		// "/home/ichigo/Descargas/log20130430111319Lbuild.1.xml" );
		try {
			// Se crea el documento a traves del archivo
			Document document = (Document) builder.build(xmlFile);

			// Se obtiene la raiz 'cruisecontrol'
			Element rootNode = document.getRootElement();
			// Obtengo el tag "info" como nodo raiz para poder trabajar sus
			// sub-tags.
			Element rootNode2 = rootNode.getChild("info");
			// Obtengo los nodos "property" del tag info.
			List list = rootNode2.getChildren("property");

			// Saco el primer elemento de la lista y lo imprimo por consola
			Element var1 = (Element) list.get(0);
			String var2 = var1.getAttributeValue("value");
			System.out.println("\nProyecto: " + var2 + "\n\n\n\n");

			List list2;

			rootNode2 = rootNode.getChild("build");
			if (rootNode2.getAttribute("error") != null) {
				list2 = rootNode2.getChildren("target");
				rootNode2 = (Element) list2.get(1);
				list2 = rootNode2.getChildren("task");
				rootNode2 = (Element) list2.get(1);
			}

			List lista_campos = rootNode2.getChildren();
			String arr[] = new String[10];
			int as = 0;
			for (int j = 0; j < lista_campos.size(); j++) {
				Element campo = (Element) lista_campos.get(j);
				String a = campo.getAttributeValue("priority");
				if (a.equalsIgnoreCase("warn")) {
					String nombre = campo.getValue();
					if (as < 10) {
						arr[as] = nombre;
						as++;
					}
				}
			}

		

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
