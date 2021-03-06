package net.greenbeansit.jobtracker.server.data.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.shared.Customer;

/**
 * Implementation of CustomerDataService {@link CustomerDataService}.
 * 
 * @author Mike Hukiewitz & Philipp Minges
 *
 */

@Service("customerService")
public class CustomerServiceJpa implements CustomerDataService
{

	@Autowired
	private CustomerEntityRepository repository;

	@Override
	public List<Customer> getAll()
	{
		ArrayList<Customer> list = new ArrayList<Customer>();
		for (CustomerEntity entity : repository.findAll())
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public Customer getById(Integer customerId)
	{
		return convert(repository.findById(customerId));
	}

	@Override
	public Customer getByName(String name)
	{
		return convert(repository.findByName(name));
	}

	/**
	 * Converts a {@link CustomerEntity} to {@link Customer}
	 * 
	 * @param entity
	 *            {@link CustomerEntity} the Customer Entity
	 * @return {@link Customer} the Customer with Name and Id.
	 */
	private Customer convert(CustomerEntity entity)
	{
		if (entity == null)
			return null;
		return new Customer(entity.getId(), entity.getName());
	}

	// private JobEntity convert(Customer user)
	// {
	// if (user == null)
	// return null;
	// return new JobEntity(job.getJobNr(), job.getPosNr(), job.getPayMode(),
	// job.getCustomerID(), job.getDesc(), job.getMaxBudget(),
	// job.getUsedBudget());
	// }
}
