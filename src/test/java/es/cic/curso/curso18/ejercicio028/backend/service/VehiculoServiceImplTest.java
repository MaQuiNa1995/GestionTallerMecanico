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
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso18.ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class VehiculoServiceImplTest {

    @PersistenceContext
	protected EntityManager entityManager;
    
	@Autowired
	private VehiculoService sut;
	
	private Marca marca1, marca2, marca3;

	@Before
	public void setUp() throws Exception {
		generabaseDatos();
	}

	@Test
	public void testCrearVehiculo() {
		Long idVehiculo = sut.aniadirVehiculo("Corolla", "0387-DCC", marca1);
		
		assertNotNull(idVehiculo);
	}

	@Test
	public void testEditarVehiculo() {
		Long idVehiculo = sut.aniadirVehiculo("Corolla", "0387-DCC", marca1);
		
		Vehiculo tipoVehiculo = sut.obtenerVehiculo(idVehiculo);
		tipoVehiculo.setNombre("Prius");
		Vehiculo tipoVehiculoMod = sut.obtenerVehiculo(idVehiculo);
		assertTrue(tipoVehiculo.getNombre().equals(tipoVehiculoMod.getNombre()));
	}

	@Test
	public void testBorrarVehiculo() {
		Long idVehiculo = sut.aniadirVehiculo("Corolla", "0387-DCC", marca1);
		
		sut.borrarVehiculo(idVehiculo);
		List<Vehiculo> tipoVehiculos = sut.obtenerVehiculos();
		assertTrue(tipoVehiculos.isEmpty());
	}

	@Test
	public void testListarVehiculo() {
		List<Vehiculo> tipoVehiculosLista = sut.obtenerVehiculos();
		for (Vehiculo tipoVehiculoSacada : tipoVehiculosLista) {
			assertNotNull(tipoVehiculoSacada.getId());
		}

	}
	
	private void generabaseDatos() {
		marca1 = new Marca("Toyota");
		marca2 = new Marca("Peugeot");
		marca3 = new Marca("Citroen");

		entityManager.persist(marca1);
		entityManager.persist(marca2);
		entityManager.persist(marca3);
	}

}