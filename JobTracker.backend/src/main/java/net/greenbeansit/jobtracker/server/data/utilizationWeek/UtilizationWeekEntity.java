package net.greenbeansit.jobtracker.server.data.utilizationWeek;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.greenbeansit.jobtracker.shared.UtilizationWeek;

/**
 * A class representing UtilizationWeek as entities in our database. Only used
 * in backend. Each entity class requires a standard constructor and
 * getters/setters for usage in Java Spring.
 * 
 * Used for capacity planning.
 * 
 * @author Mike Hukiewitz
 *
 */
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

	@Column(name = "author")
	private Integer				author;
	@Column(name = "begin")
	private Date				begin;
	@Column(name = "days_free")
	private Integer				daysFree;
	@Column(name = "days_work")
	private Integer				daysWork;
	@Column(name = "days_holiday")
	private Integer				daysHoliday;
	@Column(name = "possibility")
	private Integer				possibilty;
	@Column(name = "pseudo_job_id")
	private Integer				pseudoJobId;

	/**
	 * Standard constructor for internal purposes.
	 */
	public UtilizationWeekEntity()
	{

	}

	/**
	 * Initializes an {@link UtilizationWeekEntity}
	 * 
	 * @param id
	 *            ID of the entity
	 * @param author
	 *            ID of its author
	 * @param begin
	 *            first day of the week
	 * @param daysFree
	 *            how many days of the week are free
	 * @param daysWork
	 *            how many days of the week are working days
	 * @param daysHoliday
	 *            how many days of the week are holidays
	 * @param possibility
	 *            how likely does this planning apply
	 * @param pseudoJobId
	 *            ID of the corresponding pseudoJob
	 */
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

	/**
	 * Creates a new {@link UtilizationWeekEntity}
	 * 
	 * @param author
	 *            ID of its author
	 * @param begin
	 *            first day of the week
	 * @param daysFree
	 *            how many days of the week are free
	 * @param daysWork
	 *            how many days of the week are working days
	 * @param daysHoliday
	 *            how many days of the week are holidays
	 * @param possibility
	 *            how likely does this planning apply
	 * @param pseudoJobId
	 *            ID of the corresponding pseudoJob
	 */
	public UtilizationWeekEntity(Integer author, Date begin, Integer daysFree,
			Integer daysWork, Integer daysHoliday, Integer possibility,
			Integer pseudoJobId)
	{
		this.author = author;
		this.begin = begin;
		this.daysFree = daysFree;
		this.daysWork = daysWork;
		this.daysHoliday = daysHoliday;
		this.possibilty = possibility;
		this.pseudoJobId = pseudoJobId;
	}

	/**
	 * @return the ID
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            set ID
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the authorID
	 */
	public Integer getAuthor()
	{
		return author;
	}

	/**
	 * @param author
	 *            set authorID
	 */
	public void setAuthor(Integer author)
	{
		this.author = author;
	}

	/**
	 * @return the begin date
	 */
	public Date getBegin()
	{
		return begin;
	}

	/**
	 * @param begin
	 *            set begin date
	 */
	public void setBegin(Date begin)
	{
		this.begin = begin;
	}

	/**
	 * @return get days free
	 */
	public Integer getDaysFree()
	{
		return daysFree;
	}

	/**
	 * @param daysFree
	 *            set days free
	 */
	public void setDaysFree(Integer daysFree)
	{
		this.daysFree = daysFree;
	}

	/**
	 * @return days work
	 */
	public Integer getDaysWork()
	{
		return daysWork;
	}

	/**
	 * @param daysWork
	 *            set days work
	 */
	public void setDaysWork(Integer daysWork)
	{
		this.daysWork = daysWork;
	}

	/**
	 * @return days holiday
	 */
	public Integer getDaysHoliday()
	{
		return daysHoliday;
	}

	/**
	 * @param daysHoliday
	 *            set days holiday
	 */
	public void setDaysHoliday(Integer daysHoliday)
	{
		this.daysHoliday = daysHoliday;
	}

	/**
	 * @return the possibility (0-100)
	 */
	public Integer getPossibilty()
	{
		return possibilty;
	}

	/**
	 * @param possibilty
	 *            set possiblity (0-100)
	 */
	public void setPossibilty(Integer possibilty)
	{
		this.possibilty = possibilty;
	}

	/**
	 * @return the pseudoJob ID
	 */
	public Integer getPseudoJobId()
	{
		return pseudoJobId;
	}

	/**
	 * @param pseudoJobId
	 *            set pseudoJob ID
	 */
	public void setPseudoJobId(Integer pseudoJobId)
	{
		this.pseudoJobId = pseudoJobId;
	}
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if (obj instanceof UtilizationWeekEntity)
		{
			UtilizationWeekEntity temp = (UtilizationWeekEntity) obj;
			return this.id.equals(temp.id);
		} else
			return false;
	}
	
	@Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
