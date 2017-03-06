package es.cic.curso.curso18.ejercicio028.backend.dominio;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.cic.curso.curso18.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name = "VEHICULOSTIENENAVERIAS")
public class VehiculosTienenAverias implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4297447102360376912L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long pkidVehiculosTienenAverias;

	@JoinColumn(name = "fkidVehiculosRegistroAverias")
	@ManyToOne(fetch = FetchType.LAZY)
	RegistroAverias registroAverias;

	@JoinColumn(name = "fkidRegistroAveriasVehiculos")
	@ManyToOne(fetch = FetchType.LAZY)
	Vehiculo vehiculo;
	
	public VehiculosTienenAverias() {
		super();
	}

	public VehiculosTienenAverias(RegistroAverias registroAverias, Vehiculo vehiculo) {
		super();
		this.registroAverias = registroAverias;
		this.vehiculo = vehiculo;
	}

	public RegistroAverias getRegistroAverias() {
		return registroAverias;
	}

	public void setRegistroAverias(RegistroAverias registroAverias) {
		this.registroAverias = registroAverias;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public Long getId() {
		return pkidVehiculosTienenAverias;
	}

	@Override
	public void setId(Long pkidVehiculosTienenAverias) {
		this.pkidVehiculosTienenAverias = pkidVehiculosTienenAverias;
	}

}
