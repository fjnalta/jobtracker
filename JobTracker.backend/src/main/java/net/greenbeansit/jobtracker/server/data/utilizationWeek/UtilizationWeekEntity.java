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
 * @author Philipp Minges
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
	@Column(name = "text")
	private String				text;
	@Column(name = "begin_date")
	private Date				beginDate;
	@Column(name = "begin_time")
	private Integer				beginTime;
	@Column(name = "end_date")
	private Date				endDate;
	@Column(name = "end_time")
	private Integer				endTime;
	@Column(name = "possibility")
	private Integer				possibilty;
	@Column(name = "break_time")
	private Integer				breakTime;
	@Column(name = "pseudo_job_id")
	private Integer				pseudoJobId;

	/**
	 * Standard constructor for internal purposes.
	 */
	public UtilizationWeekEntity()
	{

	}

	/**
	 * Initializes a new Instance of {@link UtilizationWeekEntity}
	 * @param id
	 * 			the Id of the Utilization Week
	 * @param author
	 * 			the Author id
	 * @param text
	 * 			the Description
	 * @param beginDate
	 * 			the Begin Date
	 * @param beginTime
	 * 			the Begin Time
	 * @param endDate
	 * 			the End Date
	 * @param endTime
	 * 			the End Time
	 * @param possibility
	 * 			the possibility
     * @param breakTime
	 * 			the break Time
     * @param pseudoJobId
	 * 			the pseudoJobId
     */
	public UtilizationWeekEntity(Integer id, Integer author, String text, Date beginDate,
								 Integer beginTime, Date endDate, Integer endTime,
								 Integer possibility, Integer breakTime, Integer pseudoJobId)
	{
		this.id = id;
		this.author = author;
		this.text = text;
		this.beginDate = beginDate;
		this.beginTime = beginTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.possibilty = possibility;
		this.breakTime = breakTime;
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
	 * Gets the Text
	 * @return the text.
     */
	public String getText() {
		return this.text;
	}

	/**
	 * Sets the text.
	 * @param text the Text.
     */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the begin date
	 */
	public Date getBeginDate()
	{
		return this.beginDate;
	}

	/**
	 * @param begin
	 *            set begin date
	 */
	public void setBeginDate(Date begin)
	{
		this.beginDate = begin;
	}

	/**
	 * Returns the Begin Time of the Utilization Week
	 * @return the begin Time
     */
	public Integer getBeginTime(){
		return this.beginTime;
	}

	/**
	 * Sets the Begin Time
	 * @param start the begin Time
     */
	public void setBeginTime(Integer start) {
		this.beginTime = start;
	}

	/**
	 * Gets the End Date
	 * @return the End Date.
     */
	public Date getEndDate(){
		return this.endDate;
	}

	/**
	 * Sets the End Date
	 * @param end the endDate.
     */
	public void setEndDate(Date end) {
		this.endDate = end;
	}

	/**
	 * Gets the End Time
	 * @return the End Time
     */
	public Integer getEndTime(){
		return this.endTime;
	}

	/**
	 * Sets the end Time
	 * @param end the End Time
     */
	public void setEndTime(Integer end) {
		this.endTime = end;
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
	 * Gets the break Time
	 * @return the break Time
     */
	public Integer getBreakTime(){
		return this.breakTime;
	}

	/**
	 * Sets the break Time
	 * @param breakTime the Break Time
     */
	public void setBreakTime(Integer breakTime){
		this.breakTime = breakTime;
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
