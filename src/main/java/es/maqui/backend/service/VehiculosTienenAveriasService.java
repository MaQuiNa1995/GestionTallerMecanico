package es.maqui.backend.service;

import java.util.List;

import es.maqui.backend.dominio.RegistroAverias;
import es.maqui.backend.dominio.Vehiculo;
import es.maqui.backend.dominio.VehiculosTienenAverias;

public interface VehiculosTienenAveriasService {

	Long aniadirVehiculosTienenAverias(RegistroAverias registroAverias,
			Vehiculo vehiculo);

	VehiculosTienenAverias actualizarVehiculosTienenAverias(VehiculosTienenAverias modificada);

	void borrarVehiculosTienenAverias(Long id);

	VehiculosTienenAverias obtenerVehiculosTienenAveria(Long id);

	List<VehiculosTienenAverias> obtenerVehiculosTienenAverias();

}