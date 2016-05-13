package net.greenbeansit.jobtracker.server.data.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "utilization_week")
public class UtilizationWeekEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3842559068860954254L;

	@Id
	@GeneratedValue
	private Integer				id;

	private Integer				author;
	private Date				begin;
	private Integer				daysFree;
	private Integer				daysWork;
	private Integer				daysHoliday;
	private Integer				possibilty;
	private Integer				pseudoJobId;
	
	public UtilizationWeekEntity()
	{
		
	}
	
	public UtilizationWeekEntity(Integer id, Integer author, Date begin,
			Integer daysFree, Integer daysWork, Integer daysHoliday,
			Integer possibility, Integer pseudoJobId)
	{
		this.id = id;
		this.author = author;
		this.begin = begin;
		this.daysFree = daysFree;
		this.daysWork = daysWork;
		this.daysHoliday = daysHoliday;
		this.possibilty = possibility;
		this.pseudoJobId = pseudoJobId;
	}
	
	public UtilizationWeekEntity(Integer author, Date begin,
			Integer daysFree, Integer daysWork, Integer daysHoliday,
			Integer possibility, Integer pseudoJobId)
	{
		this.author = author;
		this.begin = begin;
		this.daysFree = daysFree;
		this.daysWork = daysWork;
		this.daysHoliday = daysHoliday;
		this.possibilty = possibility;
		this.pseudoJobId = pseudoJobId;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Column(name = "author")
	public Integer getAuthor()
	{
		return author;
	}

	public void setAuthor(Integer author)
	{
		this.author = author;
	}

	@Column(name = "begin")
	public Date getBegin()
	{
		return begin;
	}

	public void setBegin(Date begin)
	{
		this.begin = begin;
	}

	@Column(name = "days_free")
	public Integer getDaysFree()
	{
		return daysFree;
	}

	public void setDaysFree(Integer daysFree)
	{
		this.daysFree = daysFree;
	}

	@Column(name = "days_work")
	public Integer getDaysWork()
	{
		return daysWork;
	}

	public void setDaysWork(Integer daysWork)
	{
		this.daysWork = daysWork;
	}

	@Column(name = "days_holiday")
	public Integer getDaysHoliday()
	{
		return daysHoliday;
	}

	public void setDaysHoliday(Integer daysHoliday)
	{
		this.daysHoliday = daysHoliday;
	}

	@Column(name = "possibility")
	public Integer getPossibilty()
	{
		return possibilty;
	}

	public void setPossibilty(Integer possibilty)
	{
		this.possibilty = possibilty;
	}

	@Column(name = "pseudo_job_id")
	public Integer getPseudoJobId()
	{
		return pseudoJobId;
	}

	public void setPseudoJobId(Integer pseudoJobId)
	{
		this.pseudoJobId = pseudoJobId;
	}
}
