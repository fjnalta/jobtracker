package net.greenbeansit.jobtracker.server.data.jobTask;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface JobTaskEntityRepository
		extends CrudRepository<JobTaskEntity, Integer>
{

	/**
	 * Deletes the given entity.
	 * 
	 * @param deleted
	 *            entity to delete.
	 */
	@Override
	void delete(JobTaskEntity deleted);

	/**
	 * Saves an {@link JobTaskEntity} to the database.
	 * 
	 * @param persisted
	 *            {@link JobTaskEntity} to save
	 * @return the persisted {@link JobTaskEntity}
	 */
	@Override
	@SuppressWarnings("unchecked")
	JobTaskEntity save(JobTaskEntity persisted);

	/**
	 * @return all instances of {@link JobTaskEntity}
	 */
	@Override
	List<JobTaskEntity> findAll();

	/**
	 * Returns an {@link JobTaskEntity} by its ID.
	 * 
	 * @param id
	 *            unique ID
	 * @return an {@link JobTaskEntity}, null if non-existant
	 */
	JobTaskEntity findById(Integer id);

	/**
	 * Returns all {@link JobTaskEntity} assigned to a given job.
	 * 
	 * @param jobNr
	 *            3 to 6 digits
	 * @param posNr
	 *            up to 3 digits
	 * @return List of corresponding {@link JobTaskEntity}
	 */
	List<JobTaskEntity> findByJobNrAndPosNr(Integer jobNr, Integer posNr);
}
