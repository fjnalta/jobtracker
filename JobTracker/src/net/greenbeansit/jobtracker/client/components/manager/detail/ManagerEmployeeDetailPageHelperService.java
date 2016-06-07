package net.greenbeansit.jobtracker.client.components.manager.detail;

import java.util.List;

import net.greenbeansit.jobtracker.shared.Job;
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

	/**
	 * Get all {@link Job}s of the current employee.
	 * 
	 * @return a list of {@link Job}s.
	 */
	List<Job> getJobs();
}
