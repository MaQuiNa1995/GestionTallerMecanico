package es.cic.curso.curso18.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Marca;
import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;

public interface VehiculoService {

	Long aniadirVehiculo(String nombre,String matricula,TipoVehiculo tipoVehiculo,Marca marca);

	Vehiculo actualizarVehiculo(Vehiculo modificada);

	void borrarVehiculo(Long id);

	Vehiculo obtenerVehiculo(Long id);

	List<Vehiculo> obtenerVehiculos();

}