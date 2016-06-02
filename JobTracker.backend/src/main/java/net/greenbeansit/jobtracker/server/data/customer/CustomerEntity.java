package net.greenbeansit.jobtracker.server.data.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A class representing Customer as entities in our database. Only used in
 * backend. Each entity class requires a standard constructor and
 * getters/setters for usage in Java Spring.
 * 
 * @author Mike Hukiewitz
 *
 */
@Entity
@Table(name = "customer")
public class CustomerEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3842559068860954254L;

	@Id
	@GeneratedValue
	private Integer				id;
	@Column(name = "name")
	private String				name;

	/**
	 * Standard constructor for internal purposes.
	 */
	public CustomerEntity()
	{
	}
	
	/**
	 * Creates a new {@link CustomerEntity}
	 * @param name name of the customer (must be unique)
	 */
	public CustomerEntity(String name)
	{
		this.name = name;
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
}
