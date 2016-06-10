package net.greenbeansit.jobtracker.server.data.job;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for handling {@link JobEntity} and making requests to the
 * database.
 * 
 * @author Mike Hukiewitz
 *
 */
public interface JobEntityRepository extends CrudRepository<JobEntity, Integer>
{

	/**
	 * Deletes the given entity.
	 * 
	 * @param deleted
	 *            entity to delete.
	 */
	@Override
	void delete(JobEntity deleted);

	/**
	 * @return all instances of {@link JobEntity}
	 */
	@Override
	List<JobEntity> findAll();

	/**
	 * Saves an {@link JobEntity} to the database.
	 * 
	 * @param persisted
	 *            {@link JobEntity} to save
	 * @return the persisted {@link JobEntity}
	 */
	@Override
	@SuppressWarnings("unchecked")
	JobEntity save(JobEntity persisted);

	/**
	 * Returns the {@link JobEntity} assigned to a given job number.
	 * 
	 * @param jobNr
	 *            3 to 6 digits
	 * @param posNr
	 *            up to 3 digits
	 * @return corresponding {@link JobEntity}
	 */
	JobEntity findByJobNrAndPosNr(Integer jobNr, Integer posNr);
	
	/**
	 * Returns all {@link JobEntity} marked as internal.
	 * 
	 * @return List of {@link JobEntity}
	 */
	List<JobEntity> findByInternTrue();

	/**
	 * Returns all {@link JobEntity} related to a single customer.
	 * 
	 * @param customer_id
	 *            ID of the customer
	 * @return List of {@link JobEntity}
	 */
	List<JobEntity> findByCustomerID(Integer customer_id);
}
