package net.greenbeansit.jobtracker.server.data.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import liquibase.datatype.core.DecimalType;

@Entity
@Table(name = "UTILIZATION_WEEK")
//TODO Mike: Implement NamedQueries for Employee
//@NamedQueries( { @NamedQuery(name = "ActivityReportTemplate.findAll", query = "SELECT p FROM Person p"),
//@NamedQuery(name = "Person.findPerson", query = "SELECT p FROM Person p where p.name=:name and p.age=:age")
//})
public class UtilizationWeekEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3842559068860954254L;

	@Id
	@GeneratedValue
	private Integer id;

	private Integer		author;
	private Date		begin;
	private Integer		daysFree;
	private Integer		daysWork;
	private Integer 	daysHoliday;
	private Integer		possibilty;
	private Integer		pseudoJobId;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Column(name = "BEGIN")
	public Date getBegin()
	{
		return begin;
	}

	public void setBegin(Date begin)
	{
		this.begin = begin;
	}

	@Column(name = "DAYS_FREE")
	public Integer getDaysFree()
	{
		return daysFree;
	}

	public void setDaysFree(Integer daysFree)
	{
		this.daysFree = daysFree;
	}

	@Column(name = "DAYS_WORK")
	public Integer getDaysWork()
	{
		return daysWork;
	}

	public void setDaysWork(Integer daysWork)
	{
		this.daysWork = daysWork;
	}

	@Column(name = "DAYS_HOLIDAY")
	public Integer getDaysHoliday()
	{
		return daysHoliday;
	}

	public void setDaysHoliday(Integer daysHoliday)
	{
		this.daysHoliday = daysHoliday;
	}

	@Column(name = "POSSIBILITY")
	public Integer getPossibilty()
	{
		return possibilty;
	}

	public void setPossibilty(Integer possibilty)
	{
		this.possibilty = possibilty;
	}

	@Column(name = "PSEUDO_JOB_ID")
	public Integer getPseudoJobId()
	{
		return pseudoJobId;
	}

	public void setPseudoJobId(Integer pseudoJobId)
	{
		this.pseudoJobId = pseudoJobId;
	}
}
