package net.greenbeansit.jobtracker.backend.services;

import net.greenbeansit.Jobtracker.shared.ActivityReportService;
import net.greenbeansit.Jobtracker.shared.ActivityReportTemplateService;
import net.greenbeansit.Jobtracker.shared.Employee;
import net.greenbeansit.Jobtracker.shared.JobService;

public interface IEmployeeService {
	
	Employee getEmployee(Long employeeId);
	JobService getJobService(Long employeeId);
	ActivityReportService getReportService(Long employeeId);
	ActivityReportTemplateService getTemplateService(Long employeeId);
	
}
