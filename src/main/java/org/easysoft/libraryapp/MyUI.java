package org.easysoft.libraryapp;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.easysoft.libraryapp.component.MyLabel;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	// Add the next two lines:
	private CustomerService service = CustomerService.getInstance();
	private Grid<Customer> grid = new Grid<>(Customer.class);

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		
		MyLabel label = new MyLabel("Cissio");
		layout.addComponent(label);
		

		// fetch list of Customers from service and assign it to Grid
	    List<Customer> customers = service.findAll();
	    grid.setItems(customers);
	    
	    layout.addComponent(grid);

	    setContent(layout);
	}

	public void updateList() {
		List<Customer> customers = service.findAll();
		grid.setItems(customers);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
