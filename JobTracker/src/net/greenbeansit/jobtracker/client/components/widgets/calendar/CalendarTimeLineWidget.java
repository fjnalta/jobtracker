package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.html.Text;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.CalendarObserver;

/**
 * 3 Round Buttons , KW KWNR MONTH DATE
 * 
 * @author jonathan
 *
 */
public class CalendarTimeLineWidget extends Composite implements CalendarObserver {

	@UiField
	Text date;

	@UiField
	Text kw;
	

	

	private static CalendarTimeLineWidgetUiBinder uiBinder = GWT.create(CalendarTimeLineWidgetUiBinder.class);

	interface CalendarTimeLineWidgetUiBinder extends UiBinder<Widget, CalendarTimeLineWidget> {

	}

	public CalendarTimeLineWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		calendarHandler.addObserver(this);
		String currentDate = createDate(new Date().toString());
		setDate(currentDate);
		setKalenderWeek("KW`16");
	}

	public CalendarTimeLineWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		calendarHandler.addObserver(this);
	}

	@Override
	public void update() {
		date.setText(createDate(calendarHandler.events.eventDate.toString()));
	}

	@Override
	public void notifyHandler() {
		calendarHandler.updateObserver(this);
	}

	public void setKalenderWeek(String week) {
		kw.setText(week);
	}

	public void setDate(String monthDate) {
		date.setText(monthDate);
	}

	private String createDate(String date){
		return date.substring(4, 10).replace(" ", "`");
	}
	
}
