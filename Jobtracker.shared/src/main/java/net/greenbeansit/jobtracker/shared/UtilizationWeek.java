package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.sql.Date;

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
    private Integer beginTime;
    private Integer endTime;
    private Integer possibility;
    private Integer pseudoJobId;
    private Integer breakTime;

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
     * @param possibility the possibility the event will take place
     * @param pseudoJobId the unique identifier for the pseudo job
     * @param breakTime   the break time
     */
    public UtilizationWeek(Integer id, Integer author, String text, Date beginDate,
                           Integer beginTime, Integer endTime, Integer possibility,
                           Integer pseudoJobId, Integer breakTime) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.beginDate = beginDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.possibility = possibility;
        this.pseudoJobId = pseudoJobId;
        this.breakTime = breakTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        if (author == null) {
            throw new IllegalArgumentException();
        }
        this.author = author;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        if (beginDate == null) {
            throw new IllegalArgumentException();
        }
        this.beginDate = beginDate;
    }

    public Integer getPossibility() {
        return possibility;
    }

    public void setPossibility(Integer possibility) {
        if (possibility == null) {
            throw new IllegalArgumentException();
        }
        this.possibility = possibility;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text == null)
            throw new IllegalArgumentException();
        this.text = text;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        if (beginTime == null)
            throw new IllegalArgumentException();
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        if (endTime == null)
            throw new IllegalArgumentException();
        this.endTime = endTime;
    }

    public Integer getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Integer breakTime) {
        if (breakTime == null)
            throw new IllegalArgumentException();
        this.breakTime = breakTime;
    }

    public Integer getPseudoJobId() {
        return pseudoJobId;
    }

    public void setPseudoJobId(Integer pseudoJobId) {

        if (pseudoJobId == null) {
            throw new IllegalArgumentException();
        }
        this.pseudoJobId = pseudoJobId;
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
}
