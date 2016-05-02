package net.greenbeansit.jobtracker.server;

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
	public Job[] getAllJobs(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Job getJob(Long employeeId, Long jobId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityReport[] getAllReports(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityReport getReport(Long employeeId, Long reportId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityReport[] getReportPeriod(Long employeeId, String from, String to) {
		// TODO Auto-generated method stub
		return null;
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
	public ActivityReportTemplate[] getAllTemplates(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTemplate(Long employeeId, ActivityReportTemplate template) {
		// TODO Auto-generated method stub
		
	}
}
