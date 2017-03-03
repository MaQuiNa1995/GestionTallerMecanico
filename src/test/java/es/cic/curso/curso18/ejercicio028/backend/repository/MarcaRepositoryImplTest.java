package es.cic.curso.curso18.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Marca;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:es/cic/curso/curso18.ejercicio028/applicationContext.xml"}
)

public class MarcaRepositoryImplTest extends AbstractRepositoryImplTest<Long, Marca> {

    @Autowired
    private MarcaRepository sut;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public Marca getInstanceDeTParaNuevo() {
    	
        Marca claseMarca = new Marca();
        
        claseMarca.setNombre("nombreMarca");
        
        return claseMarca;
    }

    @Override
    public Marca getInstanceDeTParaLectura() {
    	
        Marca claseMarca = new Marca();
        
        claseMarca.setNombre("nombreMarca");

        return claseMarca;
    }

    @Override
    public Long getClavePrimariaNoExistente() {
        return Long.MAX_VALUE;
    }

    @Override
    public Marca getInstanceDeTParaModificar(Long clave) {
    	
        Marca claseMarca = getInstanceDeTParaLectura();
        
        claseMarca.setId(clave);
        claseMarca.setNombre("nombreMarca");
        
        return claseMarca;
    }

    @Override
    public IRepository<Long, Marca> getRepository() {
        return sut;
    }

    @Override
    public boolean sonDatosIguales(Marca t1, Marca t2) {
        if (t1 == null || t2 == null) {
            throw new UnsupportedOperationException("No puedo comparar nulos");
        }
        
		if (!t1.getNombre().equals(t2.getNombre())) {
			return false;
		}
        
        return true;
    }
}
