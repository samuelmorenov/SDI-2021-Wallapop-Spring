package com.uniovi.tests.ejercicios;

import java.util.List;import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.data.UserList;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.pageobjects.formularios.PO_LoginView;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ejercicio07_Tests extends BaseTests {

	/**
	 * Mostrar el listado de ofertas para dicho usuario y comprobar que se muestran
	 * todas los que existen para este usuario.
	 */
	@Test
	public void Prueba_18() {
		PO_HomeView.clickOption("login", "class", "btn btn-primary");
		PO_LoginView.fillForm(UserList.usuarios(0).email, UserList.usuarios(0).password);
		
		PO_PrivateView.accederPagina("offer-menu", "/offer/own");		
		PO_View.checkElement("text", "Oferta 1");
		PO_View.checkElement("text", "Oferta 3");
		PO_View.checkElement("text", "Oferta Test 1");
	}

}
