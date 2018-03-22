package es.maqui.backend.repository.impl;

import org.springframework.stereotype.Repository;

import es.maqui.backend.dominio.Averia;
import es.maqui.backend.repository.AveriaRepository;

@Repository
public class AveriaRepositoryImpl extends AbstractRepositoryImpl <Long, Averia> implements AveriaRepository {

	@Override
    public Class<Averia> getClassDeT() {
        return Averia.class;
    }

	@Override
    public String getNombreTabla() {
        return "AVERIA";
    }
}
