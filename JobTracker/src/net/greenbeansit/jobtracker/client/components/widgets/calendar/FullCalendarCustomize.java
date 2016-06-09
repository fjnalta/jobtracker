package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import net.greenbeansit.jobtracker.client.components.kapa.data.CapacityReportEvent;
import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.UtilizationWeek;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.CalendarConfig;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.FullCalendar;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.Header;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Extends the FullCalender to provide more functionality.
 *
 * @author Ahmed Salame.
 */
public class FullCalendarCustomize extends FullCalendar {

	private List<ActivityReport> reportsToSave = new ArrayList<ActivityReport>();
	private List<UtilizationWeek> capacityReportsToSave = new ArrayList<UtilizationWeek>();

	/**
	 * FullCalendar Constructor
	 * @param id the id
	 * @param defaultView the default view
	 * @param editable true if editable
     */
	public FullCalendarCustomize(String id, ViewOption defaultView, boolean editable) {
		super(id, defaultView, editable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * FullCalendar Constructor
	 * @param id the id
	 * @param defaultView the default view
	 * @param editable true if editable
     * @param header the header
     */
	public FullCalendarCustomize(String id, ViewOption defaultView, boolean editable, Header header) {
		super(id, defaultView, editable, header);
		// TODO Auto-generated constructor stub
	}

	/**
	 * FullCalendar Constructor
	 * @param id the id
	 * @param defaultView the default view
	 * @param config the config
	 * @param editable true if editable
     */
	public FullCalendarCustomize(String id, ViewOption defaultView, CalendarConfig config, boolean editable) {
		super(id, defaultView, config, editable);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected HandlerManager createHandlerManager() {
		// TODO Auto-generated method stub
		return super.createHandlerManager();
	}

	ActivityReportEvent currentEvent;

	/**
	 * Represents a {@link CapacityReportEvent}
	 */
	public CapacityReportEvent currentCapacityEvent;

	/**
	 * Adds a Event to the Calendar
	 * @param event the Event.
     */
	public void addEvent(ActivityReportEvent event) {
		super.addEvent(event);
		this.currentEvent = event;
	}

	public void addEvent(CapacityReportEvent event) {
		super.addEvent(event);
		this.currentCapacityEvent = event;
	}

	/**
	 * This Method is used to cache multiple events to save
	 * @param a the {@link ActivityReport} to be saved.
     */
	public void addEventToSave(ActivityReport a){
		this.reportsToSave.add(a);
	}

	public void addEventToSave(UtilizationWeek uw) {
		GWT.log(uw.getBeginDate().toString());
		this.capacityReportsToSave.add(uw);
	}

	/**
	 * Get's the List of Events to be saved
	 * @return a list of {@link ActivityReport}s.
     */
	public List<ActivityReport> getEventsToSave(){
		return this.reportsToSave;
	}

	public List<UtilizationWeek> getCapacityReportsToSave() {
		return  this.capacityReportsToSave;
	}

	/*
	 * private static final long serialVersionUID = 7682896069658320372L;
	 * 
	 * private Integer id; private Integer taskId; private Integer jobNr;
	 * private Integer jobPosNr; private Integer authorId;
	 * 
	 * private String jobDesc; private String text; private Date date; private
	 * Integer ; private Integer duration; private Integer breakTime;
	 */

}
