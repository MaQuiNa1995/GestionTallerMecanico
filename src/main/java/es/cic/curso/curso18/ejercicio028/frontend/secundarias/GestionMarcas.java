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

public class GestionMarcas extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2370949761559912513L;

	VerticalLayout panelDerecha;
	VerticalLayout panelIzquierda;
	VerticalLayout panelDatos;
	HorizontalLayout panelBotones;
	HorizontalLayout panelGrid;
	HorizontalLayout panelTodo;

	AveriaService averiaService;

	Button anadir;
	TextField nombreAveria;
	TextField descripcion;
	Button confirmarAnadir;
	Button confirmarEliminar;
	Button confirmarModificar;
	
	Button cancelar;

	Grid maestro;

	List<Averia> averiasLista;

	Averia averia;

	public GestionMarcas() {
		super();
		definirBean();

		generaBBDD();

		definirPanelTodo();

		definirPanelGrid();

		definirPanelIzquierda();
		definirPanelDerecha();

		definirPanelDatos();
		definirPanelBotones();

		agregarAPanelTodo();

		limpiarCampos();
	}

	private void definirPanelBotones() {

		panelBotones = new HorizontalLayout();

		anadir = new Button("Dar De Alta");
		anadir.addClickListener(e -> {
			controladorPrimerosprimarios(1);
			verPanelDatos();
		});

		panelBotones.addComponent(anadir);
	}

	private void definirPanelDatos() {

		panelDatos = new VerticalLayout();

		VerticalLayout panelIntroducirDatos = new VerticalLayout();
		HorizontalLayout panelConfirmacion = new HorizontalLayout();

		nombreAveria = new TextField("Introduce El Nombre De La Avería:");
		nombreAveria.setSizeFull();

		descripcion = new TextField("Introduce La Descripción:");
		descripcion.setSizeFull();

		panelIntroducirDatos.addComponents(nombreAveria, descripcion);

		confirmarAnadir = new Button("Añadir");
		confirmarEliminar = new Button("Eliminar");
		confirmarModificar= new Button("Modificar");
		
		cancelar = new Button("Cancelar");

		confirmarAnadir.addClickListener(e -> {
			averiaService.aniadirAveria(nombreAveria.getValue(), descripcion.getValue());
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
		});
		
		confirmarEliminar.addClickListener(e -> {
			for (Averia averiaSacado : averiaService.obtenerAverias()) {
				if (averiaSacado.getNombre().equals(nombreAveria.getValue())
						&& (averiaSacado.getDescripcion().equals(descripcion.getValue()))) {
					averiaService.borrarAveria(averiaSacado.getId());
				}
			}
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
		});
		
		confirmarModificar.addClickListener(e -> {
			for (Averia averiaSacado : averiaService.obtenerAverias()) {
				if (averiaSacado.getNombre().equals(nombreAveria.getValue())
						&& (averiaSacado.getDescripcion().equals(descripcion.getValue()))) {
					
					Averia objetoModificado = new Averia(nombreAveria.getValue(),descripcion.getValue());
					averiaService.actualizarAveria(objetoModificado);
				}
			}
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
		});

		cancelar.addClickListener(e -> {
			limpiarCampos();

			controladorPrimerosprimarios(3);
		});

		panelConfirmacion.addComponents(confirmarAnadir,confirmarEliminar,confirmarModificar, cancelar);

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

		panelTodo.addComponents(panelIzquierda, panelDerecha);

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
		averiasLista = new ArrayList<>();

		panelGrid = new HorizontalLayout();
		averiasLista = averiaService.obtenerAverias();

		maestro = new Grid();
		maestro.setColumns("nombre", "descripcion");

		cargaGrid();

		maestro.addSelectionListener(e -> {
			Averia averia = null;
			if (!e.getSelected().isEmpty()) {
				averia = (Averia) e.getSelected().iterator().next();
				nombreAveria.setValue(averia.getNombre());
				
				controladorPrimerosprimarios(3);
			}
			setAveria(averia);
		});

		panelGrid.addComponent(maestro);
		panelGrid.setMargin(true);
		panelGrid.setSpacing(true);

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
		maestro.setContainerDataSource(
				new BeanItemContainer<>(Averia.class, averiaService.obtenerAverias()));
	}

	private void definirBean() {
		averiaService = ContextLoader.getCurrentWebApplicationContext().getBean(AveriaService.class);
	}

	private void generaBBDD() {

		if (averiaService.obtenerAverias().isEmpty()) {
				
				Averia averia = new Averia("Tubo De Escape Ilegal","Expulsa Demasiado CO2");
				averiaService.aniadirAveria(averia.getNombre(), averia.getDescripcion());
				
				Averia averia2 = new Averia("Carburador Roto","No Carbura Bien");
				averiaService.aniadirAveria(averia.getNombre(), averia2.getDescripcion());
			}
			
		}

	private void controladorPrimerosprimarios(int opcion) {
		switch (opcion) {

		// Dar Alta
		case 1:

			anadir.setEnabled(true);
			
			confirmarAnadir.setVisible(true);
			confirmarEliminar.setVisible(false);
			confirmarModificar.setVisible(false);
			
			verPanelDatos();
			break;
		case 3:
			anadir.setEnabled(false);
			
			
			
			confirmarAnadir.setVisible(false);
			confirmarEliminar.setVisible(true);
			confirmarModificar.setVisible(true);
			
			verPanelDatos();
			break;
		}
		
	}


	private void limpiarCampos() {

		nombreAveria.setValue("");
		descripcion.setValue("");

		confirmarAnadir.setVisible(false);
		confirmarEliminar.setVisible(false);
		confirmarModificar.setVisible(false);
		cancelar.setVisible(false);
		nombreAveria.setVisible(false);
		descripcion.setVisible(false);

		anadir.setEnabled(true);
	}

	private void verPanelDatos() {
		nombreAveria.setVisible(true);
		descripcion.setVisible(true);
	}

}

