package es.cic.curso.curso18.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;

public interface AveriaSerive {

	Long aniadirAveria(String nombre, String descripcion);

	Averia actualizarAveria(Averia modificada);

	void borrarAveria(Long id);

	Averia obtenerAveria(Long id);

	List<Averia> obtenerAverias();

}