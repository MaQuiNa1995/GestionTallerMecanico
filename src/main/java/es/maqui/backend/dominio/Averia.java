package es.maqui.backend.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.maqui.backend.repository.Identificable;

@Entity
@Table(name = "AVERIA")
public class Averia implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4583881797217483524L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long pkidAveria;

	@Column(name = "nombre")
	String nombre;

	@Column(name = "descripcion")
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
		return pkidAveria;
	}

	@Override
	public void setId(Long pkidAveria) {
		this.pkidAveria = pkidAveria;
	}

}
