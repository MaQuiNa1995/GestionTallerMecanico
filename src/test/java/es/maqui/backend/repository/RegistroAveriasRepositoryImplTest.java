package es.maqui.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.maqui.backend.dominio.Averia;
import es.maqui.backend.dominio.RegistroAverias;
import es.maqui.backend.repository.IRepository;
import es.maqui.backend.repository.RegistroAveriasRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/maqui/applicationContext.xml" })

public class RegistroAveriasRepositoryImplTest extends AbstractRepositoryImplTest<Long, RegistroAverias> {

    @Autowired
    private RegistroAveriasRepository sut;
    
    private Averia averia1,averia2,averia3;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        generaBBDD();
    }

    @Override
    public RegistroAverias getInstanceDeTParaNuevo() {
    	
        RegistroAverias claseRegistroAverias = new RegistroAverias();
        
        claseRegistroAverias.setFecha("28/08/1995");
        claseRegistroAverias.setAveria(averia1);
        
        return claseRegistroAverias;
    }

    @Override
    public RegistroAverias getInstanceDeTParaLectura() {
    	
        RegistroAverias claseRegistroAverias = new RegistroAverias();
        
        claseRegistroAverias.setFecha("28/08/1995");
        claseRegistroAverias.setAveria(averia1);

        return claseRegistroAverias;
    }

    @Override
    public Long getClavePrimariaNoExistente() {
        return Long.MAX_VALUE;
    }

    @Override
    public RegistroAverias getInstanceDeTParaModificar(Long clave) {
    	
        RegistroAverias claseRegistroAverias = getInstanceDeTParaLectura();
        
        claseRegistroAverias.setId(clave);
        claseRegistroAverias.setFecha("28/08/1995");
        claseRegistroAverias.setAveria(averia1);
        
        return claseRegistroAverias;
    }

    @Override
    public IRepository<Long, RegistroAverias> getRepository() {
        return sut;
    }

    @Override
    public boolean sonDatosIguales(RegistroAverias t1, RegistroAverias t2) {
        if (t1 == null || t2 == null) {
            throw new UnsupportedOperationException("No puedo comparar nulos");
        }
        
		if (!t1.getFecha().equals(t2.getFecha())) {
			return false;
		}
		
		if (!t1.getAveria().equals(t2.getAveria())) {
			return false;
		}
        
        return true;
    }
    
    private void generaBBDD(){
    	averia1 = new Averia("Tubo Mal", "Tubo Roto");
    	averia2 = new Averia("Tubo Mal2", "Expulsa humo Negro");
    	averia3 = new Averia("Tubo Mal3", "Se Cae a Pedazos");
    	
    	em.persist(averia1);
    	em.persist(averia2);
    	em.persist(averia3);
    }
}
