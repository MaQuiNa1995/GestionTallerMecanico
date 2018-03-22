package es.maqui.backend.repository.impl;

import org.springframework.stereotype.Repository;

import es.maqui.backend.dominio.TipoVehiculo;
import es.maqui.backend.repository.TipoVehiculoRepository;

@Repository
public class TipoVehiculoRepositoryImpl extends AbstractRepositoryImpl <Long, TipoVehiculo> implements TipoVehiculoRepository {

	@Override
    public Class<TipoVehiculo> getClassDeT() {
        return TipoVehiculo.class;
    }

	@Override
    public String getNombreTabla() {
        return "TIPO_VEHICULO";
    }
}
