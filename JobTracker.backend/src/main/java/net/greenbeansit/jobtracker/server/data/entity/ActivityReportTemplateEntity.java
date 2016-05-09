package net.greenbeansit.jobtracker.server.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVITY_REPORT_TEMPLATE")
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
	private Integer id;

	//private String	templateName; TODO: In Datenbank einfügen (Philipp anhauen)
	private String	description;
	private Integer	taskId;
	private Integer	author;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	@Column(name = "NAME")
//	public String getTemplateName()
//	{
//		return templateName;
//	}
//
//	public void setTemplateName(String templateName)
//	{
//		this.templateName = templateName;
//	}

	@Column(name = "TEXT")
	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Column(name = "TASK_ID")
	public Integer getTaskId()
	{
		return taskId;
	}

	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	@Column(name = "AUTHOR")
	public Integer getAuthor()
	{
		return author;
	}

	public void setAuthor(Integer author)
	{
		this.author = author;
	}
}
