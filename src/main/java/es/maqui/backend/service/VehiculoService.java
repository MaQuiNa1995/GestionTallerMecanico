package es.maqui.backend.service;

import java.util.List;

import es.maqui.backend.dominio.Marca;
import es.maqui.backend.dominio.TipoVehiculo;
import es.maqui.backend.dominio.Vehiculo;

public interface VehiculoService {

	Long aniadirVehiculo(String nombre,String matricula,TipoVehiculo tipoVehiculo,Marca marca);

	Vehiculo actualizarVehiculo(Vehiculo modificada);

	void borrarVehiculo(Long id);

	Vehiculo obtenerVehiculo(Long id);

	List<Vehiculo> obtenerVehiculos();

}