package es.maqui.backend.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import es.maqui.backend.repository.IRepository;
import es.maqui.backend.repository.Identificable;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional
public abstract class AbstractRepositoryImplTest<K extends Number, T extends Identificable<K>> {

    @PersistenceContext
    protected EntityManager em;

    public AbstractRepositoryImplTest() {
        super();
    }

    public abstract IRepository<K, T> getRepository();

    public abstract T getInstanceDeTParaNuevo();

    public abstract T getInstanceDeTParaLectura();

    public abstract boolean sonDatosIguales(T t1, T t2);

    public abstract K getClavePrimariaNoExistente();

    public abstract T getInstanceDeTParaModificar(K clave);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAdd() {
        T instancia = getInstanceDeTParaNuevo();
        assertNull(instancia.getId());

        instancia = getRepository().add(instancia);

        assertNotNull(instancia.getId());
    }

    @Test
    public void testRead() {
        K clavePrimaria = generaDatoLectura();

        T resultado = getRepository().read(clavePrimaria);

        assertTrue(sonDatosIguales(getInstanceDeTParaLectura(), resultado));
    }

    @Test(expected = PersistenceException.class)
    public void testRead_NoExiste() {
        K clavePrimaria = getClavePrimariaNoExistente();

		@SuppressWarnings("unused")
		T resultado = getRepository().read(clavePrimaria);
    }

    @Test
    public void testList() {
        generaDatoLectura();
        generaDatoLectura();
        generaDatoLectura();

        List<T> resultado = getRepository().list();

        assertTrue(resultado.size() >= 3);
    }

    @Test
    public void testUpdate() {
        K clavePrimaria = generaDatoLectura();

        T sala2 = getInstanceDeTParaModificar(clavePrimaria);

        @SuppressWarnings("unused")
		T resultado = getRepository().update(sala2);

        T enBBDD = em.find(getRepository().getClassDeT(), clavePrimaria);

        assertTrue(sonDatosIguales(getInstanceDeTParaModificar(clavePrimaria), enBBDD));
    }

    @Test
    public void testDelete() {
        K clavePrimaria = generaDatoLectura();

        getRepository().delete(clavePrimaria);
        @SuppressWarnings("rawtypes")
		Identificable c;
        try {
            c = em.find(getRepository().getClassDeT(), clavePrimaria);
        } catch (PersistenceException pe) {
            return;
        }
        assertNull(c);
    }

    private K generaDatoLectura() {
        T instancia = getInstanceDeTParaLectura();

        em.persist(instancia);

        return instancia.getId();
    }

}
