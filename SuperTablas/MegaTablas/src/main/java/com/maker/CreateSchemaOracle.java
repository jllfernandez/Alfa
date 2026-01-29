package com.maker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.w3c.dom.Node;

import com.utils.XDomParser;

public class CreateSchemaOracle {

	private static final String SPACE = " ";
	private static final String COMILLAS = "\"";
	private static final String NEW_LINE = "\n";
	private static final String OPEN_PARENT = "(";
	private static final String CLOSE_PARENT = ")";
	private static final String POINT_COMA = ";";

	private static final String ALTER_TABLE = "ALTER TABLE";
	private static final String REFERENCES = "REFERENCES";
	private static final String COMMIT = "COMMIT;";

	public CreateSchemaOracle() {
	}

	public void generateSchema(InputStream is) {
		StringBuffer drops = null;
		StringBuffer creates = null;
		StringBuffer alterTable = null;
		StringBuffer index = null;

		String primaryKey = "";

		XDomParser.getInstance().buildXmlDocument(is);

		// Open file
		PrintStream opened = openFile("./"
				+ XDomParser.getInstance().getValorNodo("Schema"));

		String nombreTabla = "";

		ArrayList<String> dropsTables = new ArrayList<String>();

		// Tabla
		for (int posTabla = 0; posTabla < XDomParser.getInstance().getSize(
				"Tabla"); posTabla++) {
			drops = new StringBuffer();
			Node nodoTabla = XDomParser.getInstance().getNodeFromList("Tabla",
					posTabla);
			if (nodoTabla.getNodeType() == Node.ELEMENT_NODE) {
				nombreTabla = XDomParser.getInstance().getAtributoFromNodoAt(
						"Nombre_Tabla", nodoTabla, posTabla);
				drops.append("DROP TABLE");
				drops.append(SPACE);
				drops.append(COMILLAS);
				drops.append(XDomParser.getInstance().getValorNodo("Schema"));
				drops.append(COMILLAS);
				drops.append(".");
				drops.append(COMILLAS);
				drops.append(nombreTabla);
				drops.append(COMILLAS);
				drops.append(POINT_COMA);

				dropsTables.add(drops.toString());

			}
		}

		for (int pos = dropsTables.size() - 1; pos >= 0; pos--) {
			opened.println((String) dropsTables.get(pos));
		}
		opened.println(COMMIT);

		// Tabla
		for (int posTabla = 0; posTabla < XDomParser.getInstance().getSize(
				"Tabla"); posTabla++) {
			creates = new StringBuffer();
			alterTable = new StringBuffer();
			index = new StringBuffer();

			Node nodoTabla = XDomParser.getInstance().getNodeFromList("Tabla",
					posTabla);

			if (nodoTabla.getNodeType() == Node.ELEMENT_NODE) {
				nombreTabla = XDomParser.getInstance().getAtributoFromNodoAt(
						"Nombre_Tabla", nodoTabla, posTabla);

				// System.out.println("NOMBRE TABLA = " + nombreTabla);
				// creates.append(POINT_COMA);
				creates.append(NEW_LINE);

				creates.append("CREATE TABLE ");
				creates.append(COMILLAS);
				creates.append(XDomParser.getInstance().getValorNodo("Schema"));
				creates.append(COMILLAS);
				creates.append(".");
				creates.append(COMILLAS);
				creates.append(nombreTabla);
				creates.append(COMILLAS);
				creates.append(NEW_LINE);
				creates.append(OPEN_PARENT);
				creates.append(NEW_LINE);
				// Campos
				Node nodoGeneric = XDomParser.getInstance().getNodeFromList(
						"Campos", nodoTabla, 0);
				int sizeGeneric = XDomParser.getInstance().getSize("Campo",
						nodoTabla);

				for (int posLauu = 0; posLauu < sizeGeneric; posLauu++) {
					if (nodoGeneric.getNodeType() == Node.ELEMENT_NODE) {
						Node nodoLaunchh = XDomParser.getInstance()
								.getNodeFromList("Campo", nodoTabla, posLauu);
						// Nombre campo
						creates.append(XDomParser.getInstance()
								.getAtributoFromNodoAt("Nombre", nodoLaunchh,
										posLauu));
						// Space
						creates.append(SPACE);
						// Tipo campo
						creates.append(XDomParser.getInstance()
								.getAtributoFromNodoAt("Tipo", nodoLaunchh,
										posLauu));
						// Size campo
						creates.append(XDomParser.getInstance()
								.getAtributoFromNodoAt("Size", nodoLaunchh,
										posLauu));

						if ("y".equals(XDomParser.getInstance()
								.getAtributoFromNodoAt("Primary", nodoLaunchh,
										posLauu))) {
							// Space
							creates.append(SPACE);
							// Primary Key
							creates.append("PRIMARY KEY");
							primaryKey = XDomParser.getInstance()
									.getAtributoFromNodoAt("Nombre",
											nodoLaunchh, posLauu);
						}
						if ("n".equals(XDomParser.getInstance()
								.getAtributoFromNodoAt("Null", nodoLaunchh,
										posLauu))) {
							// Space
							creates.append(SPACE);
							creates.append("NOT NULL");
						}

						if ("sysdate".equals(XDomParser.getInstance()
								.getAtributoFromNodoAt("default", nodoLaunchh,
										posLauu))) {
							// Space
							creates.append(SPACE);
							creates.append("default");
							creates.append(SPACE);
							creates.append("sysdate");
						}

						if (!"".equals(XDomParser.getInstance()
								.getAtributoFromNodoAt("FK", nodoLaunchh,
										posLauu))) {
							alterTable.append(ALTER_TABLE);
							alterTable.append(SPACE);
							alterTable.append(COMILLAS);
							alterTable.append(XDomParser.getInstance()
									.getValorNodo("Schema"));
							alterTable.append(COMILLAS);
							alterTable.append(".");
							alterTable.append(COMILLAS);
							alterTable.append(nombreTabla);
							alterTable.append(COMILLAS);
							alterTable.append(NEW_LINE);

							alterTable.append(SPACE);
							alterTable.append("ADD CONSTRAINT FK_");
							//alterTable.append(nombreTabla.substring(0, 7));
							alterTable.append(nombreTabla);
							alterTable.append("_");
							alterTable.append(XDomParser.getInstance()
									.getAtributoFromNodoAt("Tabla",
											nodoLaunchh, posLauu));

							alterTable.append(NEW_LINE);
							alterTable.append(SPACE);
							alterTable.append("FOREIGN KEY (");
							alterTable.append(XDomParser.getInstance()
									.getAtributoFromNodoAt("Nombre",
											nodoLaunchh, posLauu));
							alterTable.append(CLOSE_PARENT);
							alterTable.append(NEW_LINE);
							alterTable.append(SPACE);
							alterTable.append(REFERENCES);
							alterTable.append(SPACE);
							alterTable.append(COMILLAS);
							alterTable.append(XDomParser.getInstance()
									.getValorNodo("Schema"));
							alterTable.append(COMILLAS);
							alterTable.append(".");
							alterTable.append(COMILLAS);
							alterTable.append(XDomParser.getInstance()
									.getAtributoFromNodoAt("Tabla",
											nodoLaunchh, posLauu));
							alterTable.append(COMILLAS);
							alterTable.append(OPEN_PARENT);
							alterTable.append(XDomParser.getInstance()
									.getAtributoFromNodoAt("FK", nodoLaunchh,
											posLauu));
							alterTable.append(CLOSE_PARENT);
							alterTable.append(POINT_COMA);
							alterTable.append(NEW_LINE);

						}

						if (!"".equals(XDomParser.getInstance()
								.getAtributoFromNodoAt("Index", nodoLaunchh,
										posLauu))) {
							alterTable.append(NEW_LINE);
							alterTable.append("CREATE INDEX");
							alterTable.append(SPACE);
							alterTable.append(XDomParser.getInstance()
									.getAtributoFromNodoAt("Index",
											nodoLaunchh, posLauu));
							alterTable.append(NEW_LINE);
							alterTable.append("ON");
							alterTable.append(SPACE);

							alterTable.append(COMILLAS);
							alterTable.append(XDomParser.getInstance()
									.getValorNodo("Schema"));
							alterTable.append(COMILLAS);
							alterTable.append(".");
							alterTable.append(COMILLAS);
							alterTable.append(nombreTabla);
							alterTable.append(COMILLAS);

							alterTable.append(OPEN_PARENT);
							alterTable.append(XDomParser.getInstance()
									.getAtributoFromNodoAt("Nombre",
											nodoLaunchh, posLauu));
							alterTable.append(CLOSE_PARENT);
							alterTable.append(POINT_COMA);
							alterTable.append(NEW_LINE);
						}

						creates.append(",");
						creates.append(NEW_LINE);

					}
				}
				creates.append(CLOSE_PARENT);
				creates.append(NEW_LINE);
				creates.append(POINT_COMA);

				String finale = "," + NEW_LINE + CLOSE_PARENT;
				String newFinale = NEW_LINE + CLOSE_PARENT;

				opened.println(creates.toString().replace(finale, newFinale));
				opened.println(COMMIT);
				opened.println(alterTable.toString());
				opened.println(COMMIT);
				opened.println(index.toString());
				opened.println(COMMIT);

			}
		} // Tabla

		// close SqlFile

		opened.close();
		opened = null;

	}

	private PrintStream openFile(String fileName) {
		PrintStream txt = null;
		try {
			String dir = "Generated";
			File f = new File(dir);
			f.mkdirs();
			f = null;

			FileOutputStream ftxt = new FileOutputStream(new File(dir
					+ fileName + ".sql"));
			txt = new PrintStream(ftxt);

			return txt;

		} catch (Exception e) {

			txt.close();
			txt = null;
		}
		return txt;
	}

}