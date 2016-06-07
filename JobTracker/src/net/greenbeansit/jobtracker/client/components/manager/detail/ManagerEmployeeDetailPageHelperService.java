package net.greenbeansit.jobtracker.client.components.manager.detail;

import net.greenbeansit.jobtracker.shared.User;

/**
 * Helper service for the {@link ManagerEmployeeDetailPage}.
 * 
 * @author Max Blatt
 */
interface ManagerEmployeeDetailPageHelperService
{
	/**
	 * Gets the current employee.
	 * 
	 * @return a {@link User} object.
	 */
	User getEmployee();
}
