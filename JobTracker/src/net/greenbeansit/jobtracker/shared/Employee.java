package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a normal user with no extra rights.
 * 
 * @author Max Blatt
 */
public class Employee implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5141608788110790303L;
	private Long	id;
	private String	firstName, lastName;

	
	/**
	 * Initializes a new instance of the {@link Employee} class.
	 */
	public Employee()
	{
		
	}
	
	public Employee(Long id,String firstName,String lastName){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public long getId()
	{
		return id;
	}

}
