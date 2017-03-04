package es.cic.curso.curso18.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;

public interface TipoVehiculoService {

	Long aniadirTipoVehiculo(String tipo, String descripcion, Vehiculo vehiculo);

	TipoVehiculo actualizarTipoVehiculo(TipoVehiculo modificada);

	void borrarTipoVehiculo(Long id);

	TipoVehiculo obtenerTipoVehiculo(Long id);

	List<TipoVehiculo> obtenerTipoVehiculos();

}