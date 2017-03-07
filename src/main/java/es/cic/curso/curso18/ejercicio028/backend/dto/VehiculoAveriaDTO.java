package es.cic.curso.curso18.ejercicio028.backend.dto;

public class VehiculoAveriaDTO {
	String nombreAveria;
	String fechaAveria;
	String matriculaVehiculo;

	public VehiculoAveriaDTO() {
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


	public String getMatriculaVehiculo() {
		return matriculaVehiculo;
	}

	public void setMatriculaVehiculo(String matriculaVehiculo) {
		this.matriculaVehiculo = matriculaVehiculo;
	}

}
