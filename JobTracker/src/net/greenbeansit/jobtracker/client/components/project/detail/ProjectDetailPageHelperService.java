package net.greenbeansit.jobtracker.client.components.project.detail;

import java.util.List;

import net.greenbeansit.jobtracker.shared.Customer;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;

/**
 * Helper service for the {@link ProjectDetailPage}.
 * 
 * @author Max Blatt
 */
interface ProjectDetailPageHelperService
{
	/**
	 * Gets the {@link Job} associated to the project.
	 *  
	 * @return a {@link Job}.
	 */
	Job getJob();
	
	/**
	 * Gets the {@link Customer} of the {@link Job}.
	 * 
	 * @return a {@link Customer}.
	 */
	Customer getCustomer();
	
	/**
	 * Gets all {@link User}s who work at the {@link Job}.
	 * 
	 * @return a list of {@link User}s.
	 */
	List<User> getWorker();
}
