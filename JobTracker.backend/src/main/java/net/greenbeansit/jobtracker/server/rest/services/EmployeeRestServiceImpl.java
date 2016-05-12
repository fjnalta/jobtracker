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
	public User getEmployee(Integer user)
	{
		return null;
	}

	@Override
	public List<Job> getAllJobs(Integer userId)
	{
		return dataservice.getAll();
	}

	@Override
	public List<ActivityReport> getAllReports(Integer userId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityReport getReport(Integer userId, Long reportId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityReport> getReportPeriod(Integer userId, String from,
			String to)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReport(Integer userId, ActivityReport report)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void updateReport(Integer userId, ActivityReport report)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReport(Integer userId, Long reportId)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List<ActivityReportTemplate> getAllReportTemplates(Integer userId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveReportTemplate(Integer userId,
			ActivityReportTemplate template)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReportTemplate(Integer userId, Long templateId)
	{
		// TODO Auto-generated method stub

	}

}
