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

import es.maqui.backend.dominio.Marca;
import es.maqui.backend.service.MarcaService;

public class GestionMarcas extends HorizontalLayout {

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

	private MarcaService marcaService;

	private TextField nombreMarca;
	
	private Button anadir;
	private Button confirmarAnadir;
	private Button confirmarEliminar;
	private Button confirmarModificar;

	private Button cancelar;

	private Grid maestro;

	private Marca marca;

	private String nombreMarcaSeleccionado = "";

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

		nombreMarca = new TextField("Introduce El Nombre De La Marca:");
		nombreMarca.setSizeFull();

		panelIntroducirDatos.addComponent(nombreMarca);

		confirmarAnadir = new Button("Añadir");
		confirmarEliminar = new Button("Eliminar");
		confirmarModificar = new Button("Modificar");

		cancelar = new Button("Cancelar");

		cancelar.addClickListener(e -> {
			controladorPrimerosprimarios(2);
			limpiarCampos();
		});

		confirmarAnadir.addClickListener(e -> {

			if (!nombreMarca.getValue().isEmpty()) {

				marcaService.aniadirMarca(nombreMarca.getValue());
				cargaGrid();
				controladorPrimerosprimarios(3);
				limpiarCampos();
			
			} else {
				mostrarNotificacion("Rellena Todos Los Campos");
			}
		});

		confirmarEliminar.addClickListener(e -> {
			for (Marca marcaSacado : marcaService.obtenerMarcas()) {
				if (marcaSacado.getNombre().equals(nombreMarca.getValue())) {
					marcaService.borrarMarca(marcaSacado.getId());
				}
			}
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
		});

		confirmarModificar.addClickListener(e -> {

			for (Marca marcaSacado : marcaService.obtenerMarcas()) {

				if (marcaSacado.getNombre().equals(nombreMarcaSeleccionado)) {

					Marca averiaMeter = marcaService.obtenerMarca(marcaSacado.getId());
					averiaMeter.setNombre(nombreMarca.getValue());

					modificarMarca(averiaMeter);

					nombreMarcaSeleccionado = "";
				}
			}
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;

		if (marca != null) {
			BeanFieldGroup.bindFieldsUnbuffered(marca, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Marca(), this);
		}
	}

	private void definirPanelGrid() {

		panelGrid = new HorizontalLayout();

		maestro = new Grid();
		maestro.setColumns("nombre");

		cargaGrid();

		maestro.addSelectionListener(e -> {
			Marca marcaGrid = null;
			if (!e.getSelected().isEmpty()) {
				marcaGrid = (Marca) e.getSelected().iterator().next();
				nombreMarca.setValue(marcaGrid.getNombre());

				nombreMarcaSeleccionado = marcaGrid.getNombre();

				controladorPrimerosprimarios(3);
			}
			setMarca(marca);
		});

		panelGrid.addComponent(maestro);
		panelGrid.setMargin(true);
		panelGrid.setSpacing(true);

	}

	public void annadirMarca(Marca marca) {

		marcaService.aniadirMarca(marca.getNombre());

		cargaGrid();
	}

	public void modificarMarca(Marca marca) {

		marcaService.actualizarMarca(marca);

		cargaGrid();
	}

	public void eliminarMarca(Marca marca) {

		marcaService.borrarMarca(marca.getId());

		cargaGrid();
	}

	public void cargaGrid() {
		maestro.setContainerDataSource(new BeanItemContainer<>(Marca.class, marcaService.obtenerMarcas()));
	}

	private void definirBean() {
		marcaService = ContextLoader.getCurrentWebApplicationContext().getBean(MarcaService.class);
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

		nombreMarca.setValue("");

		nombreMarca.setVisible(false);

		confirmarAnadir.setVisible(false);
		confirmarEliminar.setVisible(false);
		confirmarModificar.setVisible(false);
		cancelar.setVisible(false);
		nombreMarca.setVisible(false);

		anadir.setEnabled(true);
	}

	private void verPanelDatos() {
		nombreMarca.setVisible(true);
	}

	private void generaBBDD() {

		if (marcaService.obtenerMarcas().isEmpty()) {

			Marca marca1 = new Marca("Opel");
			marcaService.aniadirMarca(marca1.getNombre());

			Marca marca2 = new Marca("Subaru");
			marcaService.aniadirMarca(marca2.getNombre());
		}

	}

	private void mostrarNotificacion(String mostrarCadena) {
		Notification.show(mostrarCadena);
	}
}
