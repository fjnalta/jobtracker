package net.greenbeansit.jobtracker.client.components.widgets;

import java.util.Date;

import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.CalendarConfig;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.Event;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.FullCalendar;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.GeneralDisplay;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.Language;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CalendarWidget extends Composite {

	private static CalendarWidgetUiBinder uiBinder = GWT.create(CalendarWidgetUiBinder.class);

	interface CalendarWidgetUiBinder extends UiBinder<Widget, CalendarWidget> {

	}

	@UiField
	ClearFix container;

	FullCalendar calendar;

	public CalendarWidget() {
		initWidget(uiBinder.createAndBindUi(this));

//		new RenderTimer().schedule(0);
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			
			@Override
			public void execute() {
				CalendarConfig config = new CalendarConfig();
				config.setLangauge(Language.German);

				GeneralDisplay generalDisplay = new GeneralDisplay();
				generalDisplay.setHeight(600);

				config.setGeneralDisplay(generalDisplay);
				
				calendar = new FullCalendar("fullCalendar", ViewOption.agendaWeek, config, false);

				Event event = new Event("event", "Event");

				event.setStart(new Date());
				event.setEnd(new Date());

				container.add(calendar);

				calendar.addEvent(event);
				calendar.render(); 
				
			}
		});
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		
	}

}
