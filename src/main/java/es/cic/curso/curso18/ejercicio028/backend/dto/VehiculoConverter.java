package es.cic.curso.curso18.ejercicio028.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class VehiculoConverter {
	
//	private UsuarioConverter usuarioConverter;
//	
//	public VehiculoDTO entityToDto(Historico historico, Usuario u) {
//		VehiculoDTO resultado = new VehiculoDTO();
//		resultado.setUsuario(u.getNombre());
//		resultado.setOperacion(historico.getOperacion());
//		resultado.setHora(historico.getHora());
//		resultado.setPermitido(historico.isPermitido());
//		
//		return resultado;
//		
//	}
//
//	
//	public Historico dto2Entity(VehiculoDTO historicoDTO, UsuarioDTO usuarioDTO) {
//		Historico resultado = new Historico();
//		resultado.setUsuario(usuarioConverter.dto2Entity(usuarioDTO));
//		resultado.setOperacion(historicoDTO.getOperacion());
//		resultado.setHora(historicoDTO.getHora());
//		resultado.setPermitido(historicoDTO.isPermitido());
//		return resultado;		
//	}
//	
//	public List<VehiculoDTO> entity2DTO(List<Historico> historicos, List<Usuario> usuarios) {
//		List<VehiculoDTO> resultado = new ArrayList<>();
//		for(Historico historico: historicos) {
//			for(Usuario usuario : usuarios){
//				resultado.add(entityToDto(historico, usuario));
//			}
//		}
//		return resultado;
//	}
//	
//	public List<Historico> dto2Entity(List<VehiculoDTO> historicosDTO, List<UsuarioDTO> usuariosDTO) {
//		List<Historico> resultado = new ArrayList<>();
//		for(VehiculoDTO historico: historicosDTO) {
//			for(UsuarioDTO usuario : usuariosDTO){
//			resultado.add(dto2Entity(historico, usuario));
//			}
//		};
//		return resultado;		
//	}	

}
