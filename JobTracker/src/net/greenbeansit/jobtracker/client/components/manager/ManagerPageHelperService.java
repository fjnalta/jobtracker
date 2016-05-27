package net.greenbeansit.jobtracker.client.components.manager;

import java.util.List;

import net.greenbeansit.jobtracker.client.components.manager.ManagerPageSortMode;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;

interface ManagerPageHelperService
{
	List<User> sortUser(List<User> user, ManagerPageSortMode sortmode);

	List<User> filterUser(List<Job> filter);

	List<User> filterAndSortUser(List<Job> filter, ManagerPageSortMode sortmode);

	/**
	 * Gets the ID of the current {@link User}.
	 * 
	 * @return an integer object.
	 */
	Integer getUserId();

	boolean isDataLoaded();
	
	List<Job> getJobs();
}