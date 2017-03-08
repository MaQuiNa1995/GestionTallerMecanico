package es.cic.curso.curso18.ejercicio028.frontend.utilities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidadorTest {

	Validador sut;

	@Before
	public void setUp() throws Exception {
		sut = new Validador();
	}

	@Test
	public void validarEmailTest() {
		
		String matricula= "0387-DCC";
		assertTrue(sut.validarMatricula(matricula));
		
	}
}
