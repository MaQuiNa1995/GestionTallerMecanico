package es.maqui.backend.service;

import java.util.List;

import es.maqui.backend.dominio.Marca;

public interface MarcaService {

	Long aniadirMarca(String nombre);

	Marca actualizarMarca(Marca modificada);

	void borrarMarca(Long id);

	Marca obtenerMarca(Long id);

	List<Marca> obtenerMarcas();

}