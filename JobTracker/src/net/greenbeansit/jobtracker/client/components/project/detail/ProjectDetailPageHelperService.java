package net.greenbeansit.jobtracker.client.components.project.detail;

import net.greenbeansit.jobtracker.shared.Customer;
import net.greenbeansit.jobtracker.shared.Job;

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
}
