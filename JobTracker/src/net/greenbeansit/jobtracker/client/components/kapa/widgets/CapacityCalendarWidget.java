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
import net.greenbeansit.jobtracker.client.components.kapa.CapaCalendarObserver;
import net.greenbeansit.jobtracker.client.components.kapa.data.CapacityReportEvent;
import net.greenbeansit.jobtracker.client.components.widgets.calendar.FullCalendarCustomize;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.UtilizationWeek;

import org.fusesource.restygwt.client.Method;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Displays the Capacity Calendar and all {@link CapacityCalendarWidget}s in it.
 *
 * @author Philipp Minges
 */
public class CapacityCalendarWidget extends Composite implements CapaCalendarObserver, LogicObservable {

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
    List<CapacityReportEvent> eventList = new ArrayList<CapacityReportEvent>();

    private UtilizationWeek currentUtilizationWeek;

    /**
     * Initializes a new Instance of the {@link CapacityCalendarWidget}
     */
    public CapacityCalendarWidget() {

        handler.addObservable(this);
        handler.setCapacityCalendar(this);

        calendarHandler.addObserver(this);

        initWidget(uiBinder.createAndBindUi(this));

        Timer timer = new Timer() {

            String eventTitel = "Job Description";
            String eventID;
            int titleNumber;

            /**
             * refresh the ID variable. It should be called everytime you create a
             * new event.
             */
            private void updateId() {
                eventID = "" + System.currentTimeMillis() + new Date().toString();
            }

            /**
             * Create new event. It has an unique ID.
             *
             * @param title the Title of the Event.
             * @return new Event
             */
            private CapacityReportEvent createEvent(String title) {
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
                generalDisplay.setWeekends(false);
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
                handler.loadAllUtilizationWeeks();
            }

            /**
             * Sets the Click and Hover config for the {@link CapacityCalendarWidget}
             * @return the Click and Hover config
             */
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
                        fullcalendar.currentCapacityEvent = e;
                        for (CapacityReportEvent a : eventList) {
                            if (a.getId().equals(e.getId())) {
                                handler.setCurrentUtilizationWeek(a.getUw());
                                calendarHandler.setCurrentUtilizationWeek(a.getUw());
                                handler.updateAllObservables();
                            }
                        }

                        notifyLogicHandler();
                        notifyHandler();
                    }

                    /**
                     * Handles the onClick event - Not needed here
                     */
                    @Override
                    public void dayClick(JavaScriptObject moment, NativeEvent event, JavaScriptObject viewObject) {
                    }

                });
                return clickHoverConfig;
            }

            /**
             * Gets the selected config
             * @return the {@link SelectConfig} for the {@link CapacityCalendarWidget}
             */
            private SelectConfig getSelectConfig() {
                SelectConfig selectConfig = new SelectConfig(new SelectEventCallback() {

                    @Override
                    public void select(JavaScriptObject start, JavaScriptObject end, NativeEvent event,
                                       JavaScriptObject viewObject) {
                        updateId();
                        CapacityReportEvent tmp = new CapacityReportEvent(eventID, eventTitel);
                        tmp.setStart(start);
                        tmp.setEnd(end);
                        Date endDate = new Date(tmp.getISOEnd());
                        endDate.setHours(endDate.getHours()-1);
                        Date beginDate = new Date(tmp.getISOStart());

                        UtilizationWeek tempUtil = new UtilizationWeek(0,0,"","new Report",beginDate,8,16,endDate,0,0,0);

                        unselect(viewObject, event);
                        currentUtilizationWeek = tempUtil;

                        tmp.setUtilizationWeek(tempUtil);
                        fullcalendar.addEvent(tmp);
                        fullcalendar.addEventToSave(tempUtil);

                        fullcalendar.currentCapacityEvent = tmp;
                        notifyLogicHandler();
                        notifyHandler();
                    }

                    @Override
                    public void unselect(JavaScriptObject viewObject, NativeEvent event) {

                    }
                });

                return selectConfig;
            }

            /**
             * Gets the selected Drag and Resize Config for the {@link CapacityCalendarWidget}
             * @return the {@link DragAndResizeConfig}
             */
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
                        CapacityReportEvent dragEvent = new CapacityReportEvent(calendarEvent);
                        Date endDate = new Date(dragEvent.getISOEnd());
                        endDate.setHours(endDate.getHours()-1);
                        Date beginDate = new Date(dragEvent.getISOStart());
                        UtilizationWeek tempUtil = new UtilizationWeek(0,0,"","",beginDate,8,16,endDate,0,0,0);
                        fullcalendar.addEventToSave(tempUtil);
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
                            CapacityReportEvent oldEvent = createEvent(dragEvent.getTitle());
                            oldEvent.setStart(dragEvent.getISOStart());
                            oldEvent.setEnd(dragEvent.getISOEnd());
                            dragEvent.setTitle(dragEvent.getTitle() + titleNumber++);
                            fullcalendar.addEvent(oldEvent);
                            fullcalendar.currentCapacityEvent = dragEvent;
                            notifyHandler();

                        }

                    }

                });
                return dr;
            }
        };
        timer.schedule(0);
    }

    /**
     * Gets the actual year of the {@link CapacityCalendarWidget}
     *
     * @return the year
     */
    public String getYear() {
        return fullcalendar.getDate().getYear() + 1900 + "";
    }

    /**
     * Adds {@link UtilizationWeek} to the calendar.
     *
     * @param reports List of {@link UtilizationWeek} for CapacityPlaning
     */
    public void addUtilizationWeeks(List<UtilizationWeek> reports) {

        this.eventList.clear();
        fullcalendar.removeAllEvents();
        if (!reports.isEmpty()) {
            for (UtilizationWeek ap : reports) {
//                GWT.log(ap.getId() + "");
                CapacityReportEvent e = new CapacityReportEvent(ap, ap.getId() + "", ap.getText(), true, true, true);
                ap.getBeginDate().setYear((2016 - 1900));
                e.setStart(calendarHandler.getISO8601StringForDate(ap.getBeginDate(), ap.getBeginTime()));
                e.setEnd(calendarHandler.getISO8601StringForDate(ap.getEndDate(), ap.getEndTime()));
                fullcalendar.addEvent(e);
                this.eventList.add(e);
                fullcalendar.currentCapacityEvent = null;
            }
        }
        fullcalendar.render();

    }


    /**
     * This Method is called from the {@link CalendarObserver}
     */
    @Override
    public void update() {
        fullcalendar.render();
        month.setSubText(monthToString(fullcalendar.getDate().getMonth()));
    }

    /**
     * This Method notifies the {@link CalendarObserver} about changes.
     */
    @Override
    public void notifyHandler() {
        calendarHandler.updateObserver(this);
    }

    /**
     * This Method is called from the {@link LogicObservable}
     */
    @Override
    public void updateObservable() {
        currentUtilizationWeek = handler.getCurrentUtilizationWeek();
        fullcalendar.render();
        addUtilizationWeeks(handler.getCurrentUtilizationWeekList());
    }

    /**
     * This Method notifies the {@link LogicObservable} about given changes
     */
    @Override
    public void notifyLogicHandler() {
        handler.setCurrentUtilizationWeek(currentUtilizationWeek);
    }

    /**
     * Prints the Month of the Calendar into a String. This Method is used to display the
     * name of the month in the {@link CapacityCalendarWidget}
     * @param month the given {@link CapacityCalendarWidget} Month.
     * @return out the {@link String} of the Month.
     */
    private String monthToString(int month) {
        String out = "";
        switch (month) {
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
    
	/**
	 * Tells the calendar to redraw itself.
	 */
	public void onDisplayed()
	{
		Timer timer = new Timer()
		{
			@Override
			public void run()
			{
				if(fullcalendar != null)
					fullcalendar.render();
			}
		};
		timer.schedule(10);
	}


}