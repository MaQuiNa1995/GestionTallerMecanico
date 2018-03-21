package es.maqui.frontend.utilities;

public class Validador {

	public Validador() {
		super();
	}

	public boolean validarMatricula(String matricula){
		String patronMatricula= "[0-9]{4}[\\-]{1}[a-zA-Z]{1,3}";
		
		return matricula.matches(patronMatricula);
	}
}
