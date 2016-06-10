package net.greenbeansit.jobtracker.client.components.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.core.client.GWT;

import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
import net.greenbeansit.jobtracker.shared.Customer;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.ProjectPageRestServiceResponse;

/**
 * Implementation of {@link ProjectPageHelperService}.
 * 
 * @author Max Blatt
 */
class ProjectPageHelperServiceImpl implements ProjectPageHelperService
{
	/**
	 * Callback listener for the REST Services of the
	 * {@link ProjectPageHelperServiceImpl}.
	 * 
	 * @author Max Blatt
	 */
	interface Callback
	{
		/**
		 * Is invoked if the data was loaded successfully from the server.
		 */
		void onSuccess();

		/**
		 * Is invoked if the data could not be loaded from the server.
		 * 
		 * @param error
		 *            the {@link Throwable} that describes the error.
		 */
		void onFailure(Throwable error);
	}

	private List<Job>		cachedJobs;
	private List<Customer>	cachedCustomers;

	/**
	 * Initializes a new instance of the {@link ProjectPageHelperServiceImpl}
	 * class and starts loading the required data from the server.
	 * 
	 * @param initCallback
	 *            the {@link Callback} that will be called after the
	 *            asynchronous process has been finished.
	 */
	public ProjectPageHelperServiceImpl(Callback initCallback)
	{
		cachedJobs = new ArrayList<Job>();
		cachedCustomers = new ArrayList<Customer>();

		initialize(initCallback);
	}

	/**
	 * Starts the asynchronous loading process.
	 * 
	 * @param initCallback
	 *            the {@link Callback} that will be called after the
	 *            asynchronous process has been finished.
	 */
	private void initialize(final Callback initCallback)
	{
		RestClient.build(new SuccessFunction<ProjectPageRestServiceResponse>()
		{

			@Override
			public void onSuccess(Method method,
					ProjectPageRestServiceResponse response)
			{
				List<Job> jobs = new ArrayList<Job>();
				for(Job job : response.getJobs())
				{
					if(!job.isIntern())
						jobs.add(job);
				}
				
				if (response.getJobs() != null)
					cachedJobs = jobs;

				if (response.getCustomers() != null)
					cachedCustomers = response.getCustomers();

				initCallback.onSuccess();
			}

			@Override
			public void onFailure(Method method, Throwable exception)
			{
				GWT.log(exception.getMessage());
				initCallback.onFailure(exception);
			}
		}).getEmployeeService().getProjectPageData(getUserId());
	}

	@Override
	public List<Job> sortJobs(List<Job> jobs, ProjectPageSortMode sortmode)
	{
		List<Job> jobsToSort = jobs;

		if (jobsToSort == null)
			jobsToSort = cachedJobs;

		switch (sortmode)
		{
		case NAME_UP:
			Collections.sort(jobsToSort, new Comparator<Job>()
			{
				@Override
				public int compare(Job o1, Job o2)
				{
					int compareRes = compareJobsByName(o1, o2);

					if (compareRes == 0)
						compareRes = compareJobsByBudget(o1, o2);

					if (compareRes == 0)
						compareRes = compareJobsByLocked(o1, o2);

					return compareRes;
				}
			});
			break;

		case NAME_DOWN:
			Collections.sort(jobsToSort, new Comparator<Job>()
			{
				@Override
				public int compare(Job o1, Job o2)
				{
					int compareRes = compareJobsByName(o2, o1);

					if (compareRes == 0)
						compareRes = compareJobsByBudget(o2, o1);

					if (compareRes == 0)
						compareRes = compareJobsByLocked(o2, o1);

					return compareRes;
				}
			});
			break;

		case USED_BUDGET_PERCENT_UP:
			Collections.sort(jobsToSort, new Comparator<Job>()
			{
				@Override
				public int compare(Job o1, Job o2)
				{
					int compareRes = compareJobsByBudget(o1, o2);

					if (compareRes == 0)
						compareRes = compareJobsByName(o1, o2);

					if (compareRes == 0)
						compareRes = compareJobsByLocked(o1, o2);

					return compareRes;
				}
			});
			break;

		case USED_BUDGET_PERCENT_DOWN:
			Collections.sort(jobsToSort, new Comparator<Job>()
			{
				@Override
				public int compare(Job o1, Job o2)
				{
					int compareRes = compareJobsByBudget(o2, o1);

					if (compareRes == 0)
						compareRes = compareJobsByName(o1, o2);

					if (compareRes == 0)
						compareRes = compareJobsByLocked(o1, o2);

					return compareRes;
				}
			});
			break;
		case LOCKED_DOWN:
			Collections.sort(jobsToSort, new Comparator<Job>()
			{
				@Override
				public int compare(Job o1, Job o2)
				{
					int compareRes = compareJobsByLocked(o2, o1);

					if (compareRes == 0)
						compareRes = compareJobsByName(o1, o2);

					if (compareRes == 0)
						compareRes = compareJobsByBudget(o1, o2);

					return compareRes;
				}
			});
			break;

		default: // LOCKED_UP
			Collections.sort(jobsToSort, new Comparator<Job>()
			{
				@Override
				public int compare(Job o1, Job o2)
				{
					int compareRes = compareJobsByLocked(o1, o2);

					if (compareRes == 0)
						compareRes = compareJobsByName(o1, o2);

					if (compareRes == 0)
						compareRes = compareJobsByBudget(o1, o2);

					return compareRes;
				}
			});
			break;
		}

		return jobsToSort;
	}

	/**
	 * Compares two {@link Job}s by their name attributes (jobNr, posNr and
	 * desc).
	 * 
	 * @param o1
	 *            the first {@link Job}.
	 * @param o2
	 *            the second {@link Job}.
	 * @return 0 if both objects are equal. A value greater than 0 if o1 should
	 *         be displayed higher than o2. A value smaller than 0 if o1 should
	 *         be displayed below o2.
	 */
	private int compareJobsByName(Job o1, Job o2)
	{
		int compareRes = Integer.compare(o1.getJobNr(), o2.getJobNr());
		if (compareRes == 0)
		{
			compareRes = Integer.compare(o1.getPosNr(), o2.getPosNr());
			if (compareRes == 0)
			{
				if (o1.getDesc() == null)
					compareRes = -1;
				else if (o2.getDesc() == null)
					compareRes = 1;
				else
					compareRes = o1.getDesc().compareTo(o2.getDesc());
			}
		}

		return compareRes;
	}

	/**
	 * Compares two {@link Job}s by their used budget in percent.
	 * 
	 * @param o1
	 *            the first {@link Job}.
	 * @param o2
	 *            the second {@link Job}.
	 * @return 0 if both objects are equal. A value greater than 0 if o1 should
	 *         be displayed higher than o2. A value smaller than 0 if o1 should
	 *         be displayed below o2.
	 */
	private int compareJobsByBudget(Job o1, Job o2)
	{
		int compareRes = Double.compare(getUsedBudgetPercent(o1),
				getUsedBudgetPercent(o2));

		return compareRes;
	}

	/**
	 * Compares two {@link Job}s by their isLocked attribute.
	 * 
	 * @param o1
	 *            the first {@link Job}.
	 * @param o2
	 *            the second {@link Job}.
	 * @return 0 if both objects are equal. A value greater than 0 if o1 should
	 *         be displayed higher than o2. A value smaller than 0 if o1 should
	 *         be displayed below o2.
	 */
	private int compareJobsByLocked(Job o1, Job o2)
	{
		int compareRes;
		if (o1.isLocked() == null)
			compareRes = -1;
		else if (o2.isLocked() == null)
			compareRes = 1;
		else
			compareRes = Boolean.compare(o1.isLocked(), o2.isLocked());

		return compareRes;
	}

	@Override
	public double getUsedBudgetPercent(Job job)
	{
		if (job.getUsedBudget() == null)
			return 0;
		else if (job.getMaxBudget() == null)
			return 0;
		else
			return (double) job.getUsedBudget() / job.getMaxBudget();
	}

	@Override
	public List<Job> filterJobs(List<Customer> filter)
	{
		if (filter != null && filter.size() > 0)
		{
			List<Job> filteredJobs = new ArrayList<Job>();

			for (Job job : cachedJobs)
			{
				if (matchesFilter(job, filter))
					filteredJobs.add(job);
			}

			return filteredJobs;
		} else
		{
			return cachedJobs;
		}
	}

	/**
	 * Checks if the following {@link Job} contains a reference to at least one
	 * {@link Customer} in the following filter list.
	 * 
	 * @param job
	 *            the {@link Job} that should be checked.
	 * @param filter
	 *            the {@link List} of {@link Customer}s that should be applied.
	 * @return true if a reference does exist. Otherwise false.
	 */
	private boolean matchesFilter(Job job, List<Customer> filter)
	{
		for (Customer customer : filter)
		{
			if (job.getCustomerID() == customer.getId())
				return true;
		}

		return false;
	}

	@Override
	public List<Job> filterAndSortJobs(List<Customer> filter,
			ProjectPageSortMode sortmode)
	{
		return sortJobs(filterJobs(filter), sortmode);
	}

	@Override
	public List<Customer> getCustomers()
	{
		return cachedCustomers;
	}

	/**
	 * Gets the ID of the current user.
	 * @return the UserID.
	 */
	private int getUserId()
	{
		return 2;//TODO: Remove hard-coded user ID.
	}
}
