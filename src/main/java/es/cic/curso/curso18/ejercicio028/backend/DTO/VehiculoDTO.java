package es.cic.curso.curso18.ejercicio028.backend.DTO;

public class VehiculoDTO {
	String nombreAveria;
	String fechaAveria;
	String nombreVehiculo;
	String matriculaVehiculo;
	String nombreMarca;
	String tipoVehiculo;

	public VehiculoDTO() {
		super();
	}

	public String getNombreAveria() {
		return nombreAveria;
	}

	public void setNombreAveria(String nombreAveria) {
		this.nombreAveria = nombreAveria;
	}

	public String getFechaAveria() {
		return fechaAveria;
	}

	public void setFechaAveria(String fechaAveria) {
		this.fechaAveria = fechaAveria;
	}

	public String getNombreVehiculo() {
		return nombreVehiculo;
	}

	public void setNombreVehiculo(String nombreVehiculo) {
		this.nombreVehiculo = nombreVehiculo;
	}

	public String getMatriculaVehiculo() {
		return matriculaVehiculo;
	}

	public void setMatriculaVehiculo(String matriculaVehiculo) {
		this.matriculaVehiculo = matriculaVehiculo;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

}
