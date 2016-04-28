package net.greenbeansit.Jobtracker.shared;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.DirectRestService;

/**
 * The root service for the standard employee functions.
 * 
 * @author Max Blatt
 */
@Path("employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface EmployeeService extends DirectRestService
{
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
	 * Gets the {@link JobService} for the {@link Employee} with the following
	 * ID.
	 * 
	 * @param employeeId
	 *            the ID of the {@link Employee}.
	 * @return an {@link JobService}.
	 */
	@GET
	@Path("/{employeeId}/job")
	JobService getJobService(@PathParam("employeeId") Long employeeId);

	/**
	 * Gets the {@link ActivityReportService} for the {@link Employee} with the
	 * following ID.
	 * 
	 * @param employeeId
	 *            the ID of the {@link Employee}.
	 * @return an {@link ActivityReportService}.
	 */
	@GET
	@Path("/{employeeId}/report")
	ActivityReportService getReportService(
			@PathParam("employeeId") Long employeeId);

	/**
	 * Gets the {@link ActivityReportTemplateService} for the {@link Employee}
	 * with the following ID.
	 * 
	 * @param employeeId
	 *            the ID of the {@link Employee}.
	 * @return an {@link ActivityReportTemplateService}.
	 */
	@GET
	@Path("/{employeeId}/template")
	ActivityReportTemplateService getTemplateService(
			@PathParam("employeeId") Long employeeId);

}
