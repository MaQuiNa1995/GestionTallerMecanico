package es.cic.curso.curso18.ejercicio028.backend.dominio;

import javax.persistence.Entity;
import javax.persistence.Table;

import es.cic.curso.curso18.ejercicio028.backend.repository.Identificable;


@Entity
@Table(name="AVERIA")
public class Averia implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4583881797217483524L;
	
	long Pk_idAveria;
	String nombre;
	String descripcion;

	public Averia() {
		super();
	}

	public Averia(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public Long getId() {
		return Pk_idAveria;
	}

	@Override
	public void setId(Long Pk_idAveria) {
		this.Pk_idAveria=Pk_idAveria;
	}

}
