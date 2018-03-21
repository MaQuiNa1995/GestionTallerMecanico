package es.maqui.backend.repository;

import org.springframework.stereotype.Repository;

import es.maqui.backend.dominio.RegistroAverias;

@Repository
public class RegistroAveriasRepositoryImpl extends AbstractRepositoryImpl <Long, RegistroAverias> implements RegistroAveriasRepository {

	@Override
    public Class<RegistroAverias> getClassDeT() {
        return RegistroAverias.class;
    }

	@Override
    public String getNombreTabla() {
        return "REGISTROAVERIAS";
    }
}
