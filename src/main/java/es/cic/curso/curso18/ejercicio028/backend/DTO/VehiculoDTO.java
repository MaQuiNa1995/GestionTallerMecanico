package es.cic.curso.curso18.ejercicio028.backend.DTO;

public class VehiculoDTO {

	String nombreVehiculo;
	String matricula;
	String tipo;
	String nombreMarca;

	public VehiculoDTO() {
		super();
	}

	public String getNombreVehiculo() {
		return nombreVehiculo;
	}

	public void setNombreVehiculo(String nombreVehiculo) {
		this.nombreVehiculo = nombreVehiculo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

}
