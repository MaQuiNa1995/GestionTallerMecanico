package es.maqui.frontend.secundarias;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.maqui.backend.dominio.Averia;
import es.maqui.backend.service.AveriaService;

public class GestionAverias extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2370949761559912513L;

	private VerticalLayout panelDerecha;
	private VerticalLayout panelIzquierda;
	private VerticalLayout panelDatos;
	private HorizontalLayout panelBotones;
	private HorizontalLayout panelGrid;
	private HorizontalLayout panelTodo;

	private AveriaService averiaService;

	private TextField nombreAveria;
	private TextField descripcion;
	
	private Button anadir;
	private Button confirmarAnadir;
	private Button confirmarEliminar;
	private Button confirmarModificar;
	private Button cancelar;

	private Grid maestro;

	private Averia averia;

	private String nombreAveriaSeleccionado = "";
	private String descripcionAveriaSeleccionado = "";

	public GestionAverias() {
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
		confirmarModificar = new Button("Modificar");

		cancelar = new Button("Cancelar");
		cancelar.addClickListener(e -> {
			controladorPrimerosprimarios(2);
		});

		confirmarAnadir.addClickListener(e -> {

			if (!nombreAveria.getValue().isEmpty() && !descripcion.getValue().isEmpty()) {

				averiaService.aniadirAveria(nombreAveria.getValue(), descripcion.getValue());
				cargaGrid();
				mostrarNotificacion("Avería Agregada:" +nombreAveria.getValue()+" "+descripcion.getValue());
				controladorPrimerosprimarios(3);
				limpiarCampos();
			} else{
				mostrarNotificacion("Rellene Todos Los Campos");
			}
		});

		confirmarEliminar.addClickListener(e -> {
			for (Averia averiaSacado : averiaService.obtenerAverias()) {
				if (averiaSacado.getNombre().equals(nombreAveria.getValue())
						&& (averiaSacado.getDescripcion().equals(descripcion.getValue()))) {
					averiaService.borrarAveria(averiaSacado.getId());
					
					mostrarNotificacion("Avería Borrada:" +nombreAveria.getValue()+" "+descripcion.getValue());
				}
			}
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
		});

		confirmarModificar.addClickListener(e -> {

			for (Averia averiaSacado : averiaService.obtenerAverias()) {

				if (averiaSacado.getNombre().equals(nombreAveriaSeleccionado)
						&& (averiaSacado.getDescripcion().equals(descripcionAveriaSeleccionado))) {

					Averia averiaMeter = averiaService.obtenerAveria(averiaSacado.getId());
					averiaMeter.setNombre(nombreAveria.getValue());
					averiaMeter.setDescripcion(descripcion.getValue());

					modificarAveria(averiaMeter);

					mostrarNotificacion("Avería Modificada:" +nombreAveria.getValue()+" "+descripcion.getValue());
					
					nombreAveriaSeleccionado = "";
					descripcionAveriaSeleccionado = "";
				}
			}
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
		});

		cancelar.addClickListener(e -> {
			controladorPrimerosprimarios(2);
		});

		panelConfirmacion.addComponents(confirmarAnadir, confirmarEliminar, confirmarModificar, cancelar);

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

		panelGrid = new HorizontalLayout();

		maestro = new Grid();
		maestro.setColumns("nombre", "descripcion");

		cargaGrid();

		maestro.addSelectionListener(e -> {
			Averia averiaGrid = null;
			if (!e.getSelected().isEmpty()) {
				averiaGrid = (Averia) e.getSelected().iterator().next();
				nombreAveria.setValue(averiaGrid.getNombre());

				nombreAveriaSeleccionado = averiaGrid.getNombre();
				descripcionAveriaSeleccionado = averiaGrid.getDescripcion();

				controladorPrimerosprimarios(3);
			}
			setAveria(averiaGrid);
		});

		panelGrid.addComponent(maestro);
		panelGrid.setMargin(true);
		panelGrid.setSpacing(true);

	}

	public void annadirAveria(Averia averia) {

		averiaService.aniadirAveria(averia.getNombre(), averia.getDescripcion());

		cargaGrid();
	}

	public void modificarAveria(Averia modificada) {

		averiaService.actualizarAveria(modificada);

		cargaGrid();
	}

	public void eliminarAveria(Averia averia) {

		averiaService.borrarAveria(averia.getId());

		cargaGrid();
	}

	public void cargaGrid() {
		maestro.setContainerDataSource(new BeanItemContainer<>(Averia.class, averiaService.obtenerAverias()));
	}

	private void definirBean() {
		averiaService = ContextLoader.getCurrentWebApplicationContext().getBean(AveriaService.class);
	}

	private void generaBBDD() {

		if (averiaService.obtenerAverias().isEmpty()) {

			Averia averia = new Averia("Tubo De Escape Ilegal", "Expulsa Demasiado CO2");
			averiaService.aniadirAveria(averia.getNombre(), averia.getDescripcion());

			Averia averia2 = new Averia("Carburador Roto", "No Carbura Bien");
			averiaService.aniadirAveria(averia2.getNombre(), averia2.getDescripcion());

			Averia averia3 = new Averia("Cristal Frontal Roto", "Llamar a Carglass");
			averiaService.aniadirAveria(averia3.getNombre(), averia3.getDescripcion());
		}

	}

	private void controladorPrimerosprimarios(int opcion) {
		switch (opcion) {

		// Dar Alta
		case 1:

			anadir.setEnabled(false);
			cancelar.setVisible(true);

			confirmarAnadir.setVisible(true);
			confirmarEliminar.setVisible(false);
			confirmarModificar.setVisible(false);

			verPanelDatos();
			break;

		// Cancelar
		case 2:
			limpiarCampos();
			break;
		case 3:
			anadir.setEnabled(false);
			cancelar.setVisible(true);

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

		nombreAveria.setVisible(false);
		descripcion.setVisible(false);

		confirmarAnadir.setVisible(false);
		confirmarEliminar.setVisible(false);
		confirmarModificar.setVisible(false);
		cancelar.setVisible(false);
		nombreAveria.setVisible(false);
		descripcion.setVisible(false);

		anadir.setEnabled(true);
	}
	
	private void mostrarNotificacion(String mostrarCadena){
		Notification.show(mostrarCadena);	
	}

	private void verPanelDatos() {
		nombreAveria.setVisible(true);
		descripcion.setVisible(true);
	}

}
