package es.cic.curso.curso18.ejercicio028.backend.service;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;
import es.cic.curso.curso18.ejercicio028.backend.repository.AveriaRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AveriaServiceImpl implements AveriaService {

	@Autowired
	private AveriaRepository averiaRepository;

	@Override
	public Long aniadirAveria(String nombre, String descripcion) {

		Averia claseAveria = new Averia();

		claseAveria.setNombre(nombre);
		claseAveria.setDescripcion(descripcion);

		Averia nuevo = aniadirAveria(claseAveria);

		return nuevo.getId();
	}

	private Averia aniadirAveria(Averia nueva) {
		return averiaRepository.add(nueva);
	}

	@Override
	public Averia actualizarAveria(Averia modificada) {
		return averiaRepository.update(modificada);
	}

	@Override
	public void borrarAveria(Long id) {
		Averia objetoEliminable = obtenerAveria(id);
		averiaRepository.delete(objetoEliminable);
	}

	@Override
	public Averia obtenerAveria(Long id) {
		return averiaRepository.read(id);
	}

	@Override
	public List<Averia> obtenerAverias() {
		return averiaRepository.list();
	}

}