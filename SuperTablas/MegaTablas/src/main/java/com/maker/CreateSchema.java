package com.maker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.w3c.dom.Node;

import com.utils.XDomParser;

public class CreateSchema {

	private static final String SPACE = " ";
	private static final String SPACES = "     ";
	private static final String COMILLAS = "\"";
	private static final String NEW_LINE = "\n";
	private static final String OPEN_PARENT = "(";
	private static final String CLOSE_PARENT = ")";
	private static final String TABLESPACE = "TABLESPACE";
	private static final String POINT_COMA = ";";

	private static final String ALTER_TABLE = "ALTER TABLE";
	private static final String REFERENCES = "REFERENCES";
	private static final String COMMIT = "COMMIT;";
	private static final String CREATE_INDEX = "CREATE INDEX";
	private static final String FICHERO_TABLAS = "_TABLAS";
	private static final String FICHERO_CONSTRAINTS = "_CONSTRAINTS";
	private static final String FICHERO_INDICES = "_INDICES";
	private static final String FICHERO_GRANTS = "_GRANTS";

	public CreateSchema() {
	}

	public void generateSchema(InputStream is) {
		StringBuffer drops = null;
		StringBuffer creates = null;
		StringBuffer constraints = null;
		StringBuffer index = null;
		StringBuffer grants = null;

		XDomParser.getInstance().buildXmlDocument(is);

		// Open file
		PrintStream openedTablas = openFile(
				"./" + XDomParser.getInstance().getValorNodo("STRUCTURE_NAME").concat(FICHERO_TABLAS));

		// Open file
		PrintStream openedConstraints = openFile(
				"./" + XDomParser.getInstance().getValorNodo("STRUCTURE_NAME").concat(FICHERO_CONSTRAINTS));

		// Open file
		PrintStream openedIndices = openFile(
				"./" + XDomParser.getInstance().getValorNodo("STRUCTURE_NAME").concat(FICHERO_INDICES));

		// Open file
		PrintStream openedGrants = openFile(
				"./" + XDomParser.getInstance().getValorNodo("STRUCTURE_NAME").concat(FICHERO_GRANTS));

		String nombreTabla = "";

		ArrayList<String> dropsTables = new ArrayList<String>();

		// Tabla
		for (int posTabla = 0; posTabla < XDomParser.getInstance().getSize("Tabla"); posTabla++) {
			drops = new StringBuffer();
			Node nodoTabla = XDomParser.getInstance().getNodeFromList("Tabla", posTabla);
			if (nodoTabla.getNodeType() == Node.ELEMENT_NODE) {
				nombreTabla = XDomParser.getInstance().getAtributoFromNodoAt("Nombre_Tabla", nodoTabla, posTabla);
				drops.append("DROP TABLE");
				drops.append(SPACE);
				drops.append(XDomParser.getInstance().getValorNodo("Schema"));
				drops.append(".");
				drops.append(nombreTabla);
				drops.append(POINT_COMA);

				dropsTables.add(drops.toString());

			}
		}

		for (int pos = dropsTables.size() - 1; pos >= 0; pos--) {
			openedTablas.println((String) dropsTables.get(pos));
		}
		openedTablas.println(COMMIT);

		ArrayList<String> grantsAll = new ArrayList<String>();
		ArrayList<String> grantsRead = new ArrayList<String>();

		String nombreGrant, tipoGrant = null;

		// Grant
		for (int posGrant = 0; posGrant < XDomParser.getInstance().getSize("Grant"); posGrant++) {

			Node nodoGrant = XDomParser.getInstance().getNodeFromList("Grant", posGrant);
			// String primaryKey = null;
			if (nodoGrant.getNodeType() == Node.ELEMENT_NODE) {
				nombreGrant = XDomParser.getInstance().getAtributoFromNodoAt("name", nodoGrant, posGrant);
				tipoGrant = XDomParser.getInstance().getAtributoFromNodoAt("rol", nodoGrant, posGrant);
				if (tipoGrant.equals("all")) {
					grantsAll.add(nombreGrant);
				} else if (tipoGrant.equals("read")) {
					grantsRead.add(nombreGrant);
				}
			}
		}

		// Tabla
		for (int posTabla = 0; posTabla < XDomParser.getInstance().getSize("Tabla"); posTabla++) {
			creates = new StringBuffer();
			constraints = new StringBuffer();
			index = new StringBuffer();
			grants = new StringBuffer();

			Node nodoTabla = XDomParser.getInstance().getNodeFromList("Tabla", posTabla);

			// String primaryKey = null;
			if (nodoTabla.getNodeType() == Node.ELEMENT_NODE) {
				nombreTabla = XDomParser.getInstance().getAtributoFromNodoAt("Nombre_Tabla", nodoTabla, posTabla);

				creates.append(NEW_LINE);

				creates.append("CREATE TABLE IF NOT EXISTS ");
				creates.append(XDomParser.getInstance().getValorNodo("Schema"));
				creates.append(".");
				creates.append(nombreTabla);
				creates.append(NEW_LINE);
				creates.append(OPEN_PARENT);
				creates.append(NEW_LINE);
				// Campos
				Node nodoGeneric = XDomParser.getInstance().getNodeFromList("Campos", nodoTabla, 0);
				int sizeGeneric = XDomParser.getInstance().getSize("Campo", nodoTabla);

				String nombreCampo = null;

				for (int posLauu = 0; posLauu < sizeGeneric; posLauu++) {
					if (nodoGeneric.getNodeType() == Node.ELEMENT_NODE) {
						creates.append(SPACES);

						Node nodoLaunchh = XDomParser.getInstance().getNodeFromList("Campo", nodoTabla, posLauu);
						// Nombre campo
						nombreCampo = XDomParser.getInstance().getAtributoFromNodoAt("Nombre", nodoLaunchh, posLauu);
						creates.append(nombreCampo);
						// Space
						creates.append(SPACE);
						// Tipo campo
						creates.append(XDomParser.getInstance().getAtributoFromNodoAt("Tipo", nodoLaunchh, posLauu));

						if ("y".equals(
								XDomParser.getInstance().getAtributoFromNodoAt("Primary", nodoLaunchh, posLauu))) {
							// Space
							creates.append(SPACE);
							// Primary Key
							creates.append("GENERATED ALWAYS AS identity NOT NULL,  PRIMARY KEY (").append(nombreCampo)
									.append(")");
							// primaryKey = XDomParser.getInstance().getAtributoFromNodoAt("Nombre",
							// nodoLaunchh, posLauu);
						} else {
							// Size campo
							creates.append(
									XDomParser.getInstance().getAtributoFromNodoAt("Size", nodoLaunchh, posLauu));
							if ("n".equals(
									XDomParser.getInstance().getAtributoFromNodoAt("Null", nodoLaunchh, posLauu))) {
								// Space
								creates.append(SPACE);
								creates.append("NOT NULL");
							}
							if ("y".equals(
									XDomParser.getInstance().getAtributoFromNodoAt("Null", nodoLaunchh, posLauu))) {
								// Space
								creates.append(SPACE);
								creates.append("NULL");
							}

							if ("timestamp".equals(
									XDomParser.getInstance().getAtributoFromNodoAt("default", nodoLaunchh, posLauu))) {
								// Space
								creates.append(SPACE);
								creates.append("timestamp");

							}

							String tb = XDomParser.getInstance().getAtributoFromNodoAt("tabla", nodoLaunchh, posLauu);
							// fk
							String fk = XDomParser.getInstance().getAtributoFromNodoAt("fk", nodoLaunchh, posLauu);
							if (!"".equals(fk)) {
								constraints.append(ALTER_TABLE);
								constraints.append(SPACE);
								constraints.append(XDomParser.getInstance().getValorNodo("Schema"));
								constraints.append(".");
								constraints.append(nombreTabla);
								constraints.append(" ADD CONSTRAINT ");
								constraints.append("fk_");
								constraints.append(nombreTabla);
								constraints.append("_x_");
								constraints.append(tb);
								constraints.append("_");
								constraints.append(fk);
								constraints.append(SPACE);
								constraints.append("FOREIGN KEY");
								constraints.append(SPACE);
								constraints.append(OPEN_PARENT);
								constraints.append(nombreCampo);
								constraints.append(CLOSE_PARENT);
								constraints.append(SPACE);
								constraints.append(REFERENCES);
								constraints.append(SPACE);
								constraints.append(XDomParser.getInstance().getValorNodo("Schema"));
								constraints.append(".");
								constraints.append(tb);
								constraints.append(OPEN_PARENT);
								constraints.append(fk);
								constraints.append(CLOSE_PARENT);
								constraints.append(POINT_COMA);
								constraints.append(NEW_LINE);
							}

							// Indices
							String indice = XDomParser.getInstance().getAtributoFromNodoAt("Indice", nodoLaunchh,
									posLauu);
							if ("y".equals(indice)) {
								index.append(CREATE_INDEX);
								index.append(SPACE);
								index.append("idx_");
								index.append(nombreTabla);
								index.append("_");
								index.append(nombreCampo);
								index.append(SPACE);
								index.append("ON");
								index.append(SPACE);

								index.append(XDomParser.getInstance().getValorNodo("Schema"));
								index.append(".");
								index.append(nombreTabla);
								index.append(OPEN_PARENT);
								index.append(nombreCampo);
								index.append(CLOSE_PARENT);
								index.append(POINT_COMA);

							}

						}

						if ((sizeGeneric - 1) > posLauu) {
							creates.append(",");
						}
						creates.append(NEW_LINE);

					}

				}
				creates.append(NEW_LINE);
				creates.append(CLOSE_PARENT);
				creates.append(POINT_COMA);

				for (String grant : grantsAll) {
					// GRANT DELETE, INSERT, SELECT, UPDATE ON adm_perfiles TO bssos_app;

					grants.append("GRANT DELETE, INSERT, SELECT, UPDATE ON ");
					grants.append(XDomParser.getInstance().getValorNodo("Schema"));
					grants.append(".");
					grants.append(nombreTabla);
					grants.append(SPACE);
					grants.append("TO");
					grants.append(SPACE);
					grants.append(grant);
					grants.append(POINT_COMA);
					grants.append(NEW_LINE);

				}
				for (String grant : grantsRead) {
					grants.append("GRANT SELECT ON ");
					grants.append(XDomParser.getInstance().getValorNodo("Schema"));
					grants.append(".");
					grants.append(nombreTabla);
					grants.append(SPACE);
					grants.append("TO");
					grants.append(SPACE);
					grants.append(grant);
					grants.append(POINT_COMA);
					grants.append(NEW_LINE);

				}
				String finale = "," + NEW_LINE + CLOSE_PARENT;
				String newFinale = NEW_LINE + CLOSE_PARENT;

				openedTablas.println(creates.toString().replace(finale, newFinale));
				openedConstraints.println(constraints.toString());
				openedIndices.println(index.toString());
				openedGrants.println(grants.toString());

				openedTablas.println(COMMIT);

			}

		} // Tabla

		openedConstraints.println(COMMIT);
		openedIndices.println(COMMIT);
		openedGrants.println(COMMIT);

		// close SqlFile

		openedTablas.close();
		openedTablas = null;

		openedConstraints.close();
		openedConstraints = null;

		openedIndices.close();
		openedIndices = null;

	}

	private PrintStream openFile(String fileName) {
		PrintStream txt = null;
		try {
			String dir = "Generated";
			File f = new File(dir);
			f.mkdirs();
			f = null;

			FileOutputStream ftxt = new FileOutputStream(new File(dir + fileName + ".sql"));
			txt = new PrintStream(ftxt);

			return txt;

		} catch (Exception e) {

			txt.close();
			txt = null;
		}
		return txt;
	}

}