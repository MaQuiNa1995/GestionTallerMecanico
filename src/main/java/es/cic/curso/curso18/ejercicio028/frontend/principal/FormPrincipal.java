package es.cic.curso.curso18.ejercicio028.frontend.principal;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import es.cic.curso.curso18.ejercicio028.frontend.secundarias.GestionAverias;
import es.cic.curso.curso18.ejercicio028.frontend.secundarias.GestionTiposVehiculos;

@Theme("myTheme")
public class FormPrincipal extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1598331557630407298L;

	HorizontalLayout panelTodo=null;
	TabSheet menu=null;
	
	@Override
	protected void init(VaadinRequest request) {
		definirPanelTodo();
		definirMenuPestanas();
		this.setContent(panelTodo);
	}
	
	private void definirPanelTodo(){
		panelTodo = new HorizontalLayout();
		panelTodo.setMargin(true);
		panelTodo.setSpacing(true);
		panelTodo.setWidth("100%");
	}
	
	private void definirMenuPestanas(){
		menu = new TabSheet();
        menu.setHeight(100.0f, Unit.PERCENTAGE);
        
        menu.setHeight(100.f, Unit.PERCENTAGE);
        menu.addStyleName(ValoTheme.TABSHEET_FRAMED);
        menu.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        
        
		VerticalLayout gestionTiposVehiculos = new GestionTiposVehiculos();
		VerticalLayout gestionAverias = new GestionAverias();
        
        menu.addTab(gestionTiposVehiculos, "Gestión Tipos De Vehiculos");
        menu.addTab(gestionAverias, "Gestión De Averías");
        
        panelTodo.addComponent(menu);
	}
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = FormPrincipal.class)
	public static class Servlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = -796390695740401802L;
	}
}
