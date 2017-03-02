package es.cic.curso.curso18.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;

@Repository
public class TipoVehiculoRepositoryImpl extends AbstractRepositoryImpl <Long, TipoVehiculo> implements TipoVehiculoRepository {

	@Override
    public Class<TipoVehiculo> getClassDeT() {
        return TipoVehiculo.class;
    }

	@Override
    public String getNombreTabla() {
        return "TIPOVEHICULO";
    }
}
