package net.greenbeansit.jobtracker.client.localization;

import com.google.gwt.i18n.client.Constants;

import net.greenbeansit.jobtracker.client.components.manager.ManagerPage;

/**
 * Contains translations for the {@link ManagerPage}.
 * 
 * @author Max Blatt
 */
public interface ManagerPageConstants extends Constants
{
	@DefaultStringValue("Filter")
	String headingFilter();
	
	@DefaultStringValue("Select projects")
	String selectJobPlaceHolder();
	
	@DefaultStringValue("Projekt name")
	String selectJobLiveSearchPlaceHolder();
	
	@DefaultStringValue("All employees")
	String title();
	
	@DefaultStringValue("Name")
	String columnHeaderName();
	
	@DefaultStringValue("Utilization")
	String columnHeaderUtilization();
}
