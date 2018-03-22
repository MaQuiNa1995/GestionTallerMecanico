package es.maqui.backend.repository.impl;

import org.springframework.stereotype.Repository;

import es.maqui.backend.dominio.RegistroAverias;
import es.maqui.backend.repository.RegistroAveriasRepository;

@Repository
public class RegistroAveriasRepositoryImpl extends AbstractRepositoryImpl <Long, RegistroAverias> implements RegistroAveriasRepository {

	@Override
    public Class<RegistroAverias> getClassDeT() {
        return RegistroAverias.class;
    }

	@Override
    public String getNombreTabla() {
        return "REGISTRO_AVERIAS";
    }
}
