package es.maqui.backend.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.maqui.backend.repository.Identificable;

@Entity
@Table(name = "MARCA")
public class Marca implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6099747245103508351L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pkidMarca;

	@Column(name = "nombre")
	private String nombre;

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
