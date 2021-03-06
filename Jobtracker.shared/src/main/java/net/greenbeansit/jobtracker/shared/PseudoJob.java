package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a PseudoJob for the Capacity planning
 *
 * @author Philipp Minges
 */
public class PseudoJob implements Serializable
{
	/**
	 *
	 */
	private static final long	serialVersionUID	= -2051156787516002689L;
	private Integer				id;
	private String				name;
	private Integer				author;

	/**
	 * Empty Constructor for internal purposes.
	 */
	public PseudoJob()
	{

	}

	/**
	 * Initializes a new instance of the {@link PseudoJob} class
	 *
	 * @param id
	 *            the identifier
	 * @param name
	 *            the name of the PseudoJob
	 * @param author
	 *            userId of the author
	 */
	public PseudoJob(Integer id, String name, Integer author)
	{
		this.id = id;
		this.name = name;
		this.author = author;
	}

	/**
	 * Gets the Id
	 * 
	 * @return the id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the Id.
	 * 
	 * @param id
	 *            the Id.
	 */
	public void setId(Integer id)
	{
		if (id == null)
			throw new IllegalArgumentException();
		this.id = id;
	}

	/**
	 * Gets the Name
	 * 
	 * @return the Name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the Name.
	 * 
	 * @param name
	 *            the Name.
	 */
	public void setName(String name)
	{
		if (name == "")
			throw new IllegalArgumentException();
		this.name = name;
	}

	/**
	 * Gets the Author.
	 * 
	 * @return the Author.
	 */
	public Integer getAuthor()
	{
		return author;
	}

	/**
	 * Sets the Author.
	 * 
	 * @param author
	 *            the Author.
	 */
	public void setAuthor(Integer author)
	{
		if (author == null)
			throw new IllegalArgumentException();
		this.author = author;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		else if (obj instanceof PseudoJob)
		{
			PseudoJob temp = (PseudoJob) obj;
			return this.id.equals(temp.id);
		} else
			return false;
	}

	@Override
	public int hashCode()
	{
		return id == null ? 0 : id.hashCode();
	}
}
