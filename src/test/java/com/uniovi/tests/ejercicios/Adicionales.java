package com.uniovi.tests.ejercicios;

import static org.junit.Assert.fail;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.uniovi.services.data.UserList;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.pageobjects.formularios.PO_LoginView;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Adicionales extends BaseTests {

	/**
	 * El contador de dinero de cada usuario deberá mostrarse, en todas las vistas
	 * de acceso privado para el usuario, al lado de su email.
	 */
	@Test
	public void Adicional_01() {
		fail("Not yet implemented");
	}

	/**
	 * Es obligatorio realizar las validaciones del lado del servidor.
	 */
	@Test
	public void Adicional_02() {
		fail("Not yet implemented");
	}

	/**
	 * El saldo inicial al registrarse es de 100€.
	 */
	@Test
	public void Adicional_03() {
		fail("Not yet implemented");
	}

	/**
	 * En la lista de usuarios no debe aparecer el administrador
	 */
	@Test
	public void Adicional_04() {
		PO_NavView.clickOption("login", "class", "btn btn-primary");
		PO_LoginView.fillForm(UserList.admin.email, UserList.admin.password);
		PO_NavView.accederPagina("users-menu", "/user/list");
		PO_View.checkNoText(UserList.admin.email);
	}

}
