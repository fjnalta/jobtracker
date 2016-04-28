package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the communication between the calender widgets
 * 
 * @author ahmed
 *
 */
public class CalendarHandler {

	private List<CalendarObserver> list = new ArrayList<>();

	/**
	 * 
	 * @param w
	 */
	public void addObserver(CalendarObserver w) {
		
		for (CalendarObserver p : list) {
			if(p.getClass().equals(w.getClass()))
				return;
			
		}
		list.add(w);
	}

	/**
	 * 
	 */
	public void updateObserver(){
		for (CalendarObserver p : list) {
			p.update();
		}
	}

	/**
	 * 
	 * @param w
	 */
	public void deleteObserver(CalendarObserver w){
			list.remove(w);
	}

}
