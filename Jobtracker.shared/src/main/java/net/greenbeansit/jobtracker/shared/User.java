package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a normal user with no extra rights.
 * 
 * @author Max Blatt & Philipp Minges
 */
public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5141608788110790303L;
	private Integer				id;
	private String				name, surname;
	private Integer				supervisor;

	/**
	 * Initializes a new instance of the {@link User} class.
	 */
	public User()
	{

	}

	public User(Integer id, String name, String surname)
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if(id == null)
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		if(surname == "")
		{
			throw new IllegalArgumentException();
		}
		this.surname = surname;
	}

	public Integer getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Integer supervisor) {
		if(supervisor == null)
		{
			throw new IllegalArgumentException();
		}
		this.supervisor = supervisor;
	}
}
