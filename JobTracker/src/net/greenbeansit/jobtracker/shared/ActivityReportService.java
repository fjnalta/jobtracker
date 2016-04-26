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


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ActivityReportService extends DirectRestService  
{
	@GET
	@Path("/")
	ActivityReport[] getAllReports();
	
	@GET
	@Path("/{reportId}")
	ActivityReport getReport(@PathParam("reportid") Long reportId);
	
	@GET
	@Path("/{employeeId}/reportPeriod")
	ActivityReport[] getReportPeriod(@PathParam("reportid") Long employeeId, String from, String to);
	
	
	
	@POST
	@Path("/")
	void createReport(ActivityReport report);
	
	@PUT
	@Path("/")
	void updateReport(ActivityReport report);
}
