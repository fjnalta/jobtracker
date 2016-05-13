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

	/**
	 * Returns all ActivityReportTemplates. Only for debugging purposes.
	 * @return List of all templates
	 */
	List<ActivityReportTemplate> getAll();

	/**
	 * Retrieves a List of all Templates written by given author.
	 * @param author ID of the author
	 * @return List of their templates
	 */
	List<ActivityReportTemplate> getBy(Integer author);

	/**
	 * TODO Mike: JavaDocs for all DataServices
	 * @param templateId
	 * @return
	 */
	ActivityReportTemplate getTemplate(Integer templateId);

	ActivityReportTemplate getTemplate(Integer author, String name);

	boolean save(ActivityReportTemplate template);

	void delete(ActivityReportTemplate template);
}
