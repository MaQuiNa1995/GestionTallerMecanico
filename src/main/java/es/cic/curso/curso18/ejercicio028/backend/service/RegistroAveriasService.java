package es.cic.curso.curso18.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;
import es.cic.curso.curso18.ejercicio028.backend.dominio.RegistroAverias;

public interface RegistroAveriasService {

	Long aniadirRegistroAverias(String fecha,Averia Averia);

	RegistroAverias actualizarRegistroAverias(RegistroAverias modificada);

	void borrarRegistroAverias(Long id);

	RegistroAverias obtenerRegistroAverias(Long id);

	List<RegistroAverias> obtenerRegistroAveriass();

}