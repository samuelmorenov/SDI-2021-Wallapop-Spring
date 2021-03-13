package com.uniovi.tests.pageobjects.formularios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.pageobjects.PO_NavView;

public class PO_LoginView extends PO_NavView {
	static public void fillForm(String emailp, String passwordp) {

		WebElement email = driver.findElement(By.name("username"));
		email.click();
		email.clear();
		email.sendKeys(emailp);

		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);

		By boton = By.className("btn");
		driver.findElement(boton).click();

	}
}