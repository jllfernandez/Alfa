package com.maker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.w3c.dom.Node;

import com.utils.XDomParser;

public class CreateSequences {

	private static final String SPACE = " ";
	private static final String COMILLAS = "\"";
	private static final String NEW_LINE = "\n";
	private static final String OPEN_PARENT = "(";
	private static final String CLOSE_PARENT = ")";
	private static final String POINT = ".";
	private static final String POINT_COMA = ";";
	private static final String SEQ = "SEQ";
	private static final String INF = "_";
	private static final String FOR = "FOR";

	private static final String ALTER_TABLE = "ALTER TABLE";
	private static final String REFERENCES = "REFERENCES";

	public CreateSequences() {
	}

	public void generateSequences(InputStream is) {
		StringBuffer campos = null;
		StringBuffer drops = null;

		XDomParser.getInstance().buildXmlDocument(is);
		String schema = XDomParser.getInstance().getValorNodo("Schema");

		// Open file
		PrintStream opened = openFile("./" + schema);

		String nombreTabla = "";
		// Tabla
		for (int posTabla = 0; posTabla < XDomParser.getInstance().getSize("Tabla"); posTabla++) {
			campos = new StringBuffer();
			drops = new StringBuffer();

			Node nodoTabla = XDomParser.getInstance().getNodeFromList("Tabla", posTabla);

			if (nodoTabla.getNodeType() == Node.ELEMENT_NODE) {
				nombreTabla = XDomParser.getInstance().getAtributoFromNodoAt("Nombre_Tabla", nodoTabla, posTabla);
				String seq = XDomParser.getInstance().getAtributoFromNodoAt("Seq", nodoTabla, posTabla);
				if (!seq.equals("y")) {
					continue;
				}

				/*
				 * campos.append("CREATE PUBLIC SYNONYM"); campos.append(SPACE);
				 * campos.append(nombreTabla); campos.append(SPACE); campos.append("FOR");
				 * campos.append(SPACE); campos.append(nombreTabla); campos.append(POINT_COMA);
				 * campos.append(NEW_LINE);
				 */
				campos.append(NEW_LINE);

				campos.append("CREATE SEQUENCE ");
				campos.append(SEQ);
				campos.append(INF);
				campos.append(schema);
				campos.append(INF);
				campos.append(nombreTabla);
				campos.append(SPACE);
				campos.append("MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 20");
				campos.append(POINT_COMA);
				campos.append(NEW_LINE);

				campos.append(NEW_LINE);

				campos.append("COMMIT;");

				campos.append(NEW_LINE);

				drops.append(NEW_LINE);

				drops.append("DROP SEQUENCE");

				drops.append(SPACE);
				drops.append(SEQ);
				drops.append(INF);
				drops.append(schema);
				drops.append(INF);
				drops.append(nombreTabla);
				drops.append(POINT_COMA);
				drops.append(NEW_LINE);
				drops.append("COMMIT;");
				drops.append(NEW_LINE);

				opened.println(drops.toString());
				opened.println(campos.toString());

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

			FileOutputStream ftxt = new FileOutputStream(new File(dir + fileName + "Sequences.sql"));
			txt = new PrintStream(ftxt);

			return txt;

		} catch (Exception e) {

			txt.close();
			txt = null;
		}
		return txt;
	}

}