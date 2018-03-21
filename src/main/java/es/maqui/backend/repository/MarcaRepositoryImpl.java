package es.maqui.backend.repository;

import org.springframework.stereotype.Repository;

import es.maqui.backend.dominio.Marca;

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
