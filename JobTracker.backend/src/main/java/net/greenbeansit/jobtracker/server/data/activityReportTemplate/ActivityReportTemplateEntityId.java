package net.greenbeansit.jobtracker.server.data.activityReportTemplate;

import java.io.Serializable;

/**
 * Serves as representation of the composite primary key of ActivityReportTemplateEntity.
 * 
 * @author Philipp Minges
 */
public class ActivityReportTemplateEntityId implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private Integer				author;
	private String				name;

	public Integer getAuthor()
	{
		return author;
	}

	public void setAuthor(Integer author)
	{
		this.author = author;
	}

	public String getName()
	{
		return name;
	}

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
