package es.cic.curso.curso18.ejercicio028.backend.dominio;

import javax.persistence.Entity;
import javax.persistence.Table;

import es.cic.curso.curso18.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name="MARCA")
public class Marca implements Identificable<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6007890223503488331L;
	
	long Pk_idMarca;
	String nombre;
	
	public Marca() {
		super();
	}
	
	public Marca(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public Long getId() {
		return Pk_idMarca;
	}

	@Override
	public void setId(Long Pk_idMarca) {
		this.Pk_idMarca=Pk_idMarca;
	}
	
	
}
