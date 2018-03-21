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
	private static final long serialVersionUID = 31216841126896644L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pkidAveria;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

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
