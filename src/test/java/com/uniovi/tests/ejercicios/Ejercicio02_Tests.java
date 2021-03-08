package com.uniovi.tests.ejercicios;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ejercicio02_Tests extends BaseTests {

	/** Inicio de sesión con datos válidos (administrador). */
	@Test
	public void Prueba_05() {
		fail("Not yet implemented");
	}

	/** Inicio de sesión con datos válidos (usuario estándar). */
	@Test
	public void Prueba_06() {
		fail("Not yet implemented");
	}

	/**
	 * Inicio de sesión con datos inválidos (usuario estándar, campo email y
	 * contraseña vacíos).
	 */
	@Test
	public void Prueba_07() {
		fail("Not yet implemented");
	}

	/**
	 * Inicio de sesión con datos válidos (usuario estándar, email existente, pero
	 * contraseña incorrecta).
	 */
	@Test
	public void Prueba_08() {
		fail("Not yet implemented");
	}

	/**
	 * Inicio de sesión con datos inválidos (usuario estándar, email no existente en
	 * la aplicación).
	 */
	@Test
	public void Prueba_09() {
		fail("Not yet implemented");
	}

}
