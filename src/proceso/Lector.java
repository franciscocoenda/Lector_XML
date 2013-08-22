package proceso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import archivo.LeerPath;

/**Clase para leer el contenido del xml a través de la API JDom2*/
public class Lector {
	
	//Variables.
	private SAXBuilder builder; 
	private File xmlFile;
	private Document documento;
	private Element rootNode;
	private Element rootNode_Level2;
	private Element rootNode_Level3;
	private Element campo;
	private String mensaje;
	private ArrayList<String> almacen = new ArrayList<String>();
	private Iterator<String> i;
	private List<Element> lista;
	private LeerPath path = new LeerPath();
	
	
	/**Metodo que muestra como leer el nivel 0 y 1 de un xml, extraer
	 * los datos de éste y almacenarlos en una variable.*/
	public void LeerPrimerNivel() {

	
		// Se crea un SAXBuilder para poder parsear el archivo
		builder = new SAXBuilder();
		xmlFile = new File(path.leerPath());

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
			
			//Imprimo por consola la lista.
			for (int i = 0; i < lista.size(); i++){
				System.out.println(((Element)lista.get(i)).getAttributeValue("value"));
			}
			System.out.print("\n\n\n\n");
			
			
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
	public void LeerMasNiveles(){
		
		// Se crea un SAXBuilder para poder parsear el archivo
		builder = new SAXBuilder();
		xmlFile = new File(path.leerPath());

		try {
			//Se parcea el archivo xml para crear el documento 
			//que se va a tratar.
			documento = (Document) builder.build(xmlFile);

			// Se obtiene la raiz del documento. En este caso 'cruisecontrol'
			rootNode = documento.getRootElement();

			// Obtengo el tag "build" como nodo raiz para poder trabajar 
			// los tags de éste. Este tag cuenta con una profundidad mayor.
			rootNode_Level2 = rootNode.getChild("build");

			//Obtengo el atributo del tag build y verifico que este no sea null
			if (rootNode_Level2.getAttribute("error") != null) {			
				//Obtengo el tag target
				lista = rootNode_Level2.getChildren("target");
				rootNode_Level3 = (Element) lista.get(1);
				//Obtengo el tag task, que es el que me interesa
				lista = rootNode_Level3.getChildren("task");
				lista = ((Element) lista.get(1)).getChildren();
			}
		}catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}catch (IOException e) {
			System.out.println(e.getMessage());
		} 

		//recorro la lista extrayendo los valores de esta y
		//almacenandolos en el ArrayList almacen.
		for (int i = 0; i < lista.size(); i++) {
			campo = (Element) lista.get(i);
			mensaje = campo.getAttributeValue("priority");
			if (mensaje.equalsIgnoreCase("warn")) {
				String nombre = campo.getValue();
				almacen.add(nombre);
			}
		}
	
		//Imprimo lo que contiene almacen
		i = almacen.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}

	}
}	