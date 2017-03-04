package es.cic.curso.curso18.ejercicio028.frontend.secundarias;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class GestionTiposVehiculos extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2370949761559912513L;
	
	VerticalLayout panelTodo;
	VerticalLayout panelDatos;
	HorizontalLayout panelBotones;
	
	ComboBox comboVehiculos;
	
	public GestionTiposVehiculos() {
		super();
		definirPanelTodo();
		definirPanelDatos();
		definirPanelBotones();
		agregarAPanelTodo();
	}
	
	private void definirPanelBotones(){
		
		panelBotones = new HorizontalLayout();
		
		Button anadir,eliminar; 
		
		anadir = new Button("Dar De Alta");
		eliminar = new Button("Dar De Baja");
		
		panelBotones.addComponents(anadir,eliminar);
	}
	
	private void definirPanelDatos(){
		
		panelDatos = new VerticalLayout();
		
		HorizontalLayout panelIntroducirDatos = new HorizontalLayout();
		HorizontalLayout panelConfirmacion = new HorizontalLayout();
		
		TextField tipo,
				descripcion;
		
		Button confirmar,
				cancelar;
		
		tipo = new TextField("Introduce Nombre Del Tipo De Vehiculo:");
		descripcion = new TextField("Introduce La Descripción:");
		comboVehiculos= new ComboBox();
		
		refrescarComboBox();
		
		panelIntroducirDatos.addComponents(tipo,descripcion,comboVehiculos);
		
		confirmar = new Button("Confirmar");
		cancelar = new Button("Cancelar");
		
		panelConfirmacion.addComponents(confirmar,cancelar);
		
		panelDatos.addComponents(panelIntroducirDatos,panelConfirmacion);
		
	}
	
	private void definirPanelTodo(){
		panelTodo = new VerticalLayout();
		panelTodo.setMargin(true);
		panelTodo.setSpacing(true);
		panelTodo.setWidth("100%");
	}
	
	private void agregarAPanelTodo(){
		panelTodo.addComponents(panelBotones,panelDatos);
		addComponent(panelTodo);
	}
	
	private void refrescarComboBox(){
		
		comboVehiculos.addItem("Moto");
		comboVehiculos.addItem("Coche");
		
//		VehiculoServiceImpl vehiculoService = new VehiculoServiceImpl();
//		
//		List<Vehiculo> listaVehiculos = vehiculoService.obtenerVehiculos();
//		
//		for (Vehiculo vehiculo : listaVehiculos) {
//			comboVehiculos.addItem(vehiculo.getNombre());
//		}
	}
	
	
}
