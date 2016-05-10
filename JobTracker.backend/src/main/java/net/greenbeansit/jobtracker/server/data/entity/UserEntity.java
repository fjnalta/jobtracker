package net.greenbeansit.jobtracker.server.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
// TODO Mike: Implement NamedQueries for Employee
// @NamedQueries( { @NamedQuery(name = "ActivityReportTemplate.findAll", query =
// "SELECT p FROM Person p"),
// @NamedQuery(name = "Person.findPerson", query = "SELECT p FROM Person p where
// p.name=:name and p.age=:age")
// })
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

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "SURNAME")
	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	@Column(name = "SUPERVISOR")
	public Integer getSupervisor()
	{
		return supervisor;
	}

	public void setSupervisor(Integer supervisor)
	{
		this.supervisor = supervisor;
	}

}
