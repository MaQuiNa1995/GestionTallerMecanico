package es.maqui.backend.service;

import java.util.List;

import es.maqui.backend.dominio.Averia;

public interface AveriaService {

	Long aniadirAveria(String nombre, String descripcion);

	Averia actualizarAveria(Averia modificada);

	void borrarAveria(Long id);

	Averia obtenerAveria(Long id);

	List<Averia> obtenerAverias();

}