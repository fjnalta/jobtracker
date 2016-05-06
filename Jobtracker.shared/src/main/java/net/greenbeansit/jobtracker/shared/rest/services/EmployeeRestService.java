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
import net.greenbeansit.jobtracker.shared.Employee;
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
	 * Gets the {@link Employee} with the following ID.
	 * 
	 * @param employeeId
	 *            the ID of the searched {@link Employee}.
	 * @return an {@link Employee}.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/{employeeId}")
	Employee getEmployee(@PathParam("employeeId") Long employeeId);

	/**
	 * Gets all {@link Job} made by the {@link Employee} with the following ID.
	 * 
	 * @param employeeId
	 *            the ID of the {@link Employee}.
	 * @return a List of {@link Job}s.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/{employeeId}/job")
	List<Job> getAllJobs(@PathParam("employeeId") Long employeeId);

	/**
	 * Gets all {@link ActivityReport}s made by the {@link Employee} with the
	 * following ID.
	 * 
	 * @param employeeId
	 *            the ID of the {@link Employee}.
	 * @return a List of {@link ActivityReport}s.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/{employeeId}/report")
	List<ActivityReport> getAllReports(
			@PathParam("employeeId") Long employeeId);

	/**
	 * 
	 * Gets the {@link ActivityReport} with the following reportId for the
	 * {@link Employee} with the following employeeId.
	 * 
	 * @param employeeId
	 *            the ID of the {@link Employee}.
	 * @param reportId
	 *            the ID of the {@link ActivityReport}.
	 * @return an {@link ActivityReport}.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId or the reportId does not exist.
	 */
	@GET
	@Path("/{employeeId}/report/{reportId}")
	ActivityReport getReport(@PathParam("employeeId") Long employeeId,
			@PathParam("reportId") Long reportId);

	/**
	 * Gets all {@link ActivityReport}s made by the {@link Employee} with the
	 * following ID that are in the following period of time.
	 * 
	 * @param employeeId
	 *            the ID of the {@link Employee}.
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
			@PathParam("employeeId") Long employeeId, String from, String to);

	/**
	 * Creates a given {@code ActivityReport} on the server for the
	 * {@link Employee}
	 * 
	 * @param empleyeeId
	 *            the ID of {@link Employee}.
	 * @param report
	 *            {@code ActivityReport} to send.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@POST
	@Path("/{employeeId}/report/")
	void createReport(@PathParam("employeeId") Long employeeId,
			ActivityReport report);

	/**
	 * Updates the following {@link ActivityReport} if it does not exist, it
	 * will be created.
	 * 
	 * @param employeeId
	 *            the ID of {@link Employee}
	 * @param report
	 *            the {@link ActivityReport} that should be updated.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@PUT
	@Path("/{employeeId}/report/")
	void updateReport(@PathParam("employeeId") Long employeeId,
			ActivityReport report);

	/**
	 * Deletes an {@link ActivityReportTemplate}.
	 * 
	 * @param employeeId
	 *            the ID of {@link Employee}
	 * @param report
	 *            the ID of the {@link ActivityReport} that should be deleted.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId or the reportId does not exist.
	 */
	@DELETE
	@Path("/{employeeId}/report_template/{templateId}")
	void deleteReport(@PathParam("employeeId") Long employeeId,
			@PathParam("templateId") Long reportId);

	/**
	 * Gets all {@link ActivityReportTemplate}s of the {@link Employee} with the
	 * following ID.
	 * 
	 * @param employeeId
	 *            the ID of the {@link Employee}.
	 * @return a List of {@link ActivityReportTemplate}s.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@GET
	@Path("/{employeeId}/report")
	List<ActivityReportTemplate> getAllReportTemplates(
			@PathParam("employeeId") Long employeeId);

	/**
	 * Saves an {@link ActivityReportTemplate} with a new ID for the
	 * {@link Employee}
	 * 
	 * @param empleyeeId
	 *            the ID of {@link Employee}
	 * @param template
	 *            the {@link ActivityReportTemplate} that should be saved.
	 * 
	 * @throws NotFoundException
	 *             if the employeeId does not exist.
	 */
	@POST
	@Path("/{employeeId}/report/")
	void saveReportTemplate(@PathParam("employeeId") Long employeeId,
			ActivityReportTemplate template);

	@DELETE
	@Path("/{employeeId}/report/{reportId}")
	void deleteReportTemplate(@PathParam("employeeId") Long employeeId,
			@PathParam("reportId") Long templateId);
}
