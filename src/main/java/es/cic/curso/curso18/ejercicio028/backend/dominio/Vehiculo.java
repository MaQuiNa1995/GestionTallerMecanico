package es.cic.curso.curso18.ejercicio028.backend.dominio;

import javax.persistence.Entity;
import javax.persistence.Table;

import es.cic.curso.curso18.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name="VEHICULO")
public class Vehiculo implements Identificable<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4582114734221516816L;

	long Pk_idVehiculo;
	
	String nombre;
	String marca;
	String matricula;
	
	public Vehiculo() {
		super();
	}

	public Vehiculo(String nombre, String marca, String matricula) {
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public Long getId() {
		return Pk_idVehiculo;
	}

	@Override
	public void setId(Long Pk_idVehiculo) {
		this.Pk_idVehiculo=Pk_idVehiculo;
	}
	
	
	
}