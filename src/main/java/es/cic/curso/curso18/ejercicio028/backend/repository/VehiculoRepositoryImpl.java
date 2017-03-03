package es.cic.curso.curso18.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;

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
