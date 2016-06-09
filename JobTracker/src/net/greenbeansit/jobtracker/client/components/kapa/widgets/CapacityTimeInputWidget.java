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
import org.gwtbootstrap3.client.ui.constants.IconType;

import java.util.Date;

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
        projectStart.setText(splitISOString(calendarHandler.calendar.currentCapacityEvent.getISOStart()));
        projectEnd.setText(splitISOString(calendarHandler.calendar.currentCapacityEvent.getISOEnd()));
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
        if(calendarHandler.calendar.currentCapacityEvent.getUw() != null) {
            currentUtilizationWeek.setBeginDate(calendarHandler.calendar.currentCapacityEvent.getUw().getBeginDate());
            currentUtilizationWeek.setEndDate(calendarHandler.calendar.currentCapacityEvent.getUw().getEndDate());
        }
        handler.setCurrentUtilizationWeek(currentUtilizationWeek);
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


        setButtons();

        handler.addObservable(this);
        calendarHandler.addObserver(this);
        currentUtilizationWeek = handler.getCurrentUtilizationWeek();

    }

    @UiField
    TextBox projectStart, projectEnd;

    @UiField
    Button buttonUpYearStart, buttonDownYearStart,
            buttonUpYearEnd, buttonDownYearEnd;

    /**
     * {@link UiHandler} for the {@link Button} that saves the
     * {@link UtilizationWeek}
     *
     * @param e
     *            {@link ClickEvent}
     */
    @UiHandler("buttonBook")
    public void buttonBookClicked(ClickEvent e) {
        // TODO - needs work. cant read date from calendar Event.
        UtilizationWeek report = new UtilizationWeek(0, handler.getCurrentUser().getId(), currentUtilizationWeek.getText(), createDateFromText(projectStart.getText()), 8, 16,
                createDateFromText(projectEnd.getText()), currentUtilizationWeek.getPossibility(), currentUtilizationWeek.getPseudoJobId(), 0);
        handler.saveUtilizationWeek(report);
        calendarHandler.calendar.getCapacityReportsToSave().clear();
    }

    private Date createDateFromText(String text) {
        String[] split = text.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        return new Date(year, month, day);
    }

    /**
     * This Method sets the icons for all Buttons in this Widget
     */
    private void setButtons() {

        buttonUpYearStart.setIcon(IconType.ARROW_UP);
        buttonDownYearStart.setIcon(IconType.ARROW_DOWN);

        buttonUpYearEnd.setIcon(IconType.ARROW_UP);
        buttonDownYearEnd.setIcon(IconType.ARROW_DOWN);
    }

    private String splitISOString(String string){
        if (string != null && string.length() > 0) {
            string = string.substring(0, string.length()-14);
        }
        return string;
    }
}