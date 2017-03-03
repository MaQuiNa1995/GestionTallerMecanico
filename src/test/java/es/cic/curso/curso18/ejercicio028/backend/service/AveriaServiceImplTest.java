package es.cic.curso.curso18.ejercicio028.backend.service;

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

import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso18.ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class AveriaServiceImplTest {

    @PersistenceContext
	protected EntityManager entityManager;
	@Autowired
	private AveriaSerive sut;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testCrearAveria() {
		Long idAveria = sut.aniadirAveria("Tubo Escape Ilegal",
				"El tubo de escape expulsa muchos gases contaminantes");
		
		assertNotNull(idAveria);
	}

	@Test
	public void testEditarAveria() {
		Long idAveria = sut.aniadirAveria("Tubo Escape Ilegal",
				"El tubo de escape expulsa muchos gases contaminantes");
		
		Averia sala = sut.obtenerAveria(idAveria);
		sala.setNombre("Tubo Escape No Legal");
		Averia salaMod = sut.obtenerAveria(idAveria);
		assertTrue(sala.getNombre().equals(salaMod.getNombre()));
	}

	@Test
	public void testBorrarAveria() {
		Long idAveria = sut.aniadirAveria("Tubo Escape Ilegal",
				"El tubo de escape expulsa muchos gases contaminantes");
		
		sut.borrarAveria(idAveria);
		List<Averia> salas = sut.obtenerAverias();
		assertTrue(salas.isEmpty());
	}

	@Test
	public void testListarAveria() {
		List<Averia> salasLista = sut.obtenerAverias();
		for (Averia salaSacada : salasLista) {
			assertNotNull(salaSacada.getId());
		}

	}

}