package net.greenbeansit.jobtracker.server.data.activityReport;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository for handling {@link ActivityReportEntity} and making requests to
 * the database.
 * 
 * @author Mike Hukiewitz
 *
 */
public interface ActivityReportEntityRepository
		extends CrudRepository<ActivityReportEntity, Integer>
{

	/**
	 * Deletes the given entity.
	 * 
	 * @param id
	 *            entity to delete
	 */
	@Modifying
	@Query("delete from ActivityReportEntity where id = ?1")
	void delete(Integer id);

	/**
	 * @return all instances of {@link ActivityReportEntity}
	 */
	@Override
	List<ActivityReportEntity> findAll();

	/**
	 * Returns an {@link ActivityReportEntity} by its ID.
	 * 
	 * @param id
	 *            unique ID
	 * @return an {@link ActivityReportEntity}, null if non-existent
	 */
	ActivityReportEntity findById(Integer id);

	/**
	 * Saves an {@link ActivityReportEntity} to the database.
	 * 
	 * @param persisted
	 *            {@link ActivityReportEntity} to save
	 * @return the persisted {@link ActivityReportEntity}
	 */
	@Override
	@SuppressWarnings("unchecked")
	ActivityReportEntity save(ActivityReportEntity persisted);

	/**
	 * Returns all {@link ActivityReportEntity} written by a given user.
	 * 
	 * @param author
	 *            ID of the author
	 * @return List of his {@link ActivityReportEntity}
	 */
	List<ActivityReportEntity> findByAuthor(Integer author);

	/**
	 * Returns all {@link ActivityReportEntity} assigned to a task (JIRA
	 * compatibility).
	 * 
	 * @param taskId
	 *            ID of the task
	 * @return List of corresponding {@link ActivityReportEntity}
	 */
	List<ActivityReportEntity> findByTaskId(Integer taskId);

	/**
	 * Returns all {@link ActivityReportEntity} assigned to a given job.
	 * 
	 * @param jobNo
	 *            3 to 6 digits
	 * @param posNo
	 *            up to 3 digits
	 * @return List of corresponding {@link ActivityReportEntity}
	 */
	List<ActivityReportEntity> findByJobNoAndPosNo(Integer jobNo,
			Integer posNo);

	/**
	 * Returns all {@link ActivityReportEntity} from a single user in a single
	 * month.
	 * 
	 * @param author
	 *            ID of the author
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @return List of corresponding {@link ActivityReportEntity}
	 */
	@Query("select x from ActivityReportEntity x where x.author = :author and YEAR(x.beginDate) = :year and MONTH(x.beginDate) = :month")
	List<ActivityReportEntity> findByAuthorAndMonth(
			@Param("author") Integer author, @Param("year") Integer year,
			@Param("month") Integer month);

	/**
	 * Returns all {@link ActivityReportEntity} from a single user in a single
	 * year.
	 * 
	 * @param author
	 *            ID of the author
	 * @param year
	 *            the year
	 * @return List of corresponding {@link ActivityReportEntity}
	 */
	@Query("select x from ActivityReportEntity x where x.author = :author and YEAR(x.beginDate) = :year")
	List<ActivityReportEntity> findByAuthorAndYear(
			@Param("author") Integer author, @Param("year") Integer year);
}
