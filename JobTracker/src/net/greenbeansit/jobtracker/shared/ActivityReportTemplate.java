package net.greenbeansit.jobtracker.shared;

public class ActivityReportTemplate
{
	private String description;
	private Long id; 

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
