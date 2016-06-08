package net.greenbeansit.jobtracker.client.localization;

import com.google.gwt.i18n.client.Constants;

import net.greenbeansit.jobtracker.client.components.project.detail.ProjectDetailPage;

/**
 * Contains translations for the {@link ProjectDetailPage}.
 * 
 * @author Max Blatt
 */
public interface ProjectDetailPageConstants extends Constants
{
	@DefaultStringValue("Budget")
	String tabBudget();
	
	@DefaultStringValue("Tasks")
	String tabTasks();
	
	@DefaultStringValue("Worker")
	String tabEmployees();
	
	@DefaultStringValue("All Projects")
	String anchorBackward();
	
	@DefaultStringValue("Information")
	String infoFieldHeading();
	
	@DefaultStringValue("Name:")
	String labelProjectName();
	
	@DefaultStringValue("Customer:")
	String labelCustomerName();
	
	@DefaultStringValue("Budget:")
	String labelBudgetInfo();
	
	@DefaultStringArrayValue({ "Mo", "Tu", "We", "Thu", "Fr", "Sa", "Sun" })
	String[] weekDayNames();
	
	@DefaultStringValue("Project Utilization")
	String jobTaskTitle();
	
	@DefaultStringValue("Name")
	String jobTaskColumnName();
	
	@DefaultStringValue("Hours per Day")
	String jobTaskColumnHoursPerDay();
	
	@DefaultStringValue("Name")
	String jobWorkerListColumnName();
}
