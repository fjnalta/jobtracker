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
	private Integer				remainingVacationDays;
	
	/**
	 * Initializes a new instance of the {@link User} class.
	 */
	public User()
	{

	}

	/**
	 *
	 * @param id user ID
	 * @param name user name
	 * @param surname user surname
	 * @param supervisor superviser ID for this user
     */
	public User(Integer id, String name, String surname, Integer supervisor)
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.supervisor = supervisor;
	}

	/**
	 *
	 * @return id of this user
     */
	public Integer getId() {
		return id;
	}

	/**
	 * set id of this user
	 * @param id Integer value
     */
	public void setId(Integer id) {
		if(id == null)
		{
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	/**
	 * get the name of this user
	 * @return String value name
     */
	public String getName() {
		return name;
	}

	/**
	 * set the name of this user
	 * @param name String value name
     */
	public void setName(String name) {
		if(name == "")
		{
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	/**
	 * get the surname of this user
	 * @return the String value of surname
     */
	public String getSurname() {
		return surname;
	}

	/**
	 * set the surname of this user
	 * @param surname String value
     */
	public void setSurname(String surname) {
		if(surname == "")
		{
			throw new IllegalArgumentException();
		}
		this.surname = surname;
	}

	/**
	 * get the superviser ID in charge for this user
	 * @return Integer value
     */
	public Integer getSupervisor() {
		return supervisor;
	}

	/**
	 * set teh superviser id for this user
	 * @param supervisor Integer value
     */
	public void setSupervisor(Integer supervisor) {
		if(supervisor == null)
		{
			throw new IllegalArgumentException();
		}
		this.supervisor = supervisor;
	}

	/**
	 * get the utilization of this user
	 * @return utilization integer value
     */
	public Integer getUtilization()
	{
		return utilization;
	}

	/**
	 * set the utilization for this user
	 * @param utilization Integer value
     */
	public void setUtilization(Integer utilization)
	{
		this.utilization = utilization;
	}

	/**
	 * get the assigned jobs for this user
	 * @return all assigned jobs for this user
     */
	public List<JobID> getAssignedJobs()
	{
		return assignedJobs;
	}

	/**
	 * set the assigned jobs for this user
	 * @param assignedJobs List<JOBID> object
     */
	public void setAssignedJobs(List<JobID> assignedJobs)
	{
		this.assignedJobs = assignedJobs;
	}
	
	
	/**
	 * Get the remaining vacation days.
	 * 
	 * @return an Integer.
	 */
	public Integer getRemainingVacationDays()
	{
		return remainingVacationDays;
	}

	/**
	 * Sets the remaining vacation days.
	 * 
	 * @param remainingVacationDays
	 */
	public void setRemainingVacationDays(Integer remainingVacationDays)
	{
		this.remainingVacationDays = remainingVacationDays;
	}

	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if (obj instanceof User)
		{
			User temp = (User) obj;
			return this.id.equals(temp.id);
		} else
			return false;
	}
}
