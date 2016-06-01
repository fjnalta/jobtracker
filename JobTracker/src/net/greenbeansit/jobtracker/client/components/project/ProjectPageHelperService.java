package net.greenbeansit.jobtracker.client.components.project;

import java.util.List;

import net.greenbeansit.jobtracker.shared.Customer;
import net.greenbeansit.jobtracker.shared.Job;

/**
 * Helper service for the {@link ProjectPage}.
 * 
 * @author Max Blatt
 */
interface ProjectPageHelperService
{
	/**
	 * Sorts the following {@link Job}s.
	 * 
	 * @param jobs the list of {@link Job}s which should be sorted.
	 * @param sortmode the {@link ProjectPageSortMode} which should be used for the sorting.
	 * @return the sorted list.
	 */
	List<Job> sortJobs(List<Job> jobs, ProjectPageSortMode sortmode);
	
	/**
	 * Gets all {@link Job}s which belong to at least one of the following customer.
	 * 
	 * @param filter the {@link Customer}s which should be used as a filter.
	 * @return all {@link Job}s that match the filter.
	 */
	List<Job> filterJobs(List<Customer> filter);
	
	/**
	 * Combination of {@link ProjectPageHelperService#filterJob(List)}
	 * and {@link ProjectPageHelperService#sortJobs(List, ProjectPageSortMode)}.
	 * 
	 * @param filter the {@link Customer}s which should be used as a filter.
	 * @param sortmode the {@link ProjectPageSortMode} which should be used for the sorting.
	 * @return all {@link Job}s that match the filter, sorted in the specified way.
	 */
	List<Job> filterAndSortJobs(List<Customer> filter, ProjectPageSortMode sortmode);
	
	/**
	 * Gets the union of all {@link Customer}s of the {@link Job}s of the current
	 * user.
	 * 
	 * @return a list of {@link Customer}s.
	 */
	List<Customer> getCustomers();
	
	/**
	 * Gets the percentag of the of the used budget of the following job.
	 * 
	 * @param job
	 * @return a value between 100 and 0.
	 */
	double getUsedBudgetPercent(Job job);
}