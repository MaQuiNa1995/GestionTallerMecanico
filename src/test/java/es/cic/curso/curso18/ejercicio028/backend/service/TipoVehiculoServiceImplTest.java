package es.cic.curso.curso18.ejercicio028.backend.service;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Marca;
import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;
import es.cic.curso.curso18.ejercicio028.backend.service.TipoVehiculoService;

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
public class TipoVehiculoServiceImplTest {

	@PersistenceContext
	protected EntityManager entityManager;
	@Autowired
	private TipoVehiculoService sut;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testCrearTipoVehiculo() {

		Marca marca = new Marca("Toyota");
		Vehiculo vehiculo = new Vehiculo("Corola", "0387-DCC", marca);
		Long idTipoVehiculo = sut.aniadirTipoVehiculo("Coche", "Vehiculo De Cuatro Ruedas", vehiculo);

		assertNotNull(idTipoVehiculo);
	}

	@Test
	public void testEditarTipoVehiculo() {

		Marca marca = new Marca("Toyota");
		Vehiculo vehiculo = new Vehiculo("Corola", "0387-DCC", marca);
		Long idTipoVehiculo = sut.aniadirTipoVehiculo("Motocicleta", "Vehiculo De Cuatro Ruedas", vehiculo);

		TipoVehiculo sala = sut.obtenerTipoVehiculo(idTipoVehiculo);
		sala.setTipo("Coche");
		TipoVehiculo salaMod = sut.obtenerTipoVehiculo(idTipoVehiculo);
		assertTrue(sala.getTipo().equals(salaMod.getTipo()));
	}

	@Test
	public void testBorrarTipoVehiculo() {
		
		Marca marca = new Marca("Toyota");
		Vehiculo vehiculo = new Vehiculo("Corola", "0387-DCC", marca);
		Long idTipoVehiculo = sut.aniadirTipoVehiculo("Motocicleta", "Vehiculo De Cuatro Ruedas", vehiculo);
		
		sut.borrarTipoVehiculo(idTipoVehiculo);
		
		List<TipoVehiculo> salas = sut.obtenerTipoVehiculos();
		
		assertTrue(salas.isEmpty());
	}

	@Test
	public void testListarTipoVehiculo() {
		List<TipoVehiculo> vehiculosLista = sut.obtenerTipoVehiculos();
		for (TipoVehiculo vehiculoSacado : vehiculosLista) {
			assertNotNull(vehiculoSacado.getId());
		}

	}

}