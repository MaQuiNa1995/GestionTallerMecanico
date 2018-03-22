package es.maqui.backend.repository.impl;

import org.springframework.stereotype.Repository;

import es.maqui.backend.dominio.Vehiculo;
import es.maqui.backend.repository.VehiculoRepository;

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
