package net.greenbeansit.jobtracker.shared;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.DirectRestService;

@Path("employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface EmployeeService extends DirectRestService
{
	@GET
	@Path("/{employeeId}")
	Employee getEmployee(@PathParam("employeeId") Long employeeId);
	
	@GET
	@Path("/{employeeId}/job")
	JobService getJobService(@PathParam("employeeId") Long employeeId);
	
	@GET
	@Path("/{employeeId}/report")
	ActivityReportService getReportService(@PathParam("employeeId") Long employeeId);
	
	@GET
	@Path("/{employeeId}/template")
	ActivityReportTemplateService getTemplateService(@PathParam("employeeId") Long employeeId);

}
