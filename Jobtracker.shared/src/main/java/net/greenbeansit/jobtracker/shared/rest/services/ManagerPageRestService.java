package net.greenbeansit.jobtracker.shared.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.DirectRestService;

import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;

@Path("manager")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ManagerPageRestService extends DirectRestService
{
	/**
	 * Gets the employees who have the user with the following ID as
	 * supervisor.
	 * 
	 * @param supervisorId the ID of the supervisor user.
	 * @return a {@link List} of {@link User}.
	 */
	@GET
	@Path("/employees/{supervisorId}")
	Response getEmployees(@PathParam("supervisorId") Integer supervisorId);
	
	/**
	 * Response for the {@link ManagerPageRestService#getEmployees(Integer)} request.
	 */
	public class Response
	{
		private List<User> employees;
		private List<Job> jobs;
		
		public Response()
		{
			
		}
		
		public Response(List<User> employees, List<Job> jobs) 
		{
			this.employees = employees;
			this.jobs = jobs;
		}
		
		public List<User> getEmployees()
		{
			return employees;
		}
		
		public List<Job> getJobs()
		{
			return jobs;
		}
	}
}
