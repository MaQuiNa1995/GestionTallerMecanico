package es.cic.curso.curso18.ejercicio028.backend.service;

import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;
import es.cic.curso.curso18.ejercicio028.backend.repository.TipoVehiculoRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TipoVehiculoServiceImpl implements TipoVehiculoService {

	@Autowired
	private TipoVehiculoRepository tipoVehiculoRepository;

	@Override
	public Long aniadirTipoVehiculo(String tipo, String descripcion, Vehiculo vehiculo) {

		TipoVehiculo claseTipoVehiculo = new TipoVehiculo();

		claseTipoVehiculo.setTipo(tipo);
		claseTipoVehiculo.setDescripcion(descripcion);
		claseTipoVehiculo.setVehiculo(vehiculo);

		TipoVehiculo nuevo = aniadirTipoVehiculo(claseTipoVehiculo);

		return nuevo.getId();
	}

	private TipoVehiculo aniadirTipoVehiculo(TipoVehiculo nueva) {
		return tipoVehiculoRepository.add(nueva);
	}

	@Override
	public TipoVehiculo actualizarTipoVehiculo(TipoVehiculo modificada) {
		return tipoVehiculoRepository.update(modificada);
	}

	@Override
	public void borrarTipoVehiculo(Long id) {
		TipoVehiculo objetoEliminable = obtenerTipoVehiculo(id);
		tipoVehiculoRepository.delete(objetoEliminable);
	}

	@Override
	public TipoVehiculo obtenerTipoVehiculo(Long id) {
		return tipoVehiculoRepository.read(id);
	}

	@Override
	public List<TipoVehiculo> obtenerTipoVehiculos() {
		return tipoVehiculoRepository.list();
	}

}