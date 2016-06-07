package net.greenbeansit.jobtracker.client.components.kapa;

import net.greenbeansit.jobtracker.client.components.CalendarHandler;

/**
 *
 * @author Ahmed
 */
public interface CapaCalendarObserver {
    /**
     * Creates a new Instance of CalendarHandler.
     */
    CapaCalendarHandler calendarHandler = new CapaCalendarHandler();

    /**
     * Updates the Widget. Called after Handler was notified.
     */
    public void update();

    /**
     * Notifies the Handler.
     */
    public void notifyHandler();

}