package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents 
 * 
 * @author Philipp Minges
 */
public class Customer implements Serializable
{

	private static final long serialVersionUID = 5968348976224309946L;
	
	private Integer id;
	private String name;
	
	/**
	 * Initializes a new instance of the {@link Customer} class
	 * @param id the identifier of the customer
	 * @param name the name of the customer
	 */
	public Customer(Integer id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		if (id == null)
		{
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		if (name == null)
		{
			throw new IllegalArgumentException();
		}
		this.name = name;
	}
}
