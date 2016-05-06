package net.greenbeansit.jobtracker.server.data;

import java.util.List;

import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

/**
 * Database interface for {@link ActivityReportTemplate}s.
 * @author Mike Hukiewitz
 *
 */

public interface ActivityReportTemplateDataService {
	
	List<ActivityReportTemplate> getAll();
	List<ActivityReportTemplate> getBy(Long author);
	ActivityReportTemplate getTemplate(Long templateId);
	ActivityReportTemplate getTemplate(Long author, String name);
	void save(ActivityReportTemplate template);
	void update(ActivityReportTemplate template);
	void delete(ActivityReportTemplate template);
}
