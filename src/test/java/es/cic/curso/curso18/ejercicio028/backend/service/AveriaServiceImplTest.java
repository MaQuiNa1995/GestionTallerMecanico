package es.cic.curso.curso18.ejercicio028.backend.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	private AveriaService sut;

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testCrearAveria() {

		Long idAveria = sut.aniadirAveria("Tubo De Escape Ilegal","El tubo de escape expulsa mas gases contaminantes de los permitidos");

		assertNotNull(idAveria);
	}

	@Test
	public void testEditarAveria() {
		
		Long idAveria = sut.aniadirAveria("Tubo De Escape Ilegal","El tubo de escape expulsa mas gases contaminantes de los permitidos");

		Averia averia = sut.obtenerAveria(idAveria);
		averia.setNombre("Tubo De Escape Contaminante");
		
		
		Averia salaMod = sut.obtenerAveria(idAveria);
		assertTrue(averia.getNombre().equals(salaMod.getNombre()));
	}

	@Test
	public void testBorrarAveria() {
		
		Long idAveria = sut.aniadirAveria("Tubo De Escape Ilegal","El tubo de escape expulsa mas gases contaminantes de los permitidos");
		
		sut.borrarAveria(idAveria);
		
		List<Averia> salas = sut.obtenerAverias();
		
		assertTrue(salas.isEmpty());
	}

	@Test
	public void testListarAveria() {
		List<Averia> averiasLista = sut.obtenerAverias();
		for (Averia averiaSacada : averiasLista) {
			assertNotNull(averiaSacada.getId());
		}

	}

}