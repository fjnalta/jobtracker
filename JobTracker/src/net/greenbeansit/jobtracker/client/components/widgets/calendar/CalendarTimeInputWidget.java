package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.CalendarObserver;
import net.greenbeansit.jobtracker.shared.ActivityReport;

/**
 * The Calendar time input from keyboard
 * 
 * @author Jonathan
 *
 */
public class CalendarTimeInputWidget extends Composite implements CalendarObserver {

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

	private void increaseEvent(TextBox box) {
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

	private void decreaseEvent(TextBox box) {
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
		eventEnd.setText(addDoublePoint(calendarHandler.events.endTime));
		eventStart.setText(addDoublePoint(calendarHandler.events.startTime));
		pause.setText(calendarHandler.events.pause);
		workTime.setText(calendarHandler.events.workTime);
	}

	@Override
	public void notifyHandler() {
		calendarHandler.events.endTime = eventEnd.getText();
		calendarHandler.events.startTime = eventStart.getText();
		calendarHandler.events.pause = pause.getText();
		calendarHandler.events.workTime = workTime.getText();
		calendarHandler.updateObserver(this);
	}

	private String removeDoublePoint(String input) {
		return input = input.replace(":", "");
	}

	public String addDoublePoint(String input) {
		return input = input.substring(0, 2) + ":" + input.substring(2, input.length());
	}

}
