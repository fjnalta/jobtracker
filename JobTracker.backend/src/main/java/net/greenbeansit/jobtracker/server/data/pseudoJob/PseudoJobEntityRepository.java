package net.greenbeansit.jobtracker.server.data.pseudoJob;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for handling {@link PseudoJobEntity} and making requests to
 * the database.
 * 
 * @author Mike Hukiewitz
 *
 */
public interface PseudoJobEntityRepository
		extends CrudRepository<PseudoJobEntity, Integer>
{

	/**
	 * Deletes the given entity.
	 * 
	 * @param deleted
	 *            entity to delete.
	 */
	@Override
	void delete(PseudoJobEntity deleted);

	/**
	 * Saves an {@link PseudoJobEntity} to the database.
	 * 
	 * @param persisted
	 *            {@link PseudoJobEntity} to save
	 * @return the persisted {@link PseudoJobEntity}
	 */
	@Override
	@SuppressWarnings("unchecked")
	PseudoJobEntity save(PseudoJobEntity persisted);

	/**
	 * @return all instances of {@link PseudoJobEntity}
	 */
	@Override
	List<PseudoJobEntity> findAll();

	/**
	 * Returns all {@link PseudoJobEntity} by one author
	 * @param author ID of the author
	 * @return List of {@link PseudoJobEntity}
	 */
	List<PseudoJobEntity> findByAuthor(Integer author);

	/**
	 * Returns an {@link PseudoJobEntity} by its ID.
	 * 
	 * @param id
	 *            unique ID
	 * @return an {@link PseudoJobEntity}, null if non-existant
	 */
	PseudoJobEntity findById(Integer id);
}
