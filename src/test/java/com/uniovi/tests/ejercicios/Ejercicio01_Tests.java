package com.uniovi.tests.ejercicios;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.uniovi.services.data.UserList;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ejercicio01_Tests extends BaseTests {

	private static String randomEmail() {
		return "correo" + Integer.toString((int) (100000 * Math.random())) + "@email.es";
	}

	/** Registro de Usuario con datos válidos. */
	@Test
	public void Prueba_01() {
		PO_HomeView.clickOption("signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(randomEmail(), UserList.usuariosTest(0).name, UserList.usuariosTest(0).lastName,
				UserList.usuariosTest(0).password, UserList.usuariosTest(0).password);
		PO_View.checkKey("welcome.message", PO_Properties.getSPANISH());
	}

	/** Registro de Usuario con datos inválidos: email vacío */
	@Test
	public void Prueba_02_a() {
		PO_HomeView.clickOption("signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm("", UserList.usuariosTest(0).name, UserList.usuariosTest(0).lastName,
				UserList.usuariosTest(0).password, UserList.usuariosTest(0).password);
		PO_View.checkKey("Error.empty", PO_Properties.getSPANISH());
	}

	/** Registro de Usuario con datos inválidos: nombre vacío */
	@Test
	public void Prueba_02_b() {
		PO_HomeView.clickOption("signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(randomEmail(), "", UserList.usuariosTest(0).lastName,
				UserList.usuariosTest(0).password, UserList.usuariosTest(0).password);
		PO_View.checkKey("Error.signup.name.length", PO_Properties.getSPANISH());
	}

	/** Registro de Usuario con datos inválidos: apellidos vacío */
	@Test
	public void Prueba_02_c() {
		PO_HomeView.clickOption("signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(randomEmail(), UserList.usuariosTest(0).name, "", UserList.usuariosTest(0).password,
				UserList.usuariosTest(0).password);
		PO_View.checkKey("Error.signup.lastName.length", PO_Properties.getSPANISH());
	}

	/**
	 * Registro de Usuario con datos inválidos (repetición de contraseña inválida).
	 */
	@Test
	public void Prueba_03() {
		PO_HomeView.clickOption("signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(randomEmail(), UserList.usuariosTest(0).name, UserList.usuariosTest(0).lastName,
				UserList.usuariosTest(0).password, UserList.usuariosTest(0).password + "e");
		PO_View.checkKey("Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());

	}

	/** Registro de Usuario con datos inválidos (email existente). */
	@Test
	public void Prueba_04() {
		PO_HomeView.clickOption("signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(UserList.usuarios(0).email, UserList.usuariosTest(0).name,
				UserList.usuariosTest(0).lastName, UserList.usuariosTest(0).password,
				UserList.usuariosTest(0).password + "e");
		PO_View.checkKey("Error.signup.email.duplicate", PO_Properties.getSPANISH());
	}

}
