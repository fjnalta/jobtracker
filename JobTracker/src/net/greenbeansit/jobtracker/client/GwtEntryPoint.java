package net.greenbeansit.jobtracker.client;

import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * @author GreenBeansIT & DESIGN
 */
public class GwtEntryPoint implements EntryPoint
{	
	
	/**
	 * Endpoint for the restful api
	 */
	private static final String SERVICE_ROOT = "http://127.0.0.1:8080";

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
		        public void onUncaughtException(Throwable e) {
		          GWT.log(e.toString());
		      }
		});
		Defaults.setServiceRoot(SERVICE_ROOT);
		Application app = new Application();

		Panel rootPanel = RootPanel.get();

		rootPanel.add(app);
	}
	
	static {
        Defaults.setDateFormat(null);
    }
}
