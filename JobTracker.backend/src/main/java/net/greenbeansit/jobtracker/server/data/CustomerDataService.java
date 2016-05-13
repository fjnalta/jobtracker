package net.greenbeansit.jobtracker.server.data;

import java.util.List;

import net.greenbeansit.jobtracker.shared.Customer;

/**
 * Database interface for {@link Customer}s.
 * 
 * @author Mike Hukiewitz
 *
 */

public interface CustomerDataService
{

	/**
	 * Returns all existing Customers.
	 * @return Customers in database
	 */
	List<Customer> getAll();
	
	/**
	 * Returns a Customer by his ID.
	 * @param customerId ID of requested Customer
	 * @return shared Customer object
	 */
	Customer getById(Integer customerId);
	
	/**
	 * Retrieves a Customer object by his name.
	 * @param name display name of the customer
	 * @return shared Customer object
	 */
	Customer getByName(String name);
}
