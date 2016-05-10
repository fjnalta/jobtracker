package net.greenbeansit.jobtracker.server.rest.services;

import java.util.List;

import net.greenbeansit.jobtracker.server.data.JobDataService;
import net.greenbeansit.jobtracker.server.data.implementation.JobServiceJpa;
import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.Employee;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.rest.services.EmployeeRestService;

/**
 * Dummy implementation of the {@link EmployeeRestService} interface.
 * 
 * @author Max Blatt & Alexander Kirilyuk
 */
public class EmployeeRestServiceImpl implements EmployeeRestService
{
	private JobDataService dataservice;

	public EmployeeRestServiceImpl()
	{
		dataservice = new JobServiceJpa();
	}

	@Override
	public Employee getEmployee(Long employeeId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> getAllJobs(Long employeeId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityReport> getAllReports(Long employeeId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityReport getReport(Long employeeId, Long reportId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityReport> getReportPeriod(Long employeeId, String from,
			String to)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReport(Long employeeId, ActivityReport report)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void updateReport(Long employeeId, ActivityReport report)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReport(Long employeeId, Long reportId)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List<ActivityReportTemplate> getAllReportTemplates(Long employeeId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveReportTemplate(Long employeeId,
			ActivityReportTemplate template)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReportTemplate(Long employeeId, Long templateId)
	{
		// TODO Auto-generated method stub

	}

}
