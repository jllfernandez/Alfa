package com.maker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.w3c.dom.Node;

import com.utils.XDomParser;

public class CreateEntityes {

	private static final String SPACE = " ";
	private static final String COMILLAS = "\"";
	private static final String NEW_LINE = "\n";
	private static final String OPEN_PARENT = "(";
	private static final String CLOSE_PARENT = ")";
	private static final String POINT = ".";
	private static final String POINT_COMA = ";";
	private static final String COMA = ",";
	private static final String MxN = "mxn";
	private static final String INF = "_";
	private static final String FOR = "FOR";

	private static final String ALTER_TABLE = "ALTER TABLE";
	private static final String REFERENCES = "REFERENCES";

	private static final String IMPORTS = NEW_LINE.concat(NEW_LINE).concat("import java.util.List;").concat(NEW_LINE)
			.concat("import javax.persistence.Column;").concat(NEW_LINE).concat("import javax.persistence.Entity;")
			.concat(NEW_LINE).concat("import javax.persistence.FetchType;").concat(NEW_LINE)
			.concat("import javax.persistence.Id;").concat(NEW_LINE).concat("import javax.persistence.JoinColumn;")
			.concat(NEW_LINE).concat("import javax.persistence.ManyToMany;").concat(NEW_LINE)
			.concat("import javax.persistence.GeneratedValue;").concat(NEW_LINE)
			.concat("import javax.persistence.GenerationType;").concat(NEW_LINE)
			.concat("import javax.persistence.JoinTable;").concat(NEW_LINE).concat("import javax.persistence.Table;")
			.concat(NEW_LINE).concat("import lombok.Getter;").concat(NEW_LINE).concat("import lombok.Setter;")
			.concat(NEW_LINE);

	private static final String ENTITY = NEW_LINE.concat(NEW_LINE).concat("@Entity").concat(NEW_LINE)
			.concat("@Table(name = ");

	private static final String SCHEMA = "schema = ";

	private static final String PUBLIC_CLASS = "public class ";
	private static final String IMPLEMENTS = " implements java.io.Serializable {";

	private static final String SERIAL = "    private static final long serialVersionUID = 1L;";

	private static final String ID = "@Id";
	private static final String COLUMN_NAME = "@Column(name = ";
	private static final String SETTER = "@Setter";
	private static final String GETTER = "@Getter";
	private static final String PUBLIC = "public";

	private static final String END_CLASS = "}";

	public CreateEntityes() {
	}

	public void generateSequences(InputStream is) {
		StringBuffer sbEntity = null;

		PrintStream ps = null;

		XDomParser.getInstance().buildXmlDocument(is);

		ArrayList<PrintStream> entityes = new ArrayList<PrintStream>();
		ArrayList<StringBuffer> streams = new ArrayList<StringBuffer>();

		String nombreTabla = "";
		String nombreJava = "";
		boolean mxn = false;
		String tipoJava = "";
		String fk = null;
		String inverse = null;

		ArrayList<String> dropsTables = new ArrayList<String>();

		String schema = XDomParser.getInstance().getValorNodo("Schema");

		// Tabla
		for (int posTabla = 0; posTabla < XDomParser.getInstance().getSize("Tabla"); posTabla++) {
			// drops = new StringBuffer();
			Node nodoTabla = XDomParser.getInstance().getNodeFromList("Tabla", posTabla);
			if (nodoTabla.getNodeType() == Node.ELEMENT_NODE) {
				nombreTabla = XDomParser.getInstance().getAtributoFromNodoAt("Nombre_Tabla", nodoTabla, posTabla);
				nombreJava = XDomParser.getInstance().getAtributoFromNodoAt("Java", nodoTabla, posTabla);
				mxn = toBoolean(XDomParser.getInstance().getAtributoFromNodoAt(MxN, nodoTabla, posTabla));

				ps = openFile("./".concat(nombreJava));
				entityes.add(ps);

				sbEntity = new StringBuffer();
				sbEntity.append(XDomParser.getInstance().getValorNodo("PACKAGE"));
				sbEntity.append(IMPORTS);
				sbEntity.append(ENTITY).append(COMILLAS);
				sbEntity.append(nombreTabla).append(COMILLAS).append(COMA).append(SPACE);
				sbEntity.append(SCHEMA).append(COMILLAS).append(schema).append(COMILLAS).append(COMA).append(SPACE)
						.append("catalog = ").append(COMILLAS).append(COMILLAS).append(CLOSE_PARENT);

				sbEntity.append(NEW_LINE).append(PUBLIC_CLASS);
				sbEntity.append(nombreJava);
				sbEntity.append(IMPLEMENTS).append(NEW_LINE);
				sbEntity.append(NEW_LINE).append(NEW_LINE).append(NEW_LINE).append(SERIAL).append(NEW_LINE);

				// CAMPOS

				String nombreCampo = null;
				String nombreAtt = null;

				int sizeGeneric = XDomParser.getInstance().getSize("Campo", nodoTabla);
				Node nodoGeneric = XDomParser.getInstance().getNodeFromList("Campos", nodoTabla, 0);

				String size = null;

				for (int posLauu = 0; posLauu < sizeGeneric; posLauu++) {
					if (nodoGeneric.getNodeType() == Node.ELEMENT_NODE) {

						Node nodoLaunchh = XDomParser.getInstance().getNodeFromList("Campo", nodoTabla, posLauu);
						// Nombre campo
						nombreCampo = XDomParser.getInstance().getAtributoFromNodoAt("Nombre", nodoLaunchh, posLauu);

						nombreAtt = XDomParser.getInstance().getAtributoFromNodoAt("Att", nodoLaunchh, posLauu);

						tipoJava = XDomParser.getInstance().getAtributoFromNodoAt("TipoJava", nodoLaunchh, posLauu);

						size = XDomParser.getInstance().getAtributoFromNodoAt("Size", nodoLaunchh, posLauu);

						inverse = XDomParser.getInstance().getAtributoFromNodoAt("Inverse", nodoLaunchh, posLauu);

						if (mxn) {
							// Es relacion MxN

							sbEntity.append(NEW_LINE).append("@ManyToMany(fetch = FetchType.EAGER");
							sbEntity.append(NEW_LINE).append("@JoinTable(name = \"").append(nombreTabla)
									.append("\", schema = ").append(COMILLAS).append(schema).append(COMILLAS)
									.append(", joinColumns = @JoinColumn(name = \"").append(inverse)
									.append("\"), inverseJoinColumns = @JoinColumn(name = ").append(COMILLAS)
									.append(nombreCampo).append(COMILLAS).append("))");
							sbEntity.append(NEW_LINE).append(GETTER);
							sbEntity.append(NEW_LINE).append(SETTER);

							sbEntity.append(NEW_LINE).append("public List<").append(nombreCampo).append("> ")
									.append(nombreCampo).append("s;");

							sbEntity.append(NEW_LINE);
							// Seguir a siguiente Entity
							continue;
						}

						if ("y".equals(
								XDomParser.getInstance().getAtributoFromNodoAt("Primary", nodoLaunchh, posLauu))) {
							sbEntity.append(NEW_LINE).append(ID);
							sbEntity.append(NEW_LINE).append(COLUMN_NAME).append(COMILLAS).append(nombreCampo)
									.append(COMILLAS)
									.append(", unique = true, nullable = false, precision = 10, scale = 0)");
							sbEntity.append(NEW_LINE).append(" @GeneratedValue(strategy=GenerationType.IDENTITY)");
							sbEntity.append(NEW_LINE).append(GETTER).append(NEW_LINE).append(SETTER);
							sbEntity.append(NEW_LINE).append(PUBLIC).append(SPACE).append(tipoJava).append(SPACE)
									.append(nombreAtt).append(POINT_COMA).append(NEW_LINE);

						} else {
							fk = XDomParser.getInstance().getAtributoFromNodoAt("fk", nodoLaunchh, posLauu);
							if (!"".equals(fk)) {
								// Foreing key
								sbEntity.append(NEW_LINE).append("@ManyToOne(fetch = FetchType.LAZY)");
								sbEntity.append(NEW_LINE).append("@JoinColumn(name = ").append(nombreCampo)
										.append(", nullable = false)");
								sbEntity.append(NEW_LINE).append(GETTER).append(NEW_LINE).append(SETTER);
								sbEntity.append(NEW_LINE).append(PUBLIC).append(SPACE).append(tipoJava).append(SPACE)
										.append(nombreAtt).append(POINT_COMA).append(NEW_LINE);

							} else {
								if (null != size) {
									size = ", length = ".concat(size.replace("(", "").replace(")", ""))
											.concat(CLOSE_PARENT);
								} else {
									size = CLOSE_PARENT;
								}
								sbEntity.append(NEW_LINE).append(COLUMN_NAME).append(COMILLAS).append(nombreCampo)
										.append(COMILLAS).append(size);
								sbEntity.append(NEW_LINE).append(GETTER).append(NEW_LINE).append(SETTER);
								sbEntity.append(NEW_LINE).append(PUBLIC).append(SPACE).append(tipoJava).append(SPACE)
										.append(nombreAtt).append(POINT_COMA).append(NEW_LINE);
							}

						}

					}
				}
				// FIN CAMPOS

				sbEntity.append(NEW_LINE).append(NEW_LINE).append(NEW_LINE);
				sbEntity.append(END_CLASS);

				ps.println(sbEntity.toString());

				streams.add(sbEntity);

			}
		}

	}

	private PrintStream openFile(String fileName) {
		PrintStream txt = null;
		try {
			String dir = "Generated".concat(File.separator).concat("entityes");
			File f = new File(dir);
			f.mkdirs();
			f = null;

			FileOutputStream ftxt = new FileOutputStream(new File(dir + fileName + ".java"));
			txt = new PrintStream(ftxt);

			return txt;

		} catch (Exception e) {

			txt.close();
			txt = null;
		}
		return txt;
	}

	private boolean toBoolean(String s) {
		boolean result = null != s && s.equals("y");

		return result;
	}

}
