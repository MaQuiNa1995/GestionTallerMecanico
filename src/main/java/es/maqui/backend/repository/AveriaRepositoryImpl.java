package es.maqui.backend.repository;

import org.springframework.stereotype.Repository;

import es.maqui.backend.dominio.Averia;

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
