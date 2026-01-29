package com.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.maker.CreateSequences;

public class LanzaderaSecuencias {

	public LanzaderaSecuencias() {

		CreateSequences cs = new CreateSequences();
		/*
		 * InputStream is = this.getClass().getResourceAsStream( "/Schema" +
		 * "/DefinitedSchema.xml");
		 */
		InputStream is = null;
		try {
			is = new FileInputStream("." + File.separator + "Schema" + "/DefinitedSchemaOracle.xml");

		} catch (Exception e) {
		}

		if (is == null) {
			System.out.println("Es null ..." + this.getClass());
		}

		cs.generateSequences(is);

	}

	public static void main(String args[]) {

		LanzaderaSecuencias exec = new LanzaderaSecuencias();

	}

}
