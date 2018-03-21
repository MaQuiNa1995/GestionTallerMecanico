package es.maqui.frontend.utilities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.maqui.frontend.utilities.Validador;

public class ValidadorTest {

	Validador sut;

	@Before
	public void setUp() throws Exception {
		sut = new Validador();
	}

	@Test
	public void validarMatriculaTest() {
		
		String matricula= "0387-DCC";
		assertTrue(sut.validarMatricula(matricula));
		
	}
}
