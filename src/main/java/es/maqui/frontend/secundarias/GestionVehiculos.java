package es.maqui.frontend.secundarias;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.maqui.backend.dominio.Marca;
import es.maqui.backend.dominio.TipoVehiculo;
import es.maqui.backend.dominio.Vehiculo;
import es.maqui.backend.dto.VehiculoDTO;
import es.maqui.backend.service.MarcaService;
import es.maqui.backend.service.TipoVehiculoService;
import es.maqui.backend.service.VehiculoService;
import es.maqui.frontend.utilities.Validador;

public class GestionVehiculos extends HorizontalLayout {

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
	private TipoVehiculoService tipoVehiculoService;
	private VehiculoService vehiculoService;

	private TextField nombreVehiculo;
	private TextField matricula;
	
	private Button anadir;
	private Button confirmarAnadir;
	private Button confirmarEliminar;
	private Button confirmarModificar;
	private Button cancelar;

	private ComboBox tipoVehiculoCombo;
	private ComboBox marcasCombo;

	private String nombrevehiculoSeleccionado = "";
	private String matriculaSeleccionada = "";
	private String tipoVehiculoSeleccionado = "";
	private String marcaVehiculoSeleccionado = "";

	private Grid maestro;

	private List<VehiculoDTO> vehiculoDTOLista;

	private VehiculoDTO vehiculo;

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
			rellenarCombos();
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

		marcasCombo = new ComboBox("Selecciona La Marca:");
		marcasCombo.setVisible(false);
		tipoVehiculoCombo = new ComboBox("Selecciona El Tipo De Vehículo");
		tipoVehiculoCombo.setVisible(false);
		rellenarCombos();

		panelIntroducirDatos.addComponents(nombreVehiculo, matricula, marcasCombo, tipoVehiculoCombo);

		confirmarAnadir = new Button("Añadir");
		confirmarEliminar = new Button("Eliminar");
		confirmarModificar = new Button("Modificar");

		cancelar = new Button("Cancelar");
		cancelar.addClickListener(e -> {
			controladorPrimerosprimarios(2);
		});

		confirmarAnadir.addClickListener(e -> {

			Validador validar= new Validador();
			
			if (!nombreVehiculo.getValue().isEmpty() && !matricula.getValue().isEmpty() && validar.validarMatricula(matricula.getValue())) {

				TipoVehiculo meterTipoVehiculo = null;
				Marca meterMarca = null;

				for (TipoVehiculo tipoVehiculoSacado : tipoVehiculoService.obtenerTipoVehiculos()) {

					if (tipoVehiculoSacado.getTipo().equals(tipoVehiculoCombo.getValue()))
						meterTipoVehiculo = tipoVehiculoSacado;
				}

				for (Marca marcaSacada : marcaService.obtenerMarcas()) {

					if (marcaSacada.getNombre().equals(marcasCombo.getValue()))
						meterMarca = marcaSacada;
				}

				vehiculoService.aniadirVehiculo(nombreVehiculo.getValue(), matricula.getValue(), meterTipoVehiculo,
						meterMarca);

				VehiculoDTO vehiculoDTO = new VehiculoDTO();
				vehiculoDTO.setNombreVehiculo(nombreVehiculo.getValue());
				vehiculoDTO.setMatricula(matricula.getValue());
				vehiculoDTO.setTipo(meterTipoVehiculo.getTipo());
				vehiculoDTO.setNombreMarca(meterMarca.getNombre());

				vehiculoDTOLista.add(vehiculoDTO);
				cargaGrid();
				controladorPrimerosprimarios(3);
				limpiarCampos();
			} else {
				String mostrarMensaje="";
				if(!validar.validarMatricula(matricula.getValue())){
					mostrarMensaje="Introduce una matrícula Valida ";
				}
				mostrarNotificacion("Rellene Todos Los Campos: "+mostrarMensaje);
			}
		});

		confirmarEliminar.addClickListener(e -> {
			int contador = 0;

			for (Vehiculo vehiculoParaModificar : vehiculoService.obtenerVehiculos()) {

				if (vehiculoParaModificar.getNombre().equals(nombreVehiculo.getValue())
						&& (vehiculoParaModificar.getMatricula().equals(matriculaSeleccionada))) {

					VehiculoDTO vehiculoDTO = new VehiculoDTO();
					vehiculoDTO.setNombreVehiculo(nombrevehiculoSeleccionado);
					vehiculoDTO.setMatricula(matriculaSeleccionada);
					vehiculoDTO.setTipo(tipoVehiculoSeleccionado);
					vehiculoDTO.setNombreMarca(marcaVehiculoSeleccionado);

					vehiculoDTOLista.remove(contador);

					eliminarVehiculo(vehiculoParaModificar);

					nombrevehiculoSeleccionado = "";
					matriculaSeleccionada = "";
					tipoVehiculoSeleccionado = "";
					marcaVehiculoSeleccionado = "";
				}

				contador = contador++;

			}
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
		});

		confirmarModificar.addClickListener(e -> {

			TipoVehiculo meterTipoVehiculo = null;
			Marca meterMarca = null;

			for (TipoVehiculo tipoVehiculoSacado : tipoVehiculoService.obtenerTipoVehiculos()) {

				if (tipoVehiculoSacado.getTipo().equals(tipoVehiculoCombo.getValue()))
					meterTipoVehiculo = tipoVehiculoSacado;
			}

			for (Marca marcaSacada : marcaService.obtenerMarcas()) {

				if (marcaSacada.getNombre().equals(marcasCombo.getValue()))
					meterMarca = marcaSacada;
			}

			int contador = 0;
			for (Vehiculo vehiculoParaModificar : vehiculoService.obtenerVehiculos()) {
				
				if (vehiculoParaModificar.getNombre().equals(nombreVehiculo.getValue())
						&& (vehiculoParaModificar.getMatricula().equals(matricula.getValue()))) {

					VehiculoDTO vehiculoDTO = new VehiculoDTO();
					vehiculoDTO.setNombreVehiculo(nombreVehiculo.getValue());
					vehiculoDTO.setMatricula(matricula.getValue());
					vehiculoDTO.setTipo(meterTipoVehiculo.getTipo());
					vehiculoDTO.setNombreMarca(meterMarca.getNombre());

					setVehiculoDTO(vehiculoDTO);
					
					vehiculoDTOLista.set(contador, vehiculoDTO);
					cargaGrid();
					
					Vehiculo vehiculoMeter = vehiculoService.obtenerVehiculo(vehiculoParaModificar.getId());
					
					vehiculoMeter.setNombre(nombreVehiculo.getValue());
					vehiculoMeter.setMatricula(matricula.getValue());
					vehiculoMeter.setTipoVehiculo(meterTipoVehiculo);
					vehiculoMeter.setMarca(meterMarca);
					
					modificarVehiculo(vehiculoMeter);

					nombrevehiculoSeleccionado = "";
					matriculaSeleccionada = "";
					tipoVehiculoSeleccionado = "";
					marcaVehiculoSeleccionado = "";

				}
				contador++;

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

	private void modificarVehiculo(Vehiculo modificada) {
		vehiculoService.actualizarVehiculo(modificada);

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

	private void mostrarNotificacion(String mostrarCadena) {
		Notification.show(mostrarCadena);
	}

	private void definirPanelGrid() {
		vehiculoDTOLista = new ArrayList<>();

		panelGrid = new HorizontalLayout();

		maestro = new Grid();
		maestro.setColumns("nombreVehiculo", "matricula", "tipo", "nombreMarca");

		cargaGrid();

		maestro.addSelectionListener(e -> {
			VehiculoDTO vehiculo = null;
			if (!e.getSelected().isEmpty()) {
				vehiculo = (VehiculoDTO) e.getSelected().iterator().next();
				nombreVehiculo.setValue(vehiculo.getTipo());

				tipoVehiculoCombo.select(vehiculo.getTipo());
				marcasCombo.select(vehiculo.getNombreMarca());

				matriculaSeleccionada = matricula.getValue();
				nombrevehiculoSeleccionado = vehiculo.getNombreVehiculo();
				
				matriculaSeleccionada = vehiculo.getMatricula();
				marcaVehiculoSeleccionado = vehiculo.getNombreMarca();
				tipoVehiculoSeleccionado = vehiculo.getTipo();

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
		maestro.setContainerDataSource(new BeanItemContainer<>(VehiculoDTO.class, vehiculoDTOLista));
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

			anadir.setEnabled(false);

			cancelar.setVisible(true);

			confirmarAnadir.setVisible(true);
			confirmarEliminar.setVisible(false);
			confirmarModificar.setVisible(false);

			verPanelDatos();
			break;
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

	private void rellenarCombos() {
		for (TipoVehiculo tipoVehiculoSacado : tipoVehiculoService.obtenerTipoVehiculos()) {

			tipoVehiculoCombo.addItem(tipoVehiculoSacado.getTipo());
		}

		for (Marca vehiculoSacado : marcaService.obtenerMarcas()) {

			marcasCombo.addItem(vehiculoSacado.getNombre());
		}
	}

	private void limpiarCampos() {

		nombreVehiculo.setValue("");
		matricula.setValue("");

		nombreVehiculo.setVisible(false);
		matricula.setVisible(false);

		confirmarAnadir.setVisible(false);
		confirmarEliminar.setVisible(false);
		confirmarModificar.setVisible(false);
		cancelar.setVisible(false);
		nombreVehiculo.setVisible(false);
		matricula.setVisible(false);

		tipoVehiculoCombo.setVisible(false);
		tipoVehiculoCombo.select(null);

		marcasCombo.setVisible(false);
		marcasCombo.select(null);

		anadir.setEnabled(true);
	}

	private void verPanelDatos() {

		tipoVehiculoCombo.setVisible(true);
		marcasCombo.setVisible(true);
		nombreVehiculo.setVisible(true);
		matricula.setVisible(true);
	}

}
