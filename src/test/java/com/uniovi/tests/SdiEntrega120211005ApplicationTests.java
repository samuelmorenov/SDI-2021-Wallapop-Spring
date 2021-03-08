package com.uniovi.tests;

import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.uniovi.tests.ejercicios.Ejercicio01_Tests;
import com.uniovi.tests.ejercicios.Ejercicio02_Tests;
import com.uniovi.tests.ejercicios.Ejercicio03_Tests;
import com.uniovi.tests.ejercicios.Ejercicio04_Tests;
import com.uniovi.tests.ejercicios.Ejercicio05_Tests;
import com.uniovi.tests.ejercicios.Ejercicio06_Tests;
import com.uniovi.tests.ejercicios.Ejercicio07_Tests;
import com.uniovi.tests.ejercicios.Ejercicio08_Tests;
import com.uniovi.tests.ejercicios.Ejercicio09_Tests;
import com.uniovi.tests.ejercicios.Ejercicio10_Tests;
import com.uniovi.tests.ejercicios.Ejercicio11_Tests;
import com.uniovi.tests.ejercicios.Ejercicio12_Tests;
import com.uniovi.tests.ejercicios.Ejercicio13_Tests;

@RunWith(Suite.class)
@SuiteClasses({ 
	Ejercicio01_Tests.class,
	Ejercicio02_Tests.class,
	Ejercicio03_Tests.class,
	Ejercicio04_Tests.class,
	Ejercicio05_Tests.class,
	Ejercicio06_Tests.class,
	Ejercicio07_Tests.class,
	Ejercicio08_Tests.class,
	Ejercicio09_Tests.class,
	Ejercicio10_Tests.class,
	Ejercicio11_Tests.class,
	Ejercicio12_Tests.class,
	Ejercicio13_Tests.class,
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
