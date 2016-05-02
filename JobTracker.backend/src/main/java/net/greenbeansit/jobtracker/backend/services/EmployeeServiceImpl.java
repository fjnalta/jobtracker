
package net.greenbeansit.jobtracker.backend.services;

import java.util.Map;

import javax.ws.rs.NotFoundException;

import java.util.HashMap;

import net.greenbeansit.Jobtracker.shared.ActivityReportService;
import net.greenbeansit.Jobtracker.shared.ActivityReportTemplateService;
import net.greenbeansit.Jobtracker.shared.Employee;
import net.greenbeansit.Jobtracker.shared.EmployeeService;
import net.greenbeansit.Jobtracker.shared.JobService;

/**
 * Dummy implementation of the {@link EmployeeService} interface.
 * 
 * @author Max Blatt
 */
public class EmployeeServiceImpl implements IEmployeeService
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

	public Employee getEmployee(Long employeeId)
	{
		if (employeeMap.containsKey(employeeId))
			return employeeMap.get(employeeId);

		throw new NotFoundException();
	}

	public JobService getJobService(Long employeeId)
	{
		if (employeeMap.containsKey(employeeId))
			return (JobService) new JobServiceImpl();

		throw new NotFoundException();
	}

	public ActivityReportService getReportService(Long employeeId)
	{
		if (employeeMap.containsKey(employeeId))
			return (ActivityReportService) new ActivityReportServiceImpl(employeeId);

		throw new NotFoundException();
	}

	public ActivityReportTemplateService getTemplateService(Long employeeId)
	{
		if (employeeMap.containsKey(employeeId))
			return (ActivityReportTemplateService) new ActivityReportTemplateServiceImpl(employeeId);

		throw new NotFoundException();
	}

}
