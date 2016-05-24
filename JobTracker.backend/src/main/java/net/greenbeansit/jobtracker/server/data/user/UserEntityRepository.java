package net.greenbeansit.jobtracker.server.data.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.data.activityReport.ActivityReportEntity;

public interface UserEntityRepository extends CrudRepository<UserEntity, Integer> {
	

	List<UserEntity> findAll();
	UserEntity findByName(String name, String surname);
	List<UserEntity> findBySupervisor(Integer id);
	UserEntity findById(Integer id);
	@SuppressWarnings("unchecked")
	UserEntity save(UserEntity persisted);
}
