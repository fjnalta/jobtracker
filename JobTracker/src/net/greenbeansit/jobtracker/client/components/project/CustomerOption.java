package net.greenbeansit.jobtracker.client.components.project;

import org.gwtbootstrap3.extras.select.client.ui.Option;

import net.greenbeansit.jobtracker.shared.Customer;

class CustomerOption extends Option
{
	private Customer customer;
	
	public CustomerOption(Customer customer)
	{
		this.setText(customer.getName());
		this.customer = customer;
	}
	
	public Customer getCustomer()
	{
		return customer;
	}
}

