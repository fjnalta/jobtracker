package net.greenbeansit.jobtracker.client.localization;

import com.google.gwt.i18n.client.Constants;

import net.greenbeansit.jobtracker.client.components.project.ProjectPage;

/**
 * Contains translations for the {@link ProjectPage}.
 * 
 * @author Max Blatt
 */
public interface ProjectPageConstants extends Constants
{
	@DefaultStringValue("Filter")
	String headingFilter();
	
	@DefaultStringValue("Select customer")
	String selectCustomerPlaceHolder();
	
	@DefaultStringValue("Customer name")
	String selectCustomerLiveSearchPlaceHolder();
	
	@DefaultStringValue("All Projects")
	String title();
	
	@DefaultStringValue("Name")
	String columnHeaderName();
	
	@DefaultStringValue("Budget")
	String columnHeaderBudget();
}
