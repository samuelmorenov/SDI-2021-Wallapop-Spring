package com.uniovi.tests;

import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.uniovi.tests.ejercicios.Ejercicio01_Tests;

@RunWith(Suite.class)
@SuiteClasses({ 
	Ejercicio01_Tests.class
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SdiEntrega120211005ApplicationTests {

	// Al finalizar la ultima prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		DriverSingleton.getDriver().quit();
	}

}
