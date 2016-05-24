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
}