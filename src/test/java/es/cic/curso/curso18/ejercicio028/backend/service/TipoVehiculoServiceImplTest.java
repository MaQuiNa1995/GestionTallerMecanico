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
import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso18.ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class TipoVehiculoServiceImplTest {

    @PersistenceContext
	protected EntityManager entityManager;
    
	@Autowired
	private TipoVehiculoService sut;
	
	private Marca marca1, marca2, marca3;
	private Vehiculo vehiculo1, vehiculo2, vehiculo3;

	@Before
	public void setUp() throws Exception {
		generabaseDatos();
	}

	@Test
	public void testCrearTipoVehiculo() {
		Long idTipoVehiculo = sut.aniadirTipoVehiculo("Coche","Vehiculo De 4 Ruedas",vehiculo1);
		
		assertNotNull(idTipoVehiculo);
	}

	@Test
	public void testEditarTipoVehiculo() {
		Long idTipoVehiculo = sut.aniadirTipoVehiculo("Coche","Vehiculo De 4 Ruedas",vehiculo1);
		
		TipoVehiculo tipoVehiculo = sut.obtenerTipoVehiculo(idTipoVehiculo);
		tipoVehiculo.setTipo("Camion");
		TipoVehiculo tipoVehiculoMod = sut.obtenerTipoVehiculo(idTipoVehiculo);
		assertTrue(tipoVehiculo.getTipo().equals(tipoVehiculoMod.getTipo()));
	}

	@Test
	public void testBorrarTipoVehiculo() {
		Long idTipoVehiculo = sut.aniadirTipoVehiculo("Coche","Vehiculo De 4 Ruedas",vehiculo1);
		
		sut.borrarTipoVehiculo(idTipoVehiculo);
		List<TipoVehiculo> tipoVehiculos = sut.obtenerTipoVehiculos();
		assertTrue(tipoVehiculos.isEmpty());
	}

	@Test
	public void testListarTipoVehiculo() {
		List<TipoVehiculo> tipoVehiculosLista = sut.obtenerTipoVehiculos();
		for (TipoVehiculo tipoVehiculoSacada : tipoVehiculosLista) {
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
		
		vehiculo1 = new Vehiculo("Corolla", "0387-DCC", marca1);
		vehiculo2 = new Vehiculo("Corolla", "8485-DCC", marca2);
		vehiculo3 = new Vehiculo("Corolla", "5868-DCC", marca3);
		
		entityManager.persist(vehiculo1);
		entityManager.persist(vehiculo2);
		entityManager.persist(vehiculo3);
	}

}