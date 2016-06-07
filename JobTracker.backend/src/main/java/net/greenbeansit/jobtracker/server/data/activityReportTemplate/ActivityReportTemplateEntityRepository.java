package net.greenbeansit.jobtracker.server.data.activityReportTemplate;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for handling {@link ActivityReportTemplateEntity} and making requests to
 * the database.
 * 
 * @author Mike Hukiewitz
 *
 */
public interface ActivityReportTemplateEntityRepository
		extends CrudRepository<ActivityReportTemplateEntity, Integer>
{

	/**
	 * Deletes the given entity.
	 * 
	 * @param id
	 *            entity to delete.
	 */
	@Modifying
    @Query("delete from ActivityReportTemplateEntity where id = ?1")
	void delete(Integer id);

	/**
	 * Saves an {@link ActivityReportTemplateEntity} to the database.
	 * 
	 * @param persisted
	 *            {@link ActivityReportTemplateEntity} to save
	 * @return the persisted {@link ActivityReportTemplateEntity}
	 */
	@Override
	@SuppressWarnings("unchecked")
	ActivityReportTemplateEntity save(ActivityReportTemplateEntity persisted);

	/**
	 * Returns all {@link ActivityReportTemplateEntity} written by a given user.
	 * 
	 * @param author
	 *            ID of the author
	 * @return List of his {@link ActivityReportTemplateEntity}
	 */
	List<ActivityReportTemplateEntity> findByAuthor(Integer author);

	/**
	 * Returns an {@link ActivityReportTemplateEntity} by it's unique combination of author and template name.
	 * @param author ID of the author
	 * @param name name of the template
	 * @return corresponding {@link ActivityReportTemplateEntity}
	 */
	ActivityReportTemplateEntity findByAuthorAndName(Integer author,
			String name);

}
