package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.List;

import net.greenbeansit.jobtracker.client.components.widgets.calendar.FullCalendarCustomize;
import net.greenbeansit.jobtracker.shared.ActivityReport;

public class HomePageHandler {
	
	private ActivityReport currentReport;
	private List<HomePageObservable> list = new ArrayList<>();

	/**
	 * 
	 * @param w
	 */
	public void addObserver(HomePageObservable w) {

		for (HomePageObservable p : list) {
			if (p.getClass().equals(w.getClass()))
				return;

		}
		list.add(w);
	}

	/**
	 * 
	 */
	public void updateObserver(HomePageObservable obs) {
		for (HomePageObservable p : list) {
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
	public void deleteObserver(HomePageObservable w) {
		list.remove(w);
	}
	
	public void setCurrentReport(ActivityReport report){
		this.currentReport = report;
	}
	
}
