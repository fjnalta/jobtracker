package net.greenbeansit.jobtracker.server.service;

import java.util.List;

import net.greenbeansit.jobtracker.shared.ActivityReport;

/**
 * Database interface for {@link ActivityReport}s.
 * @author Mike Hukiewitz
 *
 */
public interface ActivityReportService {

	List<ActivityReport> getAllActivityReports();
	ActivityReport getActivityReport(Long reportId);
	List<ActivityReport> getActivityReportPeriod(Long employeeId, String from, String to);
	void saveActivityReport(ActivityReport report);
	void updateActivityReport(ActivityReport report);
	void deleteActivityReport(ActivityReport report);
}
