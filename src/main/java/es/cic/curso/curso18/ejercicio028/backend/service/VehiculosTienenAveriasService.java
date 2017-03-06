package es.cic.curso.curso18.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso18.ejercicio028.backend.dominio.VehiculosTienenAverias;
import es.cic.curso.curso18.ejercicio028.backend.dominio.RegistroAverias;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;

public interface VehiculosTienenAveriasService {

	Long aniadirVehiculosTienenAverias(RegistroAverias registroAverias,
			Vehiculo vehiculo);

	VehiculosTienenAverias actualizarVehiculosTienenAverias(VehiculosTienenAverias modificada);

	void borrarVehiculosTienenAverias(Long id);

	VehiculosTienenAverias obtenerVehiculosTienenAveria(Long id);

	List<VehiculosTienenAverias> obtenerVehiculosTienenAverias();

}