package net.greenbeansit.jobtracker.server.rest.services;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import net.greenbeansit.jobtracker.server.data.ActivityReportDataService;
import net.greenbeansit.jobtracker.server.data.JobDataService;
import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.User;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.rest.services.RestService;

/**
 * Dummy implementation of the {@link RestService} interface.
 * 
 * @author Max Blatt & Alexander Kirilyuk
 */
public class RestServiceImpl implements RestService
{
	@Inject
	private JobDataService jobService;
	@Inject
	private ActivityReportDataService activityService;

	public RestServiceImpl()
	{
		
	}

	@Override
	public User getEmployee(Integer user)
	{
		return null;
	}

	@Override
	public List<Job> getAllJobs(Integer userId)
	{
		//TODO: Show only those that the user may access
		return jobService.getAll();
	}

	@Override
	public List<ActivityReport> getAllReports(Integer userId)
	{
		return activityService.getByUser(userId);
	}

	@Override
	public ActivityReport getReport(Integer userId, Integer reportId)
	{
		ActivityReport report = activityService.getActivityReport(reportId);
		if(report.getAuthor() == userId)
			return report;
		else
			return null; //TODO: Throw error if not enough permission
	}

	@Override
	public List<ActivityReport> getReportPeriod(Integer userId, String from,
			String to)
	{
		return activityService.getByUserAndPeriod(userId, stringToDate(from), stringToDate(to));
	}

	@Override
	public void createReport(Integer userId, ActivityReport report)
	{
		report.setAuthor(userId);
		activityService.save(report);
	}

	@Override
	public void updateReport(Integer userId, ActivityReport report)
	{
		if(report.getAuthor() == userId)
			activityService.save(report);
		//TODO: Throw error if not enough permission
	}

	@Override
	public void deleteReport(Integer userId, Integer reportId)
	{
		ActivityReport report = activityService.getActivityReport(reportId);
		if(report.getAuthor() == userId)
			activityService.delete(report);
		//TODO: Throw error if not enough permission
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
	public void deleteReportTemplate(Integer userId, Integer templateId)
	{
		// TODO Auto-generated method stub

	}
	
	
	@SuppressWarnings("deprecation")
	private Date stringToDate(String date) {
		return new Date(Integer.parseInt(date.substring(0, 3)),Integer.parseInt(date.substring(5, 6)), Integer.parseInt(date.substring(8, 9)));
	}

}
