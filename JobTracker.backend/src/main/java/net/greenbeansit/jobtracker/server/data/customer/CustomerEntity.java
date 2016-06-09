package net.greenbeansit.jobtracker.server.data.customer;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A class representing Customer as entities in our database. Only used in
 * backend. Each entity class requires a standard constructor and
 * getters/setters for usage in Java Spring.
 * 
 * @author Mike Hukiewitz & Philipp Minges
 *
 */
@Entity
@Table(name = "customer")
public class CustomerEntity implements Serializable
{

	private static final long	serialVersionUID	= 3842559068860954254L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
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
	 * 
	 * @param name
	 *            name of the customer (must be unique)
	 */
	public CustomerEntity(String name)
	{
		this.name = name;
	}

	/**
	 * @return the ID
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the Id of the current {@link CustomerEntity}
	 * 
	 * @param id
	 *            the id.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the Name of the {@link CustomerEntity}
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the Name of the {@link CustomerEntity}
	 * 
	 * @param name
	 *            the name of the {@link CustomerEntity}
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if (obj instanceof CustomerEntity)
		{
			CustomerEntity temp = (CustomerEntity) obj;
			return this.id.equals(temp.id);
		} else
			return false;
	}
	
	@Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

}
