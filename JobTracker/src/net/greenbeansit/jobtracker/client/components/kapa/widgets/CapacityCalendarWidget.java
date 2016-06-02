package net.greenbeansit.jobtracker.client.components.kapa.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import net.greenbeansit.jobtracker.client.components.CalendarObserver;
import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.client.components.kapa.data.CapacityReportEvent;
import net.greenbeansit.jobtracker.client.components.widgets.calendar.FullCalendarCustomize;
import net.greenbeansit.jobtracker.shared.UtilizationWeek;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Philipp Minges on 31.05.16.
 */
public class CapacityCalendarWidget extends Composite implements CalendarObserver, LogicObservable {

    @UiField
    Container container;

    @UiField
    Heading month;

    @UiField
    Column calendar;

    @Override
    public void update() {
        fullcalendar.render();
        GWT.log(fullcalendar.getDate().getMonth() +"");
        month.setSubText(monthToString(fullcalendar.getDate().getMonth()));
    }

    @Override
    public void notifyHandler() {
        calendarHandler.updateObserver(this);
    }

    @Override
    public void updateObservable() {
//        addActvityReports(handler.getCurrentCapacityReportsList());
        fullcalendar.render();
    }

    @Override
    public void notifyLogicHandler() {

    }



    interface CapacityCalendarUiBinder extends UiBinder<Widget, CapacityCalendarWidget> {
    }

    private static CapacityCalendarUiBinder uiBinder = GWT.create(CapacityCalendarUiBinder.class);

    FullCalendarCustomize fullcalendar;
    CalendarConfig config;
    List<CapacityReportEvent> eventList = new ArrayList<CapacityReportEvent>();


    public CapacityCalendarWidget() {

        calendarHandler.addObserver(this);
        handler.setCapacityCalendar(this);

        initWidget(uiBinder.createAndBindUi(this));

        Timer timer = new Timer() {

            String eventTitel = "Job Description";
            String eventID;
            int titleNumber;

            /**
             * refresh the ID variable. It should be call everytime you create a
             * new event.
             */
            private void updateId() {
                eventID = "" + System.currentTimeMillis() + new Date().toString();
            }

            /**
             * Create new event. It has an unique ID.
             *
             * @param title
             *            Title of the Event.
             * @return new Event
             */
            private CapacityReportEvent createEvent(String title, Integer possibility) {
                updateId();
                return (new CapacityReportEvent(eventID, title));
            }

            @Override
            public void run() {
                config = new CalendarConfig();
                config.setLangauge(Language.German);

                config.setClickHoverConfig(getClickAndHoverConfig());
                config.setSelectConfig(getSelectConfig());
                config.setDragResizeConfig(getDragAndResizeConfig());

                config.setSelectable(true);
                config.setSelectHelper(true);

                GeneralDisplay generalDisplay = new GeneralDisplay();
                generalDisplay.setWeekNumbers(true);
                generalDisplay.setFirstDayOption(1);

                Header header = new Header();
                header.setLeft("");
                header.setCenter("");
                header.setRight("");
                generalDisplay.setHeader(header);

                config.setGeneralDisplay(generalDisplay);

                fullcalendar = new FullCalendarCustomize("CapacityCalendarWidget", ViewOption.month, config, true);
                fullcalendar.goToDate(new Date());
                calendar.add(fullcalendar);

                calendarHandler.registerCalendar(fullcalendar);
                fullcalendar.render();
                month.setSubText(monthToString(fullcalendar.getDate().getMonth()));
            }

            private ClickAndHoverConfig getClickAndHoverConfig() {
                ClickAndHoverConfig clickHoverConfig = new ClickAndHoverConfig(new ClickAndHoverEventCallback() {
                    @Override
                    public void eventMouseover(JavaScriptObject calendarEvent, NativeEvent event,
                                               JavaScriptObject viewObject) {
                    }

                    @Override
                    public void eventMouseout(JavaScriptObject calendarEvent, NativeEvent event,
                                              JavaScriptObject viewObject) {
                    }

                    /**
                     * Description Menu for Calendar Entries
                     */
                    @Override
                    public void eventClick(JavaScriptObject calendarEvent, NativeEvent event,
                                           JavaScriptObject viewObject) {
                        CapacityReportEvent e = new CapacityReportEvent(calendarEvent);
                        fullcalendar.capacityEvent = e;
                        for(CapacityReportEvent a : eventList) {
                            if (a.getDescription().equals(e.getDescription())) {
                                handler.setCurrentReport(a.getUw());
                            }
                        }
                        notifyHandler();
                    }

                    /**
                     * Not needed
                     */
                    @Override
                    public void dayClick(JavaScriptObject moment, NativeEvent event, JavaScriptObject viewObject) {
                    }

                });
                return clickHoverConfig;
            }

            private SelectConfig getSelectConfig() {
                SelectConfig selectConfig = new SelectConfig(new SelectEventCallback() {

                    @Override
                    public void select(JavaScriptObject start, JavaScriptObject end, NativeEvent event,
                                       JavaScriptObject viewObject) {
                        updateId();
                        CapacityReportEvent tmp = new CapacityReportEvent(eventID, eventTitel);
                        tmp.setStart(start);
                        tmp.setEnd(end);
                        unselect(viewObject, event);
                        fullcalendar.addEvent(tmp);
                        fullcalendar.capacityEvent = tmp;
                        notifyHandler();
                    }

                    @Override
                    public void unselect(JavaScriptObject viewObject, NativeEvent event) {

                    }
                });

                return selectConfig;
            }

            private DragAndResizeConfig getDragAndResizeConfig() {
                DragAndResizeConfig dr = new DragAndResizeConfig(new DragAndResizeCallback() {

                    @Override
                    public void eventResizeStop(JavaScriptObject calendarEvent, NativeEvent nativeEvent) {
                        notifyHandler();

                    }

                    @Override
                    public void eventResizeStart(JavaScriptObject calendarEvent, NativeEvent nativeEvent) {
                        notifyHandler();
                    }

                    @Override
                    public void eventResize(JavaScriptObject calendarEvent, JavaScriptObject revertFunction,
                                            NativeEvent nativeEvent) {
                        notifyHandler();
                    }

                    @Override
                    public void eventDrop(JavaScriptObject calendarEvent, JavaScriptObject revertFunction,
                                          NativeEvent nativeEvent) {
                        notifyHandler();
                    }

                    @Override
                    public void eventDragStop(JavaScriptObject calendarEvent, NativeEvent nativeEvent) {
                        notifyHandler();
                    }

                    /**
                     * This method copies an Element in CapacityCalendar
                     * with ALT-Key pressed
                     */
                    @Override
                    public void eventDragStart(JavaScriptObject calendarEvent, NativeEvent nativeEvent) {
                        if (nativeEvent.getAltKey()) {
                            updateId();
                            CapacityReportEvent dragEvent = new CapacityReportEvent(calendarEvent);
                            CapacityReportEvent oldEvent = createEvent(dragEvent.getTitle(), dragEvent.getPossibility());

                            oldEvent.setStart(dragEvent.getISOStart());
                            oldEvent.setEnd(dragEvent.getISOEnd());

                            dragEvent.setTitle(dragEvent.getTitle() + titleNumber++);
                            fullcalendar.addEvent(oldEvent);
                            fullcalendar.capacityEvent = dragEvent;
                            notifyHandler();
                        }

                    }

                });
                return dr;
            }
        };
        timer.schedule(0);
    }

    public String getYear(){
        return fullcalendar.getDate().getYear() + 1900 + "";
    }

    /**
     * Adds ActivityReports to the calendar.
     * @param reports List of ActivityReports for CapacityPlaning
     */
    public void addActvityReports(List<UtilizationWeek> reports) {
        if(!reports.isEmpty()){
            for (UtilizationWeek ap : reports) {
                CapacityReportEvent e = new CapacityReportEvent(ap,ap.getId() + "", ap.getText(), true, true, true);
                ap.getBeginDate().setYear((2016-1900));
                e.setStart(calendarHandler.getISO8601StringForDate(ap.getBeginDate(), ap.getBeginTime()));
                e.setEnd(calendarHandler.getISO8601StringForDate(ap.getBeginDate(), ap.getEndTime()));
                fullcalendar.addEvent(e);
                this.eventList.add(e);
                fullcalendar.render();
                fullcalendar.capacityEvent = null;

            }
        }
        fullcalendar.render();

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