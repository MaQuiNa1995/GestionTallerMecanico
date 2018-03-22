package es.maqui.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.maqui.backend.dominio.Averia;
import es.maqui.backend.dominio.RegistroAverias;
import es.maqui.backend.repository.RegistroAveriasRepository;
import es.maqui.backend.service.RegistroAveriasService;

@Service
@Transactional
public class RegistroAveriasServiceImpl implements RegistroAveriasService {

	@Autowired
	private RegistroAveriasRepository claseRegistroAveriasRepository;

	@Override
	public Long aniadirRegistroAverias(String fecha, Averia averia) {

		RegistroAverias claseRegistroAverias = new RegistroAverias();

		claseRegistroAverias.setFecha(fecha);
		claseRegistroAverias.setAveria(averia);

		RegistroAverias nuevo = aniadirRegistroAverias(claseRegistroAverias);

		return nuevo.getId();
	}

	private RegistroAverias aniadirRegistroAverias(RegistroAverias nueva) {
		return claseRegistroAveriasRepository.add(nueva);
	}

	@Override
	public RegistroAverias actualizarRegistroAverias(RegistroAverias modificada) {
		return claseRegistroAveriasRepository
				.update(modificada);
	}

	@Override
	public void borrarRegistroAverias(Long id) {
		RegistroAverias objetoEliminable = obtenerRegistroAverias(id);
		claseRegistroAveriasRepository.delete(objetoEliminable);
	}

	@Override
	public RegistroAverias obtenerRegistroAverias(Long id) {
		return claseRegistroAveriasRepository.read(id);
	}

	@Override
	public List<RegistroAverias> obtenerRegistroAveriass() {
		return claseRegistroAveriasRepository.list();
	}

}
