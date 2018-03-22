package es.maqui.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.maqui.backend.dominio.Marca;
import es.maqui.backend.repository.MarcaRepository;
import es.maqui.backend.service.MarcaService;

@Service
@Transactional
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaRepository claseMarcaRepository;

	@Override
	public Long aniadirMarca(String nombre) {

		Marca claseMarca = new Marca();

		claseMarca.setNombre(nombre);

		Marca nuevo = aniadirMarca(claseMarca);

		return nuevo.getId();
	}

	private Marca aniadirMarca(Marca nueva) {
		return claseMarcaRepository.add(nueva);
	}

	@Override
	public Marca actualizarMarca(Marca modificada) {
		return claseMarcaRepository
				.update(modificada);
	}

	@Override
	public void borrarMarca(Long id) {
		Marca objetoEliminable = obtenerMarca(id);
		claseMarcaRepository.delete(objetoEliminable);
	}

	@Override
	public Marca obtenerMarca(Long id) {
		return claseMarcaRepository.read(id);
	}

	@Override
	public List<Marca> obtenerMarcas() {
		return claseMarcaRepository.list();
	}

}
