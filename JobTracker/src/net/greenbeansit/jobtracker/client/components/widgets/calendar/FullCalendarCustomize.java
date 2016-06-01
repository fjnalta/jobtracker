package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import com.google.gwt.event.shared.HandlerManager;
import net.greenbeansit.jobtracker.client.components.kapa.data.CapacityReportEvent;
import net.greenbeansit.jobtracker.shared.UtilizationWeek;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.CalendarConfig;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.FullCalendar;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.Header;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;

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

	ActivityReportEvent currentEvent;
	public CapacityReportEvent capacityEvent;

	public void addEvent(ActivityReportEvent event) {
		// TODO Auto-generated method stub
		super.addEvent(event);
		this.currentEvent = event;
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
