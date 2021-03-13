package com.uniovi.tests.ejercicios;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.pageobjects.formularios.PO_LoginView;
import com.uniovi.tests.pageobjects.formularios.PO_PostView;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ejercicio07_Tests extends BaseTests {

	/**
	 * Mostrar el listado de ofertas para dicho usuario y comprobar que se muestran
	 * todas los que existen para este usuario.
	 */
	@Test
	public void Prueba_18() {
		PO_LoginView.loginUser0();
		
		PO_PostView.addNew("Oferta Test Prueba_18 1");
		PO_PostView.addNew("Oferta Test Prueba_18 2");
		PO_PostView.addNew("Oferta Test Prueba_18 3");
		PO_PostView.addNew("Oferta Test Prueba_18 4");

		PO_NavView.accederPagina("offer-menu", "/offer/own");
		PO_View.checkElement("text", "Oferta Test Prueba_18 1");
		PO_View.checkElement("text", "Oferta Test Prueba_18 2");
		PO_View.checkElement("text", "Oferta Test Prueba_18 3");
		PO_View.checkElement("text", "Oferta Test Prueba_18 4");
	}

}
