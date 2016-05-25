package net.greenbeansit.jobtracker.client.components.manager.detail;

import java.util.Date;

import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.CalendarConfig;
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

public class CapacityCalendar extends Composite
{

	private static CapacityCalendarUiBinder uiBinder = GWT
			.create(CapacityCalendarUiBinder.class);

	interface CapacityCalendarUiBinder
			extends UiBinder<Widget, CapacityCalendar>
	{
	}

	@UiField
	ClearFix container;
	
	FullCalendar calendar;
	CalendarConfig config;
	
	public CapacityCalendar()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		Timer timer = new Timer()
		{
			@Override
			public void run()
			{
				initCalendar();
			}
		};
		timer.schedule(0);
	}

	private void initCalendar()
	{
		config = new CalendarConfig();
		config.setLangauge(Language.German);
		
		GeneralDisplay generalDisplay = new GeneralDisplay();
		generalDisplay.setHeight(600);

		config.setGeneralDisplay(generalDisplay);
		
		calendar = new FullCalendar("test3", ViewOption.agendaWeek, config, false);
		calendar.goToDate(new Date());
		
		container.add(calendar);
	}

	public void onDisplayed()
	{
		Timer timer = new Timer()
		{
			@Override
			public void run()
			{
				calendar.render();
			}
		};
		timer.schedule(10);
	}
}
