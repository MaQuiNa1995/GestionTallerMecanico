package es.cic.curso.curso18.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Marca;
import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:es/cic/curso/curso18.ejercicio028/applicationContext.xml"}
)

public class VehiculoRepositoryImplTest extends AbstractRepositoryImplTest<Long, Vehiculo> {

	
	private Marca marca1,
					marca2,
					marca3;
	private TipoVehiculo tipoVehiculo1,
						 tipoVehiculo2,
						 tipoVehiculo3;
	
    @Autowired
    private VehiculoRepository sut;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        generaBaseDatos();
    }

    @Override
    public Vehiculo getInstanceDeTParaNuevo() {
    	
        Vehiculo claseVehiculo = new Vehiculo();
        
        claseVehiculo.setNombre("nombreVehiculo1");
        claseVehiculo.setMatricula("matricula1");
        claseVehiculo.setTipoVehiculo(tipoVehiculo1);
        claseVehiculo.setMarca(marca1);
        
        return claseVehiculo;
    }

    @Override
    public Vehiculo getInstanceDeTParaLectura() {
    	
        Vehiculo claseVehiculo = new Vehiculo();
        
        claseVehiculo.setNombre("nombreVehiculo1");
        claseVehiculo.setMatricula("matricula1");
        claseVehiculo.setTipoVehiculo(tipoVehiculo1);
        claseVehiculo.setMarca(marca1);

        return claseVehiculo;
    }

    @Override
    public Long getClavePrimariaNoExistente() {
        return Long.MAX_VALUE;
    }

    @Override
    public Vehiculo getInstanceDeTParaModificar(Long clave) {
    	
        Vehiculo claseVehiculo = getInstanceDeTParaLectura();
        claseVehiculo.setId(clave);
        claseVehiculo.setNombre("nombreVehiculo1");
        claseVehiculo.setMatricula("matricula1");
        claseVehiculo.setTipoVehiculo(tipoVehiculo1);
        claseVehiculo.setMarca(marca1);
        
        return claseVehiculo;
    }

    @Override
    public IRepository<Long, Vehiculo> getRepository() {
        return sut;
    }

    @Override
    public boolean sonDatosIguales(Vehiculo t1, Vehiculo t2) {
        if (t1 == null || t2 == null) {
            throw new UnsupportedOperationException("No puedo comparar nulos");
        }
        
		if (!t1.getNombre().equals(t2.getNombre())) {
			return false;
		}
		
		if (!t1.getMatricula().equals(t2.getMatricula())) {
			return false;
		}
		
		if (!t1.getTipoVehiculo().equals(t2.getTipoVehiculo())) {
			return false;
		}
		
		if (!t1.getMarca().equals(t2.getMarca())) {
			return false;
		}
        
        return true;
    }
    
    private void generaBaseDatos(){
    	
    	tipoVehiculo1 = new TipoVehiculo("Coche","4 Ruedas");
    	tipoVehiculo2 = new TipoVehiculo("Moto","2 Ruedas");
    	tipoVehiculo3 = new TipoVehiculo("Sidecar","3 Ruedas");
    	
        em.persist(tipoVehiculo1);
        em.persist(tipoVehiculo2);
        em.persist(tipoVehiculo3);
    	
        marca1 = new Marca("Toyota");
        marca2 = new Marca("Skoda");
        marca3 = new Marca("Seat");
        
        em.persist(marca1);
        em.persist(marca2);
        em.persist(marca3);
    }
}











