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
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CalendarActivity extends Composite {

	private static CalendarWidgetUiBinder uiBinder = GWT.create(CalendarWidgetUiBinder.class);

	interface CalendarWidgetUiBinder extends UiBinder<Widget, CalendarActivity> {

	}

	@UiField
	ClearFix container;

	FullCalendar calendar;

	public CalendarActivity() {
		initWidget(uiBinder.createAndBindUi(this));

		new RenderTimer().schedule(0);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		
		
	}
	
	private class RenderTimer extends Timer
	{

		@Override
		public void run()
		{
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
		
	}
}
