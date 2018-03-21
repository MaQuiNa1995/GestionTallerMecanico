package es.maqui.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.maqui.backend.dominio.TipoVehiculo;
import es.maqui.backend.repository.IRepository;
import es.maqui.backend.repository.TipoVehiculoRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/maqui/applicationContext.xml" })

public class TipoVehiculoRepositoryImplTest extends AbstractRepositoryImplTest<Long, TipoVehiculo> {

	
    @Autowired
    private TipoVehiculoRepository sut;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public TipoVehiculo getInstanceDeTParaNuevo() {
    	
        TipoVehiculo claseTipoVehiculo = new TipoVehiculo();
        
        claseTipoVehiculo.setTipo("nombreTipoVehiculo");
        claseTipoVehiculo.setDescripcion("descripcion1");
        
        return claseTipoVehiculo;
    }

    @Override
    public TipoVehiculo getInstanceDeTParaLectura() {
    	
        TipoVehiculo claseTipoVehiculo = new TipoVehiculo();
        
        claseTipoVehiculo.setTipo("nombreTipoVehiculo");
        claseTipoVehiculo.setDescripcion("descripcion1");

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
        
        return true;
    }
}











