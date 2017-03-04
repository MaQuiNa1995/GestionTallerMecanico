package es.cic.curso.curso18.ejercicio028.frontend.secundarias;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;
import es.cic.curso.curso18.ejercicio028.backend.service.AveriaService;

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
	
	AveriaService averiaService;
	
	TextField nombre;
	TextField descripcion;
	
	Grid maestro;
	
	List<Averia> averiaLista;

	Averia averia;

	public GestionAverias() {
		definirBean();
		
		generaBBDD();
		
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

		Button anadir;
		Button eliminar;

		// TODO Funcionalidad boton
		anadir = new Button("Dar De Alta");	
		
		// TODO Funcionalidad boton
		eliminar = new Button("Dar De Baja");

		panelBotones.addComponents(anadir, eliminar);
	}

	private void definirPanelDatos() {

		panelDatos = new VerticalLayout();

		VerticalLayout panelIntroducirDatos = new VerticalLayout();
		HorizontalLayout panelConfirmacion = new HorizontalLayout();

		Button confirmar;
		Button cancelar;

		nombre = new TextField("Introduce El Nombre De la Avería:");
		nombre.setSizeFull();
		descripcion = new TextField("Introduce La Descripción:");
		descripcion.setSizeFull();

		panelIntroducirDatos.addComponents(nombre, descripcion);

		confirmar = new Button("Confirmar");
		
		// TODO Los visibles
		confirmar.addClickListener(e->{	
			averiaService.aniadirAveria(nombre.getValue(), descripcion.getValue());
			cargaGrid();

			limpiarCampos();
		});
		
		cancelar = new Button("Cancelar");
		
		cancelar.addClickListener(e->{	
			limpiarCampos();
		});

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
		averiaLista = new ArrayList<>();

		panelGrid = new HorizontalLayout();
		averiaLista = averiaService.obtenerAverias();

		maestro = new Grid();
		maestro.setColumns("nombre", "descripcion");

		cargaGrid();

		maestro.addSelectionListener(e -> {
			Averia averia = null;
			if (!e.getSelected().isEmpty()) {
				averia = (Averia) e.getSelected().iterator().next();
				nombre.setValue(averia.getNombre());
				descripcion.setValue(averia.getDescripcion());
			}
			setAveria(averia);
		});

		panelGrid.addComponent(maestro);
		panelGrid.setMargin(true);
		panelGrid.setSpacing(true);

	}
	
	
	private void modificarAveria(Averia averia){
		averiaService.actualizarAveria(averia);	
	}

	public void annadirAveria(Averia averia) {

		averiaService.aniadirAveria(averia.getNombre(), averia.getDescripcion());

		cargaGrid();
	}

	public void eliminarAveria(Averia averia) {

		averiaService.borrarAveria(averia.getId());

		cargaGrid();
	}

	public void cargaGrid() {
		maestro.setContainerDataSource(new BeanItemContainer<>(Averia.class, averiaService.obtenerAverias()));
	}
	
	private void definirBean(){
		averiaService = ContextLoader.getCurrentWebApplicationContext().getBean(AveriaService.class);
	}
	
	private void generaBBDD(){
		
		Averia averia = new Averia("Tubo De Escape Ilegal","Expulsa Demasiado CO2");
		averiaService.aniadirAveria(averia.getNombre(), averia.getDescripcion());
		
		Averia averia2 = new Averia("Carburador Roto","No Carbura Bien");
		averiaService.aniadirAveria(averia.getNombre(), averia2.getDescripcion());
	}
	
	private void controladorEstados(short estado){
		
	}
	
	private void limpiarCampos(){
		nombre.setValue("");
		descripcion.setValue("");
	}

}
