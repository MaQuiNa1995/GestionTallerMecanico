package es.cic.curso.curso18.ejercicio028.frontend.secundarias;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso18.ejercicio028.backend.DTO.VehiculoAveriaDTO;
import es.cic.curso.curso18.ejercicio028.backend.DTO.VehiculoAveriaDTO;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Marca;
import es.cic.curso.curso18.ejercicio028.backend.dominio.RegistroAverias;
import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;
import es.cic.curso.curso18.ejercicio028.backend.service.AveriaService;
import es.cic.curso.curso18.ejercicio028.backend.service.MarcaService;
import es.cic.curso.curso18.ejercicio028.backend.service.RegistroAveriasService;
import es.cic.curso.curso18.ejercicio028.backend.service.TipoVehiculoService;
import es.cic.curso.curso18.ejercicio028.backend.service.VehiculoService;
import es.cic.curso.curso18.ejercicio028.backend.service.VehiculosTienenAveriasService;

public class RegistroIncidencias extends HorizontalLayout {

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

	VehiculoService vehiculoService;
	RegistroIncidencias registroIncidencias;
	RegistroAveriasService registroAveriasService;
	AveriaService averiaService;
	VehiculosTienenAveriasService vehiculosTienenAverias;

	Button anadir;
	
	TextField nombreVehiculo;
	TextField matricula;
	
	Button confirmarAnadir;
	Button confirmarEliminar;
	Button confirmarModificar;

	ComboBox averiasCombo;
	ComboBox vehiculosCombo;
	ComboBox vehiculosMatriculaCombo;

	Button cancelar;

	Grid maestro;

	List<VehiculoAveriaDTO> vehiculoAveriaDTOLista;

	VehiculoAveriaDTO vehiculo;

	public RegistroIncidencias() {
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
		
		averiasCombo = new ComboBox(); 
		
		for (Averia averiaSacada : averiaService.obtenerAverias()) {
			averiasCombo.addItem(averiaSacada.getNombre());
		}

		DateField fechaEscogida = new DateField();
		
		vehiculosCombo = new ComboBox();
		vehiculosMatriculaCombo = new ComboBox();
		
		for (Vehiculo vehiculoSacada : vehiculoService.obtenerVehiculos()) {
			vehiculosCombo.addItem(vehiculoSacada.getNombre());
			vehiculosMatriculaCombo.addItem(vehiculoSacada.getMatricula());
		}
		
		panelIntroducirDatos.addComponents(averiasCombo,fechaEscogida,vehiculosCombo,vehiculosMatriculaCombo);

		confirmarAnadir = new Button("Añadir");
		confirmarEliminar = new Button("Eliminar");
		confirmarModificar = new Button("Modificar");

		cancelar = new Button("Cancelar");

		confirmarAnadir.addClickListener(e -> {
			Averia meterAveria=null;
			RegistroAverias meterRegistroAveria=null;
			Vehiculo meterVehiculo=null;

			for (Averia averiaSacado : averiaService.obtenerAverias()) {

				if (averiaSacado.getNombre().equals(averiasCombo.getValue()))
					meterAveria = averiaSacado;
			}
			
			for (RegistroAverias registroAveriaSacado : registroAveriasService.obtenerRegistroAveriass()) {

				if (registroAveriaSacado.getFecha().equals(fechaEscogida.getValue().toString()))
					meterRegistroAveria = registroAveriaSacado;
			}
			
			for (Vehiculo registroAveriaSacado : vehiculoService.obtenerVehiculos()) {

				if (registroAveriaSacado.getNombre().equals(vehiculosCombo.getValue()));
				meterVehiculo = registroAveriaSacado;
			}
			
			VehiculoAveriaDTO vehiculoDTO = new VehiculoAveriaDTO();
			vehiculoDTO.setNombreAveria(meterAveria.getNombre());
			vehiculoDTO.setFechaAveria(fechaEscogida.getValue().toString());
			vehiculoDTO.setNombreVehiculo(meterVehiculo.getNombre());
			
			
			vehiculoAveriaDTOLista.add(vehiculoDTO);
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
		});

		confirmarEliminar.addClickListener(e -> {
			
			Averia meterAveria=null;
			RegistroAverias meterRegistroAveria=null;
			Vehiculo meterVehiculo=null;

			for (Averia averiaSacado : averiaService.obtenerAverias()) {

				if (averiaSacado.getNombre().equals(averiasCombo.getValue()))
					meterAveria = averiaSacado;
			}
			
			for (RegistroAverias registroAveriaSacado : registroAveriasService.obtenerRegistroAveriass()) {

				if (registroAveriaSacado.getFecha().equals(fechaEscogida.getValue().toString()))
					meterRegistroAveria = registroAveriaSacado;
			}
			
			for (Vehiculo registroAveriaSacado : vehiculoService.obtenerVehiculos()) {

				if (registroAveriaSacado.getNombre().equals(vehiculosCombo.getValue()));
				meterVehiculo = registroAveriaSacado;
			}
			
			VehiculoAveriaDTO vehiculoDTO = new VehiculoAveriaDTO();
			vehiculoDTO.setNombreAveria(meterAveria.getNombre());
			vehiculoDTO.setFechaAveria(fechaEscogida.getValue().toString());
			vehiculoDTO.setNombreVehiculo(meterVehiculo.getNombre());
			
			
			vehiculoAveriaDTOLista.remove(vehiculoDTO);
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
		});

		confirmarModificar.addClickListener(e -> {
			Averia meterAveria=null;
			RegistroAverias meterRegistroAveria=null;
			Vehiculo meterVehiculo=null;

			for (Averia averiaSacado : averiaService.obtenerAverias()) {

				if (averiaSacado.getNombre().equals(averiasCombo.getValue()))
					meterAveria = averiaSacado;
			}
			
			for (RegistroAverias registroAveriaSacado : registroAveriasService.obtenerRegistroAveriass()) {

				if (registroAveriaSacado.getFecha().equals(fechaEscogida.getValue().toString()))
					meterRegistroAveria = registroAveriaSacado;
			}
			
			for (Vehiculo registroAveriaSacado : vehiculoService.obtenerVehiculos()) {

				if (registroAveriaSacado.getNombre().equals(vehiculosCombo.getValue()));
				meterVehiculo = registroAveriaSacado;
			}
			
			VehiculoAveriaDTO vehiculoDTO = new VehiculoAveriaDTO();
			vehiculoDTO.setNombreAveria(meterAveria.getNombre());
			vehiculoDTO.setFechaAveria(fechaEscogida.getValue().toString());
			vehiculoDTO.setNombreVehiculo(meterVehiculo.getNombre());
			
			vehiculoAveriaDTOLista.add(vehiculoDTO);
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

	public VehiculoAveriaDTO getVehiculo() {
		return vehiculo;
	}

	public void setVehiculoAveriaDTO(VehiculoAveriaDTO vehiculo) {
		this.vehiculo = vehiculo;

		if (vehiculo != null) {
			BeanFieldGroup.bindFieldsUnbuffered(vehiculo, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new VehiculoAveriaDTO(), this);
		}
	}

	private void definirPanelGrid() {
		vehiculoAveriaDTOLista = new ArrayList<>();

		panelGrid = new HorizontalLayout();
		maestro = new Grid();
		maestro.setColumns("nombreAveria", "fechaAveria", "nombreVehiculo", "matriculaVehiculo");

		cargaGrid();

		maestro.addSelectionListener(e -> {
			VehiculoAveriaDTO vehiculo = null;
			if (!e.getSelected().isEmpty()) {
				vehiculo = (VehiculoAveriaDTO) e.getSelected().iterator().next();
				//nombreVehiculo.setValue(vehiculo.getTipo());
				
				controladorPrimerosprimarios(3);
			}
			setVehiculoAveriaDTO(vehiculo);
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
		maestro.setContainerDataSource(new BeanItemContainer<>(VehiculoAveriaDTO.class, vehiculoAveriaDTOLista));
	}

	private void definirBean() {
		vehiculoService = ContextLoader.getCurrentWebApplicationContext().getBean(VehiculoService.class);
		averiaService = ContextLoader.getCurrentWebApplicationContext().getBean(AveriaService.class);
		registroAveriasService = ContextLoader.getCurrentWebApplicationContext().getBean(RegistroAveriasService.class);
		vehiculosTienenAverias = ContextLoader.getCurrentWebApplicationContext().getBean(VehiculosTienenAveriasService.class);
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
