package net.greenbeansit.jobtracker.shared;

public class ActivityReportTemplate extends ActivityReport
{

	private String templateName;

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
}
