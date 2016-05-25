package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import org.gwtbootstrap3.extras.fullcalendar.client.ui.Event;

import com.google.gwt.core.client.JavaScriptObject;

import net.greenbeansit.jobtracker.shared.ActivityReport;

public class ActivityReportEvent extends Event{
	
	private ActivityReport ap;
	
	public ActivityReportEvent(ActivityReport ap,String id, String title, boolean isEditable, boolean isStartEditable,
			boolean isDurationEditable) {
		super(id, title, isEditable, isStartEditable, isDurationEditable);
		this.ap = ap;
	}

	public ActivityReportEvent(String id, String title) {
		super(id, title);
		// TODO Auto-generated constructor stub
	}

	public ActivityReportEvent(JavaScriptObject jso) {
		super(jso);
		// TODO Auto-generated constructor stub
	}

	public ActivityReport getAp() {
		return ap;
	}

	public void setAp(ActivityReport ap) {
		this.ap = ap;
	}
	
	
}
