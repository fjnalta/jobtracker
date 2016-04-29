package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.CalendarObserver;

public class CalendarTimeLineWidget extends Composite implements CalendarObserver {

	private static CalendarTimeLineWidgetUiBinder uiBinder = GWT.create(CalendarTimeLineWidgetUiBinder.class);

	interface CalendarTimeLineWidgetUiBinder extends UiBinder<Widget, CalendarTimeLineWidget> {

	}

	public CalendarTimeLineWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		handler.addObserver(this);
	}

	public CalendarTimeLineWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		handler.addObserver(this);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void notifyHandler() {
		handler.updateObserver(this);
	}


}
