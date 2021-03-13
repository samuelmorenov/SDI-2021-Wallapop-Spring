package com.uniovi.tests.pageobjects.formularios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.pageobjects.PO_NavView;

public class PO_PostView extends PO_NavView {

	public static void fillForm(String titlep, String descriptionp, String pricep) {
		WebElement title = driver.findElement(By.name("title"));
		title.click();
		title.clear();
		title.sendKeys(titlep);
		WebElement description = driver.findElement(By.name("description"));
		description.click();
		description.clear();
		description.sendKeys(descriptionp);
		WebElement price = driver.findElement(By.name("price"));
		price.click();
		price.clear();
		price.sendKeys(pricep);
		// Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
		
	}

}
