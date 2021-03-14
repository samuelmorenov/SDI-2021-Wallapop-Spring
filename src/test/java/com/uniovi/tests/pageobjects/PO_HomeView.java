package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

@Deprecated
public class PO_HomeView extends PO_NavView {
	@Deprecated
	static public void checkWelcome(int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_View.checkElement("text", p.getString("welcome.message", language));
	}

	@Deprecated
	static public void checkChangeIdiom(String textIdiom1, String textIdiom2, int locale1, int locale2) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkWelcome(locale1);
		// Cambiamos a segundo idioma
		PO_HomeView.changeIdiom(textIdiom2);
		// Comprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_HomeView.checkWelcome(locale2);
		// Volvemos a Español.
		PO_HomeView.changeIdiom(textIdiom1);
		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkWelcome(locale1);
	}

	@Deprecated
	static public void clickId(String string) {
		driver.findElement(By.id(string)).click();
	}

	@Deprecated
	public static void noEsClickable(String string) {
		assertTrue(driver.findElement(By.id(string)).isDisplayed());
	}

}
