package net.greenbeansit.jobtracker.server.data.userJob;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for handling {@link UserJobEntity} and making requests to the
 * database.
 * 
 * @author Mike Hukiewitz
 *
 */
public interface UserJobEntityRepository
		extends CrudRepository<UserJobEntity, Integer>
{
	/**
	 * Deletes the given entity.
	 * 
	 * @param deleted
	 *            entity to delete.
	 */
	@Override
	void delete(UserJobEntity deleted);

	/**
	 * Saves an {@link UserJobEntity} to the database.
	 * 
	 * @param persisted
	 *            {@link UserJobEntity} to save
	 * @return the persisted {@link UserJobEntity}
	 */
	@Override
	@SuppressWarnings("unchecked")
	UserJobEntity save(UserJobEntity persisted);

	/**
	 * @return all instances of {@link UserJobEntity}
	 */
	@Override
	List<UserJobEntity> findAll();

	/**
	 * Returns all instances of {@link UserJobEntity} which include the given
	 * user.
	 * 
	 * @param userId
	 *            ID of the user
	 * @return List of {@link UserJobEntity}
	 */
	List<UserJobEntity> findByUserId(Integer userId);

	/**
	 * Returns all instances of {@link UserJobEntity} which include the given
	 * job.
	 * 
	 * @param jobNr
	 *            3 to 6 digits
	 * @param posNr
	 *            up to 3 digits
	 * @return List of {@link UserJobEntity}
	 */
	List<UserJobEntity> findByJobNrAndPosNr(Integer jobNr, Integer posNr);
}
