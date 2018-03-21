package es.maqui.backend.service;

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

import es.maqui.backend.dominio.Marca;
import es.maqui.backend.service.MarcaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/maqui/applicationContext.xml" })
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

		Marca marca = sut.obtenerMarca(idMarca);
		marca.setNombre("Tubo Escape No Legal");
		Marca marcaMod = sut.obtenerMarca(idMarca);
		assertTrue(marca.getNombre().equals(marcaMod.getNombre()));
	}

	@Test
	public void testBorrarMarca() {
		Long idMarca = sut.aniadirMarca("Toyota");

		sut.borrarMarca(idMarca);
		List<Marca> marcas = sut.obtenerMarcas();
		assertTrue(marcas.isEmpty());
	}

	@Test
	public void testListarMarca() {
		List<Marca> marcasLista = sut.obtenerMarcas();
		for (Marca marcaSacada : marcasLista) {
			assertNotNull(marcaSacada.getId());
		}

	}



}
