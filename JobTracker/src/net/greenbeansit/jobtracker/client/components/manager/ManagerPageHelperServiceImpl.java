package net.greenbeansit.jobtracker.client.components.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.core.client.GWT;

import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;
import net.greenbeansit.jobtracker.shared.JobID;
import net.greenbeansit.jobtracker.shared.ManagerPageRestServiceResponse;

/**
 * Implementation of {@link ManagerPageHelperService}.
 * 
 * @author Max Blatt
 */
class ManagerPageHelperServiceImpl implements ManagerPageHelperService
{
	/**
	 * Callback listener for the {@link ManagerPageHelperServiceImpl}.
	 * 
	 * @author Max Blatt.
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

	private List<User>	cachedUser	= new ArrayList<User>();
	private List<Job>	cachedJobs	= new ArrayList<Job>();

	private boolean		dataLoaded	= false;

	/**
	 * Initializes a new instance of the {@link ManagerPageHelperServiceImpl}
	 * class.
	 * 
	 * @param initCallback
	 *            the {@link Callback} that will be called after the
	 *            asynchronous process has been finished.
	 */
	public ManagerPageHelperServiceImpl()
	{
		
	}

	/**
	 * Starts the asynchronous loading process.
	 * 
	 * @param initCallback
	 *            the {@link Callback} that will be called after the
	 *            asynchronous process has been finished.
	 */
	@Override
	public void loadServerData(final Callback initCallback)
	{		
		RestClient.build(new SuccessFunction<ManagerPageRestServiceResponse>()
		{
			@Override
			public void onSuccess(Method method,
					ManagerPageRestServiceResponse response)
			{
				cachedJobs = response.getJobs();
				if (cachedJobs == null)
					cachedJobs = new ArrayList<Job>();

				cachedUser = response.getEmployees();
				if (cachedUser == null)
					cachedUser = new ArrayList<User>();

				dataLoaded = true;

				initCallback.onSuccess();
			}

			@Override
			public void onFailure(Method method, Throwable exception)
			{
				GWT.log(exception.getMessage());

				initCallback.onFailure(exception);
			}
		}).getEmployeeService().getEmployees(getUserId());
	}

	@Override
	public Integer getUserId()
	{
		return 1; // TODO: Replace hard-coded user ID.
	}

	@Override
	public List<User> sortUser(List<User> user, ManagerPageSortMode sortmode)
	{
		GWT.log("sortUser" + user);
		if (user == null)
			user = cachedUser;

		switch (sortmode)
		{
		case ALPHABETICAL_UP:
			Collections.sort(user, new Comparator<User>()
			{
				@Override
				public int compare(User o1, User o2)
				{
					int compareRes = o1.getSurname().compareTo(o2.getSurname());

					if (compareRes == 0)
						compareRes = o1.getName().compareTo(o2.getName());

					return compareRes;
				}
			});
			break;
		case ALPHABETICAL_DOWN:
			Collections.sort(user, new Comparator<User>()
			{
				@Override
				public int compare(User o1, User o2)
				{
					int compareRes = o1.getSurname().compareTo(o2.getSurname())
							* -1;

					if (compareRes == 0)
						compareRes = o1.getName().compareTo(o2.getName()) * -1;

					return compareRes;
				}
			});
			break;
		case UTILIZATION_UP:
			Collections.sort(user, new Comparator<User>()
			{
				@Override
				public int compare(User o1, User o2)
				{
					int compareRes = Integer.compare(o1.getId(), o2.getId())
							* -1;

					if (compareRes == 0)
					{
						compareRes = o1.getSurname().compareTo(o2.getSurname())
								* -1;

						if (compareRes == 0)
						{
							compareRes = o1.getName().compareTo(o2.getName())
									* -1;
						}
					}

					return compareRes;
				}
			});
			break;
		default: // UTILZATION_DOWN
			Collections.sort(user, new Comparator<User>()
			{
				@Override
				public int compare(User o1, User o2)
				{
					int compareRes = Integer.compare(o1.getId(), o2.getId());

					if (compareRes == 0)
					{
						compareRes = o1.getSurname().compareTo(o2.getSurname());

						if (compareRes == 0)
						{
							compareRes = o1.getName().compareTo(o2.getName());
						}
					}

					return compareRes;
				}
			});
			break;
		}

		return user;
	}

	@Override
	public List<User> filterUser(List<Job> filter)
	{
		if (filter != null && filter.size() > 0)
		{
			List<User> filteredUser = new ArrayList<User>();
			for (User userInstance : cachedUser)
			{
				if (matchesFilter(userInstance, filter))
					filteredUser.add(userInstance);
			}

			return filteredUser;
		} else
			return cachedUser;
	}

	/**
	 * Checks whether the following {@link User} contains at least one reference
	 * to a {@link Job} in the following filter list.
	 * 
	 * @param employee
	 *            the {@link User} whose {@link Job}s references should be
	 *            checked.
	 * @param filter
	 *            the list of {@link Job}s that should be applied.
	 * @return true if a reference was found. Otherwise false.
	 */
	private boolean matchesFilter(User employee, List<Job> filter)
	{
		if (employee.getAssignedJobs() == null)
			employee.setAssignedJobs(new ArrayList<JobID>());

		for (Job filterJob : filter)
		{
			for (JobID assignedJobId : employee.getAssignedJobs())
			{
				if (assignedJobId.matchesJob(filterJob))
					return true;
			}
		}

		return false;
	}

	@Override
	public List<User> filterAndSortUser(List<Job> filter,
			ManagerPageSortMode sortmode)
	{
		return sortUser(filterUser(filter), sortmode);
	}

	@Override
	public boolean isDataLoaded()
	{
		return dataLoaded;
	}

	@Override
	public List<Job> getJobs()
	{
		return cachedJobs;
	}
}
