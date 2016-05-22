package net.greenbeansit.jobtracker.server.data.customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerEntityRepository extends CrudRepository<CustomerEntity, Integer>
{

	void delete(CustomerEntity deleted);
	
	@SuppressWarnings("unchecked")
	CustomerEntity save(CustomerEntity persisted);

	List<CustomerEntity> findAll();

	CustomerEntity findById(Integer id);
	
	CustomerEntity findByName(String name);
}
