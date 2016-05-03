package net.greenbeansit.jobtracker.server.service;

import java.util.List;

import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

/**
 * Database interface for {@link ActivityReportTemplate}s.
 * @author Mike Hukiewitz
 *
 */

public interface ActivityReportTemplateService {
	
	List<ActivityReportTemplate> getAllActivityReportTemplates();
	ActivityReportTemplate getActivityReportTemplate(Long templateId);
	void saveActivityReportTemplate(ActivityReportTemplate template);
	void updateActivityReportTemplate(ActivityReportTemplate template);
	void deleteActivityReportTemplate(ActivityReportTemplate template);
}
