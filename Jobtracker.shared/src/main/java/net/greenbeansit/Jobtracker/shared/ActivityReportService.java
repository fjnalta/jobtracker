package net.greenbeansit.Jobtracker.shared;

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


/**
 * Service for report functions of an employee.
 * 
 * @author Max Blatt & Mike Hukiewitz
 */
@Path("activityreport")
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
	List<ActivityReport> getAllActivityReports();
	
	/**
	 * Gets the {@link ActivityReport} with the following ID.
	 * 
	 * @param reportId the id of the searched report.
	 * @return a {@link ActivityReport}.
	 */
	@GET
	@Path("/{reportId}")
	ActivityReport getActivityReport(@PathParam("reportid") String reportId);
	
	@GET
	@Path("/{employeeId}/reportPeriod")
	List<ActivityReport> getActivityReportPeriod(@PathParam("reportid") String employeeId, String from, String to);
	
	
	
	@POST
	@Path("/")
	ActivityReport createActivityReport(ActivityReport report);
	
	@PUT
	@Path("/")
	ActivityReport updateActivityReport(ActivityReport report);
}
