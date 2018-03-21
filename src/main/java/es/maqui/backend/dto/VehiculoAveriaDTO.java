package es.maqui.backend.dto;

public class VehiculoAveriaDTO {
	private String nombreAveria;
	private String fechaAveria;
	private String matriculaVehiculo;

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
