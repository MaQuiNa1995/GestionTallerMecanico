package es.maqui.backend.service;

import java.util.List;

import es.maqui.backend.dominio.Averia;
import es.maqui.backend.dominio.RegistroAverias;

public interface RegistroAveriasService {

	Long aniadirRegistroAverias(String fecha,Averia averia);

	RegistroAverias actualizarRegistroAverias(RegistroAverias modificada);

	void borrarRegistroAverias(Long id);

	RegistroAverias obtenerRegistroAverias(Long id);

	List<RegistroAverias> obtenerRegistroAveriass();

}