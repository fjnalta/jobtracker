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
	@Path("/users/{userId}/job")
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
	List<ActivityReport> getAllReports(
			@PathParam("userId") Integer userId);

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
	@Path("/users/{userId}/report/{reportId}")
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
	List<ActivityReport> getReportPeriod(
			@PathParam("userId") Integer userId, @PathParam("from-to")String fromto);

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
	 * @param id
	 * 		the ID of the {@link Customer}.
	 */
	@GET
	@Path("/customers/id/{id}")
	Customer getCustomer(@PathParam("id") Integer id);
	
	@GET
	@Path("/customers/{name}")
	Customer getCustomer(@PathParam("name") String name);

	/**
	 * Saves an {@link ActivityReport} to the database.
	 * @param userId 
	 * @param report
	 */
	@POST
	@Path("/users/{userId}/reports/")
	void saveReport(@PathParam("userId") Integer userId,
			ActivityReport report);

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
	 * Retrieves all users and their role as {@link UserJob} entities for a specific job.
	 * @param jobNo 3 to 6 digits job number.
	 * @param posNo 3 digit position number.
	 * @return a List of {@link UserJob}s.
	 */
	@GET
	@Path("/usertojob/{jobNo}&{posNo}")
	List<UserJob> getUsersToJob(@PathParam("jobNo") Integer jobNo, @PathParam("posNo") Integer posNo);

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
	void saveReportTemplate(@PathParam("userId") Integer userId,
			ActivityReportTemplate template);

	/**
	 * Removes the specified {@link ActivityReportTemplate} from the database.
	 * @param userId author of the template.
	 * @param templateId id of the template.
	 */
	@DELETE
	@Path("/users/{userId}/report_templates/{templateId}")
	void deleteReportTemplate(@PathParam("userId") Integer userId,
			@PathParam("templateId") Integer templateId);
	
	
}
