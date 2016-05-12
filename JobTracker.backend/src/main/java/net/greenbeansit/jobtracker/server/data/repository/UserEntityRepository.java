package net.greenbeansit.jobtracker.server.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.data.entity.UserEntity;

public interface UserEntityRepository extends CrudRepository<UserEntity, Integer> {
	

	List<UserEntity> findAll();
	UserEntity findByName(String name, String surname);
	List<UserEntity> findBySupervisor(Integer id);
	List<UserEntity> findById(Integer id);

}
