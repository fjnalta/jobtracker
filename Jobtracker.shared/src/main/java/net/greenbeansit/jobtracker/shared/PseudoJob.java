package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a PseudoJob for the Capazity planning
 * 
 * @author Philipp Minges
 */
public class PseudoJob implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2051156787516002689L;
	private Integer id;
	private String name;
	private String customer;
	
	/**
	 * Initializes a new instance of the {@link PseudoJob} class
	 * @param id the identifier
	 * @param name the name of the PseudoJob
	 * @param customer name of the customer
	 */
	public PseudoJob(Integer id, String name, String customer)
	{
		this.id = id;
		this.name = name;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (id == null)
		{
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == "")
		{
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		if (customer == "")
		{
			throw new IllegalArgumentException();
		}
		this.customer = customer;
	}
	
}
