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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface EmployeeRestService extends DirectRestService
{
	/**
	 * Gets the {@link User} with the following ID.
	 * 
	 * @param employeeId
	 *            the ID of the searched {@link User}.
	 * @return an {@link User}.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/{employeeId}")
	User getEmployee(@PathParam("employeeId") Integer employeeId);

	/**
	 * Gets all {@link Job} made by the {@link User} with the following ID.
	 * 
	 * @param employeeId
	 *            the ID of the {@link User}.
	 * @return a List of {@link Job}s.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/{employeeId}/job")
	List<Job> getAllJobs(@PathParam("employeeId") Integer employeeId);

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
	@Path("/{employeeId}/report")
	List<ActivityReport> getAllReports(
			@PathParam("employeeId") Integer employeeId);

	/**
	 * 
	 * Gets the {@link ActivityReport} with the following reportId for the
	 * {@link User} with the following employeeId.
	 * 
	 * @param employeeId
	 *            the ID of the {@link User}.
	 * @param reportId
	 *            the ID of the {@link ActivityReport}.
	 * @return an {@link ActivityReport}.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId or the reportId does not exist.
	 */
	@GET
	@Path("/{employeeId}/report/{reportId}")
	ActivityReport getReport(@PathParam("employeeId") Integer employeeId,
			@PathParam("reportId") Long reportId);

	/**
	 * Gets all {@link ActivityReport}s made by the {@link User} with the
	 * following ID that are in the following period of time.
	 * 
	 * @param employeeId
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
	@Path("/{employeeId}/reportPeriod")
	List<ActivityReport> getReportPeriod(
			@PathParam("employeeId") Integer employeeId, String from, String to);

	/**
	 * Creates a given {@code ActivityReport} on the server for the
	 * {@link User}
	 * 
	 * @param empleyeeId
	 *            the ID of {@link User}.
	 * @param report
	 *            {@code ActivityReport} to send.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@POST
	@Path("/{employeeId}/report/")
	void createReport(@PathParam("employeeId") Integer employeeId,
			ActivityReport report);

	/**
	 * Updates the following {@link ActivityReport} if it does not exist, it
	 * will be created.
	 * 
	 * @param employeeId
	 *            the ID of {@link User}
	 * @param report
	 *            the {@link ActivityReport} that should be updated.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@PUT
	@Path("/{employeeId}/report/")
	void updateReport(@PathParam("employeeId") Integer employeeId,
			ActivityReport report);

	/**
	 * Deletes an {@link ActivityReportTemplate}.
	 * 
	 * @param employeeId
	 *            the ID of {@link User}
	 * @param report
	 *            the ID of the {@link ActivityReport} that should be deleted.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId or the reportId does not exist.
	 */
	@DELETE
	@Path("/{employeeId}/report_template/{templateId}")
	void deleteReport(@PathParam("employeeId") Integer employeeId,
			@PathParam("templateId") Long reportId);

	/**
	 * Gets all {@link ActivityReportTemplate}s of the {@link User} with the
	 * following ID.
	 * 
	 * @param employeeId
	 *            the ID of the {@link User}.
	 * @return a List of {@link ActivityReportTemplate}s.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/{employeeId}/report")
	List<ActivityReportTemplate> getAllReportTemplates(
			@PathParam("employeeId") Integer employeeId);

	/**
	 * Saves an {@link ActivityReportTemplate} with a new ID for the
	 * {@link User}
	 * 
	 * @param empleyeeId
	 *            the ID of {@link User}
	 * @param template
	 *            the {@link ActivityReportTemplate} that should be saved.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@POST
	@Path("/{employeeId}/report/")
	void saveReportTemplate(@PathParam("employeeId") Integer employeeId,
			ActivityReportTemplate template);

	@DELETE
	@Path("/{employeeId}/report/{reportId}")
	void deleteReportTemplate(@PathParam("employeeId") Integer employeeId,
			@PathParam("reportId") Long templateId);
}
