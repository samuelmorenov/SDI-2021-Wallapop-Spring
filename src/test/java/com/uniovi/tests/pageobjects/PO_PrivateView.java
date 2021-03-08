package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	static public void login(WebDriver driver, String dnip, String passwordp, String text) {

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, dnip, passwordp);
		// COmprobamos que entramos en la pagina privada del Profesor
		PO_View.checkElement(driver, "text", text);

	}

	static public void logout(WebDriver driver) {
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}

	public static void countRow(WebDriver driver, int n) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina("free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == n);
	}

	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(5);
		// Seleccionamos el alumnos userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		// Rellenemos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	public static void detailsMark(WebDriver driver, String texto) {
		By enlace = By.xpath("//td[contains(text(), '" + texto + "')]/following-sibling::*[2]");
		driver.findElement(enlace).click();
	}

	public static void deleteMark(WebDriver driver, List<WebElement> elementos, String texto) {

		// Esperamos a que aparezca la Nueva nota en la ultima pagina
		// Y Pinchamos en el enlace de borrado de la Nota "Nota Nueva 1"
		// td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(text(),
		// 'mark/delete')]"
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), '" + texto + "')]/following-sibling::*/a[contains(@href, 'mark/delete')]");
		elementos.get(0).click();

	}

	static public void accederPagina(WebDriver driver, String lista, String elemento, List<WebElement> elementos) {
		// Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, '" + lista + "')]/a");
		elementos.get(0).click();
		// Pinchamos en la opción de lista de notas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'" + elemento + "')]");
		elementos.get(0).click();
	}

	static public void ultimaPaginacion(WebDriver driver, List<WebElement> elementos) {
		// Esperamos a que se muestren los enlaces de paginación la lista de notas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Nos vamos a la última página
		elementos.get(3).click();
	}
}