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
import es.maqui.backend.dominio.RegistroAverias;
import es.maqui.backend.service.RegistroAveriasService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/maqui/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class RegistroAveriasServiceImplTest {

    @PersistenceContext
	protected EntityManager entityManager;
	@Autowired
	private RegistroAveriasService sut;
	
	private Averia averia1,averia2,averia3;

	@Before
	public void setUp() throws Exception {
		generaBBDD();
	}

	@Test
	public void testCrearRegistroAverias() {
		Long idRegistroAverias = sut.aniadirRegistroAverias("15/08/1995",averia1);
		
		assertNotNull(idRegistroAverias);
	}

	@Test
	public void testEditarRegistroAverias() {
		Long idRegistroAverias = sut.aniadirRegistroAverias("15/08/1995",averia1);
		
		
		RegistroAverias RegistroAverias = sut.obtenerRegistroAverias(idRegistroAverias);
		RegistroAverias.setAveria(averia1);
		RegistroAverias RegistroAveriasMod = sut.obtenerRegistroAverias(idRegistroAverias);
		assertTrue(RegistroAverias.getFecha().equals(RegistroAveriasMod.getFecha()));
	}

	@Test
	public void testBorrarRegistroAverias() {
		Long idRegistroAverias = sut.aniadirRegistroAverias("15/08/1995",averia1);
		
		sut.borrarRegistroAverias(idRegistroAverias);
		List<RegistroAverias> RegistroAveriass = sut.obtenerRegistroAveriass();
		assertTrue(RegistroAveriass.isEmpty());
	}

	@Test
	public void testListarRegistroAverias() {
		List<RegistroAverias> RegistroAveriassLista = sut.obtenerRegistroAveriass();
		for (RegistroAverias RegistroAveriasSacada : RegistroAveriassLista) {
			assertNotNull(RegistroAveriasSacada.getId());
		}

	}
	
	private void generaBBDD(){
		averia1=new Averia("Tubo Escape Ilegal","descripcion");
		averia2=new Averia("Tubo Escape Ilegal2","descripcion");
		averia3=new Averia("Tubo Escape Ilegal3","descripcion");
		
		entityManager.persist(averia1);
		entityManager.persist(averia2);
		entityManager.persist(averia3);
	}

}