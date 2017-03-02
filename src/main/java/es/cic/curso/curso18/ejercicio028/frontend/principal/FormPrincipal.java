package es.cic.curso.curso18.ejercicio028.frontend.principal;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@Theme("myTheme")
public class FormPrincipal extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1598331557630407298L;

	@Override
	protected void init(VaadinRequest request) {
		
		final HorizontalLayout panelTodo = new HorizontalLayout();
		panelTodo.setMargin(true);
		panelTodo.setSpacing(true);
		panelTodo.setWidth("100%");
		
		Label labelDummie = new Label("Prueba Dummie De La Puesta a Punto De Vaadin");
		
		/**
		 * final VerticalLayout gridFiguras = new GridFiguras();
		 
		
		final VerticalLayout annadirFiguras = new AnnadirFiguras();
		final VerticalLayout eliminarFiguras = new EliminarFiguras();
		final VerticalLayout actualizarFiguras = new ActualizarFiguras();
		final VerticalLayout listarFiguras = new ListarFiguras();

		TabSheet menu = new TabSheet();
        menu.setHeight(100.0f, Unit.PERCENTAGE);
        menu.addTab(, "Añadir Figuras");
        menu.addTab(, "Eliminar Figuras");
        menu.addTab(, "Actualizar Figuras");
        menu.addTab(, "listar Figuras");
        */
	
		panelTodo.addComponent(labelDummie);
		
		setContent(panelTodo);
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
