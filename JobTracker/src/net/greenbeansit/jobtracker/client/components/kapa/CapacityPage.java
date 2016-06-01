package net.greenbeansit.jobtracker.client.components.kapa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import net.greenbeansit.jobtracker.client.components.kapa.widgets.CapacityCalendarWidget;
import org.gwtbootstrap3.client.ui.Heading;

/**
 * Created by Philipp Minges on 23.05.16.
 */
public class CapacityPage extends Composite {

    @UiField
    Heading dateHeading;

    @UiField
    CapacityCalendarWidget calendar;

    interface KapaPageUiBinder extends UiBinder<Widget, CapacityPage> {
    }

    private static KapaPageUiBinder uiBinder = GWT.create(KapaPageUiBinder.class);

    public CapacityPage() {
        initWidget(uiBinder.createAndBindUi(this));

        Timer timer = new Timer() {
            @Override
            public void run() {
                initialize();
            }
        };
        timer.schedule(10);
    }

    private void initialize() {
        dateHeading.setText(calendar.getYear());
    }
}