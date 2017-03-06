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
import es.cic.curso.curso18.ejercicio028.backend.dominio.Marca;
import es.cic.curso.curso18.ejercicio028.backend.dominio.RegistroAverias;
import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.VehiculosTienenAverias;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso18.ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class VehiculosTienenAveriasServiceImplTest {

	@PersistenceContext
	protected EntityManager em;
	@Autowired
	private VehiculosTienenAveriasService sut;

	private TipoVehiculo tipoVehiculo1, tipoVehiculo2, tipoVehiculo3;

	private Marca marca1, marca2, marca3;

	private Vehiculo vehiculo1, vehiculo2, vehiculo3;

	private Averia averia1, averia2, averia3;

	private RegistroAverias registroAverias1, registroAverias2, registroAverias3;

	@Before
	public void setUp() throws Exception {
		generaBaseDatos();
	}

	@Test
	public void testCrearVehiculosTienenAverias() {
		Long idAveria = sut.aniadirVehiculosTienenAverias(registroAverias1, vehiculo1);

		assertNotNull(idAveria);
	}

	@Test
	public void testEditarVehiculosTienenAverias() {
		Long idAveria = sut.aniadirVehiculosTienenAverias(registroAverias1, vehiculo1);

		VehiculosTienenAverias averia = sut.obtenerVehiculosTienenAveria(idAveria);

		averia.setVehiculo(vehiculo1);
		averia.setRegistroAverias(registroAverias1);

		VehiculosTienenAverias averiaMod = sut.obtenerVehiculosTienenAveria(idAveria);
		assertTrue(averia.getRegistroAverias().equals(averiaMod.getRegistroAverias()));
	}

	@Test
	public void testBorrarVehiculosTienenAverias() {
		Long idAveria = sut.aniadirVehiculosTienenAverias(registroAverias1, vehiculo1);

		sut.borrarVehiculosTienenAverias(idAveria);
		List<VehiculosTienenAverias> averias = sut.obtenerVehiculosTienenAverias();
		assertTrue(averias.isEmpty());
	}

	@Test
	public void testListarVehiculosTienenAverias() {
		List<VehiculosTienenAverias> averiasLista = sut.obtenerVehiculosTienenAverias();
		for (VehiculosTienenAverias averiaSacada : averiasLista) {
			assertNotNull(averiaSacada.getId());
		}

	}

	private void generaBaseDatos() {

		tipoVehiculo1 = new TipoVehiculo("Coche23", "41 Ruedas");

		tipoVehiculo2 = new TipoVehiculo("Moto123", "22 Ruedas");

		tipoVehiculo3 = new TipoVehiculo("Sidecar123", "36 Ruedas");

		em.persist(tipoVehiculo1);

		em.persist(tipoVehiculo2);

		em.persist(tipoVehiculo3);

		marca1 = new Marca("Opela");

		marca2 = new Marca("Citroeeen");

		marca3 = new Marca("Ferrari");

		em.persist(marca1);

		em.persist(marca2);

		em.persist(marca3);

		vehiculo1 = new Vehiculo("Ca", "4444-DeR", tipoVehiculo1, marca1);

		vehiculo2 = new Vehiculo("Talbot", "4666-DuR", tipoVehiculo2, marca2);

		vehiculo3 = new Vehiculo("Peugeot", "2234-DtR", tipoVehiculo3, marca3);

		em.persist(vehiculo1);

		em.persist(vehiculo2);

		em.persist(vehiculo3);

		averia1 = new Averia("Tubo Mal45454", "sale humo blanco");

		averia2 = new Averia("Tubo Mal2313131", "expulsa");

		averia3 = new Averia("Tubo Mal1231312321", "arrastrandose por el suelo");

		em.persist(averia1);

		em.persist(averia2);

		em.persist(averia3);

		registroAverias1 = new RegistroAverias("31/08/1965", averia1);

		registroAverias2 = new RegistroAverias("3/05/193424", averia2);

		registroAverias3 = new RegistroAverias("6/11/19129", averia3);

		em.persist(registroAverias1);

		em.persist(registroAverias2);

		em.persist(registroAverias3);

		em.flush();

	}

}