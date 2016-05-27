package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.html.Text;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;

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
	 * @param year
	 * @param month
	 * @param day
	 * @return
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

	public void setCalenderWeek(String week) {
		kw.setText(week);
	}

	public void setDate(String monthDate) {
		date.setText(monthDate);
	}

	private String createDate(String date) {
		return date.substring(4, 10).replace(" ", "`");
	}

}
