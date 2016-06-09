package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.util.Date;
//import java.sql.Date;

/**
 * Represents a whole week in capacity planning
 *
 * @author Philipp Minges
 */
public class UtilizationWeek implements Serializable {

    private static final long serialVersionUID = 2259434726922682948L;

    private Integer id;
    private Integer author;
    private String  text;
    private Date    beginDate;
    private Date    endDate;
    private Integer beginTime;
    private Integer endTime;
    private Integer possibility;
    private Integer pseudoJobId;
    private Integer breakTime;

    /**
     * Empty Constructor for internal purposes
     */
    public UtilizationWeek() {

    }

    /**
     * Initializes a new instance of the {@link UtilizationWeek} class
     *
     * @param id          the unique identifier
     * @param author      the id of the author
     * @param text        the description
     * @param beginDate   the begin Date
     * @param beginTime   the begin Time
     * @param endTime     the end Time of the Pseudo Activity Report
     * @param endDate     the end Date
     * @param possibility the possibility the event will take place
     * @param pseudoJobId the unique identifier for the pseudo job
     * @param breakTime   the break time
     */
    public UtilizationWeek(Integer id, Integer author, String text, Date beginDate,
                           Integer beginTime, Integer endTime, Date endDate, Integer possibility,
                           Integer pseudoJobId, Integer breakTime) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.beginDate = beginDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.endDate = endDate;
        this.possibility = possibility;
        this.pseudoJobId = pseudoJobId;
        this.breakTime = breakTime;
    }

    /**
     * Gets the ID of the UtilizationWeek
     * @return the Id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the Id of the Utilization Week
     * @param id the id of the {@link UtilizationWeek}
     */
    public void setId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    /**
     * Gets the Author of the {@link UtilizationWeek}
     * @return the author
     */
    public Integer getAuthor() {
        return author;
    }

    /**
     * Sets the Author of the {@link UtilizationWeek}
     * @param author the author
     */
    public void setAuthor(Integer author) {
        if (author == null) {
            throw new IllegalArgumentException();
        }
        this.author = author;
    }

    /**
     * Gets the Begin Date of the {@link UtilizationWeek}
     * @return the begin Date.
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * Sets the Begin Date of the {@link UtilizationWeek}
     * @param beginDate the begin Date
     */
    public void setBeginDate(Date beginDate) {
        if (beginDate == null) {
            throw new IllegalArgumentException();
        }
        this.beginDate = beginDate;
    }

    /**
     * Gets the possibility of the {@link UtilizationWeek}
     * @return the possibility
     */
    public Integer getPossibility() {
        return possibility;
    }

    /**
     * Sets the possibility of the {@link UtilizationWeek}
     * @param possibility the possibility.
     */
    public void setPossibility(Integer possibility) {
        if (possibility == null) {
            throw new IllegalArgumentException();
        }
        this.possibility = possibility;
    }

    /**
     * Gets the Text of the {@link UtilizationWeek}
     * @return the Text of the given {@link UtilizationWeek}
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the Text of the {@link UtilizationWeek}
     * @param text the Text of the given {@link UtilizationWeek}
     */
    public void setText(String text) {
        if (text == null)
            throw new IllegalArgumentException();
        this.text = text;
    }

    /**
     * Gets the Begin Time of the {@link UtilizationWeek}
     * @return the Begin time of the {@link UtilizationWeek}
     */
    public Integer getBeginTime() {
        return beginTime;
    }

    /**
     * Sets the Begin Time of the {@link UtilizationWeek}
     * @param beginTime the begin Time of the {@link UtilizationWeek}
     */
    public void setBeginTime(Integer beginTime) {
        if (beginTime == null)
            throw new IllegalArgumentException();
        this.beginTime = beginTime;
    }

    /**
     * Gets the End Time of the {@link UtilizationWeek}
     * @return the End Time of the {@link UtilizationWeek}
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * Sets the End Time of the {@link UtilizationWeek}
     * @param endTime the End Time of the {@link UtilizationWeek}
     */
    public void setEndTime(Integer endTime) {
        if (endTime == null)
            throw new IllegalArgumentException();
        this.endTime = endTime;
    }

    /**
     * Gets the Break time of the {@link UtilizationWeek}
     * @return the Break Time of the {@link UtilizationWeek}
     */
    public Integer getBreakTime() {
        return breakTime;
    }

    /**
     * Sets the Break Time of the {@link UtilizationWeek}
     * @param breakTime the Break time of the {@link UtilizationWeek}
     */
    public void setBreakTime(Integer breakTime) {
        if (breakTime == null)
            throw new IllegalArgumentException();
        this.breakTime = breakTime;
    }

    /**
     * Gets the Pseudo Job id of the {@link UtilizationWeek}
     * @return the Pseudo Job id of the {@link UtilizationWeek}
     */
    public Integer getPseudoJobId() {
        return pseudoJobId;
    }

    /**
     * Sets the Pseudo Job id of the {@link UtilizationWeek}
     * @param pseudoJobId the Pseudo Job id of the {@link UtilizationWeek}
     */
    public void setPseudoJobId(Integer pseudoJobId) {

        if (pseudoJobId == null) {
            throw new IllegalArgumentException();
        }
        this.pseudoJobId = pseudoJobId;
    }

    /**
     * Sets the end date
     * @param date the date
     */
    public void setEndDate(Date date) {
        if(date == null) {
            throw new IllegalArgumentException();
        }
        this.endDate = date;
    }

    /**
     * Gets the end date
     * @return the date
     */
    public Date getEndDate(){
        return this.endDate;
    }
    
    public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if (obj instanceof UtilizationWeek)
		{
			UtilizationWeek temp = (UtilizationWeek) obj;
			return this.id.equals(temp.id);
		} else
			return false;
	}
    
	@Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
