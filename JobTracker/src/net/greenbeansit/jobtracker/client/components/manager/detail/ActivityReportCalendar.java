package net.greenbeansit.jobtracker.client.components.manager.detail;

import java.util.Date;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.AgendaOptions;
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

import net.greenbeansit.jobtracker.client.localization.ApplicationConstants;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
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

//		Timer timer = new Timer()
//		{
//			@Override
//			public void run()
//			{
//				
//			}
//		};
//		timer.schedule(0);
		
		initCalendar();
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
	 * Loads the reports from the server.
	 * 
	 * @param userId the userID.
	 */
	public void loadServerReports(Integer userId)
	{
		RestClient.build(new SuccessFunction<List<ActivityReport>>()
		{
			@Override
			public void onSuccess(Method method, List<ActivityReport> response)
			{
				fillCalendar(response);
			}

			@Override
			public void onFailure(Method method, Throwable exception)
			{
				NotifyHelper.errorMessage(exception.toString());
			}
		}).getEmployeeService().getAllReports(userId);
	}

	/**
	 * Fills the calendar with the following reports.
	 * 
	 * @param reportList
	 *            a list of {@link ActivityReport}s.
	 */
	private void fillCalendar(List<ActivityReport> reportList)
	{
		for (ActivityReport report : reportList)
		{
			Event event = new Event(report.getId() + "", report.getText(),
					false, false, false);
			report.getDate().setYear((2016 - 1900));
			
			event.setStart(getISO8601StringForDate(report.getDate(),
					report.getStartTime()));
			event.setEnd(getISO8601StringForDate(report.getDate(),
					report.getEndTime()));

//			Window.alert(event.getStart() + "");
			calendar.addEvent(event);
		}
	}

	/**
	 * 
	 * @param date
	 *            the date
	 * @param time
	 *            the time(hours and minutes) in minutes Integer value
	 * @return yyyy-mm-ddThh:mm:ss.000Z
	 */
	private String getISO8601StringForDate(Date date, int time)
	{
		return (date.getYear() + 1900) + "-"
				+ fillLeadingZero((date.getMonth() + 1)) + "-"
				+ fillLeadingZero(date.getDate()) + "T"
				+ fillLeadingZero(calculateHours(time)) + ":"
				+ fillLeadingZero(calculateMinutes(time)) + ":00" + ".000Z";
	}

	/**
	 * Add leading zero if number < 10
	 * 
	 * @param num
	 *            the number
	 * @return String with a leading 0
	 */
	private String fillLeadingZero(int num)
	{
		if (num < 10)
		{
			return "0" + num;
		}
		return num + "";
	}

	/**
	 * get the hours
	 * 
	 * @param time
	 *            the hours value in minutes
	 * @return the hours value
	 */
	private int calculateHours(int time)
	{
		return time / 60;
	}

	/**
	 * get the minutes
	 * 
	 * @param time
	 *            the time
	 * @return the minutes
	 */
	private int calculateMinutes(int time)
	{
		return time % 60;
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
