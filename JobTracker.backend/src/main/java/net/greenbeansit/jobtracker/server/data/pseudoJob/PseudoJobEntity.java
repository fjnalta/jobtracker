package net.greenbeansit.jobtracker.server.data.pseudoJob;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A class representing PseudoJob (for capacity planning) as entities in our database. Only used in
 * backend. Each entity class requires a standard constructor and
 * getters/setters for usage in Java Spring.
 * 
 * @author Mike Hukiewitz
 *
 */
@Entity
@Table(name = "pseudo_job")
public class PseudoJobEntity implements Serializable
{

	/**
	 *
	 */
	private static final long	serialVersionUID	= -5496667715876969877L;

	@Id
	@GeneratedValue
	private Integer				id;

	@Column(name = "name")
	private String				name;
	@Column(name = "author")
	private Integer				author;

	/**
	 * Standard constructor for internal purposes.
	 */
	public PseudoJobEntity()
	{

	}

	/**
	 * Creates a new {@link PseudoJobEntity}
	 * @param name name of the entity
	 * @param author ID of its author
	 */
	public PseudoJobEntity(String name, Integer author)
	{
		this.name = name;
		this.author = author;
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

	public Integer getAuthor()
	{
		return author;
	}

	public void setAuthor(Integer author)
	{
		this.author = author;
	}
}
