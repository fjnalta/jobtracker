package net.greenbeansit.jobtracker.client.components.manager.detail;

import java.util.Date;

import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.AgendaOptions;
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

import net.greenbeansit.jobtracker.client.localization.ApplicationConstants;
import net.greenbeansit.jobtracker.shared.ActivityReport;

/**
 * Displays the {@link ActivityReport}s of an employee.
 * 
 * @author Max Blatt
 */
public class ActivityReportCalendar extends Composite
{

	private static ActivityReportCalendarUiBinder	uiBinder				= GWT
			.create(ActivityReportCalendarUiBinder.class);

	private static ApplicationConstants				applicationConstants	= GWT
			.create(ApplicationConstants.class);

	/**
	 * UiBinder for {@link ActivityReportCalendar}.
	 * 
	 * @author Max Blatt
	 */
	interface ActivityReportCalendarUiBinder
			extends UiBinder<Widget, ActivityReportCalendar>
	{
	}

	@UiField
	ClearFix		container;

	FullCalendar	calendar;
	CalendarConfig	config;

	/**
	 * Initializes a new instance of the {@link ActivityReportCalendar} class.
	 */
	public ActivityReportCalendar()
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

	/**
	 * Initializes the calendar.
	 */
	private void initCalendar()
	{
		config = new CalendarConfig();

		switch (applicationConstants.languageName())
		{
		case "de":
			config.setLangauge(Language.German);
			break;
		default:
			config.setLangauge(Language.EnglishBritish);
			break;
		}

		GeneralDisplay generalDisplay = new GeneralDisplay();
		generalDisplay.setHeight(600);

		config.setGeneralDisplay(generalDisplay);
		
		AgendaOptions agenda = new AgendaOptions();
		agenda.setAxisFormat("");
		agenda.setAllDaySlot(false);
		agenda.setSlotEventOverlap(false);
		config.setAgendaOptions(agenda);

		calendar = new FullCalendar("test", ViewOption.agendaWeek, config,
				false);
		calendar.goToDate(new Date());

		container.add(calendar);
	}

	/**
	 * Tells the calendar to redraw itself.
	 */
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
		timer.schedule(0);
	}
}
