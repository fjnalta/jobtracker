package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a template for an {@link ActivityReport}.
 * 
 * @author Max Blatt
 */
public class ActivityReportTemplate implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1035084213210834537L;
	/**
	 * 
	 */

	private String				templateName;
	private String				description;
	private Long				id;

	/**
	 * Initializes a new instance of the {@link ActivityReportTemplate} class.
	 */
	public ActivityReportTemplate()
	{

	}

	public ActivityReportTemplate(String templateName, String description, Long id) {
		super();
		this.templateName = templateName;
		this.description = description;
		this.id = id;
	}



	public String getTemplateName()
	{
		return templateName;
	}

	public void setTemplateName(String templateName)
	{
		if (templateName == "")
			throw (new IllegalArgumentException());
		this.templateName = templateName;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
