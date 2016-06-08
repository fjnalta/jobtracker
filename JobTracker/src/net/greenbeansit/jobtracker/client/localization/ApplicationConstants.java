package net.greenbeansit.jobtracker.client.localization;

import com.google.gwt.i18n.client.Constants;
import com.sun.research.ws.wadl.Application;

/**
 * Contains translations for the main {@link Application} container.
 * 
 * @author Max Blatt
 */
public interface ApplicationConstants extends Constants
{
	@DefaultStringValue("Home")
	String homePage();
	
	@DefaultStringValue("Capacity")
	String capacityPage();
	
	@DefaultStringValue("Projects")
	String projectPage();
	
	@DefaultStringValue("Manager")
	String managerPage();
}
