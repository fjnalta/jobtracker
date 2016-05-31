package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.awt.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.Event;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.CalendarObserver;
import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.shared.ActivityReport;

/**
 * The Calendar time input from keyboard
 * 
 * @author Jonathan
 *
 */
public class CalendarTimeInputWidget extends Composite implements CalendarObserver, LogicObservable {

	private static CalendarTimeInputWidgetUiBinder uiBinder = GWT.create(CalendarTimeInputWidgetUiBinder.class);

	interface CalendarTimeInputWidgetUiBinder extends UiBinder<Widget, CalendarTimeInputWidget> {
	}

	@UiField
	Button buttonUpDateStart;
	@UiField
	Button buttonDownDateStart;
	@UiField
	TextBox dateStart;
	@UiField
	TextBox dateEnd;
	@UiField
	Button buttonUpDateEnd;
	@UiField
	Button buttonDownDateEnd;

	@UiField
	Button buttonTimeHourUpEnd;
	@UiField
	Button buttonTimeHourDownEnd;
	@UiField
	Button buttonTimeHourUpStart;
	@UiField
	Button buttonTimeHourDownStart;
	@UiField
	TextBox eventStart;
	@UiField
	TextBox eventEnd;
	@UiField
	Button buttonTimeMinuteUpEnd;
	@UiField
	Button buttonTimeMinuteDownEnd;
	@UiField
	Button buttonTimeMinuteUpStart;
	@UiField
	Button buttonTimeMinuteDownStart;

	@UiField
	Button buttonTimeHourUpPause;
	@UiField
	Button buttonTimeHourDownPause;
	@UiField
	TextBox pause;
	@UiField
	Button buttonTimeMinuteUpPause;
	@UiField
	Button buttonTimeMinuteDownPause;

	@UiField
	Button buttonTimeHourUpDuration;
	@UiField
	Button buttonTimeHourDownDuration;
	@UiField
	TextBox workTime;
	@UiField
	Button buttonTimeMinuteUpDuration;
	@UiField
	Button buttonTimeMinuteDownDuration;

	@UiField
	Button buttonBook;

	public CalendarTimeInputWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		calendarHandler.addObserver(this);

		buttonDownDateEnd.setIcon(IconType.ARROW_DOWN);
		buttonDownDateStart.setIcon(IconType.ARROW_DOWN);
		buttonTimeHourDownEnd.setIcon(IconType.ARROW_DOWN);
		buttonTimeHourDownPause.setIcon(IconType.ARROW_DOWN);
		buttonTimeHourDownStart.setIcon(IconType.ARROW_DOWN);
		buttonTimeMinuteDownEnd.setIcon(IconType.ARROW_DOWN);
		buttonTimeMinuteDownPause.setIcon(IconType.ARROW_DOWN);
		buttonTimeMinuteDownStart.setIcon(IconType.ARROW_DOWN);
		buttonTimeHourDownDuration.setIcon(IconType.ARROW_DOWN);
		buttonTimeMinuteDownDuration.setIcon(IconType.ARROW_DOWN);

		buttonTimeHourUpEnd.setIcon(IconType.ARROW_UP);
		buttonTimeHourUpPause.setIcon(IconType.ARROW_UP);
		buttonTimeHourUpStart.setIcon(IconType.ARROW_UP);
		buttonTimeMinuteUpEnd.setIcon(IconType.ARROW_UP);
		buttonTimeMinuteUpPause.setIcon(IconType.ARROW_UP);
		buttonTimeMinuteUpStart.setIcon(IconType.ARROW_UP);
		buttonUpDateEnd.setIcon(IconType.ARROW_UP);
		buttonUpDateStart.setIcon(IconType.ARROW_UP);
		buttonTimeHourUpDuration.setIcon(IconType.ARROW_UP);
		buttonTimeMinuteUpDuration.setIcon(IconType.ARROW_UP);

	}

	@UiHandler("eventStart")
	public void keyPressedEventStart(KeyPressEvent event) {
		String before = eventStart.getText().replace(":", "");
		eventStart.setText(before);
		inputLengthIsToLong(before, event);
		inputIsNotAnNumber(event);
		eventStart.setText(before);
		notifyHandler();
	}

	@UiHandler("eventEnd")
	public void keyPressedEventEnd(KeyPressEvent event) {
		inputLengthIsToLong(eventEnd.getText(), event);
		inputIsNotAnNumber(event);
		notifyHandler();
	}

	@UiHandler("pause")
	public void keyPressedPause(KeyPressEvent event) {
		inputLengthIsToLong(pause.getText(), event);
		inputIsNotAnNumber(event);
		notifyHandler();
	}

	@UiHandler("workTime")
	public void keyPressedWorkTime(KeyPressEvent event) {
		inputLengthIsToLong(workTime.getText(), event);
		inputIsNotAnNumber(event);
		if (workTime.getText().length() > 0) {
			workTimeIsEntered();
		} else {
			enableFields();
		}
		if (workTime.getText().length() > 3) {

		}
		notifyHandler();
	}

	@UiHandler("buttonTimeHourUpStart")
	public void clickButtonTimeHourUpStart(ClickEvent e) {
		increaseEventHours(eventStart);
		increaseDateTimeUpHour(eventStart, dateStart);
		notifyHandler();
	}

	@UiHandler("buttonTimeHourDownStart")
	public void clickButtonTimeHourDownStart(ClickEvent e) {
		decreaseEventHours(eventStart);
		decreaseDateTimeDownHour(eventStart, dateStart);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteUpStart")
	public void clickButtonTimeMinuteUpStart(ClickEvent e) {
		increaseEventMinutes(eventStart);
		increaseDateTimeUpMinute(eventStart, dateStart);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteDownStart")
	public void clickButtonMinuteDownStart(ClickEvent e) {
		decreaseEventMinutes(eventStart);
		decreaseDateTimeDownMinute(eventStart, dateStart);
		notifyHandler();
	}

	@UiHandler("buttonTimeHourUpEnd")
	public void clickButtonTimeHourUpEnd(ClickEvent e) {
		increaseEventHours(eventEnd);
		increaseDateTimeUpHour(eventEnd, dateEnd);
		notifyHandler();
	}

	@UiHandler("buttonTimeHourDownEnd")
	public void clickButtonTimeHourDownEnd(ClickEvent e) {
		decreaseEventHours(eventEnd);
		decreaseDateTimeDownHour(eventEnd, dateEnd);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteUpEnd")
	public void clickButtonTimeMinuteUpEnd(ClickEvent e) {
		increaseEventMinutes(eventEnd);
		increaseDateTimeUpMinute(eventEnd, dateEnd);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteDownEnd")
	public void clickButtonMinuteDownEnd(ClickEvent e) {
		decreaseEventMinutes(eventEnd);
		decreaseDateTimeDownMinute(eventEnd, dateEnd);
		notifyHandler();
	}

	@UiHandler("buttonTimeHourUpPause")
	public void clickButtonTimeHourUpPause(ClickEvent e) {
		increasePauseHours(pause);
		notifyHandler();
	}

	@UiHandler("buttonTimeHourDownPause")
	public void clickButtonTimeHourDownPause(ClickEvent e) {
		decreasePauseDurationHours(pause);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteUpPause")
	public void clickButtonTimeMinuteUpPause(ClickEvent e) {
		increasePauseMinutes(pause);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteDownPause")
	public void clickButtonMinuteDownPause(ClickEvent e) {
		decreasePauseMinutes(pause);
		notifyHandler();
	}

	@UiHandler("buttonTimeHourUpDuration")
	public void clickButtonTimeHourUpDuration(ClickEvent e) {
		increaseDurationHours(workTime);
		changeEndByDuration();
		notifyHandler();
	}

	@UiHandler("buttonTimeHourDownDuration")
	public void clickButtonTimeHourDownDuration(ClickEvent e) {
		decreasePauseDurationHours(workTime);
		changeEndByDuration();
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteUpDuration")
	public void clickButtonTimeMinuteUpDuration(ClickEvent e) {
		increaseDurationMinutes(workTime);
		changeEndByDuration();
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteDownDuration")
	public void clickButtonTimeMinuteDownDuration(ClickEvent e) {
		decreaseDurationMinutes(workTime);
		changeEndByDuration();
		notifyHandler();
	}

	@UiHandler("buttonUpDateStart")
	public void buttonUpDateStartClicked(ClickEvent e) {
		increaseDate(dateStart);
		notifyHandler();
	}

	@UiHandler("buttonDownDateStart")
	public void buttonDownDateStartClicked(ClickEvent e) {
		decreaseDate(dateStart);
		notifyHandler();
	}

	@UiHandler("buttonDownDateEnd")
	public void buttonDownDateEndClicked(ClickEvent e) {
		decreaseDate(dateEnd);
		notifyHandler();
	}

	@UiHandler("buttonUpDateEnd")
	public void buttonUpDateEndClicked(ClickEvent e) {
		increaseDate(dateEnd);
		notifyHandler();
	}

	@UiHandler("buttonBook")
	public void buttonBookClicked(ClickEvent e) {
		int startTime = createTimeFromText(eventStart.getText());
		int duration = createTimeFromText(workTime.getText());
		int breakTime = createTimeFromText(pause.getText());
		Date date = getDateFromBox(dateStart);
		ActivityReport tmp = new ActivityReport(0, 0, 0, 0, 0, "", date, startTime, duration, breakTime);
		handler.saveReport(tmp);
	}

	@Override
	public void update() {
		ActivityReportEvent arp = calendarHandler.calendar.currentEvent;

		dateStart.setText(getDateForBoxFromISOString(arp.getISOStart()));
		dateEnd.setText(getDateForBoxFromISOString(arp.getISOEnd()));

		eventStart.setText(getTimeForBoxFromISOString(arp.getISOStart()));
		eventEnd.setText(getTimeForBoxFromISOString(arp.getISOEnd()));
		pause.setText(createTimeForTextBox(arp.getBreak()));

		workTime.setText(calculateDuration());
	}

	@Override
	public void notifyHandler() {

		calendarHandler.calendar.removeEvent(calendarHandler.calendar.currentEvent.getId());

		ActivityReportEvent e = new ActivityReportEvent(calendarHandler.calendar.currentEvent.getAp(),
				calendarHandler.calendar.currentEvent.getId(), calendarHandler.calendar.currentEvent.getTitle(), true,
				true, true);

		e.setStart(calendarHandler.getISO8601StringForDate(getDateFromBox(dateStart),
				createTimeFromText(eventStart.getText())));

		e.setEnd(calendarHandler.getISO8601StringForDate(getDateFromBox(dateEnd),
				createTimeFromText(eventEnd.getText())));

		e.setBreak(createTimeFromText(pause.getText()));

		calendarHandler.calendar.currentEvent = e;

		calendarHandler.calendar.addEvent(calendarHandler.calendar.currentEvent);
		calendarHandler.updateObserver(this);
	}

	@Override
	public void updateObservable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyLogicHandler() {
		// TODO Auto-generated method stub

	}

	private String removeDoublePoint(String input) {
		return input = input.replace(":", "");
	}

	private String addDoublePoint(String input) {
		return input = input.substring(0, 2) + ":" + input.substring(2, input.length());
	}

	private void increaseEventMinutes(TextBox box) {
		String boxText = removeDoublePoint(box.getText());
		String hourString = removeLeadingNull(boxText.substring(0, 2));
		String minuteString = removeLeadingNull(boxText.substring(2, boxText.length()));

		int hours = Integer.parseInt(hourString);
		int minutes = Integer.parseInt(minuteString);

		if (minutes < 59) {
			minutes++;
		} else {
			if (hours < 23) {
				hours++;
				minutes = 0;
			} else {
				hours = 0;
				minutes = 0;
			}
		}
		hourString = "" + hours;
		minuteString = "" + minutes;

		hourString = addLeadingNull(hourString);
		minuteString = addLeadingNull(minuteString);

		box.setText(addDoublePoint(hourString + minuteString));
	}

	/**
	 * Increase the pause minutes.
	 * 
	 * @param box
	 */
	private void increasePauseMinutes(TextBox box) {
		String boxText = removeDoublePoint(box.getText());
		String hourString = removeLeadingNull(boxText.substring(0, 2));
		String minuteString = removeLeadingNull(boxText.substring(2, boxText.length()));

		int duration = createTimeFromText(workTime.getText());
		int lengthPause = createTimeFromText(pause.getText());

		int hours = Integer.parseInt(hourString);
		int minutes = Integer.parseInt(minuteString);
		if (duration-1 > lengthPause) {
			if (minutes < 59) {
				minutes++;
			} else {
				hours++;
				minutes = 0;
			}
			if (hours > 23) {
				hours = 23;
				minutes = 59;
			}
		}

		hourString = "" + hours;
		minuteString = "" + minutes;

		hourString = addLeadingNull(hourString);
		minuteString = addLeadingNull(minuteString);

		box.setText(addDoublePoint(hourString + minuteString));
	}

	/**
	 * Increase the pause.
	 * 
	 * @param box
	 */
	private void increasePauseHours(TextBox box) {
		String boxText = removeDoublePoint(box.getText());
		String hourString = removeLeadingNull(boxText.substring(0, 2));
		String minuteString = boxText.substring(2, boxText.length());
		int hours = Integer.parseInt(hourString);
		int duration = createTimeFromText(workTime.getText());
		int lengthPause = createTimeFromText(pause.getText());
		if (duration-60 > lengthPause) {
			if (hours < 23) {
				hours++;
			} else {
				hours = 23;
			}
		}
		hourString = "" + hours;
		hourString = addLeadingNull(hourString);
		box.setText(addDoublePoint(hourString + minuteString));
	}

	private void increaseEventHours(TextBox box) {
		String boxText = removeDoublePoint(box.getText());
		String hourString = removeLeadingNull(boxText.substring(0, 2));
		String minuteString = boxText.substring(2, boxText.length());
		int hours = Integer.parseInt(hourString);
		if (hours < 23) {
			hours++;
		} else {
			hours = 0;
		}
		hourString = "" + hours;
		hourString = addLeadingNull(hourString);
		box.setText(addDoublePoint(hourString + minuteString));
	}

	private void decreaseEventMinutes(TextBox box) {
		String boxText = removeDoublePoint(box.getText());
		String hourString = removeLeadingNull(boxText.substring(0, 2));
		String minuteString = removeLeadingNull(boxText.substring(2, boxText.length()));

		int hours = Integer.parseInt(hourString);
		int minutes = Integer.parseInt(minuteString);

		if (minutes > 0) {
			minutes--;
		} else {
			if (hours > 0) {
				hours--;
				minutes = 59;
			} else {
				hours = 23;
				minutes = 59;
			}
		}
		hourString = "" + hours;
		minuteString = "" + minutes;
		hourString = addLeadingNull(hourString);
		minuteString = addLeadingNull(minuteString);
		box.setText(addDoublePoint(hourString + minuteString));

	}

	private void decreaseEventHours(TextBox box) {
		String boxText = removeDoublePoint(box.getText());
		String hourString = removeLeadingNull(boxText.substring(0, 2));
		String minuteString = boxText.substring(2, boxText.length());
		int hours = Integer.parseInt(hourString);
		if (hours > 0) {
			hours--;
		} else {
			hours = 23;
		}
		hourString = "" + hours;
		hourString = addLeadingNull(hourString);
		box.setText(addDoublePoint(hourString + minuteString));
	}

	private void inputIsNotAnNumber(KeyPressEvent event) {
		if (!Character.isDigit(event.getCharCode())) {
			event.preventDefault();
		}

	}

	private String addLeadingNull(String sign) {
		if (sign.length() < 2) {
			return (0 + sign);
		} else {
			return sign;
		}
	}

	private String addLeadingNullToInteger(int sign) {
		return addLeadingNull(sign + "");
	}

	private String removeLeadingNull(String sign) {
		if (sign.startsWith("0") && !(sign.equals("00"))) {
			return sign.replace("0", "");
		} else {
			return sign;
		}
	}

	private void inputLengthIsToLong(String before, KeyPressEvent event) {
		if (before.length() > 3) {
			event.preventDefault();
		}
	}

	private void workTimeIsEntered() {
		eventEnd.setEnabled(false);
	}

	private void enableFields() {
		eventEnd.setEnabled(true);
	}

	/**
	 * 
	 * @param text
	 *            "hh:mm"
	 * @return time int int
	 */
	private int createTimeFromText(String text) {
		String[] split = text.split(":");
		int hours = Integer.parseInt(split[0]);
		int minutes = Integer.parseInt(split[1]);
		return 60 * hours + minutes;
	}

	/**
	 * Creates a date object from a string.
	 * 
	 * @param dateStart
	 *            String "dd.mm"
	 * @return Date Object
	 */
	private Date getDateFromBox(TextBox dateStart) {
		String[] split = dateStart.getText().split("\\.");
		int day = Integer.parseInt(split[0]);
		int month = Integer.parseInt(split[1]) - 1;
		int year = 2016 - 1900;
		return new Date(year, month, day);
	}

	/**
	 * Creates the time for a textbox.
	 * 
	 * @param isoStart
	 *            ISO-String"yyyy-mm-ddThh:mm:ss.000Z"
	 * @return "hh:mm"
	 */
	private String getTimeForBoxFromISOString(String isoStart) {
		String[] splitAtT = isoStart.split("T");
		String[] splitAtDoublePoint = splitAtT[1].split(":");
		return addLeadingNull(splitAtDoublePoint[0]) + ":" + addLeadingNull(splitAtDoublePoint[1]);
	}

	/**
	 * Creates a date for a textbox.
	 * 
	 * @param isoStart
	 *            ISO-String "yyyy-mm-ddThh:mm:ss.000Z"
	 * @return "dd.mm"
	 */
	private String getDateForBoxFromISOString(String isoStart) {
		String[] splitAtT = isoStart.split("T");
		String[] splitAtDoublePoint = splitAtT[0].split("-");
		return addLeadingNull(splitAtDoublePoint[2]) + "." + addLeadingNull(splitAtDoublePoint[1]);
	}

	/**
	 * Calculates the duration of an event.
	 * 
	 * @return the duration "hh:mm"
	 */
	private String calculateDuration() {
		int startOfEvent = createTimeFromText(eventStart.getText());
		int endOfEvent = createTimeFromText(eventEnd.getText());
		int eventPause = createTimeFromText(pause.getText());
		int duration = endOfEvent - startOfEvent + eventPause;
		return addLeadingNullToInteger(duration / 60) + ":" + addLeadingNullToInteger(duration % 60);
	}

	/**
	 * decrease the day by one.
	 * 
	 * @param dateTextBox
	 *            "dd.mm"
	 */
	private void increaseDate(TextBox dateTextBox) {
		Date dt = getDateFromBox(dateTextBox);
		dt.setDate(dt.getDate() + 1);
		int month = dt.getMonth() + 1;
		dateTextBox.setText(addLeadingNull(dt.getDate() + "") + "." + addLeadingNull(month + ""));
	}

	/**
	 * Increase the date by one day.
	 * 
	 * @param dateTextBox
	 */
	private void decreaseDate(TextBox dateTextBox) {
		Date dt = getDateFromBox(dateTextBox);
		dt.setDate(dt.getDate() - 1);
		int month = dt.getMonth() + 1;
		dateTextBox.setText(addLeadingNull(dt.getDate() + "") + "." + addLeadingNull(month + ""));
	}

	/**
	 * 
	 * @param time
	 *            in Integer
	 * @return String "hh:mm"
	 */
	private String createTimeForTextBox(int time) {
		return addLeadingNullToInteger(time / 60) + ":" + addLeadingNullToInteger(time % 60);
	}

	/**
	 * Calculates a new End for the Event when the duration change.
	 */
	private void changeEndByDuration() {
		int newEnd = createTimeFromText(eventStart.getText()) + createTimeFromText(workTime.getText());
		eventEnd.setText(createTimeForTextBox(newEnd));
	}

	/**
	 * Increase the duration minutes until the time is 23:59.
	 * 
	 * @param box
	 */
	private void increaseDurationMinutes(TextBox box) {
		String boxText = removeDoublePoint(box.getText());
		String hourString = removeLeadingNull(boxText.substring(0, 2));
		String minuteString = removeLeadingNull(boxText.substring(2, boxText.length()));

		int hours = Integer.parseInt(hourString);
		int minutes = Integer.parseInt(minuteString);

		if (minutes < 59) {
			minutes++;
		} else {
			hours++;
			minutes = 0;
		}
		if (hours > 23) {
			hours = 23;
			minutes = 59;
		}

		hourString = "" + hours;
		minuteString = "" + minutes;

		hourString = addLeadingNull(hourString);
		minuteString = addLeadingNull(minuteString);

		box.setText(addDoublePoint(hourString + minuteString));
	}

	/**
	 * Increase the duration time until the time is 23.XX hours.
	 * 
	 * @param box
	 */
	private void increaseDurationHours(TextBox box) {
		String boxText = removeDoublePoint(box.getText());
		String hourString = removeLeadingNull(boxText.substring(0, 2));
		String minuteString = boxText.substring(2, boxText.length());
		int hours = Integer.parseInt(hourString);
		if (hours < 23) {
			hours++;
		} else {
			hours = 23;
		}
		hourString = "" + hours;
		hourString = addLeadingNull(hourString);
		box.setText(addDoublePoint(hourString + minuteString));
	}

	/**
	 * decrease the pause minutes until the time is 00:00.
	 * 
	 * @param box
	 */
	private void decreasePauseMinutes(TextBox box) {
		String boxText = removeDoublePoint(box.getText());
		String hourString = removeLeadingNull(boxText.substring(0, 2));
		String minuteString = removeLeadingNull(boxText.substring(2, boxText.length()));

		int hours = Integer.parseInt(hourString);
		int minutes = Integer.parseInt(minuteString);

		if (minutes > 0) {
			minutes--;
		} else {
			if (hours > 0) {
				hours--;
				minutes = 59;
			} else {
				hours = 0;
				minutes = 0;
			}
		}
		hourString = "" + hours;
		minuteString = "" + minutes;
		hourString = addLeadingNull(hourString);
		minuteString = addLeadingNull(minuteString);
		box.setText(addDoublePoint(hourString + minuteString));

	}

	/**
	 * decreas the minutes of duration until it is 00:01.
	 * 
	 * @param box
	 */
	private void decreaseDurationMinutes(TextBox box) {
		String boxText = removeDoublePoint(box.getText());
		String hourString = removeLeadingNull(boxText.substring(0, 2));
		String minuteString = removeLeadingNull(boxText.substring(2, boxText.length()));

		int hours = Integer.parseInt(hourString);
		int minutes = Integer.parseInt(minuteString);

		if (hours > 0) {
			if (minutes > 0) {
				minutes--;
			} else {
				hours--;
				minutes = 59;
			}
		} else {
			if (minutes > 1) {
				hours = 0;
				minutes--;
			} else {
				hours = 0;
				minutes = 1;
			}
		}
		hourString = "" + hours;
		minuteString = "" + minutes;
		hourString =

		addLeadingNull(hourString);
		minuteString = addLeadingNull(minuteString);
		box.setText(addDoublePoint(hourString + minuteString));

	}

	/**
	 * Decrease the pause or duration hours.
	 * 
	 * @param box
	 */
	private void decreasePauseDurationHours(TextBox box) {
		String boxText = removeDoublePoint(box.getText());
		String hourString = removeLeadingNull(boxText.substring(0, 2));
		String minuteString = boxText.substring(2, boxText.length());
		int hours = Integer.parseInt(hourString);
		if (hours > 0) {
			hours--;
		} else {
			hours = 0;
		}
		hourString = "" + hours;
		hourString = addLeadingNull(hourString);
		box.setText(addDoublePoint(hourString + minuteString));
	}

	/**
	 * decrease the date of dateBox if the decreased time is between 23.00 and
	 * 23.59.
	 * 
	 * @param box
	 * @param dateBox
	 */
	private void decreaseDateTimeDownHour(TextBox box, TextBox dateBox) {
		int boxTime = createTimeFromText(box.getText());
		if (boxTime <= 1439 && boxTime >= 1380) {
			decreaseDate(dateBox);
		}
	}

	/**
	 * decrease the date if the decreased time is 23.59.
	 * 
	 * @param box
	 * @param dateBox
	 */
	private void decreaseDateTimeDownMinute(TextBox box, TextBox dateBox) {
		int boxTime = createTimeFromText(box.getText());
		if (boxTime == 1439) {
			decreaseDate(dateBox);
		}
	}

	/**
	 * increase the date if the increased time is 00:00.
	 * 
	 * @param box
	 * @param dateBox
	 */
	private void increaseDateTimeUpMinute(TextBox box, TextBox dateBox) {
		int boxTime = createTimeFromText(box.getText());
		if (boxTime == 0) {
			increaseDate(dateBox);
		}
	}

	/**
	 * increase the date if the increased time is between 00:01 and 01:00.
	 * 
	 * @param box
	 * @param dateBox
	 */
	private void increaseDateTimeUpHour(TextBox box, TextBox dateBox) {
		int boxTime = createTimeFromText(box.getText());
		if (boxTime < 60 && boxTime >= 0) {
			increaseDate(dateBox);
		}
	}

	public void makeCopyDeleteButtonsVisible() {

	}

	public void makeCopyDeleteButtonsHidden() {

	}
}
