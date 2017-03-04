package es.cic.curso.curso18.ejercicio028.frontend.secundarias;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;

public class GestionAverias extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1609217719853880929L;
	
	VerticalLayout panelTodo;
	VerticalLayout panelDatos;
	HorizontalLayout panelBotones;
	
	private Averia averia;
	
	public GestionAverias() {
		super();
	}

	public GestionAverias(String hola) {
		definirPanelTodo();
		definirPanelDatos();
		definirPanelBotones();
		agregarAPanelTodo();
	}
	
	private void definirPanelBotones(){
		
		panelBotones = new HorizontalLayout();
		
		Button anadir,eliminar; 
		
		anadir = new Button("Dar De Alta");
		eliminar = new Button("Dar De Baja");
		
		panelBotones.addComponents(anadir,eliminar);
	}
	
	private void definirPanelDatos(){
		
		panelDatos = new VerticalLayout();
		
		HorizontalLayout panelIntroducirDatos = new HorizontalLayout();
		HorizontalLayout panelConfirmacion = new HorizontalLayout();
		
		TextField nombre,
				descripcion;
		
		Button confirmar,
				cancelar;
		
		ComboBox vehiculos;
		
		nombre = new TextField("Introduce El Nombre De la Avería:");
		descripcion = new TextField("Introduce La Descripción:");
		vehiculos= new ComboBox();
		
		panelIntroducirDatos.addComponents(nombre,descripcion,vehiculos);
		
		confirmar = new Button("Confirmar");
		cancelar = new Button("Cancelar");
		
		panelConfirmacion.addComponents(confirmar,cancelar);
		
		panelDatos.addComponents(panelIntroducirDatos,panelConfirmacion);
		
	}
	
	private void definirPanelTodo(){
		panelTodo = new VerticalLayout();
		panelTodo.setMargin(true);
		panelTodo.setSpacing(true);
		panelTodo.setWidth("100%");
	}
	
	private void agregarAPanelTodo(){
		panelTodo.addComponents(panelBotones,panelDatos);
		addComponent(panelTodo);
	}

	public Averia getAveria() {
		return averia;
	}

	public void setAveria(Averia averia) {
		this.averia = averia;

		if (averia != null) {
			BeanFieldGroup.bindFieldsUnbuffered(averia, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Averia(), this);
		}
	}
	


	
}
