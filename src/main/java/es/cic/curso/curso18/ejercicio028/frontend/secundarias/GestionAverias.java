package es.cic.curso.curso18.ejercicio028.frontend.secundarias;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;

public class GestionAverias extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1609217719853880929L;

	VerticalLayout panelDerecha;
	VerticalLayout panelIzquierda;
	VerticalLayout panelDatos;
	HorizontalLayout panelBotones;
	HorizontalLayout panelGrid;
	HorizontalLayout panelTodo;
	
	Grid maestro;
	
	List<Averia> averiaLista;

	Averia averia;

	public GestionAverias() {
		
		definirPanelTodo();
		
		definirPanelGrid();

		definirPanelIzquierda();
		definirPanelDerecha();
		
		definirPanelDatos();
		definirPanelBotones();

		agregarAPanelTodo();
	}

	private void definirPanelBotones() {

		panelBotones = new HorizontalLayout();

		Button anadir, eliminar;

		anadir = new Button("Dar De Alta");
		eliminar = new Button("Dar De Baja");

		panelBotones.addComponents(anadir, eliminar);
	}

	private void definirPanelDatos() {

		panelDatos = new VerticalLayout();

		HorizontalLayout panelIntroducirDatos = new HorizontalLayout();
		HorizontalLayout panelConfirmacion = new HorizontalLayout();

		TextField nombre, descripcion;

		Button confirmar, cancelar;

		nombre = new TextField("Introduce El Nombre De la Avería:");
		descripcion = new TextField("Introduce La Descripción:");

		panelIntroducirDatos.addComponents(nombre, descripcion);

		confirmar = new Button("Confirmar");
		cancelar = new Button("Cancelar");

		panelConfirmacion.addComponents(confirmar, cancelar);

		panelDatos.addComponents(panelIntroducirDatos, panelConfirmacion);

	}

	private void definirPanelDerecha() {
		panelDerecha = new VerticalLayout();
		panelDerecha.setMargin(true);
		panelDerecha.setSpacing(true);
		panelDerecha.setWidth("100%");
	}
	
	private void definirPanelIzquierda() {
		panelIzquierda = new VerticalLayout();
		panelIzquierda.setMargin(true);
		panelIzquierda.setSpacing(true);
		panelIzquierda.setWidth("100%");
	}
	
	private void definirPanelTodo() {
		panelTodo = new HorizontalLayout();
		panelTodo.setMargin(true);
		panelTodo.setSpacing(true);
		panelTodo.setWidth("100%");
	}

	private void agregarAPanelTodo() {
		
		
		panelIzquierda.addComponent(panelGrid);
		panelDerecha.addComponents(panelBotones, panelDatos);
		
		panelTodo.addComponents(panelIzquierda,panelDerecha);
		
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

	private void definirPanelGrid() {

		panelGrid = new HorizontalLayout();

		averiaLista = new ArrayList<>();
		averiaLista.add(new Averia("Carburador En Mal Estado", "El carburador no carbura"));
		averiaLista.add(new Averia("Tubo Escape Ilegal", "Expulsa Demasiado CO2"));

		maestro = new Grid();
		maestro.setColumns("nombre", "descripcion");

		cargaGrid();

		maestro.addSelectionListener(e -> {
			Averia averia = null;
			if (!e.getSelected().isEmpty()) {
				averia = (Averia) e.getSelected().iterator().next();
			}
			setAveria(averia);
		});

		panelGrid.addComponent(maestro);
		panelGrid.setMargin(true);
		panelGrid.setSpacing(true);

	}

	public void annadirAveria(Averia averia) {

		averiaLista.add(averia);

		cargaGrid();
	}

	public void eliminarAveria(Averia averia) {

		averiaLista.remove(averia);

		cargaGrid();
	}

	public void cargaGrid() {
		maestro.setContainerDataSource(new BeanItemContainer<>(Averia.class, averiaLista));
	}

}
