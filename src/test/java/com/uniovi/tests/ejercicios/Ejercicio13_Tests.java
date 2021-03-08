package com.uniovi.tests.ejercicios;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ejercicio13_Tests extends BaseTests {

	/**
	 * Intentar acceder sin estar autenticado a la opción de listado de usuarios del
	 * administrador. Se deberá volver al formulario de login.
	 */
	@Test
	public void Prueba_28() {
		fail("Not yet implemented");
	}

	/**
	 * Intentar acceder sin estar autenticado a la opción de listado de ofertas
	 * propias de un usuario estándar. Se deberá volver al formulario de login.
	 */
	@Test
	public void Prueba_29() {
		fail("Not yet implemented");
	}

	/**
	 * Estando autenticado como usuario estándar intentar acceder a la opción de
	 * listado de usuarios del administrador. Se deberá indicar un mensaje de acción
	 * prohibida.
	 */
	@Test
	public void Prueba_30() {
		fail("Not yet implemented");
	}

}
