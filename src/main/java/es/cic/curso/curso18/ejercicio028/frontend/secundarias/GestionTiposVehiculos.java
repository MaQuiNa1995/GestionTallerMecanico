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
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso18.ejercicio028.backend.dominio.TipoVehiculo;
import es.cic.curso.curso18.ejercicio028.backend.service.TipoVehiculoService;
import es.cic.curso.curso18.ejercicio028.backend.service.VehiculoService;

public class GestionTiposVehiculos extends VerticalLayout {

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

	TipoVehiculoService tipoVehiculoService;
	VehiculoService vehiculoService;

	Button anadir;
	Button eliminar;
	TextField tipo;
	TextField descripcion;
	Button confirmar;
	Button cancelar;

	Grid maestro;

	List<TipoVehiculo> tipoVehiculoLista;

	TipoVehiculo tipoVehiculo;

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

		// TODO Funcionalidad boton
		anadir = new Button("Dar De Alta");
		anadir.addClickListener(e -> {
			controladorEstados(1);
		});
		
		// TODO Funcionalidad boton
		eliminar = new Button("Dar De Baja");
		eliminar.addClickListener(e -> {
			controladorEstados(2);
		});
		
		panelBotones.addComponents(anadir, eliminar);
	}

	private void definirPanelDatos() {

		panelDatos = new VerticalLayout();

		VerticalLayout panelIntroducirDatos = new VerticalLayout();
		HorizontalLayout panelConfirmacion = new HorizontalLayout();

		tipo = new TextField("Introduce El Tipo Del Vehiculo:");
		tipo.setSizeFull();
		
		descripcion = new TextField("Introduce La Descripción:");
		descripcion.setSizeFull();
		
		Label etiquetaCombo = new Label("");

		panelIntroducirDatos.addComponents(tipo, descripcion);

		confirmar = new Button("Confirmar");

		// TODO Los visibles
		confirmar.addClickListener(e -> {
			//controladorEstados(1);
		});

		cancelar = new Button("Cancelar");

		cancelar.addClickListener(e -> {
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
		tipoVehiculoLista = new ArrayList<>();

		panelGrid = new HorizontalLayout();
		tipoVehiculoLista = tipoVehiculoService.obtenerTipoVehiculos();

		maestro = new Grid();
		maestro.setColumns("tipo", "descripcion");

		cargaGrid();

		maestro.addSelectionListener(e -> {
			TipoVehiculo tipoVehiculo = null;
			if (!e.getSelected().isEmpty()) {
				tipoVehiculo = (TipoVehiculo) e.getSelected().iterator().next();
				controladorEstados(1);
			}
			setTipoVehiculo(tipoVehiculo);
		});

		panelGrid.addComponent(maestro);
		panelGrid.setMargin(true);
		panelGrid.setSpacing(true);

	}

	private void modificarTipoVehiculo(TipoVehiculo tipoVehiculo) {
		tipoVehiculoService.actualizarTipoVehiculo(tipoVehiculo);
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
		maestro.setContainerDataSource(new BeanItemContainer<>(TipoVehiculo.class, tipoVehiculoService.obtenerTipoVehiculos()));
	}

	private void definirBean() {
		tipoVehiculoService = ContextLoader.getCurrentWebApplicationContext().getBean(TipoVehiculoService.class);
	}

	private void generaBBDD() {

		TipoVehiculo tipoVehiculo = new TipoVehiculo("Moto", "Vehiculo de 2 Ruedas");
		tipoVehiculoService.aniadirTipoVehiculo(tipoVehiculo.getTipo(), tipoVehiculo.getDescripcion());

		
		TipoVehiculo tipoVehiculo2 = new TipoVehiculo("Coche", "Vehiculo de 4 Ruedas");
		tipoVehiculoService.aniadirTipoVehiculo(tipoVehiculo2.getTipo(), tipoVehiculo2.getDescripcion());
	}

	private void controladorEstados(int opcion) {
		
		anadir.setEnabled(false);
		eliminar.setEnabled(false);
		
		switch (opcion){
		
		// Agregar
		case 1:
			verPanelDatos();
			
			tipoVehiculoService.aniadirTipoVehiculo(tipo.getValue(), descripcion.getValue());
			cargaGrid();
			
			limpiarCampos();
			break;
		//Eliminar
		case 2:
			verPanelDatos();
			for (TipoVehiculo tipoVehiculoSacado : tipoVehiculoService.obtenerTipoVehiculos()) {
				if (tipoVehiculoSacado.getTipo().equals(tipo.getValue()) &&
						(tipoVehiculoSacado.getDescripcion().equals(descripcion.getValue()))){
					tipoVehiculoService.borrarTipoVehiculo(tipoVehiculoSacado.getId());
				}
			}
			limpiarCampos();
			break;
			//Modificar
		case 3:
			verPanelDatos();
			for (TipoVehiculo tipoVehiculoSacado : tipoVehiculoService.obtenerTipoVehiculos()) {
				if (tipoVehiculoSacado.getTipo().equals(tipo.getValue()) &&
						(tipoVehiculoSacado.getDescripcion().equals(descripcion.getValue()))){
					tipoVehiculoService.actualizarTipoVehiculo(tipoVehiculoSacado);
				}
			}
			limpiarCampos();
			break;
		}
	}

	private void limpiarCampos() {
		
		tipo.setValue("");
		descripcion.setValue("");
		
		confirmar.setVisible(false);
		cancelar.setVisible(false);
		tipo.setVisible(false);
		descripcion.setVisible(false);
		
		anadir.setEnabled(true);
		eliminar.setEnabled(true);
	}
	
	private void verPanelDatos(){
		

		
		confirmar.setVisible(true);
		cancelar.setVisible(true);
		tipo.setVisible(true);
		descripcion.setVisible(true);
	}

}
