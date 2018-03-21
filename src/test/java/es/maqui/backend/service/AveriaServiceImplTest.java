package es.maqui.backend.service;

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

import es.maqui.backend.dominio.Averia;
import es.maqui.backend.service.AveriaService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/maqui/applicationContext.xml" })
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
		Long idAveria = sut.aniadirAveria("Tubo Escape Ilegal",
				"El tubo de escape expulsa muchos gases contaminantes");
		
		assertNotNull(idAveria);
	}

	@Test
	public void testEditarAveria() {
		Long idAveria = sut.aniadirAveria("Tubo Escape Ilegal",
				"El tubo de escape expulsa muchos gases contaminantes");
		
		Averia averia = sut.obtenerAveria(idAveria);
		averia.setNombre("Tubo Escape No Legal");
		Averia averiaMod = sut.obtenerAveria(idAveria);
		assertTrue(averia.getNombre().equals(averiaMod.getNombre()));
	}

	@Test
	public void testBorrarAveria() {
		Long idAveria = sut.aniadirAveria("Tubo Escape Ilegal",
				"El tubo de escape expulsa muchos gases contaminantes");
		
		sut.borrarAveria(idAveria);
		List<Averia> averias = sut.obtenerAverias();
		assertTrue(averias.isEmpty());
	}

	@Test
	public void testListarAveria() {
		List<Averia> averiasLista = sut.obtenerAverias();
		for (Averia averiaSacada : averiasLista) {
			assertNotNull(averiaSacada.getId());
		}

	}

}