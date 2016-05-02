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

import net.greenbeansit.jobtracker.client.components.CalendarObserver;

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
	
	@UiField
	Button buttonBuchen;

	public CalendarTimeInputWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		handler.addObserver(this);
		buttonUpStart.setIcon(IconType.ARROW_UP);
		buttonDownStart.setIcon(IconType.ARROW_DOWN);
		buttonUpEnd.setIcon(IconType.ARROW_UP);
		buttonDownEnd.setIcon(IconType.ARROW_DOWN);
	}

	@UiHandler("buttonUpStart")
	public void onClickUpStart(ClickEvent event) {
		increaseEvent(eventStart);
		notifyHandler();
	}

	@UiHandler("buttonDownStart")
	public void onClickDownStart(ClickEvent event) {
		decreaseEvent(eventStart);
		notifyHandler();
	}

	@UiHandler("buttonUpEnd")
	public void onClickUpEnd(ClickEvent event) {
		increaseEvent(eventEnd);
		notifyHandler();
	}

	@UiHandler("buttonDownEnd")
	public void onClickDownEnd(ClickEvent event) {
		decreaseEvent(eventEnd);
		notifyHandler();
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
		if(workTime.getText().length() > 0){
			workTimeIsEntered();
		}else{
			enableFields();
		}
		notifyHandler();
	}

	private void increaseEvent(TextBox box) {
		String boxText = removeDoublePoint(box.getText());
		String hourString = removeLeadingNull(boxText.substring(0, 2));
		String minuteString = removeLeadingNull(boxText.substring(2, boxText.length()));
		
		
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
		
		box.setText(addDoublePoint(hourString + minuteString));
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
		String boxText = removeDoublePoint(box.getText());
		String hourString = removeLeadingNull(boxText.substring(0, 2));
		String minuteString = removeLeadingNull(boxText.substring(2, boxText.length()));
		
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
	
	private void workTimeIsEntered(){
		buttonDownEnd.setEnabled(false);
		buttonDownStart.setEnabled(false);
		buttonUpEnd.setEnabled(false);
		buttonUpStart.setEnabled(false);
		eventEnd.setEnabled(false);
	}
	
	private void enableFields(){
		buttonDownEnd.setEnabled(true);
		buttonDownStart.setEnabled(true);
		buttonUpEnd.setEnabled(true);
		buttonUpStart.setEnabled(true);
		eventEnd.setEnabled(true);
	}
	
	private String calculateNewEnd(){
		String startTime = addDoublePoint(eventStart.getText());
		String workString = addDoublePoint(workTime.getText());
//		int startTimeHour
//		int startTimeMinute
//		int work
		return null;
	}

	
	
	@Override
	public void update() {
		eventEnd.setText(addDoublePoint(handler.events.endTime));
		eventStart.setText(addDoublePoint(handler.events.startTime));
		pause.setText(handler.events.pause);
		workTime.setText(handler.events.workTime);
		Window.alert("Start:" + handler.events.startTime + " End:" + handler.events.endTime);
	}

	@Override
	public void notifyHandler() {
		handler.events.endTime = eventEnd.getText();
		handler.events.startTime = eventStart.getText();
		handler.events.pause = pause.getText();
		handler.events.workTime = workTime.getText();
		handler.updateObserver(this);
	}
	
	private String removeDoublePoint(String input){
		return input = input.replace(":", "");
	}
	
	public String addDoublePoint(String input){
		return input = input.substring(0, 2) + ":" + input.substring(2, input.length());
	}
}
