package es.cic.curso.curso18.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;

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
