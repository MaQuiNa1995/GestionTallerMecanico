package es.cic.curso.curso18.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Dummie;
import es.cic.curso.curso18.ejercicio028.backend.repository.ClaseDummieRepository;
import es.cic.curso.curso18.ejercicio028.backend.repository.IRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:es/cic/curso/curso18.ejercicio028/applicationContext.xml"})

public class ClaseDummieRepositoryImplTest extends AbstractRepositoryImplTest<Long, Dummie> {

    @Autowired
    private ClaseDummieRepository sut;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public Dummie getInstanceDeTParaNuevo() {
        Dummie claseDummie = new Dummie();
        claseDummie.setPalabra("OnePiece");
        claseDummie.setApagado(true);
        claseDummie.setNumero(776);
        claseDummie.setNumeroGran(5);
        claseDummie.setNumeroComas(5f);

        return claseDummie;
    }

    @Override
    public Dummie getInstanceDeTParaLectura() {
        Dummie claseDummie = new Dummie();
        claseDummie.setPalabra("OnePiece");
        claseDummie.setApagado(true);
        claseDummie.setNumero(776);
        claseDummie.setNumeroGran(5);
        claseDummie.setNumeroComas(5f);

        return claseDummie;
    }

    @Override
    public Long getClavePrimariaNoExistente() {
        return Long.MAX_VALUE;
    }

    @Override
    public Dummie getInstanceDeTParaModificar(Long clave) {
        Dummie claseDummie = getInstanceDeTParaLectura();
        claseDummie.setId(clave);
        claseDummie.setPalabra("Monogatari");
        return claseDummie;
    }

    @Override
    public IRepository<Long, Dummie> getRepository() {
        return sut;
    }

    @Override
    public boolean sonDatosIguales(Dummie t1, Dummie t2) {
        if (t1 == null || t2 == null) {
            throw new UnsupportedOperationException("No puedo comparar nulos");
        }
        /**
        Esto es porque son Strings para numeros y tal usar el metodo comentado
        */
        if (!t1.getPalabra().equals(t2.getPalabra())) {
            return false;
        }
        return true;
    }
    //	@Override
//	public boolean sonDatosIguales(Anime t1, Anime t2) {
//		if (t1 == null || t2 == null) {
//			throw new UnsupportedOperationException("No puedo comparar nulos");
//		}
//		if (t1.getNombre() != t2.getNombre()) {
//			return false;
//		}
//		if (t1.getGenero() != t2.getGenero()) {
//			return false;
//		}
//		if (t1.getCapitulos() != t2.getCapitulos()) {
//			return false;
//		}
//		if (t1.getTemporadas() != t2.getTemporadas()) {
//			return false;
//		}
//		if (t1.getValoracion() != t2.getValoracion()) {
//			return false;
//		}
//		if (t1.getEnEmision() != t2.getEnEmision()) {
//			return false;
//		}
//		return true;
//	}
}
