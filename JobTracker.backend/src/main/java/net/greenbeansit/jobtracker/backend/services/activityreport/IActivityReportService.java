package net.greenbeansit.jobtracker.backend.services.activityreport;

import java.util.List;

import net.greenbeansit.Jobtracker.shared.ActivityReport;

/**
 * Database interface for {@link ActivityReport}s.
 * @author Mike Hukiewitz
 *
 */
public interface IActivityReportService {

	List<ActivityReport> getAllActivityReports();
	ActivityReport getActivityReport(Long reportId);
	List<ActivityReport> getActivityReportPeriod(Long employeeId, String from, String to);
	void createActivityReport(ActivityReport report);
	void updateActivityReport(ActivityReport report);
	
}
