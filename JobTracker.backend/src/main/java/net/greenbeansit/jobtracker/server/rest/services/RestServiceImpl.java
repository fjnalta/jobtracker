package net.greenbeansit.jobtracker.server.rest.services;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import net.greenbeansit.jobtracker.server.data.activityReport.ActivityReportDataService;
import net.greenbeansit.jobtracker.server.data.activityReportTemplate.ActivityReportTemplateDataService;
import net.greenbeansit.jobtracker.server.data.customer.CustomerDataService;
import net.greenbeansit.jobtracker.server.data.job.JobDataService;
import net.greenbeansit.jobtracker.server.data.jobTask.JobTaskDataService;
import net.greenbeansit.jobtracker.server.data.pseudoJob.PseudoJobDataService;
import net.greenbeansit.jobtracker.server.data.user.UserDataService;
import net.greenbeansit.jobtracker.server.data.userJob.UserJobDataService;
import net.greenbeansit.jobtracker.server.data.utilizationWeek.UtilizationWeekDataService;
import net.greenbeansit.jobtracker.shared.*;
import net.greenbeansit.jobtracker.shared.rest.services.RestService;

/**
 * Dummy implementation of the {@link RestService} interface.
 * 
 * @author Max Blatt & Alexander Kirilyuk
 */
public class RestServiceImpl implements RestService
{
	@Inject
	private ActivityReportDataService activityReportService;
	@Inject
	private ActivityReportTemplateDataService activityReportTemplateService;
	@Inject
	private CustomerDataService customerService;
	@Inject
	private JobDataService jobService;
	@Inject
	private UserDataService userService;
	@Inject
	private UserJobDataService userJobService;

	/**
	 * Empty Constructor for Spring mapping
	*/
	public RestServiceImpl()
	{
		
	}

	//User
	@Override
	public List<User> getAllUser() {
		return userService.getAll();
	}
	
	@Override
	public User getUser(Integer id)
	{
		return userService.getUser(id);
	}
	
	//Jobs
	@Override
	public List<Job> getAllJobs(Integer userId)
	{
		//TODO: Show only those that the user may access
		return jobService.getByUser(userId);
	}

	//Activity Reports
	@Override
	public List<ActivityReport> getAllReports(Integer userId)
	{
		return activityReportService.getByUser(userId);
	}

	@Override
	public ActivityReport getReport(Integer userId, Integer reportId)
	{
		ActivityReport report = activityReportService.getActivityReport(reportId);
		if(report.getAuthor().equals(userId))
			return report;
		else
			return null; //TODO: Throw error if not enough permission
	}


	@Override
	public void saveReport(Integer userId, ActivityReport report)
	{
		report.setAuthor(userId);
		activityReportService.save(report);
	}

	@Override
	public void deleteReport(Integer userId, Integer reportId)
	{
		ActivityReport report = activityReportService.getActivityReport(reportId);
		if(report.getAuthor().equals(userId))
			activityReportService.delete(report);
		//TODO: Throw error if not enough permission
	}

	//Activity Report Templates
	@Override
	public List<ActivityReportTemplate> getAllReportTemplates(Integer userId)
	{
		return activityReportTemplateService.getBy(userId);
	}

	@Override
	public void saveReportTemplate(Integer userId,
			ActivityReportTemplate template)
	{
		template.setAuthor(userId);
		activityReportTemplateService.save(template);
	}

	@Override
	public void deleteReportTemplate(Integer userId, Integer templateId)
	{
		ActivityReportTemplate template = activityReportTemplateService.getTemplate(templateId);
		if(template.getAuthor().equals(userId))
			activityReportTemplateService.delete(template);
	}

	//Customer
	@Override
	public List<Customer> getAllCustomer() {
		return customerService.getAll();
	}

	@Override
	public Customer getCustomer(Integer id) {
		return customerService.getById(id);
	}


	@SuppressWarnings("deprecation")
	private Date stringToDate(String date) {
		return new Date(Integer.parseInt(date.substring(0, 3)),Integer.parseInt(date.substring(5, 6)), Integer.parseInt(date.substring(8, 9)));
	}

	@Override
	public List<UserJob> getUsersToJob(Integer jobNo, Integer posNo) {
		return userJobService.getByJobNrAndPosNr(jobNo, posNo);
	}

	@Override
	public List<ActivityReport> getReportPeriod(Integer userId, String fromto)
	{
		return activityReportService.getByUserAndPeriod(userId, stringToDate(fromto.substring(0, 9)), stringToDate(fromto.substring(11, 20)));
	}

	@Override
	public Customer getCustomer(String name)
	{
		return customerService.getByName(name);
	}

}
