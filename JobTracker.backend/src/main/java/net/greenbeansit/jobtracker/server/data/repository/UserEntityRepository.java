package net.greenbeansit.jobtracker.server.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.data.entity.UserEntity;

public interface UserEntityRepository extends CrudRepository<UserEntity, Integer> {
	
	void delete(UserEntity deleted);

	List<UserEntity> findAll();

	@SuppressWarnings("unchecked")
	UserEntity save(UserEntity persisted);

	UserEntity findByName(String name, String surname);
	List<UserEntity> findById(Integer id);

}
