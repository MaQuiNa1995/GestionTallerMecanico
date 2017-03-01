package es.cic.curso.curso18.ejercicio028.backend.service;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Dummie;
import es.cic.curso.curso18.ejercicio028.backend.service.ClaseDummieService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso18.ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class ClaseDummieServiceImplTest {

        @PersistenceContext
	protected EntityManager entityManager;
	@Autowired
	private ClaseDummieService sut;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testCrearClaseDummie() {
		Long idClaseDummie = sut.aniadirClaseDummie("PatatasFritas",true,5,100,5f);
		assertNotNull(idClaseDummie);
	}

	@Test
	public void testEditarClaseDummie() {
		Long idClaseDummie = sut.aniadirClaseDummie("PatatasBrabas",true,5,100,5f);
		Dummie sala = sut.obtenerClaseDummie(idClaseDummie);
		sala.setPalabra("PatatasBrabas");
		Dummie salaMod = sut.obtenerClaseDummie(idClaseDummie);
		assertTrue(sala.getPalabra().equals(salaMod.getPalabra()));
	}

	@Test
	public void testBorrarClaseDummie() {
		Long idClaseDummie = sut.aniadirClaseDummie("PatatasFritas",true,5,100,5f);
		sut.borrarClaseDummie(idClaseDummie);
		List<Dummie> salas = sut.obtenerClaseDummies();
		assertTrue(salas.isEmpty());
	}

	@Test
	public void testListarClaseDummie() {
		List<Dummie> salasLista = sut.obtenerClaseDummies();
		for (Dummie salaSacada : salasLista) {
			assertNotNull(salaSacada.getId());
		}

	}

}