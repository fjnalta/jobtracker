package net.greenbeansit.jobtracker.client.components.kapa.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.client.components.kapa.CapaCalendarObserver;
import net.greenbeansit.jobtracker.shared.UtilizationWeek;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;

/**
 * Shows the bottom Widget on the Capacity Page. This Widget is used to display
 * the data of the actual UtilizationWeek of the {@link CapacityCalendarWidget}
 * It is also used to save new CapacityReportEvents to the Database.
 *
 * @author Philipp Minges
 */
public class CapacityTimeInputWidget extends Composite implements CapaCalendarObserver, LogicObservable {

    /**
     * Calendar Handler
     */
    @Override
    public void update() {
        if(calendarHandler.calendar.currentCapacityEvent != null) {
            projectStart.setText(splitISOString(calendarHandler.calendar.currentCapacityEvent.getISOStart()));
            projectEnd.setText(splitISOString(calendarHandler.calendar.currentCapacityEvent.getISOEnd()));
        }
        notifyLogicHandler();
    }

    @Override
    public void notifyHandler() {
        calendarHandler.updateObserver(this);
    }

    /**
     * Logic Handler
     */
    @Override
    public void updateObservable() {
        currentUtilizationWeek = handler.getCurrentUtilizationWeek();
    }

    @Override
    public void notifyLogicHandler() {
        //handler.setCurrentUtilizationWeek(currentUtilizationWeek);
    }

    interface CapacityTimeInputWidgetUiBinder extends UiBinder<Widget, CapacityTimeInputWidget> {
    }

    private static CapacityTimeInputWidgetUiBinder uiBinder = GWT.create(CapacityTimeInputWidgetUiBinder.class);

    private UtilizationWeek currentUtilizationWeek;

    /**
     * Initializes a new Instance of CapacityTimeInputWidget.
     */
    public CapacityTimeInputWidget() {

        initWidget(uiBinder.createAndBindUi(this));

        handler.addObservable(this);
        handler.updateObservable(this);
        calendarHandler.addObserver(this);
        currentUtilizationWeek = handler.getCurrentUtilizationWeek();

    }

    @UiField
    TextBox projectStart, projectEnd;

    /**
     * {@link UiHandler} for the {@link Button} that saves the
     * {@link UtilizationWeek}
     *
     * @param e
     *            {@link ClickEvent}
     */
    @UiHandler("buttonBook")
    public void buttonBookClicked(ClickEvent e) {
        for(UtilizationWeek u : calendarHandler.calendar.getCapacityReportsToSave()){
            handler.saveUtilizationWeek(u);
        }
        calendarHandler.calendar.getCapacityReportsToSave().clear();
    }

    /**
     * Removes the Time from the Date String.
     * @param string the Date string.
     * @return the Date string.
     */
    private String splitISOString(String string){
        if (string != null && string.length() > 0) {
            string = string.substring(0, string.length()-14);
        }
        return string;
    }
}