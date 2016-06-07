package net.greenbeansit.jobtracker.client.components.kapa;

import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.client.components.widgets.calendar.FullCalendarCustomize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.google.gwt.user.client.Window;


import net.greenbeansit.jobtracker.client.components.widgets.calendar.FullCalendarCustomize;

/**
 * This class handles the communication between the calender widgets
 *
 * @author ahmed
 *
 */
public class CapaCalendarHandler implements LogicObservable {
    /**
     * calendar
     */
    public FullCalendarCustomize calendar;
    //public Event currentEvent;

    private List<CapaCalendarObserver> list = new ArrayList<>();

    /**
     *
     * @param w add a calendar observer
     */
    public void addObserver(CapaCalendarObserver w) {

        for (CapaCalendarObserver p : list) {
            if (p.getClass().equals(w.getClass()))
                return;

        }
        list.add(w);
    }

    /**
     * Updates the Observer.
     * @param obs the Observer.
     */
    public void updateObserver(CapaCalendarObserver obs) {
        for (CapaCalendarObserver p : list) {
            if (p.getClass() == obs.getClass()) {

            } else {
                p.update();
            }
        }
    }

    /**
     * delete a calendar observer
     * @param w observer to delete
     */
    public void deleteObserver(CapaCalendarObserver w) {
        list.remove(w);
    }

    /**
     * register a calender to this handler
     * @param calenda the calendar to add
     */
    public void registerCalendar(FullCalendarCustomize calenda) {
        this.calendar = calenda;
    }

    @Override
    public void updateObservable() {
        updateAllObservables();
    }

    /**
     * update all observables
     */
    private void updateAllObservables() {
        for (CapaCalendarObserver p : list) {
            p.update();
        }
    }

    @Override
    public void notifyLogicHandler() {
        handler.updateAllObservables();
    }

    /**
     *
     * @param date the date
     * @param time the time(hours and minutes) in minutes Integer value
     * @return yyyy-mm-ddThh:mm:ss.000Z
     */
    public String getISO8601StringForDate(Date date, int time) {
        return (date.getYear()+1900) + "-" + fillLeadingZero((date.getMonth()+1)) + "-" + fillLeadingZero(date.getDate()) + "T"
                + fillLeadingZero(calculateHours(time)) + ":" + fillLeadingZero(calculateMinutes(time)) + ":00"
                + ".000Z";
    }

    /**
     * Add leading zero if number < 10
     * @param num the number
     * @return String with a leading 0
     */
    private String fillLeadingZero(int num) {
        if (num < 10) {
            return "0" + num;
        }
        return num + "";
    }

    /**
     * get the minutes
     * @param time the time
     * @return the minutes
     */
    private int calculateMinutes(int time) {
        return time % 60;
    }

    /**
     * get the hours
     * @param time the hours value in minutes
     * @return the hours value
     */
    private int calculateHours(int time) {
        return time / 60;
    }


}
