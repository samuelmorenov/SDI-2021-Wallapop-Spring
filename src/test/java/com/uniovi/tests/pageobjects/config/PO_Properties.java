package com.uniovi.tests.pageobjects.config;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class PO_Properties {
	public static int getSPANISH() {
		return SPANISH;
	}

	public static int getENGLISH() {
		return ENGLISH;
	}

	private String Path;
	static int SPANISH = 0;
	static int ENGLISH = 1;
	static Locale[] idioms = new Locale[] { new Locale("ES"), new Locale("EN") };


	public PO_Properties(String Path) {
		this.Path = Path;
	}

	public String getString(String prop, int locale) {

		ResourceBundle bundle = ResourceBundle.getBundle(Path, idioms[locale]);
		String value = bundle.getString(prop);
		String result = "";
		try {
			result = new String(value.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

}