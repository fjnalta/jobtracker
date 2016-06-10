package net.greenbeansit.jobtracker.shared;

import java.util.List;

import net.greenbeansit.jobtracker.shared.rest.services.RestService;

/**
 * @author Mike Hukiewitz
 *
 */
/**
 * Response for the {@link RestService#getProjectPageData(Integer)} request.
 */
public class ProjectPageRestServiceResponse
{
	private List<Job>		jobs;
	private List<Customer>	customers;

	/**
	 * standard constructor
	 */
	public ProjectPageRestServiceResponse()
	{

	}

	/**
	 * Creates a new {@link ProjectPageRestServiceResponse}
	 * 
	 * @param jobs
	 *            list of unique jobs associated with the list of customers
	 * @param customers
	 *            list of customers
	 */
	public ProjectPageRestServiceResponse(List<Job> jobs,
			List<Customer> customers)
	{
		this.jobs = jobs;
		this.customers = customers;
	}

	/**
	 * @return the jobs
	 */
	public List<Job> getJobs()
	{
		return jobs;
	}

	/**
	 * set jobs
	 * 
	 * @param jobs
	 *            the Jobs.
	 */
	public void setJobs(List<Job> jobs)
	{
		this.jobs = jobs;
	}

	/**
	 * @return the customers
	 */
	public List<Customer> getCustomers()
	{
		return customers;
	}

	/**
	 * set customers
	 * 
	 * @param customers
	 *            the customers.
	 */
	public void setCustomers(List<Customer> customers)
	{
		this.customers = customers;
	}
}
