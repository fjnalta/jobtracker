package net.greenbeansit.jobtracker.client.components.manager;

import java.util.List;

import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;

/**
 * Helper service for the {@link ManagerPage}.
 * 
 * @author Max Blatt
 */
interface ManagerPageHelperService
{
	/**
	 * Sorts the following list of {@link User}s by the given
	 * {@link ManagerPageSortMode}. If the parameter <code>user</code> is null,
	 * the cached users should be sorted.
	 * 
	 * @param user
	 *            the list {@link User}s that should be sorted.
	 * @param sortmode
	 *            the {@link ManagerPageSortMode} that should be used.
	 * @return the sorted list.
	 */
	List<User> sortUser(List<User> user, ManagerPageSortMode sortmode);

	/**
	 * Filters all users using the following filter.
	 * 
	 * @param filter
	 *            the list of {@link Job}s that should be used as filter.
	 * @return the filtered list.
	 */
	List<User> filterUser(List<Job> filter);

	/**
	 * Combination of {@link ManagerPageHelperService#filterUser(List)} and
	 * {@link ManagerPageHelperService#sortUser(List, ManagerPageSortMode)}.
	 * 
	 * @param filter
	 *            the list of {@link Job}s that should be used as filter.
	 * @param sortmode
	 *            the {@link ManagerPageSortMode} that should be used.
	 * @return the filtered list sorted in the specified way.
	 */
	List<User> filterAndSortUser(List<Job> filter,
			ManagerPageSortMode sortmode);

	/**
	 * Gets the ID of the current {@link User}.
	 * 
	 * @return an integer object.
	 */
	Integer getUserId();

	/**
	 * Gets a value that indicates whether the data has already been loaded from
	 * the server or not.
	 * 
	 * @return
	 */
	boolean isDataLoaded();

	/**
	 * Gets all jobs loaded by from the server.
	 * 
	 * @return a list of {@link Job}s.
	 */
	List<Job> getJobs();
}