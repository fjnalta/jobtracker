package net.greenbeansit.jobtracker.server.services;

import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

public interface IActivityReportTemplateService {
	
	ActivityReportTemplate[] getAllTemplates();
	void saveTemplate(ActivityReportTemplate template);
	

}
