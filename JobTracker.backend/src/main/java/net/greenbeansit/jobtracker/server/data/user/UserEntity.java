package net.greenbeansit.jobtracker.server.data.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

	private String				name;
	private String				surname;
	private Integer				supervisor;
	private Integer				utilization;
	private Integer				utilizationYear;
	
	public UserEntity()
	{
		
	}
	
	public UserEntity(Integer id, String name, String surname, Integer supervisor)
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.supervisor = supervisor;
	}
	
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

	@Column(name = "name")
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "surname")
	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	@Column(name = "supervisor")
	public Integer getSupervisor()
	{
		return supervisor;
	}

	public void setSupervisor(Integer supervisor)
	{
		this.supervisor = supervisor;
	}

	@Column(name = "utilization")
	public Integer getUtilization()
	{
		return utilization;
	}

	public void setUtilization(Integer utilization)
	{
		this.utilization = utilization;
	}

	@Column(name = "utilization_year")
	public Integer getUtilizationYear()
	{
		return utilizationYear;
	}

	public void setUtilizationYear(Integer utilizationYear)
	{
		this.utilizationYear = utilizationYear;
	}

}
