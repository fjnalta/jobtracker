package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.greenbeansit.jobtracker.client.components.widgets.calendar.EventDummy;

/**
 * This class handles the communication between the calender widgets
 * 
 * @author ahmed
 *
 */
public class CalendarHandler {
	
	public EventDummy events = new EventDummy();
	

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

}
