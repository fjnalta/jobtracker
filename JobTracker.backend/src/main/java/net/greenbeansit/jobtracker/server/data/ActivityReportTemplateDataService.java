package net.greenbeansit.jobtracker.server.data;

import java.util.List;

import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

/**
 * Database interface for {@link ActivityReportTemplate}s.
 * 
 * @author Mike Hukiewitz
 *
 */

public interface ActivityReportTemplateDataService
{

	List<ActivityReportTemplate> getAll();

	List<ActivityReportTemplate> getBy(Integer author);

	ActivityReportTemplate getTemplate(Integer templateId);

	ActivityReportTemplate getTemplate(Integer author, String name);

	boolean save(ActivityReportTemplate template);

	void delete(ActivityReportTemplate template);
}
