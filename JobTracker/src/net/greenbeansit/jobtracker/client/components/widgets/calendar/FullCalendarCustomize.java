package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import org.gwtbootstrap3.extras.fullcalendar.client.ui.CalendarConfig;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.FullCalendar;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.Header;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;

import com.google.gwt.event.shared.HandlerManager;

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
}
