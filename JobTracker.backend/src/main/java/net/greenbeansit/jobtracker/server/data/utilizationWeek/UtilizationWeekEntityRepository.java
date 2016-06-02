package net.greenbeansit.jobtracker.server.data.utilizationWeek;

import java.util.List;

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
	 * @param deleted
	 *            entity to delete.
	 */
	@Override
	void delete(UtilizationWeekEntity deleted);

	/**
	 * Retrieves all entities.
	 * @return List of UtilizationWeekEntity
	 */
	@Override
	List<UtilizationWeekEntity> findAll();

	/**
	 * Retrieves an entity by its ID.
	 * @param id its ID
	 * @return a UtilizationWeekEntity
	 */
	UtilizationWeekEntity findById(Integer id);

	/**
	 * Saves an entity to the database.
	 * @param persisted entity to save
	 * @return the same entity
	 */
	@Override
	@SuppressWarnings("unchecked")
	UtilizationWeekEntity save(UtilizationWeekEntity persisted);

	/**
	 * Retrieves all entities authored by given user.
	 * @param author ID of the author
	 * @return List of UtilizationWeekEntity
	 */
	List<UtilizationWeekEntity> findByAuthor(Integer author);

	/**
	 * Retrieve all entities associated with the given PseudoJob.
	 * @param pseudoJobId ID of the PseudoJob
	 * @return List of UtilizationWeekEntity
	 */
	List<UtilizationWeekEntity> findByPseudoJobId(Integer pseudoJobId);
}
