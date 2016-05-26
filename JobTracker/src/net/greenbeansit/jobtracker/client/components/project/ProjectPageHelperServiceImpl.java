package net.greenbeansit.jobtracker.client.components.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.greenbeansit.jobtracker.shared.Customer;
import net.greenbeansit.jobtracker.shared.Job;

class ProjectPageHelperServiceImpl implements ProjectPageHelperService
{
	interface Callback
	{
		void onSuccess();

		void onFailure(Throwable error);
	}

	private List<Job>				cachedJobs;
	private List<Customer>			cachedCustomers;


	public ProjectPageHelperServiceImpl(Callback initCallback)
	{
		initialize(initCallback);
	}

	private void initialize(Callback initCallback)
	{
		// RestClient.build<.....
	}

	@Override
	public List<Job> sortJobs(List<Job> jobs, ProjectPageSortMode sortmode)
	{
		if (jobs == null)
			jobs = cachedJobs;

		switch (sortmode)
		{
		case NAME_UP:
			Collections.sort(jobs, new Comparator<Job>()
			{
				@Override
				public int compare(Job o1, Job o2)
				{
					return compareJobsByName(o1, o2);
				}
			});
			break;

		case NAME_DOWN:
			Collections.sort(jobs, new Comparator<Job>()
			{
				@Override
				public int compare(Job o1, Job o2)
				{
					return compareJobsByName(o2, o1);
				}
			});
			break;

		case USED_BUDGET_PERCENT_DOWN:
			Collections.sort(jobs, new Comparator<Job>()
			{
				@Override
				public int compare(Job o1, Job o2)
				{
					return compareJobsByBudget(o2, o1);
				}
			});
			break;

		default: // USED_BUDGET_PERCENT_UP:
			Collections.sort(jobs, new Comparator<Job>()
			{
				@Override
				public int compare(Job o1, Job o2)
				{
					return compareJobsByBudget(o1, o2);
				}
			});
			break;
		}
		
		return jobs;
	}

	private int compareJobsByName(Job o1, Job o2)
	{
		int compareRes = Integer.compare(o1.getJobNr(), o2.getJobNr());
		if (compareRes == 0)
		{
			compareRes = Integer.compare(o1.getPosNr(), o2.getPosNr());
			if (compareRes == 0)
			{
				compareRes = o1.getDesc().compareTo(o2.getDesc());
				if (compareRes == 0)
				{
					compareRes = Double.compare(getUsedBudgetPercent(o1),
							getUsedBudgetPercent(o2));
				}
			}
		}

		return compareRes;
	}
	
	private int compareJobsByBudget(Job o1, Job o2)
	{
		int compareRes = Double.compare(getUsedBudgetPercent(o1),
				getUsedBudgetPercent(o2));
		if(compareRes == 0)
		{
			compareRes = Integer.compare(o1.getJobNr(), o2.getJobNr());
			if (compareRes == 0)
			{
				compareRes = Integer.compare(o1.getPosNr(), o2.getPosNr());
				if (compareRes == 0)
				{
					compareRes = o1.getDesc().compareTo(o2.getDesc());
				}
			}
		}
		
		return compareRes;
	}

	public double getUsedBudgetPercent(Job job)
	{
		return job.getUsedBudget() / job.getMaxBudget();
	}

	
	@Override
	public List<Job> filterJobs(List<Customer> filter)
	{
		if(filter != null
				&& filter.size() > 0)
		{
			List<Job> filteredJobs = new ArrayList<Job>();
			
			for(Job job : cachedJobs)
			{
				for(Customer customer : cachedCustomers)
				{
					if(job.getCustomerID() == customer.getId())
						filteredJobs.add(job);
						
				}
			}
			
			return filteredJobs;
		}
		else
		{
			return cachedJobs;
		}
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

}

