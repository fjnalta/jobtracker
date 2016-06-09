package net.greenbeansit.jobtracker.server.rest.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
 * @author Max Blatt & Alexander Kirilyuk & Philipp Minges & Mike Hukiewitz
 */
public class RestServiceImpl implements RestService
{
	@Inject
	private ActivityReportDataService			activityReportService;
	@Inject
	private ActivityReportTemplateDataService	activityReportTemplateService;
	@Inject
	private CustomerDataService					customerService;
	@Inject
	private JobDataService						jobService;
	@Inject
	private UserDataService						userService;
	@Inject
	private UserJobDataService					userJobService;
	@Inject
	private PseudoJobDataService				pseudoService;
	@Inject
	private UtilizationWeekDataService			utilizationWeekService;
	@Inject
	private JobTaskDataService					jobTaskService;

	/**
	 * Empty Constructor for Spring mapping
	 */
	public RestServiceImpl()
	{

	}

	// User
	@Override
	public List<User> getAllUser()
	{
		return userService.getAll();
	}

	@Override
	public User getUser(Integer id)
	{
		return userService.getUser(id);
	}

	// Jobs
	@Override
	public List<Job> getAllJobs(Integer userId)
	{
		return jobService.getByUser(userId);
	}

	// Activity Reports
	@Override
	public List<ActivityReport> getAllReports(Integer userId)
	{
		return activityReportService.getByUser(userId);
	}

	@Override
	public List<UtilizationWeek> getAllUtilizationWeeks(Integer userId)
	{
		return utilizationWeekService.getByUser(userId);
	}

	@Override
	public UtilizationWeek getSingleUtilizationWeek(Integer userId,
			Integer utilId)
	{
		return utilizationWeekService.getUtilizationWeek(utilId);
	}

	@Override
	public ActivityReport getReport(Integer userId, Integer reportId)
	{
		ActivityReport report = activityReportService
				.getActivityReport(reportId);
		if (report.getAuthor().equals(userId))
			return report;
		else
			return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void saveReport(Integer userId, ActivityReport report)
	{
		// report.setAuthor(userId);
		activityReportService.save(report);
		userService.updateYearUtilization(userId, report.getDate().getYear());
	}

	@Override
	public void saveUtilizationWeek(Integer userId, UtilizationWeek report)
	{
		utilizationWeekService.save(report);
	}

	@Override
	public void deleteReport(Integer userId, Integer reportId)
	{
		activityReportService.delete(reportId);

	}

	// Activity Report Templates
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
	public void deleteReportTemplate(Integer author, String name)
	{
		activityReportTemplateService.delete(author, name);
	}

	// Customer
	@Override
	public List<Customer> getAllCustomer()
	{
		return customerService.getAll();
	}

	@Override
	public Customer getCustomer(Integer id)
	{
		return customerService.getById(id);
	}

	/**
	 * Parses a String to Date
	 * 
	 * @param date
	 *            the Date
	 * @return the newly created String.
	 */
	@SuppressWarnings("deprecation")
	private Date stringToDate(String date)
	{
		return new Date(Integer.parseInt(date.substring(0, 4)) - 1900,
				Integer.parseInt(date.substring(5, 7)) - 1,
				Integer.parseInt(date.substring(8)));
	}

	@Override
	public List<UserJob> getUsersToJob(Integer jobNo, Integer posNo)
	{
		return userJobService.getByJobNrAndPosNr(jobNo, posNo);
	}

	@Override
	@Deprecated
	public List<ActivityReport> getReportPeriod(Integer userId, String fromto)
	{
		return activityReportService.getByUserAndPeriod(userId,
				stringToDate(fromto.substring(0, 10)),
				stringToDate(fromto.substring(11)));
	}

	@Override
	public Customer getCustomer(String name)
	{
		return customerService.getByName(name);
	}

	@Override
	public ManagerPageRestServiceResponse getEmployees(Integer supervisorId)
	{
		List<User> users = userService.getBySupervisor(supervisorId);
		List<Job> jobs = new ArrayList<Job>();
		for (User user : users)
		{
			List<Job> temp = jobService.getByUser(user.getId());
			if (temp != null)
			{
				List<JobID> keys = new ArrayList<JobID>();
				for (Job job : temp)
				{
					keys.add(new JobID(job.getJobNr(), job.getPosNr()));
					if (!jobs.contains(job))
						jobs.add(job);
				}
				user.setAssignedJobs(keys);
			}
		}
		return new ManagerPageRestServiceResponse(users, jobs);
	}

	@Override
	public List<PseudoJob> getAllPseudoJobs(Integer userId)
	{
		return pseudoService.getAllByAuthor(userId);
	}

	@Override
	public void savePseudoJob(Integer userId, PseudoJob pseudoJob)
	{
		pseudoService.save(pseudoJob);
	}

	@Override
	public void deletePseudoJob(Integer userId, Integer pseudoJobId)
	{
		pseudoService.delete(pseudoService.getById(pseudoJobId));
	}

	@Override
	public ProjectPageRestServiceResponse getProjectPageData(Integer userId)
	{
		List<Job> jobs = jobService.getByUser(userId);
		List<Customer> customers = new ArrayList<Customer>();
		for (Job job : jobs)
		{
			Customer customer = customerService.getById(job.getCustomerID());
			if (customer != null && !customers.contains(customer))
			{
				customers.add(customerService.getById(job.getCustomerID()));
			}
		}
		return new ProjectPageRestServiceResponse(jobs, customers);
	}

	@Override
	public void setJobLock(Integer jobNo, Integer posNo, boolean lock)
	{
		Job job = jobService.getJob(jobNo, posNo);
		job.setLocked(lock);
		jobService.save(job);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Integer getUtilizationMonth(Integer userId, Integer year,
			Integer month)
	{
		// Sooooo dirty...
		Date from = new Date(year - 1900, month - 1, 1);
		Date to = new Date(year - 1900, month - 1, 30);
		return userService.getUtilization(userId, from, to);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Integer getUtilizationYear(Integer userId, Integer year)
	{
		// Oh gaaaaawd...
		Date from = new Date(year - 1900, 0, 1);
		Date to = new Date(year - 1900, 11, 30);
		return userService.getUtilization(userId, from, to);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Integer> getUtilizationDays(Integer userId, Integer year,
			Integer month)
	{
		List<Integer> utilization = new ArrayList<Integer>();
		List<ActivityReport> reports = activityReportService
				.getByUserAndMonth(userId, year, month);
		for (Integer i = 0; i < 32; i++)
			utilization.add(0);
		for (ActivityReport report : reports)
		{
			// 8 Hours = 480 Minutes = 100%
			Integer index = report.getDate().getDate();
			utilization.set(index, utilization.get(index)
					+ (int) (report.getDuration() * (10f / 48f)));
		}
		for (Integer p : utilization)
			System.out.print(p + ", ");
		return utilization;
	}

	@Override
	@SuppressWarnings("deprecation")
	public List<Integer> getUtilizationMonths(Integer userId, Integer year)
	{
		List<Integer> utilization = new ArrayList<Integer>();
		List<ActivityReport> reports = activityReportService
				.getByUserAndYear(userId, year);
		for (Integer i = 0; i < 13; i++)
			utilization.add(0);
		List<Integer> workingDays = new ArrayList<Integer>();
		for (Integer i = 0; i < 12; i++)
			workingDays.add(getWorkDays(year, i));

		for (ActivityReport report : reports)
		{
			Integer index = report.getDate().getMonth() + 1;
			utilization.set(index, utilization.get(index)
					+ (int) (report.getDuration() * (10f / 48f)));
		}

		// 8 Hours * workingDays = 480 Minutes * workingDays = 100%
		for (Integer i = 1; i < 13; i++)
			utilization.set(i, (int) ((utilization.get(i) * (10f / 48f))
					/ workingDays.get(i - 1)));

		for (Integer p : utilization)
			System.out.print(p + ", ");
		return utilization;
	}

	/**
	 * Returns the amount of working days in the given month, but does not
	 * exclude public and personal holidays, etc. TODO: Include those holidays.
	 * 
	 * @param year
	 *            the year
	 * @param month
	 *            the month, 0 is January
	 * @return amount of working days
	 */
	private Integer getWorkDays(Integer year, Integer month)
	{
		Calendar firstDay = Calendar.getInstance();
		Calendar lastDay = Calendar.getInstance();
		firstDay.set(year, month, 1, 0, 0, 0);
		lastDay.set(year, month, 0, 0, 0, 0);
		lastDay.set(Calendar.DAY_OF_MONTH,
				firstDay.getMaximum(Calendar.DAY_OF_MONTH));
		Integer workDays = 0;

		do
		{
			if (firstDay.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& firstDay.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
			{
				workDays++;
			}
			firstDay.add(Calendar.DAY_OF_MONTH, 1);
		} while (firstDay.get(Calendar.DAY_OF_MONTH) < lastDay
				.get(Calendar.DAY_OF_MONTH));
		// Has to be repeated
		if (firstDay.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
				&& firstDay.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
			workDays++;
		return workDays;
	}

	@Override
	public Job getJob(Integer jobNo, Integer posNo)
	{
		return jobService.getJob(jobNo, posNo);
	}

	@Override
	public void deleteUtilizationWeek(Integer userId, Integer utilId)
	{
		utilizationWeekService.delete(utilId);
	}

	@Override
	public List<JobTask> getJobTasks(Integer user)
	{
		List<JobTask> tasks = new ArrayList<JobTask>();
		for(Job job : jobService.getByUser(user))
		{
			tasks.addAll(jobTaskService.getByJobNr(job.getJobNr(), job.getPosNr()));
		}
		return tasks;
	}
}
