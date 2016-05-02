package net.greenbeansit.jobtracker.backend.services;

import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

public interface IActivityReportTemplateService {
	
	ActivityReportTemplate[] getAllTemplates();
	void saveTemplate(ActivityReportTemplate template);
	

}
