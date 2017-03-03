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

public class TipoVehiculoRepositoryImplTest extends AbstractRepositoryImplTest<Long, TipoVehiculo> {

	private Vehiculo vehiculo1,
						vehiculo2,
						vehiculo3;
	
	private Marca marca1,
					marca2,
					marca3;
	
    @Autowired
    private TipoVehiculoRepository sut;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        generaBaseDatos();
    }

    @Override
    public TipoVehiculo getInstanceDeTParaNuevo() {
    	
        TipoVehiculo claseTipoVehiculo = new TipoVehiculo();
        
        claseTipoVehiculo.setTipo("nombreTipoVehiculo");
        claseTipoVehiculo.setDescripcion("descripcion1");
        claseTipoVehiculo.setVehiculo(vehiculo1);
        
        return claseTipoVehiculo;
    }

    @Override
    public TipoVehiculo getInstanceDeTParaLectura() {
    	
        TipoVehiculo claseTipoVehiculo = new TipoVehiculo();
        
        claseTipoVehiculo.setTipo("nombreTipoVehiculo");
        claseTipoVehiculo.setDescripcion("descripcion1");
        claseTipoVehiculo.setVehiculo(vehiculo1);

        return claseTipoVehiculo;
    }

    @Override
    public Long getClavePrimariaNoExistente() {
        return Long.MAX_VALUE;
    }

    @Override
    public TipoVehiculo getInstanceDeTParaModificar(Long clave) {
    	
        TipoVehiculo claseTipoVehiculo = getInstanceDeTParaLectura();
        claseTipoVehiculo.setId(clave);
        claseTipoVehiculo.setTipo("nombreTipoVehiculo");
        claseTipoVehiculo.setDescripcion("descripcion1");
        claseTipoVehiculo.setVehiculo(vehiculo1);
        
        return claseTipoVehiculo;
    }

    @Override
    public IRepository<Long, TipoVehiculo> getRepository() {
        return sut;
    }

    @Override
    public boolean sonDatosIguales(TipoVehiculo t1, TipoVehiculo t2) {
        if (t1 == null || t2 == null) {
            throw new UnsupportedOperationException("No puedo comparar nulos");
        }
        
		if (!t1.getTipo().equals(t2.getTipo())) {
			return false;
		}
		
		if (!t1.getDescripcion().equals(t2.getDescripcion())) {
			return false;
		}
		
		if (!t1.getVehiculo().equals(t2.getVehiculo())) {
			return false;
		}
        
        return true;
    }
    
    private void generaBaseDatos(){
        marca1 = new Marca("Toyota");
        marca2 = new Marca("Skoda");
        marca3 = new Marca("Seat");
        
        em.persist(marca1);
        em.persist(marca2);
        em.persist(marca3);
        
        vehiculo1 =new Vehiculo("Corola","0387-DCC",marca1);
        vehiculo2 =new Vehiculo("Favia","0127-DRC",marca2);
        vehiculo3 =new Vehiculo("Panda","0327-DOC",marca3);
        
        em.persist(vehiculo1);
        em.persist(vehiculo2);
        em.persist(vehiculo3);
    }
}











