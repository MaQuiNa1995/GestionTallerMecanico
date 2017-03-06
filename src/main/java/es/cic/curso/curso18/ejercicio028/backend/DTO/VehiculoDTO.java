package es.cic.curso.curso18.ejercicio028.backend.DTO;

public class VehiculoDTO {
	String nombreAveria;
	String fechaAveria;
	String nombreVehiculo;
	String matriculaVehiculo;

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

}
