/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.curso.curso18.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;

/**
 *
 * @author MaQuiNa
 */
public interface AveriaService {

    Averia actualizarAveria(Averia modificada);

    Long aniadirAveria(String nombre, String descripcion);

    void borrarAveria(Long id);

    Averia obtenerAveria(Long id);

    List<Averia> obtenerAverias();
}
