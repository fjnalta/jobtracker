package net.greenbeansit.jobtracker.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gwt.dev.util.collect.HashMap;

import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.Employee;
import net.greenbeansit.jobtracker.shared.EmployeeService;
import net.greenbeansit.jobtracker.shared.Job;

@Path("employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
/**
 * Dummy implementation of the {@link EmployeeService} interface.
 * 
 * @author Max Blatt
 */
public class EmployeeServiceImpl implements EmployeeService
{
	private static Map<Long, Employee> employeeMap = new HashMap<Long, Employee>();
	private static Map<Long, ActivityReport> reportMap = new HashMap<Long, ActivityReport>();
	private static Map<Long, ActivityReportTemplate> reportTemplateMap = new HashMap<Long, ActivityReportTemplate>();
	private static Map<Long, Job> jobMap = new HashMap<Long, Job>();

	static{
		employeeMap.put(1L, new Employee(1L,"Alexander","Kirilyuk"));
	}
	/**
	 * Initializes a new instance of the {@link EmployeeService} class.
	 */
	public EmployeeServiceImpl()
	{
		
			
	}

	@Override
	public Employee getEmployee(Long employeeId)
	{
		if (employeeMap.containsKey(employeeId))
			return employeeMap.get(employeeId);

		throw new NotFoundException();
	}
	
	@Override
	public List<Job> getAllJobs(Long employeeId) {
		if (jobMap.containsKey(employeeId))
			return new ArrayList<Job>(jobMap.values());
		throw new NotFoundException();
	}
	
	
	@Override
	public Job getJob(Long employeeId, Long jobId) {
		if (jobMap.containsKey(employeeId))
			return jobMap.get(employeeId);

		throw new NotFoundException();
	}
	
	@Override
	public List<ActivityReport> getAllReports(Long employeeId) {
		if (reportMap.containsKey(employeeId))
			return new ArrayList<ActivityReport>(reportMap.values());
		throw new NotFoundException();
	}
	
	@Override
	public ActivityReport getReport(Long employeeId, Long reportId) {
		if (reportMap.containsKey(employeeId))
			return reportMap.get(employeeId);

		throw new NotFoundException();
	}
	
	@Override
	public List<ActivityReport> getReportPeriod(Long employeeId, String from, String to) {
		if (reportMap.containsKey(employeeId))
			return new ArrayList<ActivityReport>(reportMap.values());
		throw new NotFoundException();
	}
	
	@Override
	public void createReport(Long employeeId, ActivityReport report) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReport(Long employeeId, ActivityReport report) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<ActivityReportTemplate> getAllTemplates(Long employeeId) {
		if (reportTemplateMap.containsKey(employeeId))
			return new ArrayList<ActivityReportTemplate>(reportTemplateMap.values());
		throw new NotFoundException();
	}
	
	@Override
	public void saveTemplate(Long employeeId, ActivityReportTemplate template) {
		// TODO Auto-generated method stub
		
	}
}
