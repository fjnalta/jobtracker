package net.greenbeansit.jobtracker.server.data.activityReportTemplate;

import java.util.List;

import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;

/**
 * Database interface for {@link ActivityReportTemplate}s.
 * 
 * @author Mike Hukiewitz & Philipp Minges
 *
 */

public interface ActivityReportTemplateDataService
{

	/**
	 * Returns all ActivityReportTemplates. Only for debugging purposes.
	 * 
	 * @return List of all templates
	 */
	List<ActivityReportTemplate> getAll();

	/**
	 * Retrieves a List of all Templates written by given author.
	 * 
	 * @param author
	 *            ID of the author
	 * @return List of their templates
	 */
	List<ActivityReportTemplate> getBy(Integer author);

	/**
	 * Retrieve a template by it's author and unique name
	 * 
	 * @param author
	 *            ID of the author
	 * @param name
	 *            name of the template
	 * @return a template, if it exists
	 */
	ActivityReportTemplate getTemplate(Integer author, String name);

	/**
	 * Saves a template to the database.
	 * 
	 * @param template
	 *            template to be saved
	 * @return true if successful
	 */
	boolean save(ActivityReportTemplate template);

	/**
	 * Deletes a template from the database.
	 * 
	 * @param template the given Template
	 */
	void delete(ActivityReportTemplate template);
}
