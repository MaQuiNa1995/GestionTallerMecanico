package es.cic.curso.curso18.ejercicio028.backend.DTO;

import org.springframework.stereotype.Component;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;
import es.cic.curso.curso18.ejercicio028.backend.dominio.RegistroAverias;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;

@Component
public class ConverterVehiculoAveria {

	

//	public VehiculoDTO entity2DTO(Averia averia, RegistroAverias registroAverias, Vehiculo vehiculo, Marca marca,
//			TipoVehiculo tipoVehiculo) {
//
//		VehiculoDTO resultado = new VehiculoDTO();
//		resultado.setNombreAveria(averia.getNombre());
//		resultado.setFechaAveria(registroAverias.getFecha());
//		resultado.setNombreVehiculo(vehiculo.getNombre());
//		resultado.setMatriculaVehiculo(vehiculo.getNombre());
//		resultado.setNombreMarca(marca.getNombre());
//		resultado.setTipoVehiculo(tipoVehiculo.getTipo());
//
//		return resultado;
//
//	}

	public VehiculoAveriaDTO entity2DTO(Averia averia,RegistroAverias registroAverias, Vehiculo vehiculo) {

		VehiculoAveriaDTO resultado = new VehiculoAveriaDTO();
		
		resultado.setNombreAveria(averia.getNombre());
		resultado.setFechaAveria(registroAverias.getFecha());
		resultado.setNombreVehiculo(vehiculo.getNombre());
		resultado.setMatriculaVehiculo(vehiculo.getMatricula());

		return resultado;

	}
}
