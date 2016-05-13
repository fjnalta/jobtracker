package net.greenbeansit.jobtracker.server.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.data.entity.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer>
{

	void delete(CustomerEntity deleted);
	
	@SuppressWarnings("unchecked")
	CustomerEntity save(CustomerEntity persisted);

	List<CustomerEntity> findAll();

	CustomerEntity findById(Integer id);
	
	List<CustomerEntity> findByName(String name);
}
