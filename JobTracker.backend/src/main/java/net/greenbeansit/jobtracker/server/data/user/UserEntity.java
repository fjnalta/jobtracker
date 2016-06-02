package net.greenbeansit.jobtracker.server.data.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A class representing User as entities in our database. Only used in
 * backend. Each entity class requires a standard constructor and
 * getters/setters for usage in Java Spring.
 * 
 * @author Mike Hukiewitz
 *
 */
@Entity
@Table(name = "user")
public class UserEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1371314200909587258L;

	@Id
	@GeneratedValue
	private Integer				id;

	@Column(name = "name")
	private String				name;
	@Column(name = "surname")
	private String				surname;
	@Column(name = "supervisor")
	private Integer				supervisor;
	@Column(name = "utilization")
	private Integer				utilization;
	@Column(name = "utilization_year")
	private Integer				utilizationYear;

	/**
	 * Standard constructor for internal purposes.
	 */
	public UserEntity()
	{

	}

	/**
	 * Initializes an {@link UserEntity}
	 * @param id ID of the user
	 * @param name their first name
	 * @param surname their last name
	 * @param supervisor ID of the supervisor
	 */
	public UserEntity(Integer id, String name, String surname,
			Integer supervisor)
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.supervisor = supervisor;
	}

	/**
	 * Creates a new {@link UserEntity}
	 * @param name their first name
	 * @param surname their last name
	 * @param supervisor ID of the supervisor
	 */
	public UserEntity(String name, String surname, Integer supervisor)
	{
		this.name = name;
		this.surname = surname;
		this.supervisor = supervisor;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public Integer getSupervisor()
	{
		return supervisor;
	}

	public void setSupervisor(Integer supervisor)
	{
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

	public Integer getUtilizationYear()
	{
		return utilizationYear;
	}

	public void setUtilizationYear(Integer utilizationYear)
	{
		this.utilizationYear = utilizationYear;
	}

}
