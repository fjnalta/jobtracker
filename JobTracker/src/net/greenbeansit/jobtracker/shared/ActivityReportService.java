package net.greenbeansit.jobtracker.shared;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.DirectRestService;

public interface ActivityReportService extends DirectRestService  
{
	@GET
	@Path("/")
	ActivityReport[] getAllReports();
	
	@GET
	@Path("/{reportId}")
	ActivityReport getReport(Long reportId);
	
	@GET
	@Path("/{employeeId}/reportPeriod")
	ActivityReport[] getReportPeriod(Long employeeId, String from, String to);
	
	
	
	@POST
	@Path("/")
	ActivityReport createReport(ActivityReport report);
	
	@PUT
	@Path("/")
	ActivityReport updateReport(ActivityReport report);
}
