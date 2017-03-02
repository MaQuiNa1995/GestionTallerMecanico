package es.cic.curso.curso18.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Marca;
import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;
import es.cic.curso.curso18.ejercicio028.backend.repository.TipoVehiculoRepository;
import es.cic.curso.curso18.ejercicio028.backend.repository.IRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:es/cic/curso/curso18.ejercicio028/applicationContext.xml"})

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
        
        Vehiculo vehiculo = new Vehiculo("Fabia", "0857-ABC", new Marca("Skoda"));
        
        claseTipoVehiculo.setTipo("Coche");
        claseTipoVehiculo.setDescripcion("Esto es un coche y tiene cuatro ruedas");
        claseTipoVehiculo.setVehiculo(vehiculo);

        return claseTipoVehiculo;
    }

    @Override
    public TipoVehiculo getInstanceDeTParaLectura() {
        TipoVehiculo claseTipoVehiculo = new TipoVehiculo();
        
        Marca marca = new Marca("Skoda");
        Vehiculo vehiculo = new Vehiculo("Fabia", "0857-ABC", marca);
        
        claseTipoVehiculo.setTipo("Coche");
        claseTipoVehiculo.setDescripcion("Esto es un coche y tiene cuatro ruedas");
        claseTipoVehiculo.setVehiculo(vehiculo);

        return claseTipoVehiculo;
    }

    @Override
    public Long getClavePrimariaNoExistente() {
        return Long.MAX_VALUE;
    }

    @Override
    public TipoVehiculo getInstanceDeTParaModificar(Long clave) {
        TipoVehiculo claseTipoVehiculo = getInstanceDeTParaLectura();
        
        Marca marca = new Marca("Skoda");
        Vehiculo vehiculo = new Vehiculo("Fabia", "0857-ABC", marca);
        
        claseTipoVehiculo.setId(clave);
        claseTipoVehiculo.setTipo("Coche");
        claseTipoVehiculo.setDescripcion("Esto es un coche y tiene cuatro ruedas");
        claseTipoVehiculo.setVehiculo(vehiculo);

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
}
