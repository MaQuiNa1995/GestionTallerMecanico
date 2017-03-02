package es.cic.curso.curso18.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;

public interface TipoVehiculoService {

    TipoVehiculo actualizarTipoVehiculo(TipoVehiculo modificada);

    Long aniadirTipoVehiculo(String tipo, String descripcion, TipoVehiculo vehiculo);

    void borrarTipoVehiculo(Long id);

    TipoVehiculo obtenerTipoVehiculo(Long id);

    List<TipoVehiculo> obtenerTipoVehiculos();
}
