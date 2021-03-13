package com.uniovi.tests.ejercicios;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.uniovi.services.data.UserList;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.pageobjects.formularios.PO_LoginView;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ejercicio02_Tests extends BaseTests {

	/** Inicio de sesión con datos válidos (administrador). */
	@Test
	public void Prueba_05() {
		PO_HomeView.clickOption("login", "class", "btn btn-primary");
		PO_LoginView.fillForm(UserList.admin.email, UserList.admin.password);
		PO_View.checkKey("welcome.message", PO_Properties.getSPANISH());
	}

	/** Inicio de sesión con datos válidos (usuario estándar). */
	@Test
	public void Prueba_06() {
		PO_HomeView.clickOption("login", "class", "btn btn-primary");
		PO_LoginView.fillForm(UserList.usuarios(0).email, UserList.usuarios(0).password);
		PO_View.checkKey("welcome.message", PO_Properties.getSPANISH());
	}

	/**
	 * Inicio de sesión con datos inválidos (usuario estándar, campo email y
	 * contraseña vacíos).
	 */
	@Test
	public void Prueba_07() {
		PO_HomeView.clickOption("login", "class", "btn btn-primary");
		PO_LoginView.fillForm("", "");
		PO_View.checkKey("Error.login", PO_Properties.getSPANISH());
	}

	/**
	 * Inicio de sesión con datos válidos (usuario estándar, email existente, pero
	 * contraseña incorrecta).
	 */
	@Test
	public void Prueba_08() {
		PO_HomeView.clickOption("login", "class", "btn btn-primary");
		PO_LoginView.fillForm(UserList.usuarios(0).email, "incorrecta");
		PO_View.checkKey("Error.login", PO_Properties.getSPANISH());
	}

	/**
	 * Inicio de sesión con datos inválidos (usuario estándar, email no existente en
	 * la aplicación).
	 */
	@Test
	public void Prueba_09() {
		PO_HomeView.clickOption("login", "class", "btn btn-primary");
		PO_LoginView.fillForm("email@incorrecto.com", UserList.usuarios(0).password);
		PO_View.checkKey("Error.login", PO_Properties.getSPANISH());
	}

}
