package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import org.gwtbootstrap3.client.ui.Label;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CalendarTimeLineWidget extends Composite {

	private static CalendarTimeLineWidgetUiBinder uiBinder = GWT.create(CalendarTimeLineWidgetUiBinder.class);

	interface CalendarTimeLineWidgetUiBinder extends UiBinder<Widget, CalendarTimeLineWidget> {
	}

	public CalendarTimeLineWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public CalendarTimeLineWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}
}
