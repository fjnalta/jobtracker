package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Observer;

import org.gwtbootstrap3.client.ui.Button;
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
	TextBox pause;

	@UiField
	TextBox workTime;

	public CalendarTimeInputWidget() {
		initWidget(uiBinder.createAndBindUi(this));

		buttonUpStart.setIcon(IconType.ARROW_UP);
		buttonDownStart.setIcon(IconType.ARROW_DOWN);
		buttonUpEnd.setIcon(IconType.ARROW_UP);
		buttonDownEnd.setIcon(IconType.ARROW_DOWN);
	}

	@UiHandler("buttonUpStart")
	public void onClickUpStart(ClickEvent event) {

	}

	@UiHandler("buttonDownStart")
	public void onClickDownStart(ClickEvent event) {

	}

	@UiHandler("buttonUpEnd")
	public void onClickUpEnd(ClickEvent event) {

	}

	@UiHandler("buttonDownEnd")
	public void onClickDownEnd(ClickEvent event) {

	}

	@UiHandler("eventStart")
	public void keyPressedEventStart(KeyPressEvent event) {
		if (verifyTextInput(event))
			event.preventDefault();
	}

	@UiHandler("eventEnd")
	public void keyPressedEventEnd(KeyPressEvent event) {

	}

	@UiHandler("pause")
	public void keyPressedPause(KeyPressEvent event) {

	}
	@UiHandler("workTime")
	public void keyPressedWorkTime(KeyPressEvent event) {

	}
	private boolean verifyTextInput(KeyPressEvent event) {
		String input = String.valueOf(event.getCharCode());
		if (!input.matches("[0-9]*") || (input.length() > 3))
			return true;
		return false;
	}
}
