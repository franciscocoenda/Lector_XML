package proceso;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**Clase para leer el contenido del xml a través de la API JDom2*/
public class Lector {
	
	//Variables.
	private SAXBuilder builder; 
	private File xmlFile;
	private Document documento;
	private Element rootNode;
	private Element rootNode_Level2;
	private List<Element> lista;
	
	
	/**Metodo que muestra como leer el nivel 0 y 1 de un xml, extraer
	 * los datos de éste y almacenarlos en una variable.*/
	public void LeerPrimerNivel(String path) {


		// Se crea un SAXBuilder para poder parsear el archivo
		builder = new SAXBuilder();
		xmlFile = new File(path);

		try {
			//Se parcea el archivo xml para crear el documento 
			//que se va a tratar.
			documento = (Document) builder.build(xmlFile);

			// Se obtiene la raiz del documento. En este caso 'cruisecontrol'
			rootNode = documento.getRootElement();
			
			// Obtengo el tag "info" como nodo raiz para poder trabajar 
			// los tags de éste.
			rootNode_Level2 = rootNode.getChild("info");
			
			// Obtengo los nodos "property" del tag info y los almaceno en
			// una lista.
			lista = rootNode_Level2.getChildren("property");

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} 
		catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		} 
	}
	
	/**Metodo que muestra como leer el niveles de mayor
	 * profundidad  de un xml, extraer los datos de éste y 
	 * almacenarlos en una variable.*/
	public static void LeerMasNiveles(String path){
		
		
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
	
	}
}	