package es.cic.curso.curso18.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso18.ejercicio028.backend.dominio.RegistroAverias;

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
