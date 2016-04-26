package net.greenbeansit.jobtracker.shared;

public class Employee
{
	private Long id;
	private String firstName,
				   lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getId()
	{
		return id;
	}

}
