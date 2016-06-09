package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.html.Span;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;

import net.greenbeansit.jobtracker.client.components.CalendarObserver;
import net.greenbeansit.jobtracker.client.localization.HomePageConstants;

/**
 * 3 Round Buttons , KW KWNR MONTH DATE
 * 
 * @author jonathan
 *
 */
public class CalendarTimeLineWidget extends Composite implements CalendarObserver {

	@UiField
	Span date;

	@UiField
	Span kw;

	@UiField
	Button buttonWeek;

	@UiField
	Button buttonMonth;

	/**
	 * Changes the Calendar view to monthly.
	 * 
	 * @param event
	 *            the ClickEvent.
	 */
	@UiHandler("buttonMonth")
	public void onClickButtonMonth(ClickEvent event) {

		calendarHandler.calendar.setView(ViewOption.month);

		notifyHandler();
	}

	/**
	 * Changes the Calendar view to weekly.
	 * 
	 * @param event
	 *            the ClickEvent.
	 */
	@UiHandler("buttonWeek")
	public void onClickButtonWeek(ClickEvent event) {

		calendarHandler.calendar.setView(ViewOption.agendaWeek);

		notifyHandler();
	}

	private static CalendarTimeLineWidgetUiBinder uiBinder = GWT.create(CalendarTimeLineWidgetUiBinder.class);

	private static HomePageConstants constants	= GWT.create(HomePageConstants.class);
	
	interface CalendarTimeLineWidgetUiBinder extends UiBinder<Widget, CalendarTimeLineWidget> {
	}

	/**
	 * Initializes a new Instane of CalendarTimeLineWidget.
	 */
	@SuppressWarnings("deprecation")
	public CalendarTimeLineWidget() {
		initWidget(uiBinder.createAndBindUi(this));

		Date day = new Date();
		
		calendarHandler.addObserver(this);

		//String currentDate = createDate(new Date().toString());

		setCalenderWeek(getMonthName(day.getMonth() + 1));
		
		//setDate(currentDate);
		
		//setCalenderWeek(getCalendarWeek(day.getYear() + 1900, day.getMonth() + 1 , day.getDate()));
	}

	/**
	 * Initializes a new Instance of {@link CalendarTimeLineWidget}
	 * 
	 * @param firstName
	 *            the FirstName.
	 */


	@Override
	public void update() {

		setCalenderWeek(getMonthName(calendarHandler.getDisplayMonth()));	
	}

	/**
	 * Gets the Month name to a specific month.
	 * 
	 * @param string
	 *            the String of the Month
	 * @return the Name of the Month.
	 */
	private String getMonthName(int month) {
		switch (month) {
		case 1:
			return "Januar";
		case 2:
			return "Februar";
		case 3:
			return "März";
		case 4:
			return "April";
		case 5:
			return "Mai";
		case 6:
			return "Juni";
		case 7:
			return "Juli";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "Oktober";
		case 11:
			return "November";
		case 12:
			return "Dezember";
		default:
			return null;
		}
	}

	
	@Override
	public void notifyHandler() {
		calendarHandler.updateObserver(this);
	}

	/**
	 * Sets the CalendarWeek.
	 * 
	 * @param week
	 *            the Week.
	 */
	private void setCalenderWeek(String week) {
		kw.setText(week);
	}
}
