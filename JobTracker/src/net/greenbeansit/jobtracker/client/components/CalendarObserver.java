package net.greenbeansit.jobtracker.client.components;

/**
 *  
 * @author Ahmed
 */
public interface CalendarObserver {
	/**
	 * Creates a new Instance of CalendarHandler.
	 */
	CalendarHandler calendarHandler = new CalendarHandler();

	/**
	 * Updates the Widget. Called after Handler was notified.
	 */
	public void update();

	/**
	 * Notifies the Handler.
	 */
	public void notifyHandler();
	
}
