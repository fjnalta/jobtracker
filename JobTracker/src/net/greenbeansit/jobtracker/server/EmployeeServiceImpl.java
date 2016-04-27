package net.greenbeansit.jobtracker.server;

import java.util.Map;

import javax.ws.rs.NotFoundException;

import com.google.gwt.dev.util.collect.HashMap;

import net.greenbeansit.jobtracker.shared.ActivityReportService;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplateService;
import net.greenbeansit.jobtracker.shared.Employee;
import net.greenbeansit.jobtracker.shared.EmployeeService;
import net.greenbeansit.jobtracker.shared.JobService;

/**
 * Dummy implementation of the {@link EmployeeService} interface.
 * 
 * @author Max Blatt
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
	public Employee getEmployee(Long employeeId)
	{
		if (employeeMap.containsKey(employeeId))
			return employeeMap.get(employeeId);

		throw new NotFoundException();
	}

	@Override
	public JobService getJobService(Long employeeId)
	{
		if (employeeMap.containsKey(employeeId))
			return new JobServiceImpl();

		throw new NotFoundException();
	}

	@Override
	public ActivityReportService getReportService(Long employeeId)
	{
		if (employeeMap.containsKey(employeeId))
			return new ActivityReportServiceImpl(employeeId);

		throw new NotFoundException();
	}

	@Override
	public ActivityReportTemplateService getTemplateService(Long employeeId)
	{
		if (employeeMap.containsKey(employeeId))
			return new ActivityReportTemplateServiceImpl(employeeId);

		throw new NotFoundException();
	}

}
