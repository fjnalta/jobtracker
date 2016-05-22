package net.greenbeansit.jobtracker.server.data.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity, Integer> {
	

	List<UserEntity> findAll();
	UserEntity findByName(String name, String surname);
	List<UserEntity> findBySupervisor(Integer id);
	List<UserEntity> findById(Integer id);

}
