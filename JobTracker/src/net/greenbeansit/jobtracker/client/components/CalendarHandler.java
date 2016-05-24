package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.ws.handler.LogicalHandler;

import com.google.gwt.user.client.Window;

import net.greenbeansit.jobtracker.client.components.HomePage.HomePageUiBinder;

import net.greenbeansit.jobtracker.client.components.widgets.calendar.FullCalendarCustomize;

/**
 * This class handles the communication between the calender widgets
 * 
 * @author ahmed
 *
 */
public class CalendarHandler implements LogicObservable {

	public FullCalendarCustomize calendar;
	//public Event currentEvent;

	private List<CalendarObserver> list = new ArrayList<>();

	/**
	 * 
	 * @param w
	 */
	public void addObserver(CalendarObserver w) {

		for (CalendarObserver p : list) {
			if (p.getClass().equals(w.getClass()))
				return;

		}
		list.add(w);
	}

	/**
	 * 
	 */
	public void updateObserver(CalendarObserver obs) {
		for (CalendarObserver p : list) {
			if (p.getClass() == obs.getClass()) {

			} else {
				p.update();
			}
		}
	}

	/**
	 * 
	 * @param w
	 */
	public void deleteObserver(CalendarObserver w) {
		list.remove(w);
	}

	public void registerCalendar(FullCalendarCustomize calenda) {
		this.calendar = calenda;
	}

	@Override
	public void updateObservable() {
		updateAllObservables();
	}

	private void updateAllObservables() {
		for (CalendarObserver p : list) {
			p.update();
		}
	}

	@Override
	public void notifyLogicHandler() {
		handler.updateAllObservables();
	}
	
	public String getTimeAsText(int all){
		int hours = all/60;
		int minutes = all%60;
		return hours + "" + minutes;
	}
	
	public int createTimeFromText(String date ){
		String[] temp = date.split("T");
		String[] temp2 = temp[1].split(":");
		int hours = Integer.parseInt(temp2[0]);
		int minutes = Integer.parseInt(temp2[1]);
		return (60 * hours) + minutes;
	}
	
	public int timeParserForISO(String date) {
		String[] temp2 = date.split(":");
		int hours = Integer.parseInt(temp2[0]);
		int minutes = Integer.parseInt(temp2[1]);
		return (60 * hours) + minutes;
	}
	
	/**
	 * 
	 * @param date
	 * @param time
	 * @return yyyy-mm-ddThh:mm:ss.000Z
	 */
	public String getISO8601StringForDate(Date date, int time) {
		return date.getYear() + "-" + fillLeadingZero(date.getMonth()) + "-" + fillLeadingZero(date.getDate()) + "T"
				+ fillLeadingZero(calculateHours(time)) + ":" + fillLeadingZero(calculateMinutes(time)) + ":00"
				+ ".000Z";
	}

	/**
	 * Add leading zero if number < 10
	 * @param num
	 * @return
	 */
	private String fillLeadingZero(int num) {
		if (num < 10) {
			return "0" + num;
		}
		return num + "";
	}

	
	private int calculateMinutes(int time) {
		return time % 60;
	}

	private int calculateHours(int time) {
		return time / 60;
	}
	

}
