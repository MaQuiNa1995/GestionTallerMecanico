package es.maqui.backend.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.maqui.backend.repository.Identificable;

@Entity
@Table(name = "REGISTRO_AVERIAS")
public class RegistroAverias implements Identificable<Long> {

	/**
	* 
	*/
	private static final long serialVersionUID = 3168040100384450885L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pkidRegistroAverias;

	@Column(name = "fecha")
	private String fecha;

	@JoinColumn(name = "fkidAveria")
	@ManyToOne(fetch = FetchType.LAZY)
	private Averia averia;

	public RegistroAverias() {
		super();
	}

	public RegistroAverias(String fecha, Averia averia) {
		super();
		this.fecha = fecha;
		this.averia = averia;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Averia getAveria() {
		return averia;
	}

	public void setAveria(Averia averia) {
		this.averia = averia;
	}

	@Override
	public Long getId() {
		return pkidRegistroAverias;
	}

	@Override
	public void setId(Long pkidRegistroAverias) {
		this.pkidRegistroAverias = pkidRegistroAverias;
	}
}
