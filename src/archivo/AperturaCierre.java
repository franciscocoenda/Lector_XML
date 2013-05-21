/**Francisco Coenda
 * 21/05/2013
 * */

//Empaquetado
package archivo;

//Importación Archivos.
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element; 
import org.jdom2.JDOMException; 
import org.jdom2.input.SAXBuilder;

public class AperturaCierre {

	private static Connection conexion;

	public static void main(String[] args) {
		// Creación de las variables para realizar la lectura del archivo.
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String texto = null;

		try {
			// Apertura del fichero y creacion del BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File("/home/ichigo/Descargas/log20130430140719.xml");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}// Fin del bloque try-catch.
		System.out.print("\n\n\nCORTE\n\n");
		System.out.print(texto + "\n\n\n\n"
				+ "*************************************\n");
		cargarXml();
	}// Fin clase leer

	@SuppressWarnings("null")
	public static void cargarXml() {

	
		String consulta = null, descripcion_bug = "";
		int idProducto = 0, idComponente = 0, idBug = 0;

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

			consulta = "SELECT products.id FROM bugs.products WHERE products.name LIKE 'De Prueba'";
			ResultSet rs = con.ejecutarConsulta(consulta);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					idProducto = rs.getInt("products.id");
				}
			}

			consulta = "SELECT components.id FROM bugs.components WHERE product_id = "
					+ idProducto;
			rs = con.ejecutarConsulta(consulta);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					idComponente = rs.getInt("components.id");
				}
			}

			consulta = "INSERT INTO bugs (priority, reporter, bug_file_loc, product_id,rep_platform,"
					+ "assigned_to, qa_contact, short_desc, everconfirmed,status_whiteboard, "
					+ "bug_severity,bug_status, delta_ts, version,estimated_time, deadline, "
					+ "component_id, target_milestone, alias, op_sys)"
					+ "VALUES ('1','1','',"
					+ idProducto
					+ ",'','1',NULL,'"
					+ arr[0]
					+ "','1','','','',"
					+ "'0000-00-00 00:00:00','','0',NULL,"
					+ idComponente
					+ ",'---',NULL,'')";
			con.ejecutarUpdate(consulta);

			consulta = "SELECT MAX(bugs.bug_id) AS bug_id  FROM bugs.bugs";
			rs = con.ejecutarConsulta(consulta);
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					idBug = rs.getInt("bug_id");
				}

			}

			
			for (int i = 0; i < arr.length; i++) {
				if (!(arr[i].endsWith("^"))) {
					descripcion_bug = descripcion_bug + arr[i] + "\n";
				} else if (arr[i].endsWith("^")) {
					consulta = "INSERT INTO longdescs (thetext, bug_when, who, bug_id) "
							+ "VALUES	('"
							+ descripcion_bug
							+ "','0000-00-00 00:00:00','1'," + idBug + ")";
					con.ejecutarUpdate(consulta);
					descripcion_bug = "";
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
