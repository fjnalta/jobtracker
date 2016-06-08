package net.greenbeansit.jobtracker.client.localization;

import com.google.gwt.i18n.client.Constants;

import net.greenbeansit.jobtracker.client.components.manager.detail.ManagerEmployeeDetailPage;

/**
 * Contains translations for the {@link ManagerEmployeeDetailPage}
 * 
 * @author Max Blatt
 */
public interface ManagerEmployeeDetailPageConstants extends Constants
{
	@DefaultStringValue("Name:")
	String labelName();

	@DefaultStringValue("Surname:")
	String labelSurName();

	@DefaultStringValue("Projects")
	String tabProject();

	@DefaultStringValue("Reports")
	String tabReport();

	@DefaultStringValue("Capacity")
	String tabCapacity();

	@DefaultStringValue("< All employees")
	String anchorBackward();

	@DefaultStringValue("Information")
	String infoFieldHeading();
}
