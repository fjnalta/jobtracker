package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.AgendaOptions;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.CalendarConfig;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ClickAndHoverConfig;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ClickAndHoverEventCallback;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.DragAndResizeCallback;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.DragAndResizeConfig;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.Event;
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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;

import net.greenbeansit.jobtracker.client.components.CalendarObserver;

public class CalendarWidget extends Composite implements CalendarObserver {

	private static CalendarWidgetUiBinder uiBinder = GWT.create(CalendarWidgetUiBinder.class);

	interface CalendarWidgetUiBinder extends UiBinder<Widget, CalendarWidget> {
	}

	@UiField
	Row calendarRow;

	FullCalendarCustomize calendar;

	public CalendarWidget() {
		initWidget(uiBinder.createAndBindUi(this));

		Timer t = new Timer() {
			String eventTitel = "new Event";
			int titleNumber;
			String eventID;

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
			private Event createEvent(String title) {
				updateId();
				return (new Event(eventID, title));
			}

			@Override
			public void run() {

				CalendarConfig config = new CalendarConfig();
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
						Event e = new Event(calendarEvent);
						calendar.removeEvent(e.getId());

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

					@Override
					public void select(JavaScriptObject start, JavaScriptObject end, NativeEvent event,
							JavaScriptObject viewObject) {
						updateId();
						Event tmp = new Event(eventID, eventTitel);
						tmp.setStart(start);
						tmp.setEnd(end);
						unselect(viewObject, event);
						calendar.addEvent(tmp);
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
						// System.out.println("eventResizeStop");

					}

					@Override
					public void eventResizeStart(JavaScriptObject calendarEvent, NativeEvent nativeEvent) {
						// System.out.println("eventResizeStart");
					}

					@Override
					public void eventResize(JavaScriptObject calendarEvent, JavaScriptObject revertFunction,
							NativeEvent nativeEvent) {
						// System.out.println("eventResize");
					}

					@Override
					public void eventDrop(JavaScriptObject calendarEvent, JavaScriptObject revertFunction,
							NativeEvent nativeEvent) {
						// System.out.println("eventResize");
					}

					@Override
					public void eventDragStop(JavaScriptObject calendarEvent, NativeEvent nativeEvent) {
						// System.out.println("eventResize");
					}

					/**
					 * This method should copy an event
					 */
					@Override
					public void eventDragStart(JavaScriptObject calendarEvent, NativeEvent nativeEvent) {

						// .alert(nativeEvent.getCharCode() + " " + '\u0061');
						if (true) {
							updateId();
							Event dragEvent = new Event(calendarEvent);
							Event oldEvent = createEvent(dragEvent.getTitle());

							oldEvent.setStart(dragEvent.getISOStart());
							oldEvent.setEnd(dragEvent.getISOEnd());

							dragEvent.setTitle(dragEvent.getTitle() + titleNumber++);
							calendar.addEvent(oldEvent);
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
		// TODO Auto-generated method stub

	}

}
