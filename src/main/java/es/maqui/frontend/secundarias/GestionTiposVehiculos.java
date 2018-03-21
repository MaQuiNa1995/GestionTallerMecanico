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

import es.maqui.backend.dominio.TipoVehiculo;
import es.maqui.backend.service.TipoVehiculoService;

public class GestionTiposVehiculos extends VerticalLayout {

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

	private TipoVehiculoService tipoVehiculoService;

	private TextField tipo;
	private TextField descripcion;
	
	private Button anadir;
	private Button confirmarAnadir;
	private Button confirmarEliminar;
	private Button confirmarModificar;
	private Button cancelar;
	
	private String nombreVehiculoSeleccionado = "";
	private String descripcionVehiculoSeleccionado = "";

	private Grid maestro;

	private TipoVehiculo tipoVehiculo;

	public GestionTiposVehiculos() {
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

		tipo = new TextField("Introduce El Tipo Del Vehiculo:");
		tipo.setSizeFull();

		descripcion = new TextField("Introduce La Descripción:");
		descripcion.setSizeFull();

		panelIntroducirDatos.addComponents(tipo, descripcion);

		confirmarAnadir = new Button("Añadir");
		confirmarEliminar = new Button("Eliminar");
		confirmarModificar = new Button("Modificar");

		cancelar = new Button("Cancelar");

		confirmarAnadir.addClickListener(e -> {
			anadirConfirmarMetodo();
		});

		confirmarEliminar.addClickListener(e -> {
			eliminarConfirmarMetodo();
		});

		confirmarModificar.addClickListener(e -> {

			confirmarModificarMetodo();
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

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;

		if (tipoVehiculo != null) {
			BeanFieldGroup.bindFieldsUnbuffered(tipoVehiculo, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new TipoVehiculo(), this);
		}
	}

	private void definirPanelGrid() {

		panelGrid = new HorizontalLayout();

		maestro = new Grid();
		maestro.setColumns("tipo", "descripcion");

		cargaGrid();

		maestro.addSelectionListener(e -> {
			TipoVehiculo tipoDeVehiculo = null;
			if (!e.getSelected().isEmpty()) {
				tipoDeVehiculo = (TipoVehiculo) e.getSelected().iterator().next();

				nombreVehiculoSeleccionado = tipoDeVehiculo.getTipo();
				descripcionVehiculoSeleccionado = tipoDeVehiculo.getDescripcion();

				controladorPrimerosprimarios(3);
			}
			setTipoVehiculo(tipoDeVehiculo);
		});

		panelGrid.addComponent(maestro);
		panelGrid.setMargin(true);
		panelGrid.setSpacing(true);

	}

	private void modificarTipoVehiculo(TipoVehiculo objetoModificado) {
		tipoVehiculoService.actualizarTipoVehiculo(objetoModificado);

		cargaGrid();
	}

	public void annadirTipoVehiculo(TipoVehiculo tipoVehiculo) {

		tipoVehiculoService.aniadirTipoVehiculo(tipoVehiculo.getTipo(), tipoVehiculo.getDescripcion());

		cargaGrid();
	}

	public void eliminarTipoVehiculo(TipoVehiculo tipoVehiculo) {

		tipoVehiculoService.borrarTipoVehiculo(tipoVehiculo.getId());

		cargaGrid();
	}

	public void cargaGrid() {
		maestro.setContainerDataSource(
				new BeanItemContainer<>(TipoVehiculo.class, tipoVehiculoService.obtenerTipoVehiculos()));
	}

	private void definirBean() {
		tipoVehiculoService = ContextLoader.getCurrentWebApplicationContext().getBean(TipoVehiculoService.class);
	}

	private void generaBBDD() {

		if (tipoVehiculoService.obtenerTipoVehiculos().isEmpty()) {

			TipoVehiculo tipoVehiculoPorDefecto = new TipoVehiculo("Moto", "Vehiculo de 2 Ruedas");
			tipoVehiculoService.aniadirTipoVehiculo(tipoVehiculoPorDefecto.getTipo(),
					tipoVehiculoPorDefecto.getDescripcion());

			TipoVehiculo tipoVehiculoPorDefecto2 = new TipoVehiculo("Coche", "Vehiculo de 4 Ruedas");
			tipoVehiculoService.aniadirTipoVehiculo(tipoVehiculoPorDefecto2.getTipo(),
					tipoVehiculoPorDefecto2.getDescripcion());
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

		tipo.setValue("");
		descripcion.setValue("");

		confirmarAnadir.setVisible(false);
		confirmarEliminar.setVisible(false);
		confirmarModificar.setVisible(false);

		cancelar.setVisible(false);
		tipo.setVisible(false);
		descripcion.setVisible(false);

		anadir.setEnabled(true);
	}

	private void verPanelDatos() {
		tipo.setVisible(true);
		descripcion.setVisible(true);
	}

	private void confirmarModificarMetodo() {
		for (TipoVehiculo tipoVehiculoSacado : tipoVehiculoService.obtenerTipoVehiculos()) {

			if (tipoVehiculoSacado.getTipo().equals(nombreVehiculoSeleccionado)
					&& (tipoVehiculoSacado.getDescripcion().equals(descripcionVehiculoSeleccionado))) {

				TipoVehiculo tipoVehiculoModificar = tipoVehiculoService
						.obtenerTipoVehiculo(tipoVehiculoSacado.getId());
				tipoVehiculoModificar.setTipo(tipo.getValue());
				tipoVehiculoModificar.setDescripcion(descripcion.getValue());

				modificarTipoVehiculo(tipoVehiculoModificar);

				nombreVehiculoSeleccionado = "";
				descripcionVehiculoSeleccionado = "";
			}
		}

		cargaGrid();
		controladorPrimerosprimarios(3);
		limpiarCampos();
	}

	private void eliminarConfirmarMetodo() {
		for (TipoVehiculo tipoVehiculoSacado : tipoVehiculoService.obtenerTipoVehiculos()) {
			if (tipoVehiculoSacado.getTipo().equals(tipo.getValue())
					&& (tipoVehiculoSacado.getDescripcion().equals(descripcion.getValue()))) {
				tipoVehiculoService.borrarTipoVehiculo(tipoVehiculoSacado.getId());
			}
		}
		cargaGrid();
		controladorPrimerosprimarios(3);
		limpiarCampos();
	}

	private void anadirConfirmarMetodo() {

		if (!tipo.getValue().isEmpty() && !descripcion.getValue().isEmpty()) {

			tipoVehiculoService.aniadirTipoVehiculo(tipo.getValue(), descripcion.getValue());
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
		} else {
			mostrarNotificacion("Rellene Todos Los Campos");
		}
	}

	private void mostrarNotificacion(String mostrarCadena) {
		Notification.show(mostrarCadena);
	}

}
