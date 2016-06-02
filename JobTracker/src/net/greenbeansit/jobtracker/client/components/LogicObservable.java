package net.greenbeansit.jobtracker.client.components;

/**
 * Interface for the observable pattern used by the widgets
 *
 * @author Alexander Kirilyuk
 */
public interface LogicObservable {
	/**
	 * {@link LogicHandler} object, in charge for the logic and synchronization of the widgets
	 */
	LogicHandler handler = new LogicHandler();

	/**
	 * update the observable
	 */
	public void updateObservable();

	/**
	 * notify logic handler
	 */
	public void notifyLogicHandler();

}
