package net.greenbeansit.jobtracker.client.utils.rest;

import org.gwtbootstrap3.extras.notify.client.constants.NotifyType;
import org.gwtbootstrap3.extras.notify.client.ui.Notify;
import org.gwtbootstrap3.extras.notify.client.ui.NotifySettings;

/**
 * Contains helper methods for dealing with Bootstrap {@link Notify}.
 * 
 * @author Max Blatt
 */
public final class NotifyHelper
{
	/**
	 * Empty constructor for internal purposes.
	 */
	private NotifyHelper() { }
	
	/**
	 * Displays an error notify message.
	 * 
	 * @param message the error message that should be displayed.
	 */
	public static void errorMessage(String message)
	{
		NotifySettings settings = NotifySettings.newSettings();
		settings.setType(NotifyType.DANGER);
		Notify.notify(message, settings);
	}

	/**
	 * Displays the success notify message.
	 * @param message the message.
     */
	public static void successMessage(String message)
	{
		NotifySettings settings = NotifySettings.newSettings();
		settings.setType(NotifyType.SUCCESS);
		Notify.notify(message, settings);
	}
}
