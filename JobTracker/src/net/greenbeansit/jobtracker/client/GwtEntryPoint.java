package net.greenbeansit.jobtracker.client;

import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtEntryPoint implements EntryPoint
{	
	
	/**
	 * Endpoint for the restful api
	 */
	private static final String SERVICE_ROOT = "http://localhost:8080";

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
		Defaults.setServiceRoot(SERVICE_ROOT);
		Application app = new Application();

		Panel rootPanel = RootPanel.get();

		rootPanel.add(app);
	}
}
