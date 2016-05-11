package net.greenbeansit.jobtracker.server.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PSEUDO_JOB")
public class PseudoJobEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5496667715876969877L;

	@Id
	@GeneratedValue
	private Integer				id;

	private String				name;
	private String				customer;

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

	@Column(name = "CUSTOMER")
	public String getCustomer()
	{
		return customer;
	}

	public void setCustomer(String customer)
	{
		this.customer = customer;
	}
}
