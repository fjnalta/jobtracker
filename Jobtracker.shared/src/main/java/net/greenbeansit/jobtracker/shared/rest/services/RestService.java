package net.greenbeansit.jobtracker.shared.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.greenbeansit.jobtracker.shared.*;

import org.fusesource.restygwt.client.DirectRestService;

/**
 * The service for all server fuctions
 * 
 * @author Max Blatt & Alexander Kirilyuk & Philipp Minges
 */
@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface RestService extends DirectRestService
{
	/**
	 * Gets all {@link User}.
	 * 
	 * @return a List of {@link User}s.
	 * 
	 * @throws NotFoundException
	 *             if there are no Users.
	 */
	@GET
	@Path("/users")
	List<User> getAllUser();

	/**
	 * Gets the {@link User} with the following ID.
	 * 
	 * @param userId
	 *            the ID of the searched {@link User}.
	 * @return an {@link User}.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/users/{userId}")
	User getUser(@PathParam("userId") Integer userId);

	/**
	 * Gets all {@link Job} made by the {@link User} with the following ID.
	 * 
	 * @param userId
	 *            the ID of the {@link User}.
	 * @return a List of {@link Job}s.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/users/{userId}/jobs")
	List<Job> getAllJobs(@PathParam("userId") Integer userId);

	/**
	 * Gets all {@link ActivityReport}s made by the {@link User} with the
	 * following ID.
	 * 
	 * @param userId
	 *            the ID of the {@link User}.
	 * @return a List of {@link ActivityReport}s.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/users/{userId}/reports")
	List<ActivityReport> getAllReports(@PathParam("userId") Integer userId);

	/**
	 * 
	 * Gets the {@link ActivityReport} with the following reportId for the
	 * {@link User} with the following employeeId.
	 * 
	 * @param userId
	 *            the ID of the {@link User}.
	 * @param reportId
	 *            the ID of the {@link ActivityReport}.
	 * @return an {@link ActivityReport}.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId or the reportId does not exist.
	 */
	@GET
	@Path("/users/{userId}/reports/{reportId}")
	ActivityReport getReport(@PathParam("userId") Integer userId,
			@PathParam("reportId") Integer reportId);

	/**
	 * Gets all {@link ActivityReport}s made by the {@link User} with the
	 * following ID that are in the following period of time.
	 * 
	 * The pattern of the date is "yyyy_mm_dd-yyyy_mm_dd"
	 * 
	 * @param userId
	 *            the ID of the {@link User}.
	 * @param fromto
	 *            the start and end date.
	 * @return List of {@link ActivityReport}.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/users/{userId}/reportPeriod/{from-to}")
	List<ActivityReport> getReportPeriod(@PathParam("userId") Integer userId,
			@PathParam("from-to") String fromto);

	/**
	 * Gets all {@link Customer}s
	 *
	 * @throws NotFoundException
	 *             if there are no Customers.
	 */
	@GET
	@Path("/customers")
	List<Customer> getAllCustomer();

	/**
	 * Gives all {@link Customer}s with the given id
	 * 
	 * @param id
	 *            the ID of the {@link Customer}.
	 */
	@GET
	@Path("/customers/id/{id}")
	Customer getCustomer(@PathParam("id") Integer id);

	@GET
	@Path("/customers/{name}")
	Customer getCustomer(@PathParam("name") String name);

	/**
	 * Saves an {@link ActivityReport} to the database.
	 * 
	 * @param userId
	 * @param report
	 */
	@POST
	@Path("/users/{userId}/reports/")
	void saveReport(@PathParam("userId") Integer userId, ActivityReport report);

	@DELETE
	@Path("/users/{userId}/reports/{reportId}")
	void deleteReport(@PathParam("userId") Integer userId,
			@PathParam("reportId") Integer reportId);

	/**
	 * Gets all {@link ActivityReportTemplate}s of the {@link User} with the
	 * following ID.
	 * 
	 * @param userId
	 *            the ID of the {@link User}.
	 * @return a List of {@link ActivityReportTemplate}s.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/users/{userId}/report_templates")
	List<ActivityReportTemplate> getAllReportTemplates(
			@PathParam("userId") Integer userId);

	/**
	 * Retrieves all users and their role as {@link UserJob} entities for a
	 * specific job.
	 * 
	 * @param jobNo
	 *            3 to 6 digits job number.
	 * @param posNo
	 *            3 digit position number.
	 * @return a List of {@link UserJob}s.
	 */
	@GET
	@Path("/usertojob/{jobNo}&{posNo}")
	List<UserJob> getUsersToJob(@PathParam("jobNo") Integer jobNo,
			@PathParam("posNo") Integer posNo);

	/**
	 * Returns the utilization of an employee for the given month.
	 * @param userId user
	 * @param year year
	 * @param month month
	 * @return percent value (0-100 (and even more))
	 */
	@GET
	@Path("/users/{userId}/utilization/{year}/{month}")
	Integer getUtilizationMonth(@PathParam("userId") Integer userId,
			@PathParam("year") Integer year, @PathParam("month") Integer month);

	/**
	 * Returns the utilization of an employee for the given year.
	 * @param userId user
	 * @param year year
	 * @return percent value (0-100 (and even more))
	 */
	@GET
	@Path("/users/{userId}/utilization/{year}")
	Integer getUtilizationYear(@PathParam("userId") Integer userId,
			@PathParam("year") Integer year);

	/**
	 * Saves an {@link ActivityReportTemplate} with a new ID for the
	 * {@link User}
	 * 
	 * @param userId
	 *            the ID of {@link User}
	 * @param template
	 *            the {@link ActivityReportTemplate} that should be saved.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@POST
	@Path("/users/{userId}/report_templates/")
	void saveReportTemplate(@PathParam("userId") Integer userId, ActivityReportTemplate template);

	/**
	 * Removes the specified {@link ActivityReportTemplate} from the database.
	 * 
	 * @param userId
	 *            author of the template.
	 * @param templateId
	 *            id of the template.
	 */
	@DELETE
	@Path("/users/{userId}/report_templates/{templateName}")
	void deleteReportTemplate(@PathParam("userId") Integer author,
			@PathParam("templateName") String name);

	/**
	 * Gets the employees who have the user with the following ID as supervisor.
	 * Additionally all {@link User}s will have their List assignedJobs filled with their assigned jobs.
	 * 
	 * @param supervisorId
	 *            the ID of the supervisor user.
	 * @return a {@link List} of {@link User}.
	 */
	@GET
	@Path("manager/employees/{supervisorId}")
	ManagerPageRestServiceResponse getEmployees(
			@PathParam("supervisorId") Integer supervisorId);

	/**
	 * Response for the {@link ManagerPageRestService#getEmployees(Integer)}
	 * request.
	 */
	public class ManagerPageRestServiceResponse
	{
		private List<User>	employees;
		private List<Job>	jobs;

		public ManagerPageRestServiceResponse()
		{

		}

		public ManagerPageRestServiceResponse(List<User> employees,
				List<Job> jobs)
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

		public void setEmployees(List<User> employees)
		{
			this.employees = employees;
		}

		public void setJobs(List<Job> jobs)
		{
			this.jobs = jobs;
		}
		
	}
	
	public class JobID
	{
		private Integer jobNr, posNr;
		
		public JobID()
		{
			
		}
		
		public JobID(Integer jobNr, Integer posNr)
		{
			this.setJobNr(jobNr);
			this.setPosNr(posNr);
		}

		public Integer getJobNr()
		{
			return jobNr;
		}

		public void setJobNr(Integer jobNr)
		{
			this.jobNr = jobNr;
		}

		public Integer getPosNr()
		{
			return posNr;
		}

		public void setPosNr(Integer posNr)
		{
			this.posNr = posNr;
		}
		
		public boolean matchesJob(Job job)
		{
			return posNr.equals(job.getPosNr())
					&& jobNr.equals(job.getJobNr());
		}
	}
}
