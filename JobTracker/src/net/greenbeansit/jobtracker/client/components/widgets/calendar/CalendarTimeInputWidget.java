package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Observer;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.IntegerBox;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconPosition;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;

import com.gargoylesoftware.htmlunit.javascript.host.Event;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * The Calendar time input from keyboard
 * 
 * @author Jonathan
 *
 */
public class CalendarTimeInputWidget extends Composite {

	private static CalendarTimeInputWidgetUiBinder uiBinder = GWT.create(CalendarTimeInputWidgetUiBinder.class);

	interface CalendarTimeInputWidgetUiBinder extends UiBinder<Widget, CalendarTimeInputWidget> {
	}

	@UiField
	Button buttonUpStart;
	@UiField
	Button buttonDownStart;

	@UiField
	Button buttonUpEnd;
	@UiField
	Button buttonDownEnd;

	@UiField
	TextBox eventStart;

	@UiField
	TextBox eventEnd;

	@UiField
	IntegerBox pause;

	@UiField
	IntegerBox workTime;

	public CalendarTimeInputWidget() {
		initWidget(uiBinder.createAndBindUi(this));

		buttonUpStart.setIcon(IconType.ARROW_UP);
		buttonDownStart.setIcon(IconType.ARROW_DOWN);
		buttonUpEnd.setIcon(IconType.ARROW_UP);
		buttonDownEnd.setIcon(IconType.ARROW_DOWN);
	}

	@UiHandler("buttonUpStart")
	public void onClickUpStart(ClickEvent event) {
		increaseEvent(eventStart);
	}

	@UiHandler("buttonDownStart")
	public void onClickDownStart(ClickEvent event) {
		decreaseEvent(eventStart);
	}

	@UiHandler("buttonUpEnd")
	public void onClickUpEnd(ClickEvent event) {
		increaseEvent(eventEnd);
	}

	@UiHandler("buttonDownEnd")
	public void onClickDownEnd(ClickEvent event) {
		decreaseEvent(eventEnd);
	}

	@UiHandler("eventStart")
	public void keyPressedEventStart(KeyPressEvent event) {
		String before = eventStart.getText().replace(":", "");
		eventStart.setText(before);
		inputLengthIsToLong(before, event);
		inputIsNotAnNumber(event);
		eventStart.setText(before);
	}

	@UiHandler("eventEnd")
	public void keyPressedEventEnd(KeyPressEvent event) {
		inputLengthIsToLong(eventEnd.getText(), event);
		inputIsNotAnNumber(event);
	}

	@UiHandler("pause")
	public void keyPressedPause(KeyPressEvent event) {
		inputLengthIsToLong(pause.getText(), event);
		inputIsNotAnNumber(event);
	}

	@UiHandler("workTime")
	public void keyPressedWorkTime(KeyPressEvent event) {
		inputLengthIsToLong(workTime.getText(), event);
		inputIsNotAnNumber(event);
	}

	private void increaseEvent(TextBox box) {
		String hourString = removeLeadingNull(box.getText().substring(0, 2));
		String minuteString = removeLeadingNull(box.getText().substring(2, box.getText().length()));
		
		int hours = Integer.parseInt(hourString);
		int minutes = Integer.parseInt(minuteString);
		
		if(minutes < 59){
			minutes++;
		}else{
			if(hours < 23){
				hours++;
				minutes = 0;
			}else{
				hours = 0;
				minutes = 0;
			}
		}
		hourString = "" + hours;
		minuteString = "" + minutes;
		
		hourString  = addLeadingNull(hourString);
		minuteString = addLeadingNull(minuteString);
		
		box.setText(hourString + minuteString);
	}
	
	private String addLeadingNull(String sign){
		if(sign.length()< 2){
			return (0 + sign);
		}else{
			return sign;
		}
	}
	
	private String removeLeadingNull(String sign){
		if(sign.startsWith("0") && !(sign.equals("00"))){
			return sign.replace("0", "");
		}else{
			return sign;
		}
	}

	private void decreaseEvent(TextBox box) {
		String hourString = removeLeadingNull(box.getText().substring(0, 2));
		String minuteString = removeLeadingNull(box.getText().substring(2, box.getText().length()));
		
		int hours = Integer.parseInt(hourString);
		int minutes = Integer.parseInt(minuteString);
		
		if(minutes > 0){
			minutes--;
		}else{
			if(hours > 0){
				hours--;
				minutes = 59;
			}else{
				hours = 23;
				minutes = 59;
			}
		}
		hourString = "" + hours;
		minuteString = "" + minutes;
		
		hourString  = addLeadingNull(hourString);
		minuteString = addLeadingNull(minuteString);
		
		box.setText(hourString + minuteString);
	}

	private void inputIsNotAnNumber(KeyPressEvent event) {
		String input = String.valueOf(event.getCharCode());
		if (!Character.isDigit(event.getCharCode())) {
			event.preventDefault();
		}

	}

	private void inputLengthIsToLong(String before, KeyPressEvent event) {
		// Window.alert(""+ before.length() +" " + before);
		if (before.length() > 3) {
			event.preventDefault();
		}
	}
}
