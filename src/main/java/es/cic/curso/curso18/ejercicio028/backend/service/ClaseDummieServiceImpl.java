/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.curso.curso18.ejercicio028.backend.service;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Dummie;
import es.cic.curso.curso18.ejercicio028.backend.repository.ClaseDummieRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MaQuiNa
 */
@Service
@Transactional
public class ClaseDummieServiceImpl implements ClaseDummieService{

	@Autowired
	private ClaseDummieRepository claseDummieRepository;

        @Override
	public Long aniadirClaseDummie(String palabra,
                boolean apagado,
                int numero,
                long numeroGran,
                float numeroComas) {
            
		Dummie claseDummie = new Dummie();

                
		claseDummie.setPalabra(palabra);
                claseDummie.setApagado(apagado);
                claseDummie.setNumero(numero);
                claseDummie.setNumeroGran(numeroGran);
                claseDummie.setNumeroComas(numeroComas);
		
		Dummie nuevo = aniadirClaseDummie( claseDummie );
		
		return nuevo.getId();
	}

    private Dummie aniadirClaseDummie(Dummie nueva) {
        return claseDummieRepository.add(nueva);
    }
        
    @Override
    public Dummie actualizarClaseDummie(Dummie modificada) {
        return claseDummieRepository.update( modificada );
    }


    @Override
    public void borrarClaseDummie(Long id) {
        Dummie objetoEliminable = obtenerClaseDummie(id);
	claseDummieRepository.delete( objetoEliminable );
    }

    @Override
    public Dummie obtenerClaseDummie(Long id) {
        return claseDummieRepository.read(id);
    }

    @Override
    public List<Dummie> obtenerClaseDummies() {
        return claseDummieRepository.list();
    }

}