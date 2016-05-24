package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.util.List;

import net.greenbeansit.jobtracker.shared.rest.services.RestService.JobID;

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
	private Integer				utilization;
	private List<JobID>			assignedJobs;

	/**
	 * Initializes a new instance of the {@link User} class.
	 */
	public User()
	{

	}

	public User(Integer id, String name, String surname, Integer supervisor)
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.supervisor = supervisor;
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

	public Integer getUtilization()
	{
		return utilization;
	}

	public void setUtilization(Integer utilization)
	{
		this.utilization = utilization;
	}

	public List<JobID> getAssignedJobs()
	{
		return assignedJobs;
	}

	public void setAssignedJobs(List<JobID> assignedJobs)
	{
		this.assignedJobs = assignedJobs;
	}
}
