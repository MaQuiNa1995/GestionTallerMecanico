package es.maqui.backend.dominio;

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
@Table(name = "VEHICULOS_TIENEN_AVERIAS")
public class VehiculosTienenAverias implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5633469589639032676L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pkidVehiculosTienenAverias;

	@JoinColumn(name = "fkidVehiculosRegistroAverias")
	@ManyToOne(fetch = FetchType.LAZY)
	private RegistroAverias registroAverias;

	@JoinColumn(name = "fkidRegistroAveriasVehiculos")
	@ManyToOne(fetch = FetchType.LAZY)
	private Vehiculo vehiculo;

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
