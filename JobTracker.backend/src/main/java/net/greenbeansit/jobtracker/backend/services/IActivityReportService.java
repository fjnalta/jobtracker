package net.greenbeansit.jobtracker.backend.services;

import net.greenbeansit.Jobtracker.shared.ActivityReport;

public interface IActivityReportService {

	ActivityReport[] getAllReports();
	ActivityReport getReport(Long reportId);
	ActivityReport[] getReportPeriod(Long employeeId, String from, String to);
	void createReport(ActivityReport report);
	void updateReport(ActivityReport report);
	
}
