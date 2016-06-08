package net.greenbeansit.jobtracker.client.components.kapa.data;

import com.google.gwt.core.client.JavaScriptObject;
import net.greenbeansit.jobtracker.client.components.kapa.widgets.CapacityCalendarWidget;
import net.greenbeansit.jobtracker.shared.UtilizationWeek;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.Event;

/**
 * A Special {@link Event} for the {@link CapacityCalendarWidget}. It is used to display
 * the {@link UtilizationWeek} on the {@link CapacityCalendarWidget} It inherits from
 * {@link Event}.
 *
 * @author Philipp Minges
 *
 */
public class CapacityReportEvent extends Event {

    private UtilizationWeek uw;
    private Integer possibility;

    /**
     *
     * @param uw
     *          an {@link UtilizationWeek}
     * @param id
     *          the id of the {@link UtilizationWeek}
     * @param title
     *          the title of the {@link UtilizationWeek}
     * @param isEditable
     *          Determines whether the event on the calendar can be modified.
     * @param isStartEditable
     *          Allow events' start times to be editable through dragging.
     * @param isDurationEditable
     *          Allow events' durations to be editable through resizing.
     */
    public CapacityReportEvent(UtilizationWeek uw, String id, String title, boolean isEditable,
                               boolean isStartEditable, boolean isDurationEditable) {

        super(id, title, isEditable, isStartEditable, isDurationEditable);
        this.uw = uw;
    }

    /**
     * Initializes a new Instance of {@link CapacityReportEvent} with the following values
     * @param id
     *          the id of the {@link CapacityReportEvent}
     * @param title
     *          the title of the {@link CapacityReportEvent}
     */
    public CapacityReportEvent(String id, String title) {
        super(id, title);
    }

    /**
     * Initializes a new instance of the {@link CapacityReportEvent} class with
     * the following values
     *
     * @param jso
     *            as a {@link JavaScriptObject}
     */
    public CapacityReportEvent(JavaScriptObject jso) {
        super(jso);
    }

    /**
     * Method for getting the {@link UtilizationWeek} of the
     * {@link CapacityReportEvent}.
     *
     * @return the {@link UtilizationWeek} of the {@link CapacityReportEvent}
     */
    public UtilizationWeek getUw() {
        return uw;
    }


    /**
     * Method for setting the {@link UtilizationWeek} of the
     * @param uw
     *          the {@link UtilizationWeek}
     */
    public void setUtilizationWeek(UtilizationWeek uw) {
        this.uw = uw;
    }
}
