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

import es.maqui.backend.dominio.TipoVehiculo;
import es.maqui.backend.service.TipoVehiculoService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/maqui/applicationContext.xml" })
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
		Long idTipoVehiculo = sut.aniadirTipoVehiculo("Coche","Vehiculo De 4 Ruedas");
		
		assertNotNull(idTipoVehiculo);
	}

	@Test
	public void testEditarTipoVehiculo() {
		Long idTipoVehiculo = sut.aniadirTipoVehiculo("Coche","Vehiculo De 4 Ruedas");
		
		TipoVehiculo tipoVehiculo = sut.obtenerTipoVehiculo(idTipoVehiculo);
		tipoVehiculo.setTipo("Camion");
		TipoVehiculo tipoVehiculoMod = sut.obtenerTipoVehiculo(idTipoVehiculo);
		assertTrue(tipoVehiculo.getTipo().equals(tipoVehiculoMod.getTipo()));
	}

	@Test
	public void testBorrarTipoVehiculo() {
		Long idTipoVehiculo = sut.aniadirTipoVehiculo("Coche","Vehiculo De 4 Ruedas");
		
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

}