package proceso;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**Clase para leer el contenido del xml*/
public class Lector {
	
	
	private static SAXBuilder builder; 
	private static File xmlFile;
	
	/**Metodo para procesar el archivo xml*/
	public static void ProcesarXML(String path) {


		// Se crea un SAXBuilder para poder parsear el archivo
		builder = new SAXBuilder();
		xmlFile = new File(path);
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