package es.maqui.backend.service;

import java.util.List;

import es.maqui.backend.dominio.TipoVehiculo;

public interface TipoVehiculoService {

	Long aniadirTipoVehiculo(String tipo, String descripcion);

	TipoVehiculo actualizarTipoVehiculo(TipoVehiculo modificada);

	void borrarTipoVehiculo(Long id);

	TipoVehiculo obtenerTipoVehiculo(Long id);

	List<TipoVehiculo> obtenerTipoVehiculos();

}