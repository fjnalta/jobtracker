package net.greenbeansit.jobtracker.server.rest.services;

import java.util.List;

import net.greenbeansit.jobtracker.server.data.JobDataService;
import net.greenbeansit.jobtracker.server.data.implementation.JobServiceJpa;
import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.User;
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
	public User getEmployee(Integer employeeId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> getAllJobs(Integer employeeId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityReport> getAllReports(Integer employeeId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityReport getReport(Integer employeeId, Long reportId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityReport> getReportPeriod(Integer employeeId, String from,
			String to)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReport(Integer employeeId, ActivityReport report)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void updateReport(Integer employeeId, ActivityReport report)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReport(Integer employeeId, Long reportId)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List<ActivityReportTemplate> getAllReportTemplates(Integer employeeId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveReportTemplate(Integer employeeId,
			ActivityReportTemplate template)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReportTemplate(Integer employeeId, Long templateId)
	{
		// TODO Auto-generated method stub

	}

}
