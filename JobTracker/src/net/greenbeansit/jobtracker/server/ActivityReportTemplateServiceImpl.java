package net.greenbeansit.jobtracker.server;

import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplateService;

public class ActivityReportTemplateServiceImpl implements ActivityReportTemplateService {

	private Long employeeId;
	
	public ActivityReportTemplateServiceImpl(Long employeeId)
	{
		this.employeeId = employeeId;
	}
	
	@Override
	public ActivityReportTemplate[] getAllTemplates() {
		// TODO Auto-generated method stub
		return null;
	}

}
