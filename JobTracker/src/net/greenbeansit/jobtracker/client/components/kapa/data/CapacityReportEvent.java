package net.greenbeansit.jobtracker.client.components.kapa.data;

import com.google.gwt.core.client.JavaScriptObject;
import net.greenbeansit.jobtracker.shared.UtilizationWeek;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.*;

/**
 * Created by Philipp Minges on 01.06.16.
 */
public class CapacityReportEvent extends Event {

    private UtilizationWeek uw;
    private Integer possibility;

    public CapacityReportEvent(UtilizationWeek uw, String id, String title, boolean isEditable,
                               boolean isStartEditable, boolean isDurationEditable) {

        super(id, title, isEditable, isStartEditable, isDurationEditable);
        this.uw = uw;
    }

    public CapacityReportEvent(String id, String title) {
        super(id, title);
    }

    public CapacityReportEvent(JavaScriptObject jso) {
        super(jso);
    }

    public UtilizationWeek getUw() {
        return uw;
    }

    public void setUtilizationWeek(UtilizationWeek uw) {
        this.uw = uw;
    }

    public int getPossibility() {
        return this.possibility;
    }

    public void setPossibility(Integer possibility) {
        this.possibility = possibility;
    }
}
