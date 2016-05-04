package net.greenbeansit.jobtracker.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtEntryPoint implements EntryPoint
{
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{	
		Application app = new Application();
		
		Panel rootPanel = RootPanel.get();
		
		rootPanel.add(app);
	}
}
