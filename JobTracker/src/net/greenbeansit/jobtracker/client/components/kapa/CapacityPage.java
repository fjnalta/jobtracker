package net.greenbeansit.jobtracker.client.components.kapa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import net.greenbeansit.jobtracker.client.components.CalendarObserver;
import net.greenbeansit.jobtracker.client.components.kapa.widgets.CapacityCalendarWidget;
import org.gwtbootstrap3.client.ui.Heading;

/**
 * This Page shows the Capacity Planning. It contains all Widgets for
 * {@link CapacityCalendarWidget},
 *
 * @author Philipp Minges
 */
public class CapacityPage extends Composite implements CalendarObserver {

    @UiField
    Heading dateHeading;

    @UiField
    CapacityCalendarWidget calendar;

    /**
     * This Method is called from the {@link CalendarObserver}
     */
    @Override
    public void update() {
        GWT.log(calendar.getYear());
        dateHeading.setText(calendar.getYear());
    }

    /**
     * This Method notifies the {@link CalendarObserver} about changes.
     */
    @Override
    public void notifyHandler() {
        calendarHandler.updateObserver(this);
    }

    interface KapaPageUiBinder extends UiBinder<Widget, CapacityPage> {
    }

    private static KapaPageUiBinder uiBinder = GWT.create(KapaPageUiBinder.class);

    /**
     * Creates a new Instance of the CapacityPage
     */
    public CapacityPage() {
        initWidget(uiBinder.createAndBindUi(this));

        calendarHandler.addObserver(this);

        Timer timer = new Timer() {
            @Override
            public void run() {
                initialize();
            }
        };
        timer.schedule(300);
    }

    /**
     * Sets the Text of {@link Heading} to the actual Year of the {@link CapacityCalendarWidget}
     */
    private void initialize() {
        dateHeading.setText(calendar.getYear());
    }
}