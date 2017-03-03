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

import es.cic.curso.curso18.ejercicio028.backend.dominio.Marca;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso18.ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class MarcaServiceImplTest {

    @PersistenceContext
	protected EntityManager entityManager;
    
	@Autowired
	private MarcaService sut;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testCrearMarca() {
		Long idMarca = sut.aniadirMarca("Skoda");
		
		assertNotNull(idMarca);
	}

	@Test
	public void testEditarMarca() {
		Long idMarca = sut.aniadirMarca("Fabia");
		
		Marca sala = sut.obtenerMarca(idMarca);
		sala.setNombre("Tubo Escape No Legal");
		Marca salaMod = sut.obtenerMarca(idMarca);
		assertTrue(sala.getNombre().equals(salaMod.getNombre()));
	}

	@Test
	public void testBorrarMarca() {
		Long idMarca = sut.aniadirMarca("Toyota");
		
		sut.borrarMarca(idMarca);
		List<Marca> salas = sut.obtenerMarcas();
		assertTrue(salas.isEmpty());
	}

	@Test
	public void testListarMarca() {
		List<Marca> salasLista = sut.obtenerMarcas();
		for (Marca salaSacada : salasLista) {
			assertNotNull(salaSacada.getId());
		}

	}

}