package net.greenbeansit.jobtracker.server.data.pseudoJob;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PseudoJobEntityRepository extends CrudRepository<PseudoJobEntity, Integer>
{

	void delete(PseudoJobEntity deleted);
	
	@SuppressWarnings("unchecked")
	PseudoJobEntity save(PseudoJobEntity persisted);

	List<PseudoJobEntity> findAll();

	PseudoJobEntity findById(Integer id);
}
