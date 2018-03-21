package es.cic.curso.curso18.ejercicio028.backend.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.cic.curso.curso18.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name = "MARCA")
public class Marca implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6007890223503488331L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long pkidMarca;

	@Column(name = "nombre")
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
		return pkidMarca;
	}

	@Override
	public void setId(Long pkidMarca) {
		this.pkidMarca = pkidMarca;
	}

}
