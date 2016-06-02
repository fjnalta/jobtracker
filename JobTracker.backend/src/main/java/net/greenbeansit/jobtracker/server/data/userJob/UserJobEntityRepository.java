package net.greenbeansit.jobtracker.server.data.userJob;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.data.activityReport.ActivityReportEntity;

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
	 * Saves an {@link ActivityReportEntity} to the database.
	 * 
	 * @param persisted
	 *            {@link ActivityReportEntity} to save
	 * @return the persisted {@link ActivityReportEntity}
	 */
	@Override
	@SuppressWarnings("unchecked")
	UserJobEntity save(UserJobEntity persisted);

	/**
	 * @return all instances of {@link ActivityReportEntity}
	 */
	@Override
	List<UserJobEntity> findAll();

	/**
	 * Returns all relations {@link 
	 * @param userId
	 * @return
	 */
	List<UserJobEntity> findByUserId(Integer userId);

	List<UserJobEntity> findByJobNrAndPosNr(Integer jobNr, Integer posNr);
}
