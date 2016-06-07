package net.greenbeansit.jobtracker.server.data.activityReportTemplate;

import java.io.Serializable;

/**
 * Serves as representation of the composite primary key of
 * ActivityReportTemplateEntity.
 * 
 * @author Philipp Minges
 */
public class ActivityReportTemplateEntityId implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private Integer				author;
	private String				name;

	/**
	 * Gets the Author of the {@link ActivityReportTemplateEntityId}
	 * 
	 * @return the Author of the {@link ActivityReportTemplateEntityId}
	 */
	public Integer getAuthor()
	{
		return author;
	}

	/**
	 * Sets the Author of the {@link ActivityReportTemplateEntityId}
	 * 
	 * @param author
	 *            the Author of the {@link ActivityReportTemplateEntityId}
	 */
	public void setAuthor(Integer author)
	{
		this.author = author;
	}

	/**
	 * Gets the Name of the {@link ActivityReportTemplateEntityId}
	 * 
	 * @return the Name of the {@link ActivityReportTemplateEntityId}.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the Name of the {@link ActivityReportTemplateEntityId}
	 * 
	 * @param name
	 *            the Name of the {@link ActivityReportTemplateEntityId}
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		else if (obj instanceof ActivityReportTemplateEntityId)
		{
			ActivityReportTemplateEntityId temp = (ActivityReportTemplateEntityId) obj;
			return this.author.equals(temp.author)
					&& this.name.equals(temp.name);
		} else
			return false;
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

}
