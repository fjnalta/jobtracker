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

import org.fusesource.restygwt.client.DirectRestService;

import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.User;
import net.greenbeansit.jobtracker.shared.Job;

/**
 * The service for all server fuctions
 * 
 * @author Max Blatt & Alexander Kirilyuk
 */
@Path("rest/employee")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public interface RestService extends DirectRestService
{
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
	@Path("/{userId}")
	User getEmployee(@PathParam("userId") Integer userId);

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
	@Path("/{userId}/job")
	List<Job> getAllJobs(@PathParam("employeeId") Integer userId);

	/**
	 * Gets all {@link ActivityReport}s made by the {@link User} with the
	 * following ID.
	 * 
	 * @param employeeId
	 *            the ID of the {@link User}.
	 * @return a List of {@link ActivityReport}s.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/{userId}/report")
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
	@Path("/{userId}/report/{reportId}")
	ActivityReport getReport(@PathParam("userId") Integer userId,
			@PathParam("reportId") Integer reportId);

	/**
	 * Gets all {@link ActivityReport}s made by the {@link User} with the
	 * following ID that are in the following period of time.
	 * 
	 * The pattern of the date is yyyy_mm_dd
	 * 
	 * @param userId
	 *            the ID of the {@link User}.
	 * @param from
	 *            the start date.
	 * @param to
	 *            the end date.
	 * @return List of {@link ActivityReport}.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/{userId}/reportPeriod/{from}/{to}")
	List<ActivityReport> getReportPeriod(
			@PathParam("userId") Integer userId, @PathParam("from")String from, @PathParam("to")String to);

	/**
	 * Creates a given {@code ActivityReport} on the server for the
	 * {@link User}
	 * 
	 * @param userId
	 *            the ID of {@link User}.
	 * @param report
	 *            {@code ActivityReport} to send.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@POST
	@Path("/{userId}/report/")
	void createReport(@PathParam("userId") Integer userId,
			ActivityReport report);

	/**
	 * Updates the following {@link ActivityReport} if it does not exist, it
	 * will be created.
	 * 
	 * @param userId
	 *            the ID of {@link User}
	 * @param report
	 *            the {@link ActivityReport} that should be updated.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@PUT
	@Path("/{userId}/report/")
	void updateReport(@PathParam("userId") Integer userId,
			ActivityReport report);

	/**
	 * Deletes an {@link ActivityReportTemplate}.
	 * 
	 * @param userId
	 *            the ID of {@link User}
	 * @param report
	 *            the ID of the {@link ActivityReport} that should be deleted.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId or the reportId does not exist.
	 */
	@DELETE
	@Path("/{userId}/report/{reportId}")
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
	@Path("/{userId}/report_template")
	List<ActivityReportTemplate> getAllReportTemplates(
			@PathParam("userId") Integer userId);

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
	@Path("/{userId}/report_template/")
	void saveReportTemplate(@PathParam("userId") Integer userId,
			ActivityReportTemplate template);

	@DELETE
	@Path("/{userId}/report_template/{reportId}")
	void deleteReportTemplate(@PathParam("userId") Integer userId,
			@PathParam("reportId") Integer templateId);
}
