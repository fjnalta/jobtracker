package net.greenbeansit.jobtracker.server;

import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;

import com.google.gwt.dev.util.collect.HashMap;

import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.Employee;
import net.greenbeansit.jobtracker.shared.EmployeeService;
import net.greenbeansit.jobtracker.shared.Job;

/**
 * Dummy implementation of the {@link EmployeeService} interface.
 * 
 * @author Max Blatt & Alexander Kirilyuk
 */
public class EmployeeServiceImpl implements EmployeeService
{
	private static Map<Long, Employee> employeeMap;

	/**
	 * Initializes a new instance of the {@link EmployeeService} class.
	 */
	public EmployeeServiceImpl()
	{
		if (employeeMap == null)
			employeeMap = new HashMap<Long, Employee>();
	}

	@Override
	public Employee getEmployee(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> getAllJobs(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Job getJob(Long employeeId, Long jobId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityReport> getAllReports(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityReport getReport(Long employeeId, Long reportId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityReport> getReportPeriod(Long employeeId, String from, String to) {
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
	public List<ActivityReportTemplate> getAllTemplates(Long employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTemplate(Long employeeId, ActivityReportTemplate template) {
		// TODO Auto-generated method stub
		
	}


}
