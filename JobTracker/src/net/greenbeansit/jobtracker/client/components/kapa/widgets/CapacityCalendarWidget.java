package net.greenbeansit.jobtracker.client.components.kapa.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import net.greenbeansit.jobtracker.client.components.widgets.calendar.FullCalendarCustomize;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.*;

import java.util.Date;

/**
 * Created by ajo on 31.05.16.
 */
public class CapacityCalendarWidget extends Composite {

    @UiField
    Container container;

    @UiField
    Heading month;

    @UiField
    Column calendar;

    interface CapacityCalendarUiBinder extends UiBinder<Widget, CapacityCalendarWidget> {
    }

    private static CapacityCalendarUiBinder uiBinder = GWT.create(CapacityCalendarUiBinder.class);

    FullCalendarCustomize fullcalendar;
    CalendarConfig config;

    private CapacityCalendarWidget capacityCalendar = this;

    public CapacityCalendarWidget() {
//        calendarHandler.addObserver(this);
//        handler.setCapacityCalendar(this);

        initWidget(uiBinder.createAndBindUi(this));

        Timer timer = new Timer() {
            @Override
            public void run() {
                initCalendar();
            }
        };
        timer.schedule(0);
    }

    private void initCalendar() {

        config = new CalendarConfig();
        config.setLangauge(Language.German);

        GeneralDisplay generalDisplay = new GeneralDisplay();
        generalDisplay.setWeekNumbers(true);
        generalDisplay.setFirstDayOption(1);

        Header header = new Header();
        header.setLeft("");
        header.setCenter("");
        header.setRight("");
        generalDisplay.setHeader(header);

        config.setGeneralDisplay(generalDisplay);

        fullcalendar = new FullCalendarCustomize("CapacityCalendarWidget", ViewOption.month, config, false);
        fullcalendar.goToDate(new Date());
        calendar.add(fullcalendar);

        month.setSubText(monthToString(fullcalendar.getDate().getMonth()));
    }

    public String getYear(){
        return fullcalendar.getDate().getYear() + 1900 + "";
    }

    private String monthToString(int month){
        String out = "";
        switch (month)
        {
            case 0:
                out = "Jan";
                break;
            case 1:
                out = "Feb";
                break;
            case 2:
                out = "Mär";
                break;
            case 3:
                out = "Apr";
                break;
            case 4:
                out = "Mai";
                break;
            case 5:
                out = "Jun";
                break;
            case 6:
                out = "Jul";
                break;
            case 7:
                out = "Aug";
                break;
            case 8:
                out = "Sep";
                break;
            case 9:
                out = "Okt";
                break;
            case 10:
                out = "Nov";
                break;
            case 11:
                out = "Dez";
                break;
        }
        return out;
    }
}