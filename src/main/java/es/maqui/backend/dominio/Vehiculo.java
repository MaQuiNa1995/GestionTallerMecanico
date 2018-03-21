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
@Table(name = "VEHICULO")
public class Vehiculo implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1982196828951192944L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pkidVehiculo;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "matricula")
	private String matricula;

	@JoinColumn(name = "fkidTipoVehiculo")
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoVehiculo tipoVehiculo;

	@JoinColumn(name = "fkidMarca")
	@ManyToOne(fetch = FetchType.LAZY)
	private Marca marca;

	public Vehiculo() {
		super();
	}

	public Vehiculo(String nombre, String matricula, TipoVehiculo tipoVehiculo, Marca marca) {
		super();
		this.nombre = nombre;
		this.matricula = matricula;
		this.tipoVehiculo = tipoVehiculo;
		this.marca = marca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Override
	public Long getId() {
		return pkidVehiculo;
	}

	@Override
	public void setId(Long pkidVehiculo) {
		this.pkidVehiculo = pkidVehiculo;
	}

}
