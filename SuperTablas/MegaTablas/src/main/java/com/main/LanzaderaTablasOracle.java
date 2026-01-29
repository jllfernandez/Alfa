package com.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.maker.CreateSchemaOracle;

public class LanzaderaTablasOracle {

	public LanzaderaTablasOracle() {

		CreateSchemaOracle cs = new CreateSchemaOracle();
		
		InputStream is = null;
		try {
			is = new FileInputStream("." + File.separator + "Schema" + "/DefinitedSchemaOracle.xml");
		} catch (Exception e) {
			System.out.println("Error" + e);
		}

		if (is == null) {
			System.out.println("Es null ..." + this.getClass());
		}
		cs.generateSchema(is);
	}

	public static void main(String args[]) {

		LanzaderaTablasOracle exec = new LanzaderaTablasOracle();

	}
}