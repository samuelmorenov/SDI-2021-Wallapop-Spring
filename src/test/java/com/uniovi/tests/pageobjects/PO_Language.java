package com.uniovi.tests.pageobjects;

public class PO_Language {

	private static String ES = "btnSpanish";
	private static String EN = "btnEnglish";

	static public void checkChangeIdiom(String message) {
		PO_View.checkKey(message, PO_Properties.getSPANISH());
		PO_HomeView.changeIdiom(EN);
		PO_View.checkKey(message, PO_Properties.getENGLISH());
		PO_HomeView.changeIdiom(ES);
		PO_View.checkKey(message, PO_Properties.getSPANISH());
	}

	static public void checkChangeIdiom(String message1, String message2) {
		PO_View.checkKey(message1, PO_Properties.getSPANISH());
		PO_View.checkKey(message2, PO_Properties.getSPANISH());
		PO_HomeView.changeIdiom(EN);
		PO_View.checkKey(message1, PO_Properties.getENGLISH());
		PO_View.checkKey(message2, PO_Properties.getENGLISH());
		PO_HomeView.changeIdiom(ES);
		PO_View.checkKey(message1, PO_Properties.getSPANISH());
		PO_View.checkKey(message2, PO_Properties.getSPANISH());
	}
}
