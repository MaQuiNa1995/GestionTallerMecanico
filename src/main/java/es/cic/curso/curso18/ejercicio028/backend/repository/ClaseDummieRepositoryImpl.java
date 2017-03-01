package es.cic.curso.curso18.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Dummie;

@Repository
public class ClaseDummieRepositoryImpl extends AbstractRepositoryImpl<Long, Dummie> implements ClaseDummieRepository {

    @Override
    public Class<Dummie> getClassDeT() {
        return Dummie.class;
    }

    @Override
    public String getNombreTabla() {
        /**
         * Escribelo tal cual lo tienes en el changelog
         */
        return "Dummie";
    }
}
