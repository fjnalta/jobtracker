package net.greenbeansit.jobtracker.server.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.data.entity.UserJobEntity;

public interface UserJobEntityRepository extends CrudRepository<UserJobEntity, Integer>
{
	
	void delete(UserJobEntity deleted);
	
	@SuppressWarnings("unchecked")
	UserJobEntity save(UserJobEntity persisted);

	List<UserJobEntity> findAll();

	List<UserJobEntity> findByUserId(Integer userId);
	
	List<UserJobEntity> findByJobNrAndPosNr(Integer jobNr, Integer posNr);
}
