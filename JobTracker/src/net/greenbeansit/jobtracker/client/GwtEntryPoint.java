package net.greenbeansit.jobtracker.client;

import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
class GwtEntryPoint implements EntryPoint
{
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	private static final String SERVICE_ROOT = GWT.getHostPageBaseURL() + "api/";
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
