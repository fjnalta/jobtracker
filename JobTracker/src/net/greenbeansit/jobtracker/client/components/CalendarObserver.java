package net.greenbeansit.jobtracker.client.components;

/**
 *  
 * @author Ahmed
 */
public interface CalendarObserver {
	CalendarHandler handler = new CalendarHandler();
	/**
	 * 
	 */
	public void update();
	public void notifyHandler();
	
}
