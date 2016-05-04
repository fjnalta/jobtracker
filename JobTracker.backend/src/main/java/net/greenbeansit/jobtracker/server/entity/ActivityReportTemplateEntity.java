package net.greenbeansit.jobtracker.server.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVITYREPORTTEMPLATE")
//TODO Mike: Implement NamedQueries for ActivityReportTemplates
//@NamedQueries( { @NamedQuery(name = "ActivityReportTemplate.findAll", query = "SELECT p FROM Person p"),
//@NamedQuery(name = "Person.findPerson", query = "SELECT p FROM Person p where p.name=:name and p.age=:age")
//})
public class ActivityReportTemplateEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -804679538322787505L;

	@Id
	@GeneratedValue
	private Long id;

	private String	templateName;
	private String	description;
	private String	identifier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getTemplateName()
	{
		return templateName;
	}

	public void setTemplateName(String templateName)
	{
		this.templateName = templateName;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Column(name = "IDENTIFIER")
	public String getIdentifier()
	{
		return identifier;
	}

	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}
}
