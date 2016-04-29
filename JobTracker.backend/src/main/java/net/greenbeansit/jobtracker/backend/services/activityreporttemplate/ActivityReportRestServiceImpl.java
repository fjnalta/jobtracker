package net.greenbeansit.jobtracker.backend.services.activityreporttemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import net.greenbeansit.Jobtracker.shared.ActivityReport;
import net.greenbeansit.Jobtracker.shared.ActivityReportService;

@Component
@Path("activityreport")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ActivityReportRestServiceImpl implements ActivityReportService {

	
	@Inject
	private IActivityReportService activityReportService;
	/**
	 * Map representing a mock Database
	 */
	private  static Map<Long, ActivityReport> databaseActivityReport = new HashMap<Long, ActivityReport>();
	
	
	public ActivityReportRestServiceImpl(){
	}

	public List<ActivityReport> getAllActivityReports()
	{
		return new ArrayList<ActivityReport>(databaseActivityReport.values());
	}

	public ActivityReport getActivityReport(String reportId)
	{
		return activityReportService.getActivityReport(Long.valueOf(reportId));
	}

	/**
	 * Returns all activities from a given employee in a given time span.
	 * @param employeeId ID of employee 
	 * @param from
	 * @param to
	 * @return
	 */
	public List<ActivityReport> getActivityReportPeriod(String employeeId, String from,
			String to)
	{
		List<ActivityReport> activityReports = new ArrayList<ActivityReport>();
		for(Entry<Long, ActivityReport> a : databaseActivityReport.entrySet()) {
			ActivityReport report = a.getValue();
			if(report.getAuthor() == Long.parseLong(employeeId) && report.getDate().getTime() >= Long.parseLong(from) && report.getDate().getTime() <= Long.parseLong(to))
				activityReports.add(report);
		}
		return activityReports;
	}

	public ActivityReport createActivityReport(ActivityReport activityReport)
	{
		activityReport.setId(System.currentTimeMillis());
		databaseActivityReport.put(activityReport.getId(), activityReport);
		return activityReport;
	}

	public ActivityReport updateActivityReport(ActivityReport activityReport)
	{
		databaseActivityReport.put(activityReport.getId(), activityReport);
		return activityReport;
	}
	
}
