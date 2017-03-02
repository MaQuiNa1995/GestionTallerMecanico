package es.cic.curso.curso18.ejercicio028.backend.dominio;

import javax.persistence.Entity;
import javax.persistence.Table;

import es.cic.curso.curso18.ejercicio028.backend.repository.Identificable;


@Entity
@Table(name="TIPOVEHICULO")
public class TipoVehiculo implements Identificable<Long>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8513598203876189283L;
	
	long Pk_idTipoVehiculo;
	String tipo;
	String descripcion;
	Vehiculo vehiculo;
	
	public TipoVehiculo() {
		super();
	}
	public TipoVehiculo(String tipo, String descripcion, Vehiculo vehiculo) {
		super();
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.vehiculo = vehiculo;
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
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	@Override
	public Long getId() {
		return Pk_idTipoVehiculo;
	}
	@Override
	public void setId(Long Pk_idTipoVehiculo) {
		this.Pk_idTipoVehiculo=Pk_idTipoVehiculo;
		
	}
	
	
	
}
