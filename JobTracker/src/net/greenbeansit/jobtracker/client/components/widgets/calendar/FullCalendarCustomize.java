package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import org.gwtbootstrap3.extras.fullcalendar.client.ui.CalendarConfig;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.Event;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.FullCalendar;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.Header;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;

import com.google.gwt.event.shared.HandlerManager;

import net.greenbeansit.jobtracker.shared.ActivityReport;

public class FullCalendarCustomize extends FullCalendar {

	public FullCalendarCustomize(String id, ViewOption defaultView, boolean editable) {
		super(id, defaultView, editable);
		// TODO Auto-generated constructor stub
	}

	public FullCalendarCustomize(String id, ViewOption defaultView, boolean editable, Header header) {
		super(id, defaultView, editable, header);
		// TODO Auto-generated constructor stub
	}

	public FullCalendarCustomize(String id, ViewOption defaultView, CalendarConfig config, boolean editable) {
		super(id, defaultView, config, editable);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected HandlerManager createHandlerManager() {
		// TODO Auto-generated method stub
		return super.createHandlerManager();
	}
	
	Event currentEvent;
	@Override
	public void addEvent(Event event) {
		// TODO Auto-generated method stub
		super.addEvent(event);
		this.currentEvent = event;
	}
	
	/*
	ActivityReport Dummy
	private static final long	serialVersionUID	= 7682896069658320372L;

	private String				description;
	private String				identifier;
	private Date				date;
	private Integer				startTime;
	private Integer				duration;
	private Integer				breakTime;
	private Long				author;
	private Long				id;
	 */
	
	public void addActvityRepors(ActivityReport report){
		Event event = new Event(report.getIdentifier(), report.getDescription(), true, true, true);
		//event start,ende usw setzen
	}
	
	
}
