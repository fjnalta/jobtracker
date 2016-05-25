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

	@UiHandler("buttonBook")
	public void clickButtonBook(ClickEvent e) {

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
			eventEnd.setText(calculateNewEnd());
		}
		notifyHandler();
	}
	
	@UiHandler("buttonTimeHourUpStart")
	public void clickButtonTimeHourUpStart(ClickEvent e) {
		increaseEventHours(eventStart);
		notifyHandler();
	}

	@UiHandler("buttonTimeHourDownStart")
	public void clickButtonTimeHourDownStart(ClickEvent e) {
		decreaseEventHours(eventStart);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteUpStart")
	public void clickButtonTimeMinuteUpStart(ClickEvent e) {
		increaseEventMinutes(eventStart);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteDownStart")
	public void clickButtonMinuteDownStart(ClickEvent e) {
		decreaseEventMinutes(eventStart);
		notifyHandler();
	}

	@UiHandler("buttonTimeHourUpEnd")
	public void clickButtonTimeHourUpEnd(ClickEvent e) {
		increaseEventHours(eventEnd);
		notifyHandler();
	}

	@UiHandler("buttonTimeHourDownEnd")
	public void clickButtonTimeHourDownEnd(ClickEvent e) {
		decreaseEventHours(eventEnd);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteUpEnd")
	public void clickButtonTimeMinuteUpEnd(ClickEvent e) {
		increaseEventMinutes(eventEnd);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteDownEnd")
	public void clickButtonMinuteDownEnd(ClickEvent e) {
		decreaseEventMinutes(eventEnd);
		notifyHandler();
	}

	@UiHandler("buttonTimeHourUpPause")
	public void clickButtonTimeHourUpPause(ClickEvent e) {
		increaseEventHours(pause);
		notifyHandler();
	}

	@UiHandler("buttonTimeHourDownPause")
	public void clickButtonTimeHourDownPause(ClickEvent e) {
		decreaseEventHours(pause);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteUpPause")
	public void clickButtonTimeMinuteUpPause(ClickEvent e) {
		increaseEventMinutes(pause);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteDownPause")
	public void clickButtonMinuteDownPause(ClickEvent e) {
		decreaseEventMinutes(pause);
		notifyHandler();
	}

	@UiHandler("buttonTimeHourUpDuration")
	public void clickButtonTimeHourUpDuration(ClickEvent e) {
		increaseEventHours(workTime);
		notifyHandler();
	}

	@UiHandler("buttonTimeHourDownDuration")
	public void clickButtonTimeHourDownDuration(ClickEvent e) {
		decreaseEventHours(workTime);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteUpDuration")
	public void clickButtonTimeMinuteUpDuration(ClickEvent e) {
		increaseEventMinutes(workTime);
		notifyHandler();
	}

	@UiHandler("buttonTimeMinuteDownDuration")
	public void clickButtonTimeMinuteDownDuration(ClickEvent e) {
		decreaseEventMinutes(workTime);
		notifyHandler();
	}
	
	@UiHandler("buttonUpDateStart")
	public void buttonUpDateStartClicked(ClickEvent e){
		increaseDate(dateStart);
		notifyHandler();
	}
	
	@UiHandler("buttonDownDateStart")
	public void buttonDownDateStartClicked(ClickEvent e){
		decreaseDate(dateStart);
		notifyHandler();
	}
	
	@UiHandler("buttonDownDateEnd")
	public void buttonDownDateEndClicked(ClickEvent e){
		decreaseDate(dateEnd);
		notifyHandler();
	}
	
	@UiHandler("buttonUpDateEnd")
	public void buttonUpDateEndClicked(ClickEvent e){
		increaseDate(dateEnd);
		notifyHandler();
	}
	
	@UiHandler("buttonBook")
	public void buttonBookClicked(ClickEvent e){
		int startTime = createTimeFromText(eventStart.getText());
		int duration = createTimeFromText(workTime.getText());
		int breakTime = createTimeFromText(pause.getText());
		Date date = getDateFromBox(dateStart);
		ActivityReport tmp = new ActivityReport(0, 0, 0, 0, 0,"", date, startTime, duration, breakTime);
		handler.saveReport(tmp);
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

	private String addLeadingNull(String sign) {
		if (sign.length() < 2) {
			return (0 + sign);
		} else {
			return sign;
		}
	}

	private String removeLeadingNull(String sign) {
		if (sign.startsWith("0") && !(sign.equals("00"))) {
			return sign.replace("0", "");
		} else {
			return sign;
		}
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

	private String calculateNewEnd() {
		String startTime = removeDoublePoint(eventStart.getText());
		String workString = removeDoublePoint(workTime.getText());
		int hours = (getHours(startTime) + getHours(workString));
		int minutes = (getMinutes(startTime) + getMinutes(workString));
		if (hours > 23) {
			hours = hours % 24;
		}
		if (minutes > 59) {
			minutes = minutes % 60;
		}
		return addDoublePoint(addLeadingNull("" + hours) + addLeadingNull("" + minutes));
	}

	private int getHours(String time) {
		return Integer.parseInt(time.substring(0, 2));
	}

	private int getMinutes(String time) {
		return Integer.parseInt(time.substring(2, time.length()));
	}

	@Override
	public void update() {
		dateStart.setText(dateParser(calendarHandler.calendar.currentEvent.getISOStart()));
		dateEnd.setText(dateParser(calendarHandler.calendar.currentEvent.getISOEnd()));
		eventStart.setText(timeParser(calendarHandler.calendar.currentEvent.getISOStart()));
		eventEnd.setText(timeParser(calendarHandler.calendar.currentEvent.getISOEnd()));
		pause.setText("00:00");
		workTime.setText(calculateDuration());
	}

	private Date getDateFromTextBox(TextBox box) {
		String text = box.getText();
		int day = Integer.parseInt(text.substring(0, 2));
		int month = Integer.parseInt(text.substring(3, text.length()));
		return new Date(2016, month, day);
	}

	@Override
	public void notifyHandler() {
		Window.alert(getDateFromBox(dateStart).toString());
		calendarHandler.calendar.removeEvent(calendarHandler.calendar.currentEvent.getId());
		
		ActivityReportEvent e = new ActivityReportEvent(calendarHandler.calendar.currentEvent.getAp(),calendarHandler.calendar.currentEvent.getId(),
				calendarHandler.calendar.currentEvent.getTitle(), true, true, true);
		
		e.setStart(calendarHandler.getISO8601StringForDate(getDateFromTextBox(dateStart),
				createTimeFromText(eventStart.getText())));
		
		e.setEnd(calendarHandler.getISO8601StringForDate(getDateFromTextBox(dateEnd), createTimeFromText(eventEnd.getText())));
		
		calendarHandler.calendar.currentEvent = e;
		calendarHandler.calendar.addEvent(calendarHandler.calendar.currentEvent);
		calendarHandler.updateObserver(this);
	}

	private String removeDoublePoint(String input) {
		return input = input.replace(":", "");
	}

	public String addDoublePoint(String input) {
		return input = input.substring(0, 2) + ":" + input.substring(2, input.length());
	}

	/**
	 * 
	 * @param date
	 * @return String hh:mm
	 */
	private String timeParser(String date) {
		String[] temp = date.split("T");
		String[] temp2 = temp[1].split(":");
		return addDoublePoint(temp2[0] + temp2[1]);
	}

	private String dateParser(String date2) {
		String[] temp = date2.split("T");
		String[] temp2 = temp[0].split("-");
		return temp2[2] + "." + temp2[1];
	}

	private String calculateDuration() {
		int start = createTimeFromText(eventStart.getText());
		int end = createTimeFromText(eventEnd.getText());
		int duration = end - start;
		return addLeadingNull(duration/60+"") +":" +addLeadingNull("" + duration%60);
	}

		
	
	private int createTimeFromText(String time) {
		String[] temp2 = time.split(":");
		int hours = Integer.parseInt(temp2[0]);
		int minutes = Integer.parseInt(temp2[1]);
		return (60 * hours) + minutes;
	}


	@Override
	public void updateObservable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyLogicHandler() {
		// TODO Auto-generated method stub
		
	}
	

	private Date getDateFromBox(TextBox dateTextBox){
		int year = 116;
		String[] split = dateTextBox.getText().split("\\.");
		int month = Integer.parseInt(split[1]);
		int day = Integer.parseInt(split[0]);
		return new Date(year,month,day);
	}
	
	public void increaseDate(TextBox dateTextBox){
		Date dt = getDateFromBox(dateTextBox);
		dt.setDate(dt.getDate()+1);
		int month = dt.getMonth() ;
		dateTextBox.setText(dt.getDate() + "." + addLeadingNull(month+""));
	}
	
	public String decreaseDate(TextBox dateTextBox){
		return null;
	}
	
}
