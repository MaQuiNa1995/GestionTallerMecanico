/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.curso.curso18.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Dummie;

/**
 *
 * @author MaQuiNa
 */
public interface ClaseDummieService {

    Dummie actualizarClaseDummie(Dummie modificada);

    Long aniadirClaseDummie(String palabra, boolean apagado, int numero, long numeroGran, float decimal);

    void borrarClaseDummie(Long id);

    Dummie obtenerClaseDummie(Long id);

    List<Dummie> obtenerClaseDummies();
}
