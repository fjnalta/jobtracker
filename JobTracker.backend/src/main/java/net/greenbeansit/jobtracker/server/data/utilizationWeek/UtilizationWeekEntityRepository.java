package net.greenbeansit.jobtracker.server.data.utilizationWeek;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for handling {@link UtilizationWeekEntity} and making requests to
 * the database.
 * 
 * @author Mike Hukiewitz
 *
 */
public interface UtilizationWeekEntityRepository
		extends CrudRepository<UtilizationWeekEntity, Integer>
{

	/**
	 * Deletes the given entity.
	 * 
	 * @param id
	 *            entity to delete.
	 */
	@Modifying
	@Query("delete from UtilizationWeekEntity where id = ?1")
	void delete(Integer id);

	/**
	 * Retrieves all entities.
	 * 
	 * @return List of UtilizationWeekEntity
	 */
	@Override
	List<UtilizationWeekEntity> findAll();

	// /**
	// * Returns all {@link UtilizationWeekEntity} from a single user in a
	// single
	// * month.
	// *
	// * @param author
	// * ID of the author
	// * @param year
	// * the year
	// * @param month
	// * the month
	// * @return List of corresponding {@link UtilizationWeekEntity}
	// */
	// @Query("select x from UtilizationWeekEntity x where x.author = :author
	// and YEAR(x.begin) = :year and MONTH(x.begin) = :month")
	// List<UtilizationWeekEntity> findByAuthorAndMonth(
	// @Param("author") Integer author, @Param("year") Integer year,
	// @Param("month") Integer month);
	//
	// /**
	// * Returns all {@link UtilizationWeekEntity} from a single user in a
	// single
	// * year.
	// *
	// * @param author
	// * ID of the author
	// * @param year
	// * the year
	// * @return List of corresponding {@link UtilizationWeekEntity}
	// */
	// @Query("select x from UtilizationWeekEntity x where x.author = :author
	// and YEAR(x.begin) = :year")
	// List<UtilizationWeekEntity> findByAuthorAndYear(
	// @Param("author") Integer author, @Param("year") Integer year);

	/**
	 * Retrieves an entity by its ID.
	 * 
	 * @param id
	 *            its ID
	 * @return a UtilizationWeekEntity
	 */
	UtilizationWeekEntity findById(Integer id);

	/**
	 * Saves an entity to the database.
	 * 
	 * @param persisted
	 *            entity to save
	 * @return the same entity
	 */
	@Override
	@SuppressWarnings("unchecked")
	UtilizationWeekEntity save(UtilizationWeekEntity persisted);

	/**
	 * Retrieves all entities authored by given user.
	 * 
	 * @param author
	 *            ID of the author
	 * @return List of UtilizationWeekEntity
	 */
	List<UtilizationWeekEntity> findByAuthor(Integer author);

	/**
	 * Retrieve all entities associated with the given PseudoJob.
	 * 
	 * @param pseudoJobId
	 *            ID of the PseudoJob
	 * @return List of UtilizationWeekEntity
	 */
	List<UtilizationWeekEntity> findByPseudoJobId(Integer pseudoJobId);
}
