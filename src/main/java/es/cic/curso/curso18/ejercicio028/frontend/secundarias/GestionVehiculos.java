package es.cic.curso.curso18.ejercicio028.frontend.secundarias;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso18.ejercicio028.backend.DTO.VehiculoDTO;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Marca;
import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;
import es.cic.curso.curso18.ejercicio028.backend.service.MarcaService;
import es.cic.curso.curso18.ejercicio028.backend.service.TipoVehiculoService;
import es.cic.curso.curso18.ejercicio028.backend.service.VehiculoService;

public class GestionVehiculos extends HorizontalLayout {

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

	MarcaService marcaService;
	TipoVehiculoService tipoVehiculoService;
	VehiculoService vehiculoService;

	Button anadir;
	TextField nombreVehiculo;
	TextField matricula;
	Button confirmarAnadir;
	Button confirmarEliminar;
	Button confirmarModificar;

	ComboBox tipoVehiculo;
	ComboBox marca;

	Button cancelar;

	Grid maestro;

	List<Vehiculo> vehiculoLista;

	VehiculoDTO vehiculo;

	public GestionVehiculos() {
		super();
		definirBean();

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

		nombreVehiculo = new TextField("Introduce El Nombre Del Vehículo:");
		nombreVehiculo.setSizeFull();

		matricula = new TextField("Introduce La Matrícula:");
		matricula.setSizeFull();
		
		marca = new ComboBox();
		
		for (Marca marcaSacada : marcaService.obtenerMarcas()) {
			marca.addItem(marcaSacada.getNombre());
		}
		
		tipoVehiculo = new ComboBox(); 
		
		for (TipoVehiculo tipoVehiculoSacado : tipoVehiculoService.obtenerTipoVehiculos()) {
			marca.addItem(tipoVehiculoSacado.getTipo());
		}
		
		panelIntroducirDatos.addComponents(nombreVehiculo, matricula,marca,tipoVehiculo);

		confirmarAnadir = new Button("Añadir");
		confirmarEliminar = new Button("Eliminar");
		confirmarModificar = new Button("Modificar");

		cancelar = new Button("Cancelar");

		confirmarAnadir.addClickListener(e -> {
			TipoVehiculo meterTipoVehiculo;
			Marca meterMarca;

			for (TipoVehiculo tipoVehiculoSacado : tipoVehiculoService.obtenerTipoVehiculos()) {

				if (tipoVehiculoSacado.getTipo().equals(tipoVehiculo.getValue().toString()))
					meterTipoVehiculo = tipoVehiculoSacado;
			}
			
			for (Marca marcaSacada : marcaService.obtenerMarcas()) {

				if (marcaSacada.getNombre().equals(marca.getValue().toString()))
					meterMarca = marcaSacada;
			}

			vehiculoService.aniadirVehiculo(nombreVehiculo.getValue(), matricula.getValue(),meterTipoVehiculo,meterMarca);
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
		});

		confirmarEliminar.addClickListener(e -> {
			
			TipoVehiculo meterTipoVehiculo;
			Marca meterMarca;

			for (TipoVehiculo tipoVehiculoSacado : tipoVehiculoService.obtenerTipoVehiculos()) {

				if (tipoVehiculoSacado.getTipo().equals(tipoVehiculo.getValue().toString()))
					meterTipoVehiculo = tipoVehiculoSacado;
			}
			
			for (Marca marcaSacada : marcaService.obtenerMarcas()) {

				if (marcaSacada.getNombre().equals(marca.getValue().toString()))
					meterMarca = marcaSacada;
			}
			
			for (Vehiculo vehiculoSacado : vehiculoService.obtenerVehiculos()) {
				if (vehiculoSacado.getNombre().equals(nombreVehiculo.getValue())
						&& (vehiculoSacado.getMatricula().equals(matricula.getValue()))
						&& (vehiculoSacado.getTipoVehiculo().equals(meterTipoVehiculo))
						&& (vehiculoSacado.getMarca().equals(meterMarca))
						) {
					vehiculoService.borrarVehiculo(vehiculoSacado.getId());
				}
			}
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
		});

		confirmarModificar.addClickListener(e -> {
			TipoVehiculo meterTipoVehiculo;
			Marca meterMarca;

			for (TipoVehiculo tipoVehiculoSacado : tipoVehiculoService.obtenerTipoVehiculos()) {

				if (tipoVehiculoSacado.getTipo().equals(tipoVehiculo.getValue().toString()))
					meterTipoVehiculo = tipoVehiculoSacado;
			}
			
			for (Marca marcaSacada : marcaService.obtenerMarcas()) {

				if (marcaSacada.getNombre().equals(marca.getValue().toString()))
					meterMarca = marcaSacada;
			}

			vehiculoService.aniadirVehiculo(nombreVehiculo.getValue(), matricula.getValue(),meterTipoVehiculo,meterMarca);
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
		});

		cancelar.addClickListener(e -> {
			limpiarCampos();

			controladorPrimerosprimarios(3);
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

	public VehiculoDTO getVehiculo() {
		return vehiculo;
	}

	public void setVehiculoDTO(VehiculoDTO vehiculo) {
		this.vehiculo = vehiculo;

		if (vehiculo != null) {
			BeanFieldGroup.bindFieldsUnbuffered(vehiculo, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new VehiculoDTO(), this);
		}
	}

	private void definirPanelGrid() {
		vehiculoLista = new ArrayList<>();

		panelGrid = new HorizontalLayout();
		vehiculoLista = vehiculoService.obtenerVehiculos();

		maestro = new Grid();
		maestro.setColumns("nombreVehiculo", "matricula", "tipo", "nombreMarca");

		cargaGrid();

		maestro.addSelectionListener(e -> {
			VehiculoDTO vehiculo = null;
			if (!e.getSelected().isEmpty()) {
				vehiculo = (VehiculoDTO) e.getSelected().iterator().next();
				nombreVehiculo.setValue(vehiculo.getTipo());

				controladorPrimerosprimarios(3);
			}
			setVehiculoDTO(vehiculo);
		});

		panelGrid.addComponent(maestro);
		panelGrid.setMargin(true);
		panelGrid.setSpacing(true);

	}

	public void annadirVehiculo(Vehiculo vehiculo) {

		vehiculoService.aniadirVehiculo(vehiculo.getNombre(), vehiculo.getMatricula(), vehiculo.getTipoVehiculo(),
				vehiculo.getMarca());

		cargaGrid();
	}

	public void eliminarVehiculo(Vehiculo vehiculo) {

		vehiculoService.borrarVehiculo(vehiculo.getId());

		cargaGrid();
	}

	public void cargaGrid() {
		maestro.setContainerDataSource(new BeanItemContainer<>(Vehiculo.class, vehiculoService.obtenerVehiculos()));
	}

	private void definirBean() {
		vehiculoService = ContextLoader.getCurrentWebApplicationContext().getBean(VehiculoService.class);
		tipoVehiculoService = ContextLoader.getCurrentWebApplicationContext().getBean(TipoVehiculoService.class);
		marcaService = ContextLoader.getCurrentWebApplicationContext().getBean(MarcaService.class);
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

		nombreVehiculo.setValue("");
		matricula.setValue("");

		confirmarAnadir.setVisible(false);
		confirmarEliminar.setVisible(false);
		confirmarModificar.setVisible(false);
		cancelar.setVisible(false);
		nombreVehiculo.setVisible(false);
		matricula.setVisible(false);

		anadir.setEnabled(true);
	}

	private void verPanelDatos() {
		nombreVehiculo.setVisible(true);
		matricula.setVisible(true);
	}

}