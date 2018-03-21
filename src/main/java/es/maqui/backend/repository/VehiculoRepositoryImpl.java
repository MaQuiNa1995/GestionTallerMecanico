package es.maqui.backend.repository;

import org.springframework.stereotype.Repository;

import es.maqui.backend.dominio.Vehiculo;

@Repository
public class VehiculoRepositoryImpl extends AbstractRepositoryImpl <Long, Vehiculo> implements VehiculoRepository {

	@Override
    public Class<Vehiculo> getClassDeT() {
        return Vehiculo.class;
    }

	@Override
    public String getNombreTabla() {
        return "VEHICULO";
    }
}
