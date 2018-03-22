package es.maqui.backend.repository.impl;

import org.springframework.stereotype.Repository;

import es.maqui.backend.dominio.VehiculosTienenAverias;
import es.maqui.backend.repository.VehiculosTienenAveriasRepository;

@Repository
public class VehiculosTienenAveriasRepositoryImpl extends AbstractRepositoryImpl <Long, VehiculosTienenAverias> implements VehiculosTienenAveriasRepository {

	@Override
    public Class<VehiculosTienenAverias> getClassDeT() {
        return VehiculosTienenAverias.class;
    }

	@Override
    public String getNombreTabla() {
        return "VEHICULOS_TIENEN_AVERIAS";
    }
}
