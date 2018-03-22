package es.maqui.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.maqui.backend.dominio.RegistroAverias;
import es.maqui.backend.dominio.Vehiculo;
import es.maqui.backend.dominio.VehiculosTienenAverias;
import es.maqui.backend.repository.VehiculosTienenAveriasRepository;
import es.maqui.backend.service.VehiculosTienenAveriasService;

@Service
@Transactional
public class VehiculosTienenAveriasServiceImpl implements VehiculosTienenAveriasService {

	@Autowired
	private VehiculosTienenAveriasRepository claseVehiculosTienenAveriasRepository;

	@Override
	public Long aniadirVehiculosTienenAverias(RegistroAverias registroAverias, Vehiculo vehiculo) {

		VehiculosTienenAverias claseVehiculosTienenAverias = new VehiculosTienenAverias();

		claseVehiculosTienenAverias.setVehiculo(vehiculo);
		claseVehiculosTienenAverias.setRegistroAverias(registroAverias);

		VehiculosTienenAverias nuevo = aniadirVehiculosTienenAverias(claseVehiculosTienenAverias);

		return nuevo.getId();
	}

	private VehiculosTienenAverias aniadirVehiculosTienenAverias(VehiculosTienenAverias nueva) {
		return claseVehiculosTienenAveriasRepository.add(nueva);
	}

	@Override
	public VehiculosTienenAverias actualizarVehiculosTienenAverias(VehiculosTienenAverias modificada) {
		return claseVehiculosTienenAveriasRepository.update(modificada);
	}

	@Override
	public void borrarVehiculosTienenAverias(Long id) {
		VehiculosTienenAverias objetoEliminable = obtenerVehiculosTienenAveria(id);
		claseVehiculosTienenAveriasRepository.delete(objetoEliminable.getId());
	}

	@Override
	public VehiculosTienenAverias obtenerVehiculosTienenAveria(Long id) {
		return claseVehiculosTienenAveriasRepository.read(id);
	}

	@Override
	public List<VehiculosTienenAverias> obtenerVehiculosTienenAverias() {
		return claseVehiculosTienenAveriasRepository.list();
	}

}
