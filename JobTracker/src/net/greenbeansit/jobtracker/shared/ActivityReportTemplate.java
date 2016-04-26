package net.greenbeansit.jobtracker.shared;

public class ActivityReportTemplate extends ActivityReport
{

	private String templateName;
	private String description;
	private Long id; 

	public ActivityReportTemplate(String description, String identifier,
			Date date, int startTime, int duration, String templateName)
	{
		super(description, identifier, date, startTime, duration);
		this.setTemplateName(templateName);
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
		public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
