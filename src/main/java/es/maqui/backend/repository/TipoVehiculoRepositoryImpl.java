package es.maqui.backend.repository;

import org.springframework.stereotype.Repository;

import es.maqui.backend.dominio.TipoVehiculo;

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
