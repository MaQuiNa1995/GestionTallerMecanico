package es.maqui.backend.repository.impl;

import org.springframework.stereotype.Repository;

import es.maqui.backend.dominio.Marca;
import es.maqui.backend.repository.MarcaRepository;

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
