package net.greenbeansit.jobtracker.backend.services;

import net.greenbeansit.jobtracker.shared.ActivityReportService;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplateService;
import net.greenbeansit.jobtracker.shared.Employee;
import net.greenbeansit.jobtracker.shared.JobService;

public interface IEmployeeService {
	
	Employee getEmployee(Long employeeId);
	JobService getJobService(Long employeeId);
	ActivityReportService getReportService(Long employeeId);
	ActivityReportTemplateService getTemplateService(Long employeeId);
	
}
