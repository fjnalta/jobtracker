package net.greenbeansit.jobtracker.client.components.kapa.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.IconType;

/**
 * Created by Philipp Minges on 31.05.16.
 */
public class CapacityTimeInputWidget extends Composite {
    interface CapacityTimeInputWidgetUiBinder extends UiBinder<Widget, CapacityTimeInputWidget> {
    }

    private static CapacityTimeInputWidgetUiBinder uiBinder = GWT.create(CapacityTimeInputWidgetUiBinder.class);

    public CapacityTimeInputWidget() {
        initWidget(uiBinder.createAndBindUi(this));

        setButtons();
    }

    @UiField
    Button buttonUpYearStart, buttonDownYearStart,
            buttonUpCalendarWeekStart, buttonDownCalendarWeekStart,
            buttonUpYearEnd, buttonDownYearEnd, buttonUpDayWeek,
            buttonDownDayWeek, buttonUpCalendarWeekEnd, buttonDownCalendarWeekEnd;

    private void setButtons() {

        buttonUpYearStart.setIcon(IconType.ARROW_UP);
        buttonDownYearStart.setIcon(IconType.ARROW_DOWN);

        buttonUpCalendarWeekStart.setIcon(IconType.ARROW_UP);
        buttonDownCalendarWeekStart.setIcon(IconType.ARROW_DOWN);

        buttonUpYearEnd.setIcon(IconType.ARROW_UP);
        buttonDownYearEnd.setIcon(IconType.ARROW_DOWN);

        buttonUpCalendarWeekEnd.setIcon(IconType.ARROW_UP);
        buttonDownCalendarWeekEnd.setIcon(IconType.ARROW_DOWN);

        buttonUpDayWeek.setIcon(IconType.ARROW_UP);
        buttonDownDayWeek.setIcon(IconType.ARROW_DOWN);
    }
}