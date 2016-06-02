package net.greenbeansit.jobtracker.server.data.utilizationWeek;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

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

	@Override
	List<UtilizationWeekEntity> findAll();

	UtilizationWeekEntity findById(Integer id);

	@Override
	@SuppressWarnings("unchecked")
	UtilizationWeekEntity save(UtilizationWeekEntity persisted);

	List<UtilizationWeekEntity> findByAuthor(Integer author);

	List<UtilizationWeekEntity> findByPseudoJobId(Integer pseudoJobId);
}
