package net.greenbeansit.jobtracker.server.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.data.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>
{

	void delete(UserEntity deleted);

	List<UserEntity> findAll();

	UserEntity findOne(Long id);

	@SuppressWarnings("unchecked")
	UserEntity save(UserEntity persisted);

	UserEntity update(UserEntity persisted);

	List<UserEntity> findByNameAndSurname(String NAME, String SURNAME);
	
	List<UserEntity> findBySupervisor(Integer SUPERVISOR);
}
