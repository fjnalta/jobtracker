package net.greenbeansit.jobtracker.shared.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
public interface EmployeeRestService extends DirectRestService {
	/**
	 * Gets the {@link Employee} with the following ID.
	 * 
	 * @param employeeId
	 *            the ID of the searched {@link Employee}.
	 * @return an {@link Employee}.
	 */
	@GET
	@Path("/{employeeId}")
	Employee getEmployee(@PathParam("employeeId") Long employeeId);

	/**
	 * Gets all {@link Job} for the {@link Employee} with the following ID.
	 * 
	 * @param employeeId
	 *            the ID of the {@link Employee}.
	 * @return List of {@link Job}.
	 */
	
	@GET
	@Path("/{employeeId}/job")
	List<Job> getAllJobs(@PathParam("employeeId") Long employeeId);

	/**
	 * Gets one {@link Job} for the {@link Employee} with the following IDs
	 * 
	 * @param employeeId
	 *            the id of the {@link Employee}
	 * @param jobId
	 *            the id of the {@link Job}
	 * @return one single {@link Job} with the jobId
	 */
	
	@GET
	@Path("/{employeeId}/job/{jobId}")
	Job getJob(@PathParam("employeeId") Long employeeId, @PathParam("jobId") Integer jobId);

	/**
	 * Gets all {@link ActivityReport} for the {@link Employee} with the
	 * following ID.
	 * 
	 * @param employeeId
	 *            the ID of the {@link Employee}.
	 * @return an {@link ActivityReport} List
	 */
	
	@GET
	@Path("/{employeeId}/report")
	List<ActivityReport> getAllReports(@PathParam("employeeId") Long employeeId);

	/**
	 * 
	 * Gets one {@link ActivityReport} for the {@link Employee} with the
	 * following IDs
	 * 
	 * @param employeeId
	 *           the ID of the {@link Employee}.
	 * @param reportId
	 * 				the ID of the {@link ActivityReport}
	 * @return an {@link ActivityReport}
	 */

	@GET
	@Path("/{employeeId}/report/{reportId}")
	ActivityReport getReport(@PathParam("employeeId") Long employeeId, @PathParam("reportId") Long reportId);

	/**
	 * Gets all report {@link ActivityReport} for the {@link Employee} from a
	 * period to a certain period of time. if only from is set it gives back the
	 * reports from till now
	 * 
	 * @param employeeId
	 * 			the ID of the {@link Employee}
	 * @param from
	 * 			the start date
	 * @param to
	 * 			the end date
	 * @return
	 * 			List of {@link ActivityReport}
	 */
	
	@GET
	@Path("/{employeeId}/report/reportPeriod/{from}/{to}")
	List<ActivityReport> getReportPeriod(@PathParam("employeeId") Long employeeId, @PathParam("from") String from,
			@PathParam("to") String to);

	/**
	 * Creates a given {@code ActivityReport} on the server for the
	 * {@link Employee}
	 * 
	 * @param report
	 *            {@code ActivityReport} to send.
	 * 
	 * @param empleyeeId
	 *            the Id of {@link Employee}
	 */
	@POST
	@Path("/{employeeId}/report/")
	void createReport(@PathParam("employeeId") Long employeeId, ActivityReport report);

	@PUT
	@Path("/{employeeId}/report/")
	void updateReport(@PathParam("employeeId") Long employeeId, ActivityReport report);

	/**
	 * Gets all {@link ActivityReportTemplate} for the {@link Employee}
	 * with the following ID.
	 * 
	 * @param employeeId
	 *            the ID of the {@link Employee}.
	 * @return List of {@link ActivityReportTemplate}.
	 */
	
	@GET
	@Path("/{employeeId}/template")
	List<ActivityReportTemplate> getAllTemplates(@PathParam("employeeId") Long employeeId);

	/**
	 * Saves an {@link ActivityReportTemplate} with a new ID for the
	 * {@link Employee}
	 * 
	 * @param template
	 *            the {@link ActivityReportTemplate} that should be saved.
	 * @param empleyeeId
	 *            the Id of {@link Employee}
	 */
	@POST
	@Path("/{employeeId}/template/")
	void saveTemplate(@PathParam("employeeId") Long employeeId, ActivityReportTemplate template);

}
