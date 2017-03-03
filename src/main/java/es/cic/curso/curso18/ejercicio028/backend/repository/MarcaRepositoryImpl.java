package es.cic.curso.curso18.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Marca;

@Repository
public class MarcaRepositoryImpl extends AbstractRepositoryImpl <Long, Marca> implements MarcaRepository {

	@Override
    public Class<Marca> getClassDeT() {
        return Marca.class;
    }

	@Override
    public String getNombreTabla() {
        return "MARCA";
    }
}
