package com.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.maker.CreateEntityes;

public class LanzaderaEntityes {

	public LanzaderaEntityes() {

		CreateEntityes cs = new CreateEntityes();
		/*
		 * InputStream is = this.getClass().getResourceAsStream( "/Schema" +
		 * "/DefinitedSchema.xml");
		 */
		InputStream is = null;
		try {
			is = new FileInputStream("." + File.separator + "Schema"
					+ "/DefinitedSchema.xml");

		} catch (Exception e) {
		}

		if (is == null) {
			System.out.println("Es null ..." + this.getClass());
		}

		cs.generateSequences(is);

	}

	public static void main(String args[]) {

		LanzaderaEntityes exec = new LanzaderaEntityes();

	}
}