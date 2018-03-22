package es.maqui.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.maqui.backend.dominio.Averia;
import es.maqui.backend.repository.AveriaRepository;
import es.maqui.backend.service.AveriaService;

@Service
@Transactional
public class AveriaServiceImpl implements AveriaService {

	@Autowired
	private AveriaRepository claseAveriaRepository;

	@Override
	public Long aniadirAveria(String nombre, String descripcion) {

		Averia claseAveria = new Averia();

		claseAveria.setNombre(nombre);
		claseAveria.setDescripcion(descripcion);

		Averia nuevo = aniadirAveria(claseAveria);

		return nuevo.getId();
	}

	private Averia aniadirAveria(Averia nueva) {
		return claseAveriaRepository.add(nueva);
	}

	@Override
	public Averia actualizarAveria(Averia modificada) {
		return claseAveriaRepository.update(modificada);
	}

	@Override
	public void borrarAveria(Long id) {
		Averia objetoEliminable = obtenerAveria(id);
		claseAveriaRepository.delete(objetoEliminable);
	}

	@Override
	public Averia obtenerAveria(Long id) {
		return claseAveriaRepository.read(id);
	}

	@Override
	public List<Averia> obtenerAverias() {
		return claseAveriaRepository.list();
	}

}
