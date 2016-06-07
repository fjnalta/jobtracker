package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Date;
import java.util.List;

import org.gwtbootstrap3.extras.fullcalendar.client.ui.Event;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.shared.ActivityReport;

/**
 * A Special {@link Event} for the {@link CalendarWidget}.It is used to display
 * the {@link ActivityReport} on the {@link CalendarWidget} It inherits from
 * {@link Event}.
 * 
 * @author Jonathan Brenner
 *
 */
public class ActivityReportEvent extends Event implements LogicObservable {

	private ActivityReport ap;
	private int breakTime;
	private List<ActivityReport> reportList;

	/**
	 * Initializes a new instance of the {@link ActivityReportEvent} class with
	 * the following values
	 * 
	 * @param ap
	 *            an {@link ActivityReport} ap
	 * @param id
	 *            the id of the {@link ActivityReport} ap
	 * @param title
	 *            the description of the {@link ActivityReport} ap
	 * @param isEditable
	 *            Determines whether the events on the calendar can be modified.
	 * @param isStartEditable
	 *            Allow events' start times to be editable through dragging.
	 * @param isDurationEditable
	 *            Allow events' durations to be editable through resizing.
	 */
	public ActivityReportEvent(ActivityReport ap, String id, String title, boolean isEditable, boolean isStartEditable,
			boolean isDurationEditable) {
		super(id, title, isEditable, isStartEditable, isDurationEditable);
		this.ap = ap;
		this.setColor("rgb(0,0,153)");
		
	}

	/**
	 * Initializes a new instance of the {@link ActivityReportEvent} class with
	 * the following values
	 * 
	 * @param id
	 *            id as a {@link String}
	 * @param title
	 *            description as a {@link String}.
	 */
	public ActivityReportEvent(String id, String title) {
		super(id, title);
		this.setColor("rgb(0,0,153)");
	}

	/**
	 * Initializes a new instance of the {@link ActivityReportEvent} class with
	 * the following values
	 * 
	 * @param jso
	 *            as a {@link JavaScriptObject}
	 */
	public ActivityReportEvent(JavaScriptObject jso) {
		super(jso);
		this.setColor("rgb(0,0,153)");
	}

	/**
	 * Method for getting the {@link ActivityReport} of the
	 * {@link ActivityReportEvent}.
	 * 
	 * @return ap the {@link ActivityReport} of the {@link ActivityReportEvent}
	 */
	public ActivityReport getAp() {
		return ap;
	}

	/**
	 * Method for setting the {@link ActivityReport} of the
	 * {@link ActivityReportEvent}.
	 *
	 * @param ap {@link ActivityReport} to set
	 */
	public void setAp(ActivityReport ap) {
		this.ap = ap;
	}

	/**
	 * Method for getting the time of the break from a
	 * {@link ActivityReportEvent}.
	 * 
	 * @return the time of the break from an {@link ActivityReportEvent} as an
	 *         {@link int}
	 */
	public int getBreak() {
		return this.breakTime;
	}

	/**
	 * Method for setting the time of the break from a
	 * {@link ActivityReportEvent}.
	 * 
	 * @param breakTime
	 *            the time of the break from an {@link ActivityReportEvent} as
	 *            an {@link int}
	 */
	public void setBreak(int breakTime) {
		this.breakTime = breakTime;
	}

	/**
	 * This method checks if two {@link ActivityReportEvent}s are at the same
	 * time.
	 * 
	 * @return true if the {@link ActivityReportEvent}s are at the same time
	 *         else false.
	 */
	public boolean overlapsOtherEvent() {
		handler.updateObservable(this);
		return false;
	}

	/**
	 * This Method copies an {@link ActivityReportEvent} and returns a new
	 * instance with the time given as parameter.
	 * 
	 * @param ISOStart
	 *            Start of the {@link ActivityReportEvent}as an ISO8601
	 *            {@link String} (yyyy-mm-ddThh:mm:ss.000Z)
	 *
	 * @param ISOEnd
	 *            End of the {@link ActivityReportEvent} as an ISO8601
	 *            {@link String} (yyyy-mm-ddThh:mm:ss.000Z)
	 * @return a copy of a {@link ActivityReportEvent}
	 */
	public ActivityReportEvent copyEvent(String ISOStart, String ISOEnd) {
		ActivityReportEvent arp = new ActivityReportEvent(this.getId(), this.getTitle());
		arp.setStart(ISOStart);
		arp.setEnd(ISOEnd);
		arp.setBreak(this.getBreak());
		return arp;
	}

	@Override
	public void updateObservable() {		
		String[] isoDateString = this.getISOStart().split("T")[0].split("-");
		int year = Integer.parseInt((isoDateString[0])) - 1900;
		int month = Integer.parseInt((isoDateString[1])) - 1;
		int day = Integer.parseInt((isoDateString[2]));
		reportList = handler.getReportsForDay(new Date(year, month, day));
	}

	@Override
	public void notifyLogicHandler() {

	}

}
