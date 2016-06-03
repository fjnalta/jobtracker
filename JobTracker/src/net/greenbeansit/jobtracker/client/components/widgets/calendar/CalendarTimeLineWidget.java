package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Date;

import org.gwtbootstrap3.client.ui.html.Text;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.CalendarObserver;

/**
 * 3 Round Buttons , KW KWNR MONTH DATE
 * 
 * @author jonathan
 *
 */
public class CalendarTimeLineWidget extends Composite implements CalendarObserver {

	@UiField
	Text date;

	@UiField
	Text kw;

	private static CalendarTimeLineWidgetUiBinder uiBinder = GWT.create(CalendarTimeLineWidgetUiBinder.class);

	interface CalendarTimeLineWidgetUiBinder extends UiBinder<Widget, CalendarTimeLineWidget> {
	}

	@SuppressWarnings("deprecation")
	/**
	 * Initializes a new Instane of CalendarTimeLineWidget.
	 */
	public CalendarTimeLineWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		calendarHandler.addObserver(this);
		String currentDate = createDate(new Date().toString());
		setDate(currentDate);
		Date d = new Date();
		setCalenderWeek(getCalendarWeek(d.getYear()+1900,d.getMonth() + 1, d.getDate()));
	}

	/**
	 * Get the calendarweek from the given Date
	 * @param year the year
	 * @param month the month
	 * @param day the day
	 * @return the CalendarWeek
	 */
	// TODO- Schaltjahr berechnung
	private String getCalendarWeek(int year, int month, int day) {
		
		int summDay = 0;
		while(month > 0 ){
			summDay += this.getMonthDayNumber(month--);
		}
		summDay /= 7;
		return summDay+" KW";
	}

	/**
	 * Initializes a new Instance of {@link CalendarTimeLineWidget}
	 * @param firstName the FirstName.
     */
	public CalendarTimeLineWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		calendarHandler.addObserver(this);
	}
	
	@Override
	public void update() {
		String[] sum = calendarHandler.calendar.currentEvent.getISOStart().split("T");
		String[] sum2 = sum[0].split("-");
		String day = sum2[2];
		String month = getMonthName(sum2[1]);
		
		int yearInt = Integer.parseInt(sum2[0]);
		int monthInt = Integer.parseInt(sum2[1]);
		int dayInt = Integer.parseInt(sum2[2]);
		
		setDate(month + "`" + day);
		//Window.alert(yearInt +" "+ monthInt+ " " + dayInt);
		setCalenderWeek(getCalendarWeek(yearInt, monthInt, dayInt));
		
	}

	/**
	 * Gets the Month name to a specific month.
	 * @param string the String of the Month
	 * @return the Name of the Month.
     */
	private String getMonthName(String string) {
		switch(string){
		case"01":
			return "Januar";
		case"02":
			return "Februar";
		case"03":
			return "März";
		case"04":
			return "April";
		case"05":
			return "Mai";
		case"06":
			return "Juni";
		case"07":
			return "Juli";
		case"08":
			return "August";
		case"09":
			return "September";
		case"10":
			return "Oktober";
		case"11":
			return "November";
		case"12":
			return "Dezember";
		default:
			return null;
		}	
	}

	/**
	 * Gets the number of the Day.
	 * @param month the Month.
	 * @return the day.
     */
	private int getMonthDayNumber(int month){
		switch(month){
		case 1:
			return 31;
		case 2:
			return 28;
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		default:
			return -100;
		}
	}

	@Override
	public void notifyHandler() {
		calendarHandler.updateObserver(this);
	}

	/**
	 * Sets the CalendarWeek.
	 * @param week the Week.
     */
	public void setCalenderWeek(String week) {
		kw.setText(week);
	}

	/**
	 * Sets the Date
	 * @param monthDate the date of the Month.
     */
	public void setDate(String monthDate) {
		date.setText(monthDate);
	}

	/**
	 * Creates a new Date
	 * @param date the Date as a String.
	 * @return the Date.
     */
	private String createDate(String date) {
		return date.substring(4, 10).replace(" ", "`");
	}

}
