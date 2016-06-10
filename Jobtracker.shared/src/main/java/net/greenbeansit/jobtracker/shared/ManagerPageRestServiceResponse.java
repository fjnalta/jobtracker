/**
 * 
 */
package net.greenbeansit.jobtracker.shared;

import java.util.List;

/**
 * Response for the RestService.getEmployees(Integer) request.
 */
public class ManagerPageRestServiceResponse
{
	private List<User>	employees;
	private List<Job>	jobs;

	/**
	 * standard constructor
	 */
	public ManagerPageRestServiceResponse()
	{

	}

	/**
	 * Creates a new {@link ManagerPageRestServiceResponse}
	 * 
	 * @param employees
	 *            List of all employees, their attributes for assigned jobs
	 *            are filled
	 * @param jobs
	 *            list of all jobs that are being processed by the employees
	 */
	public ManagerPageRestServiceResponse(List<User> employees,
			List<Job> jobs)
	{
		this.employees = employees;
		this.jobs = jobs;
	}

	/**
	 * @return the employees
	 */
	public List<User> getEmployees()
	{
		return employees;
	}

	/**
	 * @return the jobs
	 */
	public List<Job> getJobs()
	{
		return jobs;
	}

	/**
	 * @param employees
	 *            set employees
	 */
	public void setEmployees(List<User> employees)
	{
		this.employees = employees;
	}

	/**
	 * @param jobs
	 *            set jobs
	 */
	public void setJobs(List<Job> jobs)
	{
		this.jobs = jobs;
	}

}
