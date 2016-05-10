package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.sql.Date;

/**
 * Represents a whole week in capacity planning
 * 
 * @author Philipp Minges
 */
public class UtilizationWeek implements Serializable 
{

	private static final long serialVersionUID = 2259434726922682948L;
	
	private Integer 	id;
	private Integer		author;
	private Date		begin;
	private Integer		daysFree;
	private Integer		daysWork;
	private Integer 	daysHoliday;
	private Integer		possibilty;
	private Integer		pseudoJobId;
	
	/**
	 * Initializes a new instance of the {@link UtilizationWeek} class
	 * 
	 * @param id the unique identifier
	 * @param author the id of the author
	 * @param begin the begin date
	 * @param daysFree the free days in the specific week
	 * @param daysWork the work days in the specific week
	 * @param daysHoliday the holydays in the specific week
	 * @param possibility the possibility the event will take place
	 * @param pseudoJobId the unique identifier for the pseudo job
	 */
	public UtilizationWeek(Integer id, Integer author, Date begin,
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if(id == null)
		{
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	public Integer getAuthor() {
		return author;
	}

	public void setAuthor(Integer author) {
		if(author == null)
		{
			throw new IllegalArgumentException();
		}
		this.author = author;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		if(begin == null)
		{
			throw new IllegalArgumentException();
		}
		this.begin = begin;
	}

	public Integer getDaysFree() {
		return daysFree;
	}

	public void setDaysFree(Integer daysFree) {
		if(daysFree == null)
		{
			throw new IllegalArgumentException();
		}
		this.daysFree = daysFree;
	}

	public Integer getDaysWork() {
		return daysWork;
	}

	public void setDaysWork(Integer daysWork) {
		if(daysWork == null)
		{
			throw new IllegalArgumentException();
		}
		this.daysWork = daysWork;
	}

	public Integer getDaysHoliday() {
		return daysHoliday;
	}

	public void setDaysHoliday(Integer daysHoliday) {
		if(daysHoliday == null)
		{
			throw new IllegalArgumentException();
		}
		this.daysHoliday = daysHoliday;
	}

	public Integer getPossibilty() {
		return possibilty;
	}

	public void setPossibilty(Integer possibility) {
		if(possibility == null)
		{
			throw new IllegalArgumentException();
		}
		this.possibilty = possibility;
	}

	public Integer getPseudoJobId() {
		return pseudoJobId;
	}

	public void setPseudoJobId(Integer pseudoJobId) {
		if(pseudoJobId == null)
		{
			throw new IllegalArgumentException();
		}
		this.pseudoJobId = pseudoJobId;
	}
}
