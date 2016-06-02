package net.greenbeansit.jobtracker.client.components.project;

import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import net.greenbeansit.jobtracker.shared.Customer;

/**
 * Specialization of the Bootstrap {@link Option} class which helps to display
 * a {@link Customer} in a {@link Select} box.
 * 
 * @author Max Blatt
 */
class CustomerOption extends Option
{
	private Customer customer;
	
	/**
	 * Initializes a new instance of the {@link CustomerOption} class.
	 * 
	 * @param customer the {@link Customer} that should be displayed.
	 */
	public CustomerOption(Customer customer)
	{
		this.setText(customer.getName());
		this.customer = customer;
	}
	
	/**
	 * Gets the {@link Customer} of the current {@link CustomerOption}.
	 * 
	 * @return a {@link Customer}.
	 */
	public Customer getCustomer()
	{
		return customer;
	}
}

