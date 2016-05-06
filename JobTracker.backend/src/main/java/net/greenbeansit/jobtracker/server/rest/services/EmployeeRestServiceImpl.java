package net.greenbeansit.jobtracker.server.rest.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.Employee;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.JobID;
import net.greenbeansit.jobtracker.shared.rest.services.EmployeeRestService;


/**
 * Dummy implementation of the {@link EmployeeRestService} interface.
 * 
 * @author Max Blatt & Alexander Kirilyuk
 */
public class EmployeeRestServiceImpl implements EmployeeRestService
{
	private static Map<Long, Employee>						employeeMap			= new HashMap<Long, Employee>();
	private static Map<Long, List<ActivityReport>>			reportMap			= new HashMap<Long, List<ActivityReport>>();
	private static Map<Long, List<ActivityReportTemplate>>	reportTemplateMap	= new HashMap<Long, List<ActivityReportTemplate>>();
	private static Map<Long, List<Job>>						jobMap				= new HashMap<Long, List<Job>>();

	static
	{
		employeeMap.put(1L, new Employee(1L, "Max Mus", "Kirilyuk"));
		employeeMap.put(2L, new Employee(2L, "Alexander", "Kirilyuk"));

		List<Job> firstJobList = new ArrayList<Job>();
		firstJobList.add(
				new Job(new JobID(1, 2, "TEST", "TEST", "TEST"), 1000, 1000));
		firstJobList.add(new Job(new JobID(2, 2, "TEST2", "TEST2", "TEST2"),
				2000, 2000));
		firstJobList.add(new Job(new JobID(3, 2, "TEST3", "TEST3", "TEST3"),
				2000, 2000));
		jobMap.put(1L, firstJobList);
		List<Job> secondJobList = new ArrayList<Job>();
		secondJobList.add(
				new Job(new JobID(3, 2, "TEST3", "TEST", "TEST"), 1000, 1000));
		secondJobList.add(new Job(
				new JobID(4, 2, "TEST4", "Discription", "TEST2"), 2000, 2000));
		secondJobList.add(new Job(new JobID(5, 2, "TEST5", "TEST3", "TEST3"),
				2000, 2000));
		jobMap.put(2L, secondJobList);

	}

	/**
	 * Initializes a new instance of the {@link EmployeeRestService} class.
	 */
	public EmployeeRestServiceImpl()
	{

	}

	public Employee getEmployee(Long employeeId)
	{
		System.out.println("getEmployee(" + employeeId + ")");
		
		if (employeeMap.containsKey(employeeId))
			return employeeMap.get(employeeId);

		throw new NotFoundException();
	}

	public List<Job> getAllJobs(Long employeeId)
	{
		if (jobMap.containsKey(employeeId))
			return jobMap.get(employeeId);

		throw new NotFoundException();
	}

	public Job getJob(Long employeeId, Integer jobId)
	{
		if (jobMap.containsKey(employeeId))
		{
			for (Job currentJob : jobMap.get(employeeId))
			{
				if (currentJob.getJobID().getJobNr() == jobId)
				{
					return currentJob;
				}
			}
		}
		throw new NotFoundException();
	}

	public List<ActivityReport> getAllReports(Long employeeId)
	{
		if (reportMap.containsKey(employeeId))
			return reportMap.get(employeeId);
		throw new NotFoundException();
	}

	public ActivityReport getReport(Long employeeId, Long reportId)
	{
		if (reportMap.containsKey(employeeId))
			for (ActivityReport currentReport : reportMap.get(employeeId))
			{
				if (currentReport.getId() == reportId)
				{
					return currentReport;
				}
			}
		throw new NotFoundException();
	}

	public List<ActivityReport> getReportPeriod(Long employeeId, String from,
			String to)
	{
		if (reportMap.containsKey(employeeId))
			return reportMap.get(employeeId);
		throw new NotFoundException();
	}

	public void createReport(Long employeeId, ActivityReport report)
	{
		// TODO Auto-generated method stub

	}

	public void updateReport(Long employeeId, ActivityReport report)
	{
		// TODO Auto-generated method stub

	}

	public List<ActivityReportTemplate> getAllTemplates(Long employeeId)
	{
		if (reportTemplateMap.containsKey(employeeId))
			return reportTemplateMap.get(employeeId);
		throw new NotFoundException();
	}

	public void saveTemplate(Long employeeId, ActivityReportTemplate template)
	{
		// TODO Auto-generated method stub

	}
}
