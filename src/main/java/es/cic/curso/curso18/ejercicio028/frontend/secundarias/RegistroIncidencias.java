package es.cic.curso.curso18.ejercicio028.frontend.secundarias;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso18.ejercicio028.backend.dominio.Averia;
import es.cic.curso.curso18.ejercicio028.backend.dominio.RegistroAverias;
import es.cic.curso.curso18.ejercicio028.backend.dominio.Vehiculo;
import es.cic.curso.curso18.ejercicio028.backend.dominio.VehiculosTienenAverias;
import es.cic.curso.curso18.ejercicio028.backend.dto.VehiculoAveriaDTO;
import es.cic.curso.curso18.ejercicio028.backend.service.AveriaService;
import es.cic.curso.curso18.ejercicio028.backend.service.RegistroAveriasService;
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
	VehiculosTienenAveriasService vehiculosTienenAveriasService;

	DateField fechaEscogida;

	Button anadir;
	Button confirmarAnadir;
	Button confirmarEliminar;
	Button confirmarModificar;

	ComboBox averiasCombo;
	ComboBox matriculasVehiculosCombo;

	Button cancelar;

	Grid maestro;

	DateFormat fechaHora;
	TextField fechaParaModificar;

	String convertido;

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
			rellenarCombos();
			verPanelDatos(false);
		});

		panelBotones.addComponent(anadir);
	}

	private void definirPanelDatos() {

		panelDatos = new VerticalLayout();

		VerticalLayout panelIntroducirDatos = new VerticalLayout();
		HorizontalLayout panelConfirmacion = new HorizontalLayout();

		averiasCombo = new ComboBox("Introduce La Avería:");

		fechaEscogida = new DateField("Introduce La Fecha:");
		fechaParaModificar = new TextField();
		fechaParaModificar.setVisible(false);

		matriculasVehiculosCombo = new ComboBox("Introduce La Matrícula:");

		rellenarCombos();
		
		panelIntroducirDatos.addComponents(averiasCombo, fechaEscogida, fechaParaModificar, matriculasVehiculosCombo);

		confirmarAnadir = new Button("Añadir");
		confirmarEliminar = new Button("Eliminar");
		confirmarModificar = new Button("Modificar");

		cancelar = new Button("Cancelar");
		
		cancelar.addClickListener(e -> {
			controladorPrimerosprimarios(2);
		});

		confirmarAnadir.addClickListener(e -> {
			
			if (averiasCombo.getValue()!=null && matriculasVehiculosCombo.getValue()!=null){
			
			Averia meterAveria = null;
			RegistroAverias meterRegistroAveria = null;
			Vehiculo meterVehiculo = null;
			
			for (Averia averiaSacado : averiaService.obtenerAverias()) {

				if (averiaSacado.getNombre().equals(averiasCombo.getValue()))
					meterAveria = averiaSacado;
			}

			fechaHora = new SimpleDateFormat("yyyy-MM-dd ");
			convertido = fechaHora.format(fechaEscogida.getValue());

			for (RegistroAverias registroAveriaSacado : registroAveriasService.obtenerRegistroAveriass()) {

				if (registroAveriaSacado.getFecha().equals(convertido))
					meterRegistroAveria = registroAveriaSacado;
			}

			for (Vehiculo vehiculoAveriaSacado : vehiculoService.obtenerVehiculos()) {

				if (vehiculoAveriaSacado.getMatricula().equals(matriculasVehiculosCombo.getValue()))
					meterVehiculo = vehiculoAveriaSacado;
			}
			registroAveriasService.aniadirRegistroAverias(convertido, meterAveria);
			vehiculosTienenAveriasService.aniadirVehiculosTienenAverias(meterRegistroAveria, meterVehiculo);

			VehiculoAveriaDTO vehiculoDTO = new VehiculoAveriaDTO();

			vehiculoDTO.setFechaAveria(convertido);
			vehiculoDTO.setMatriculaVehiculo(meterVehiculo.getMatricula());
			vehiculoDTO.setNombreAveria(meterAveria.getNombre());

			vehiculoAveriaDTOLista.add(vehiculoDTO);
			cargaGrid();
			controladorPrimerosprimarios(3);
			limpiarCampos();
			}else{
				mostrarNotificacion("Rellene Todos Los Campos");
			}
		});

		confirmarEliminar.addClickListener(e -> {

			Averia meterAveria = null;
			Vehiculo meterVehiculo = null;

			for (Averia averiaSacado : averiaService.obtenerAverias()) {

				if (averiaSacado.getNombre().equals(averiasCombo.getValue()))
					meterAveria = averiaSacado;
			}

			fechaHora = new SimpleDateFormat("yyyy-MM-dd ");
			convertido = fechaHora.format(fechaEscogida.getValue());

			for (Vehiculo vehiculoAveriaSacado : vehiculoService.obtenerVehiculos()) {

				if (vehiculoAveriaSacado.getNombre().equals(matriculasVehiculosCombo.getValue()))
					meterVehiculo = vehiculoAveriaSacado;
			}
			
			for (VehiculosTienenAverias objetoSacado : vehiculosTienenAveriasService.obtenerVehiculosTienenAverias()) {
				
				//VehiculosTienenAverias objetoBorrar = vehiculosTienenAveriasService.obtenerVehiculosTienenAveria(objetoSacado.getId());
				vehiculosTienenAveriasService.borrarVehiculosTienenAverias(objetoSacado.getId());
			}
			

			VehiculoAveriaDTO vehiculoDTO = new VehiculoAveriaDTO();
			vehiculoDTO.setNombreAveria(meterAveria.getNombre());

			vehiculoDTO.setFechaAveria(fechaParaModificar.getValue());
			vehiculoDTO.setMatriculaVehiculo(meterVehiculo.getNombre());
			vehiculoDTO.setNombreAveria(meterAveria.getNombre());

			vehiculoAveriaDTOLista.remove(vehiculoDTO);
			cargaGrid();
			
			controladorPrimerosprimarios(3);
			limpiarCampos();
		});

		confirmarModificar.addClickListener(e -> {
			Averia meterAveria = null;
			RegistroAverias meterRegistroAveria = null;
			Vehiculo meterVehiculo = null;

			for (Averia averiaSacado : averiaService.obtenerAverias()) {

				if (averiaSacado.getNombre().equals(averiasCombo.getValue()))
					meterAveria = averiaSacado;
			}

//			fechaHora = new SimpleDateFormat("dd-MM-yyyy");
//			convertido = fechaHora.format(fechaEscogida.getValue());

			for (RegistroAverias registroAveriaSacado : registroAveriasService.obtenerRegistroAveriass()) {

				if (registroAveriaSacado.getFecha().equals(convertido))
					meterRegistroAveria = registroAveriaSacado;
			}

			for (Vehiculo vehiculoAveriaSacado : vehiculoService.obtenerVehiculos()) {

				if (vehiculoAveriaSacado.getMatricula().equals(matriculasVehiculosCombo.getValue()))
					meterVehiculo = vehiculoAveriaSacado;
			}
			
			vehiculosTienenAveriasService.actualizarVehiculosTienenAverias(new VehiculosTienenAverias(meterRegistroAveria,meterVehiculo));

			VehiculoAveriaDTO vehiculoDTO = new VehiculoAveriaDTO();

			vehiculoDTO.setFechaAveria(convertido);
			vehiculoDTO.setMatriculaVehiculo(meterVehiculo.getMatricula());
			vehiculoDTO.setNombreAveria(meterAveria.getNombre());

			vehiculoAveriaDTOLista.add(vehiculoDTO);
			cargaGrid();
			controladorPrimerosprimarios(4);
			limpiarCampos();
		});

		panelConfirmacion.addComponents(confirmarAnadir, confirmarEliminar, confirmarModificar, cancelar);

		panelDatos.addComponents(panelIntroducirDatos, panelConfirmacion);

	}
	
	private void mostrarNotificacion(String mostrarCadena){
		Notification notificacion = new Notification(mostrarCadena);
		notificacion.show(mostrarCadena);	
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
		maestro.setColumns("nombreAveria", "fechaAveria", "matriculaVehiculo");

		cargaGrid();

		maestro.addSelectionListener(e -> {
			VehiculoAveriaDTO vehiculo = null;
			
			if (!e.getSelected().isEmpty()) {
				vehiculo = (VehiculoAveriaDTO) e.getSelected().iterator().next();
				
				controladorPrimerosprimarios(4);
				
				matriculasVehiculosCombo.select(vehiculo.getMatriculaVehiculo());
				averiasCombo.select(vehiculo.getNombreAveria());
				fechaParaModificar.setValue(vehiculo.getFechaAveria());
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
		vehiculosTienenAveriasService = ContextLoader.getCurrentWebApplicationContext().getBean(VehiculosTienenAveriasService.class);
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

			break;
			
			
			
		case 2:
			limpiarCampos();
			break;
		case 3:
			anadir.setEnabled(false);
			
			cancelar.setVisible(true);
			verPanelDatos(false);

			confirmarAnadir.setVisible(false);
			confirmarEliminar.setVisible(true);
			confirmarModificar.setVisible(true);

			break;
			//Modificar
		case 4:
			anadir.setEnabled(false);
			
			fechaEscogida.setVisible(false);
			
			cancelar.setVisible(true);
			verPanelDatos(true);
			
			confirmarModificar.setVisible(true);
			break;
		}

	}

	private void rellenarCombos() {
		for (Averia averiaSacado : averiaService.obtenerAverias()) {

			averiasCombo.addItem(averiaSacado.getNombre());
		}

		for (Vehiculo vehiculoSacado : vehiculoService.obtenerVehiculos()) {
			
			matriculasVehiculosCombo.addItem(vehiculoSacado.getMatricula());
		}
	}

	private void limpiarCampos() {

		
		averiasCombo.setValue(null);
		fechaEscogida.clear();
		fechaParaModificar.setValue("");
		matriculasVehiculosCombo.setValue(null);
		
		averiasCombo.setVisible(false);
		fechaEscogida.setVisible(false);
		fechaParaModificar.setVisible(false);
		matriculasVehiculosCombo.setVisible(false);

		confirmarAnadir.setVisible(false);
		confirmarEliminar.setVisible(false);
		confirmarModificar.setVisible(false);
		
		cancelar.setVisible(false);
		
		anadir.setEnabled(true);
	}

	private void verPanelDatos(boolean modificar) {

		averiasCombo.setVisible(true);
		fechaEscogida.setVisible(!modificar);
		fechaParaModificar.setVisible(modificar);
		matriculasVehiculosCombo.setVisible(true);

	}

}
