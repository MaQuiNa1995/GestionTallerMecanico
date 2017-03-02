package es.cic.curso.curso18.ejercicio028.backend.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.cic.curso.curso18.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name = "VEHICULO")
public class Vehiculo implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4582114734221516816L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long pkidVehiculo;

	@Column(name = "nombre")
	String nombre;

	@Column(name = "matricula")
	String matricula;

	// TODO Cambiar lo del OneToOne
	@JoinColumn(name = "pkidMarca")
	@OneToOne(fetch = FetchType.LAZY)
	Marca marca;

	public Vehiculo() {
		super();
	}

	public Vehiculo(String nombre, String matricula, Marca marca) {
		super();
		this.nombre = nombre;
		this.marca = marca;
		this.matricula = matricula;
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
