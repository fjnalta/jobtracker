package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a Customer
 * 
 * @author Philipp Minges
 */
public class Customer implements Serializable
{

	private static final long	serialVersionUID	= 5968348976224309946L;

	private Integer				id;
	private String				name;

	/**
	 * Empty Constructor
	 */
	public Customer(){

	}

	/**
	 * Initializes a new instance of the {@link Customer} class
	 *
	 * @param id
	 *            the identifier of the customer
	 * @param name
	 *            the name of the customer
	 */
	public Customer(Integer id, String name)
	{
		this.id = id;
		this.name = name;
	}

	/**
	 * Gets the Id of the Customer
	 * @return the id.
     */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id of the Customer
	 * @param id the id of the Customer.
     */
	public void setId(Integer id)
	{
		if (id == null)
		{
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	/**
	 * Gets the Name of the Customer
	 * @return the Name of the Customer.
     */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the Name of the Customer.
	 * @param name the Name of the Customer.
     */
	public void setName(String name)
	{
		if (name == null)
		{
			throw new IllegalArgumentException();
		}
		this.name = name;
	}
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if (obj instanceof Customer)
		{
			Customer temp = (Customer) obj;
			return this.id.equals(temp.id);
		} else
			return false;
	}
	
	@Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
