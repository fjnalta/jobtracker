package net.greenbeansit.jobtracker.shared;

/**
 * Represents a template for an {@link ActivityReport}.
 * 
 * @author Max Blatt
 */
public class ActivityReportTemplate
{
	private String	templateName;
	private String	description;
	private Long	id;

	/**
	 * Initializes a new instance of the {@link ActivityReportTemplate} class.
	 */
	public ActivityReportTemplate()
	{

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
