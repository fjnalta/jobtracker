package net.greenbeansit.Jobtracker.shared;


/**
 * Represents a normal user with no extra rights.
 * 
 * @author Max Blatt
 */
public class Employee
{
	private Long	id;
	private String	firstName, lastName;

	
	/**
	 * Initializes a new instance of the {@link Employee} class.
	 */
	public Employee()
	{
		
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