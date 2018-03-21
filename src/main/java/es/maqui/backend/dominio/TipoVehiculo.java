package es.maqui.backend.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.maqui.backend.repository.Identificable;

@Entity
@Table(name = "TIPO_VEHICULO")
public class TipoVehiculo implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -236007345030468080L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pkidTipoVehiculo;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "descripcion")
	private String descripcion;

	public TipoVehiculo() {
		super();
	}

	public TipoVehiculo(String tipo, String descripcion) {
		super();
		this.tipo = tipo;
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public Long getId() {
		return pkidTipoVehiculo;
	}

	@Override
	public void setId(Long pkidTipoVehiculo) {
		this.pkidTipoVehiculo = pkidTipoVehiculo;

	}

}
