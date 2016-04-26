package net.greenbeansit.jobtracker.server;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gwt.dev.util.collect.HashMap;

import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportService;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplateService;
import net.greenbeansit.jobtracker.shared.Employee;
import net.greenbeansit.jobtracker.shared.EmployeeService;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.JobService;


@Path("employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeServiceImpl implements EmployeeService
{
	private Map<Long, Employee> employeeMap;
	private Map<Long, ActivityReport> reportMap;
	private Map<Long, ActivityReportTemplate> templateMap;
	
	public EmployeeServiceImpl()
	{
		employeeMap = new HashMap<Long, Employee>();
		reportMap = new HashMap<Long, ActivityReport>();
		templateMap = new HashMap<Long, ActivityReportTemplate>();
	}

	@Override
	public Employee getEmployee(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobService getJobService(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityReportService getReportService(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityReportTemplateService getTemplateService(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
