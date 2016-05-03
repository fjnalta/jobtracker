package net.greenbeansit.jobtracker.server.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVITYREPORT")
//TODO: Implement NamedQueries for ActivityReport
//@NamedQueries( { @NamedQuery(name = "ActivityReportTemplate.findAll", query = "SELECT p FROM Person p"),
//@NamedQuery(name = "Person.findPerson", query = "SELECT p FROM Person p where p.name=:name and p.age=:age")
//})
public class ActivityReportEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5183872556752783738L;

	@Id
	@GeneratedValue
	private Long id;

	private String				description;
	private String				identifier;
	private Date				date;
	private Integer				startTime;
	private Integer				duration;
	private Long				author;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Column(name = "DATE")
	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	@Column(name = "STARTTIME")
	public Integer getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Integer startTime)
	{
		this.startTime = startTime;
	}

	@Column(name = "DURATION")
	public Integer getDuration()
	{
		return duration;
	}

	public void setDuration(Integer duration)
	{
		this.duration = duration;
	}

	@Column(name = "AUTHOR")
	public Long getAuthor()
	{
		return author;
	}

	public void setAuthor(Long author)
	{
		this.author = author;
	}

}
