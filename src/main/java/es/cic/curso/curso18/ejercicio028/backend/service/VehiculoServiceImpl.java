package es.cic.curso.curso18.ejercicio028.backend.service;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Marca;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;
import es.cic.curso.curso18.ejercicio028.backend.repository.VehiculoRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VehiculoServiceImpl implements VehiculoService {

	@Autowired
	private VehiculoRepository claseVehiculoRepository;

	@Override
	public Long aniadirVehiculo(String nombre,String matricula,Marca marca) {

		Vehiculo claseVehiculo = new Vehiculo();

		claseVehiculo.setNombre(nombre);
		claseVehiculo.setMatricula(matricula);
		claseVehiculo.setMarca(marca);

		Vehiculo nuevo = aniadirVehiculo(claseVehiculo);

		return nuevo.getId();
	}

	private Vehiculo aniadirVehiculo(Vehiculo nueva) {
		return claseVehiculoRepository.add(nueva);
	}

	@Override
	public Vehiculo actualizarVehiculo(Vehiculo modificada) {
		return claseVehiculoRepository
				.update(modificada);
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
