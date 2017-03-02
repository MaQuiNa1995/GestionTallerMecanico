package es.cic.curso.curso18.ejercicio028.backend.service;

import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TipoVehiculoServiceImpl implements TipoVehiculoService {

	@Autowired
	private TipoVehiculoRepository averiaRepository;

	@Override
	public Long aniadirTipoVehiculo(String nombre, String descripcion) {

		TipoVehiculo claseTipoVehiculo = new TipoVehiculo();

		claseTipoVehiculo.setNombre(nombre);
		claseTipoVehiculo.setDescripcion(descripcion);

		TipoVehiculo nuevo = aniadirTipoVehiculo(claseTipoVehiculo);

		return nuevo.getId();
	}

	private TipoVehiculo aniadirTipoVehiculo(TipoVehiculo nueva) {
		return averiaRepository.add(nueva);
	}

	@Override
	public TipoVehiculo actualizarTipoVehiculo(TipoVehiculo modificada) {
		return averiaRepository.update(modificada);
	}

	@Override
	public void borrarTipoVehiculo(Long id) {
		TipoVehiculo objetoEliminable = obtenerTipoVehiculo(id);
		averiaRepository.delete(objetoEliminable);
	}

	@Override
	public TipoVehiculo obtenerTipoVehiculo(Long id) {
		return averiaRepository.read(id);
	}

	@Override
	public List<TipoVehiculo> obtenerTipoVehiculos() {
		return averiaRepository.list();
	}

}