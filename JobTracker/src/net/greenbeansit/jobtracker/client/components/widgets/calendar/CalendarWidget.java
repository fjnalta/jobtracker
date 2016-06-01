package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.AgendaOptions;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.CalendarConfig;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ClickAndHoverConfig;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ClickAndHoverEventCallback;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.DragAndResizeCallback;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.DragAndResizeConfig;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.Event;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.EventDataConfig;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.GeneralDisplay;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.Header;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.Language;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.SelectConfig;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.SelectEventCallback;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;

import net.greenbeansit.jobtracker.client.components.CalendarHandler;
import net.greenbeansit.jobtracker.client.components.CalendarObserver;
import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.ActivityReport;

/**
 * Displays the {@link CalendarWidget} and it {@link ActivityReportEvents}
 * 
 * @author Jonathan Brenner
 *
 */
public class CalendarWidget extends Composite implements CalendarObserver, LogicObservable {

	private static CalendarWidgetUiBinder uiBinder = GWT.create(CalendarWidgetUiBinder.class);

	private int eventID;

	interface CalendarWidgetUiBinder extends UiBinder<Widget, CalendarWidget> {
	}

	@UiField
	Row calendarRow;

	FullCalendarCustomize calendar;
	CalendarConfig config;
	List<ActivityReportEvent> eventList = new ArrayList<ActivityReportEvent>();

	/**
	 * Initializes a new instance of the CalendarWidget and sets the settings.
	 */
	public CalendarWidget() {
		initWidget(uiBinder.createAndBindUi(this));

		calendarHandler.addObserver(this);
		handler.setCalendar(this);

		Timer t = new Timer() {
			String eventTitel = "Job Description";
			int titleNumber;

			/**
			 * refresh the ID variable. It should be call everytime you create a
			 * new event.
			 */
			private void updateId() {
				eventID++;
				;
			}

			/**
			 * Create new event. It has an unique ID.
			 * 
			 * @param title
			 *            Title of the Event.
			 * @return new Event
			 */
			private ActivityReportEvent createEvent(String id, String title) {
				eventID = Integer.parseInt(id);
				updateId();
				return (new ActivityReportEvent(id, title));
			}

			private ActivityReportEvent createEvent(String title) {
				updateId();
				return (new ActivityReportEvent(eventID + "", title));
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
				generalDisplay.setHeight(600);
				Header header = new Header();
				header.setLeft("");
				header.setCenter("");
				header.setRight("");
				generalDisplay.setHeader(header);

				config.setGeneralDisplay(generalDisplay);

				AgendaOptions agenda = new AgendaOptions();
				agenda.setAxisFormat("");
				agenda.setSlotDuration(15);
				agenda.setSnapDuration(15);
				agenda.setAllDaySlot(false);
				agenda.setSlotEventOverlap(false);
				config.setAgendaOptions(agenda);

				calendar = new FullCalendarCustomize("fullCalendar", ViewOption.agendaWeek, config, true);
				calendarRow.add(calendar);
				calendar.render();

				calendarHandler.registerCalendar(calendar);
				handler.loadAllReports();
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
					 * Open the context menu To-Do
					 */
					@Override
					public void eventClick(JavaScriptObject calendarEvent, NativeEvent event,
							JavaScriptObject viewObject) {
						// Event e = new Event(calendarEvent);
						// calendar.removeEvent(e.getId());
						ActivityReportEvent e = new ActivityReportEvent(calendarEvent);
						calendar.currentEvent = e;
						for (ActivityReportEvent a : eventList) {
							if (a.getId().equals(e.getId())) {
								handler.setCurrentReport(a.getAp());
							}
						}
						notifyHandler();
					}

					/**
					 * Create new Event in Calendar
					 */
					@Override
					public void dayClick(JavaScriptObject moment, NativeEvent event, JavaScriptObject viewObject) {

					}

				});
				return clickHoverConfig;
			}

			private SelectConfig getSelectConfig() {
				SelectConfig selectConfig = new SelectConfig(new SelectEventCallback() {
					/**
					 * Creates a new {@link Event} or splits it into several if
					 * it over several days
					 */
					@Override
					public void select(JavaScriptObject start, JavaScriptObject end, NativeEvent event,
							JavaScriptObject viewObject) {

						GWT.log(start.toString() + "\n\rdraw\n\r" + calendar.getCurrentView().toString());
						/*
						 * Thu Jun 02 2016 08:15:00 GMT+0000
						 *
						 */
						updateId();
						ActivityReportEvent tmp = new ActivityReportEvent(eventID + "", eventTitel + eventID);
						if (calendar.getCurrentView().toString().equals("month")) {
							String[] splitStart = start.toString().split(" ");
							String startYear = splitStart[3];
							String startMonth = addLeadingNull((getMonth(splitStart[1]) + 1) + "");
							String startDay = splitStart[2];
							Date neweEventDateStart = new Date(Integer.parseInt(startYear), getMonth(splitStart[1]),
									(Integer.parseInt(splitStart[2])));

							String[] splitEnd = end.toString().split(" ");
							String endYear = splitEnd[3];
							String endMonth = addLeadingNull((getMonth(splitEnd[1]) + 1) + "");
							Date newEventDateEnd = new Date(Integer.parseInt(endYear), getMonth(splitEnd[1]),
									(Integer.parseInt(splitEnd[2])));
							newEventDateEnd.setDate(newEventDateEnd.getDate() - 1);
							String endDay = addLeadingNull(newEventDateEnd.getDate() + "");

							int st = Integer.parseInt(startDay);
							int ed = Integer.parseInt(endDay);
							String eventStandardStart = startYear + "-" + startMonth + "-" + startDay
									+ "T08:00:00.000Z";
							String eventStandardEnd = startYear + "-" + startMonth + "-" + startDay + "T16:00:00.000Z";
							if (neweEventDateStart.before(newEventDateEnd)) {
								if (neweEventDateStart.getDay() != 1 && neweEventDateStart.getDay() != 0) {
									tmp.setStart(eventStandardStart);
									tmp.setEnd(eventStandardEnd);
									addEvent(tmp, viewObject, event);
								}

								while (neweEventDateStart.before(newEventDateEnd)) {
									neweEventDateStart.setDate(neweEventDateStart.getDate() + 1);
									startDay = addLeadingNull("" + neweEventDateStart.getDate());
									startMonth = addLeadingNull("" + (neweEventDateStart.getMonth() + 1));
									startYear = "" + neweEventDateStart.getYear();

									if (neweEventDateStart.getDay() != 1 && neweEventDateStart.getDay() != 0) {
										GWT.log(neweEventDateStart.toString() + "\n\ragain\n\r"
												+ newEventDateEnd.toString() + "\n\r"
												+ neweEventDateStart.before(newEventDateEnd));
										ActivityReportEvent tempEvent = new ActivityReportEvent(eventID + "",
												eventTitel + eventID);

										eventStandardStart = startYear + "-" + startMonth + "-" + startDay
												+ "T08:00:00.000Z";
										eventStandardEnd = startYear + "-" + startMonth + "-" + startDay
												+ "T16:00:00.000Z";

										tempEvent.setStart(eventStandardStart);
										tempEvent.setEnd(eventStandardEnd);

										addEvent(tempEvent, viewObject, event);
										updateId();
									}

								}
							} else {
								eventStandardEnd = startYear + "-" + startMonth + "-" + endDay + "T16:00:00.000Z";
								tmp.setStart(eventStandardStart);
								tmp.setEnd(eventStandardEnd);
								addEvent(tmp, viewObject, event);
							}
						} else {
							tmp.setStart(start);
							tmp.setEnd(end);
							addEvent(tmp, viewObject, event);
						}

						notifyHandler();
					}

					private void addEvent(ActivityReportEvent tmp, JavaScriptObject viewObject, NativeEvent event) {
						unselect(viewObject, event);
						calendar.addEvent(tmp);
						calendar.currentEvent = tmp;
					}

					private String addLeadingNull(String sign) {
						if (sign.length() < 2) {
							return (0 + sign);
						} else {
							return sign;
						}
					}

					/**
					 * Get the Month from a String.
					 * 
					 * @param string
					 *            the month as a {@link String} e.g. "Jan"
					 * @return a {@link int} from 0 to 11 ( january - december)
					 */
					private int getMonth(String string) {
						switch (string) {
						case "Jan":
							return 0;
						case "Feb":
							return 1;
						case "Mar":
							return 2;
						case "Apr":
							return 3;
						case "May":
							return 4;
						case "Jun":
							return 5;
						case "Jul":
							return 6;
						case "Aug":
							return 7;
						case "Sep":
							return 8;
						case "Oct":
							return 9;
						case "Nov":
							return 10;
						case "Dev":
							return 11;
						}
						return 12;
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
					 * This method should copy an event
					 */
					@Override
					public void eventDragStart(JavaScriptObject calendarEvent, NativeEvent nativeEvent) {

						// .alert(nativeEvent.getCharCode() + " " + '\u0061');
						if (nativeEvent.getAltKey()) {
							updateId();
							ActivityReportEvent dragEvent = new ActivityReportEvent(calendarEvent);
							ActivityReportEvent oldEvent = createEvent(dragEvent.getId(), dragEvent.getTitle());

							oldEvent.setStart(dragEvent.getISOStart());
							oldEvent.setEnd(dragEvent.getISOEnd());

							dragEvent.setTitle(dragEvent.getTitle() + titleNumber++);
							calendar.addEvent(oldEvent);
							calendar.currentEvent = dragEvent;
							notifyHandler();
						}

					}

				});
				return dr;
			}
		};
		t.schedule(0);
	}

	@Override
	public void update() {
		calendar.render();
	}

	@Override
	public void notifyHandler() {
		calendarHandler.updateObserver(this);
	}

	/**
	 * This Metho adds {@link ActivityReport}s from a {@link List} to the
	 * calendar.
	 * 
	 * @param reports
	 *            {@link List} of {@link ActivityReport}s
	 */
	public void addActvityReports(List<ActivityReport> reports) {
		if (!reports.isEmpty()) {
			for (ActivityReport ap : reports) {
				ActivityReportEvent e = new ActivityReportEvent(ap, ap.getId() + "", ap.getText(), true, true, true);
				ap.getDate().setYear((2016 - 1900));
				e.setStart(calendarHandler.getISO8601StringForDate(ap.getDate(), ap.getStartTime()));
				e.setEnd(calendarHandler.getISO8601StringForDate(ap.getDate(), ap.getEndTime()));
				calendar.addEvent(e);
				this.eventList.add(e);
				eventID = Integer.parseInt(e.getId());
				calendar.render();
				calendar.currentEvent = null;

			}
		}
		calendar.render();

	}

	@Override
	public void updateObservable() {
		addActvityReports(handler.getCurrentReportsList());
		calendar.render();
	}

	@Override
	public void notifyLogicHandler() {
		// TODO Auto-generated method stub
	}

}
