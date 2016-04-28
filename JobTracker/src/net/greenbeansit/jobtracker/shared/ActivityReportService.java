
package net.greenbeansit.jobtracker.shared;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.DirectRestService;

/**
 * Service for report functions of an employee.
 * 
 * @author Max Blatt
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ActivityReportService extends DirectRestService
{
	/**
	 * Gets all of reports of the associated employee.
	 * 
	 * @return an array of {@link ActivityReport}s.
	 */
	@GET
	@Path("/")
	ActivityReport[] getAllReports();

	/**
	 * Gets the {@link ActivityReport} with the following ID.
	 * 
	 * @param reportId
	 *            the id of the searched report.
	 * @return a {@link ActivityReport}.
	 */
	@GET
	@Path("/{reportId}")
	ActivityReport getReport(@PathParam("reportid") Long reportId);

	/**
	 * Returns all {@code ActivityReport}s of a certain employee in a given time
	 * span.
	 * 
	 * @param employeeId
	 *            whose reports to load.
	 * @param from
	 *            beginning of time span.
	 * @param to
	 *            end of time span.
	 * @return an array of {@link ActivityReport}s.
	 */
	@GET
	@Path("/{employeeId}/reportPeriod")
	ActivityReport[] getReportPeriod(@PathParam("reportid") Long employeeId,
			String from, String to);

	/**
	 * Creates a given {@code ActivityReport} on the server.
	 * 
	 * @param report
	 *            {@code ActivityReport} to send.
	 */
	@POST
	@Path("/")
	void createReport(ActivityReport report);

	@PUT
	@Path("/")
	void updateReport(ActivityReport report);
}
