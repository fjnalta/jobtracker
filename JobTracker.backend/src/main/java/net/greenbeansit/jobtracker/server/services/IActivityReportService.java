package net.greenbeansit.jobtracker.server.services;

import net.greenbeansit.jobtracker.shared.ActivityReport;

public interface IActivityReportService {

	ActivityReport[] getAllReports();
	ActivityReport getReport(Long reportId);
	ActivityReport[] getReportPeriod(Long employeeId, String from, String to);
	void createReport(ActivityReport report);
	void updateReport(ActivityReport report);
	
}
