package net.greenbeansit.jobtracker.server.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.data.entity.UtilizationWeekEntity;

public interface UtilizationWeekEntityRepository extends CrudRepository<UtilizationWeekEntity, Integer>
{

	void delete(UtilizationWeekEntity deleted);

	List<UtilizationWeekEntity> findAll();

	UtilizationWeekEntity findById(Integer id);

	@SuppressWarnings("unchecked")
	UtilizationWeekEntity save(UtilizationWeekEntity persisted);

	List<UtilizationWeekEntity> findByAuthor(Integer author);
	
	List<UtilizationWeekEntity> findByPseudoJobId(Integer pseudoJobId);
	
	//TODO Mike: add from Date to Date
}
