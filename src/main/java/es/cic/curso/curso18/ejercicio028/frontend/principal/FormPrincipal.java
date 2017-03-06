package es.cic.curso.curso18.ejercicio028.frontend.principal;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;

import com.vaadin.ui.HorizontalLayout;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso18.ejercicio028.frontend.secundarias.GestionAverias;
import es.cic.curso.curso18.ejercicio028.frontend.secundarias.GestionMarcas;
import es.cic.curso.curso18.ejercicio028.frontend.secundarias.GestionTiposVehiculos;
import es.cic.curso.curso18.ejercicio028.frontend.secundarias.GestionVehiculos;

@Title("Mechanic Manager")
@Theme("mytheme")
public class FormPrincipal extends UI {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -1598331557630407298L;

	HorizontalLayout panelTodo = null;
	TabSheet menu = null;

	@Override
	protected void init(VaadinRequest request) {
		definirPanelTodo();
		definirMenuPestanas();
		this.setContent(panelTodo);
	}

	private void definirPanelTodo() {
		panelTodo = new HorizontalLayout();
		panelTodo.setMargin(true);
		panelTodo.setSpacing(true);
		panelTodo.setWidth("100%");
	}

	private void definirMenuPestanas() {
		menu = new TabSheet();

		menu.setHeight(100.f, Unit.PERCENTAGE);

		VerticalLayout gestionTiposVehiculos = new GestionTiposVehiculos();
		gestionTiposVehiculos.setSizeFull();
		HorizontalLayout gestionAverias = new GestionAverias();
		gestionAverias.setSizeFull();
		HorizontalLayout gestionMarcas = new GestionMarcas();
		gestionMarcas.setSizeFull();
		HorizontalLayout gestionVehiculos = new GestionVehiculos();
		gestionMarcas.setSizeFull();

		menu.addTab(gestionTiposVehiculos, "Tipos De Vehiculos");
		menu.addTab(gestionAverias, "Averías");
		menu.addTab(gestionMarcas, "Marcas");
		menu.addTab(gestionVehiculos, "Vehículos");
		// TODO Picar ultima Interfaz
		// menu.addTab(registroIncidencias, "Registro De Incidencias");

		panelTodo.addComponent(menu);
	}
	   @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	    @VaadinServletConfiguration(ui = FormPrincipal.class, productionMode = false)
	    public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = -692740140427143858L;	 
	   }
}
