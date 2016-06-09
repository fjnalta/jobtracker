package net.greenbeansit.jobtracker.client.localization;

import com.google.gwt.i18n.client.Constants;

import net.greenbeansit.jobtracker.client.components.home.HomePage;

/**
 * Contains translations for the {@link HomePage}.
 * 
 * @author Max Blatt
 */
public interface HomePageConstants extends Constants
{
	@DefaultStringValue("Favorites")
	String groupFavoriteJobs();
	
	@DefaultStringValue("Other")
	String groupAllOtherJobs();
	
	@DefaultStringValue("Select project")
	String selectJobPlaceHolder();
	
	@DefaultStringValue("Project name")
	String selectJobLiveSearchPlaceHolder();
	
	@DefaultStringValue("Report description")
	String headingWorkDescription();
	
	@DefaultStringValue("Enter your description here")
	String workDescriptionPlaceHolder();
	
	@DefaultStringValue("Task")
	String headingSelectTask();
	
	@DefaultStringValue("Templates")
	String buttonTemplateText();
	
	@DefaultStringValue("Template name")
	String textTemplateNamePlaceHolder();
	
	@DefaultStringValue("Save template")
	String buttonSaveTemplate();
	
	@DefaultStringValue("Load template")
	String buttonLoadTemplate();
	
	@DefaultStringValue("Remaining vacation days:")
	String headingRemainingVacationDays();
	
	@DefaultStringValue("Utilization:")
	String headingUtilization();
	
	@DefaultStringValue("Date from")
	String addonDateStart();
	
	@DefaultStringValue("to")
	String addonDateEnd();
	
	@DefaultStringValue("Time from")
	String addonTimeStart();
	
	@DefaultStringValue("to")
	String addonTimeEnd();
	
	@DefaultStringValue("Break")
	String addonBreak();
	
	@DefaultStringValue("Duration")
	String addonDuration();
	
	@DefaultStringValue("Book")
	String buttonBook();
	
	@DefaultStringArrayValue({ "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" })
	String[] dayNames();
}
