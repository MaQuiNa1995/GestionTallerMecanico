/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.curso.curso18.ejercicio028.backend.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import es.cic.curso.curso18.ejercicio028.backend.repository.Identificable;

/**
 *
 * @author MaQuiNa
 */
@Entity
public class Dummie implements Identificable<Long>{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1427338651547869435L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String palabra;
    boolean apagado;
    int numero;
    long numeroGran;
    float numeroComas;

    public Dummie() {
    	super();
    }

    public Dummie(String palabra, boolean apagado, int numero, long numeroGran, float numeroComas) {

        this.palabra = palabra;
        this.apagado = apagado;
        this.numero = numero;
        this.numeroGran = numeroGran;
        this.numeroComas = numeroComas;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public boolean isApagado() {
        return apagado;
    }

    public void setApagado(boolean apagado) {
        this.apagado = apagado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getNumeroGran() {
        return numeroGran;
    }

    public void setNumeroGran(long numeroGran) {
        this.numeroGran = numeroGran;
    }

    public float getNumeroComas() {
        return numeroComas;
    }

    public void setNumeroComas(float numeroComas) {
        this.numeroComas = numeroComas;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }
    
}
