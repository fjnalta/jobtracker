package net.greenbeansit.jobtracker.server.data.customer;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for handling {@link CustomerEntity} and making requests to the
 * database.
 * 
 * @author Mike Hukiewitz
 *
 */
public interface CustomerEntityRepository
		extends CrudRepository<CustomerEntity, Integer>
{

	/**
	 * Deletes the given entity.
	 * 
	 * @param id
	 *            entity to delete.
	 */
	@Modifying
	@Query("delete from CustomerEntity where id = ?1")
	void delete(Integer id);

	/**
	 * Saves an {@link CustomerEntity} to the database.
	 * 
	 * @param persisted
	 *            {@link CustomerEntity} to save
	 * @return the persisted {@link CustomerEntity}
	 */
	@Override
	@SuppressWarnings("unchecked")
	CustomerEntity save(CustomerEntity persisted);

	/**
	 * @return all instances of {@link CustomerEntity}
	 */
	@Override
	List<CustomerEntity> findAll();

	/**
	 * Returns an {@link CustomerEntity} by its ID.
	 * 
	 * @param id
	 *            unique ID
	 * @return an {@link CustomerEntity}, null if non-existent
	 */
	CustomerEntity findById(Integer id);

	/**
	 * Returns an {@link CustomerEntity} by its unique name.
	 * 
	 * @param name
	 *            unique name
	 * @return an {@link CustomerEntity}, null if non-existent
	 */
	CustomerEntity findByName(String name);
}
