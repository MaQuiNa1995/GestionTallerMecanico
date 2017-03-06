package es.cic.curso.curso18.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Marca;
import es.cic.curso.curso18.ejercicio028.backend.dominio.RegistroAverias;
import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.VehiculosTienenAverias;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:es/cic/curso/curso18.ejercicio028/applicationContext.xml"}
)

public class VehiculosTienenAveriasRepositoryImplTest extends AbstractRepositoryImplTest<Long, VehiculosTienenAverias> {

    @Autowired
    private VehiculosTienenAveriasRepository sut;

    private TipoVehiculo tipoVehiculo1,tipoVehiculo2,tipoVehiculo3;
    private Marca marca1,marca2,marca3;
    private Vehiculo vehiculo1,vehiculo2,vehiculo3;
    private Averia averia1,averia2,averia3;
    private RegistroAverias registroAverias1,registroAverias2,registroAverias3;
    
    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        generaBaseDatos();
    }

    @Override
    public VehiculosTienenAverias getInstanceDeTParaNuevo() {
    	
        VehiculosTienenAverias claseVehiculosTienenAverias = new VehiculosTienenAverias();
        
        claseVehiculosTienenAverias.setRegistroAverias(registroAverias1);
        claseVehiculosTienenAverias.setVehiculo(vehiculo1);

        
        return claseVehiculosTienenAverias;
    }

    @Override
    public VehiculosTienenAverias getInstanceDeTParaLectura() {
    	
        VehiculosTienenAverias claseVehiculosTienenAverias = new VehiculosTienenAverias();

        claseVehiculosTienenAverias.setVehiculo(vehiculo1);
        claseVehiculosTienenAverias.setRegistroAverias(registroAverias1);
        
        return claseVehiculosTienenAverias;
    }

    @Override
    public Long getClavePrimariaNoExistente() {
        return Long.MAX_VALUE;
    }

    @Override
    public VehiculosTienenAverias getInstanceDeTParaModificar(Long clave) {
    	
        VehiculosTienenAverias claseVehiculosTienenAverias = getInstanceDeTParaLectura();
        
        claseVehiculosTienenAverias.setId(clave);
        claseVehiculosTienenAverias.setVehiculo(vehiculo1);
        claseVehiculosTienenAverias.setRegistroAverias(registroAverias1);
        
        return claseVehiculosTienenAverias;
    }

    @Override
    public IRepository<Long, VehiculosTienenAverias> getRepository() {
        return sut;
    }

    @Override
    public boolean sonDatosIguales(VehiculosTienenAverias t1, VehiculosTienenAverias t2) {
        if (t1 == null || t2 == null) {
            throw new UnsupportedOperationException("No puedo comparar nulos");
        }
        
		if (!t1.getRegistroAverias().equals(t2.getRegistroAverias())) {
			return false;
		}
		
		if (!t1.getVehiculo().equals(t2.getVehiculo())) {
			return false;
		}
        
        return true;
    }
    
    private void generaBaseDatos(){
    	
    	tipoVehiculo1 = new TipoVehiculo("Coche23","41 Ruedas");
    	tipoVehiculo2 = new TipoVehiculo("Moto123","22 Ruedas");
    	tipoVehiculo3 = new TipoVehiculo("Sidecar123","36 Ruedas");
    	
        em.persist(tipoVehiculo1);
        em.persist(tipoVehiculo2);
        em.persist(tipoVehiculo3);
    	
        marca1 = new Marca("Opela");
        marca2 = new Marca("Citroeeen");
        marca3 = new Marca("Ferrari");
        
        em.persist(marca1);
        em.persist(marca2);
        em.persist(marca3);
        
        vehiculo1 = new Vehiculo("Ca","4444-DeR",tipoVehiculo1,marca1);
        vehiculo2 = new Vehiculo("Talbot","4666-DuR",tipoVehiculo2,marca2);
        vehiculo3 = new Vehiculo("Peugeot","2234-DtR",tipoVehiculo3,marca3);
        
        em.persist(vehiculo1);
        em.persist(vehiculo2);
        em.persist(vehiculo3);
        
    	averia1 = new Averia("Tubo Mal45454", "sale humo blanco");
    	averia2 = new Averia("Tubo Mal2313131", "expulsa");
    	averia3 = new Averia("Tubo Mal1231312321", "arrastrandose por el suelo");
    	
    	em.persist(averia1);
    	em.persist(averia2);
    	em.persist(averia3);
    	
    	registroAverias1 = new RegistroAverias("31/08/1965",averia1);
    	registroAverias2 = new RegistroAverias("3/05/193424",averia2);
    	registroAverias3 = new RegistroAverias("6/11/19129",averia3);
    	
    	em.persist(registroAverias1);
    	em.persist(registroAverias2);
    	em.persist(registroAverias3);
    }
}
