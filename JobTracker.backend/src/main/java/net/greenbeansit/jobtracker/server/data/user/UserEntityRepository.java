package net.greenbeansit.jobtracker.server.data.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for handling {@link UserEntity} and making requests to
 * the database.
 * 
 * @author Mike Hukiewitz
 *
 */
public interface UserEntityRepository
		extends CrudRepository<UserEntity, Integer>
{

	/**
	 * @return all instances of {@link UserEntity}
	 */
	@Override
	List<UserEntity> findAll();

	/**
	 * Returns the first {@link UserEntity} associated with this name. Should not really be used.
	 * @param name first name
	 * @param surname last name
	 * @return The first user known by this name
	 */
	UserEntity findByName(String name, String surname);

	/**
	 * Returns all {@link UserEntity} supervised by the given user.
	 * @param id ID of the supervisor
	 * @return List of {@link UserEntity}
	 */
	List<UserEntity> findBySupervisor(Integer id);

	/**
	 * Returns an {@link UserEntity} by its ID.
	 * 
	 * @param id
	 *            unique ID
	 * @return an {@link UserEntity}, null if non-existent
	 */
	UserEntity findById(Integer id);

	/**
	 * Saves an {@link UserEntity} to the database.
	 * 
	 * @param persisted
	 *            {@link UserEntity} to save
	 * @return the persisted {@link UserEntity}
	 */
	@Override
	@SuppressWarnings("unchecked")
	UserEntity save(UserEntity persisted);
}
