package es.maqui.backend.repository;

import org.springframework.stereotype.Repository;

import es.maqui.backend.dominio.VehiculosTienenAverias;

@Repository
public class VehiculosTienenAveriasRepositoryImpl extends AbstractRepositoryImpl <Long, VehiculosTienenAverias> implements VehiculosTienenAveriasRepository {

	@Override
    public Class<VehiculosTienenAverias> getClassDeT() {
        return VehiculosTienenAverias.class;
    }

	@Override
    public String getNombreTabla() {
        return "VEHICULOSTIENENAVERIAS";
    }
}
