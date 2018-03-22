package es.maqui.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.maqui.backend.dominio.Marca;
import es.maqui.backend.dominio.TipoVehiculo;
import es.maqui.backend.dominio.Vehiculo;
import es.maqui.backend.repository.VehiculoRepository;
import es.maqui.backend.service.VehiculoService;

@Service
@Transactional
public class VehiculoServiceImpl implements VehiculoService {

	@Autowired
	private VehiculoRepository claseVehiculoRepository;

	@Override
	public Long aniadirVehiculo(String nombre, String matricula, TipoVehiculo tipoVehiculo, Marca marca) {

		Vehiculo claseVehiculo = new Vehiculo();

		claseVehiculo.setNombre(nombre);
		claseVehiculo.setMatricula(matricula);
		claseVehiculo.setTipoVehiculo(tipoVehiculo);
		claseVehiculo.setMarca(marca);

		Vehiculo nuevo = aniadirVehiculo(claseVehiculo);

		return nuevo.getId();
	}

	private Vehiculo aniadirVehiculo(Vehiculo nueva) {
		return claseVehiculoRepository.add(nueva);
	}

	@Override
	public Vehiculo actualizarVehiculo(Vehiculo modificada) {
		return claseVehiculoRepository.update(modificada);
	}

	@Override
	public void borrarVehiculo(Long id) {
		Vehiculo objetoEliminable = obtenerVehiculo(id);
		claseVehiculoRepository.delete(objetoEliminable);
	}

	@Override
	public Vehiculo obtenerVehiculo(Long id) {
		return claseVehiculoRepository.read(id);
	}

	@Override
	public List<Vehiculo> obtenerVehiculos() {
		return claseVehiculoRepository.list();
	}

}
