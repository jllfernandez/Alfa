package com.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.maker.CreateSchema;

public class LanzaderaTablas {

	public LanzaderaTablas() {

		CreateSchema cs = new CreateSchema();
		/*
		 * InputStream is = this.getClass().getResourceAsStream( "/Schema" +
		 * "/DefinitedSchema.xml");
		 */
		InputStream is = null;
		try {
			is = new FileInputStream("." + File.separator + "Schema"
					+ "/DefinitedSchema.xml");
		} catch (Exception e) {
			System.out.println("Error" + e);
		}

		if (is == null) {
			System.out.println("Es null ..." + this.getClass());
		}
		cs.generateSchema(is);
	}

	public static void main(String args[]) {

		LanzaderaTablas exec = new LanzaderaTablas();

	}
}