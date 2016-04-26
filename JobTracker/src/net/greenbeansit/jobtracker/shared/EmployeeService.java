package net.greenbeansit.jobtracker.shared;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.DirectRestService;

@Path("employee")
public interface EmployeeService extends DirectRestService
{
	@GET
	@Path("/{employeeId}")
	Employee getEmployee(Long employeeId);
	
	@GET
	@Path("/{employeeId}/job")
	JobService getJobService(Long employeeId);
	
	@GET
	@Path("/{employeeId}/report")
	ActivityReportService getReportService(Long employeeId);
	
	@GET
	@Path("/{employeeId}/template")
	ActivityReportTemplateService getTemplateService(Long employeeId);

}
