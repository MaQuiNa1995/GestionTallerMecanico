package es.cic.curso.curso18.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:es/cic/curso/curso18.ejercicio028/applicationContext.xml"}
)

public class AveriaRepositoryImplTest extends AbstractRepositoryImplTest<Long, Averia> {

    @Autowired
    private AveriaRepository sut;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public Averia getInstanceDeTParaNuevo() {
    	
        Averia claseAveria = new Averia();
        
        claseAveria.setNombre("nombreAveria");
        claseAveria.setDescripcion("descripcion1");
        
        return claseAveria;
    }

    @Override
    public Averia getInstanceDeTParaLectura() {
    	
        Averia claseAveria = new Averia();
        
        claseAveria.setNombre("nombreAveria");
        claseAveria.setDescripcion("descripcion1");

        return claseAveria;
    }

    @Override
    public Long getClavePrimariaNoExistente() {
        return Long.MAX_VALUE;
    }

    @Override
    public Averia getInstanceDeTParaModificar(Long clave) {
    	
        Averia claseAveria = getInstanceDeTParaLectura();
        
        claseAveria.setId(clave);
        claseAveria.setNombre("nombreAveria");
        claseAveria.setDescripcion("descripcion1");
        
        return claseAveria;
    }

    @Override
    public IRepository<Long, Averia> getRepository() {
        return sut;
    }

    @Override
    public boolean sonDatosIguales(Averia t1, Averia t2) {
        if (t1 == null || t2 == null) {
            throw new UnsupportedOperationException("No puedo comparar nulos");
        }
        
		if (!t1.getNombre().equals(t2.getNombre())) {
			return false;
		}
		
		if (!t1.getDescripcion().equals(t2.getDescripcion())) {
			return false;
		}
        
        return true;
    }
}
