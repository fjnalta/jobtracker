package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.html.Text;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
	Text month;

	@UiField
	Text day;

	@UiField
	Text kw;

	private static CalendarTimeLineWidgetUiBinder uiBinder = GWT.create(CalendarTimeLineWidgetUiBinder.class);

	interface CalendarTimeLineWidgetUiBinder extends UiBinder<Widget, CalendarTimeLineWidget> {

	}

	public CalendarTimeLineWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		handler.addObserver(this);
		setDayDate("29");
		setMonthDate("April");
		setKalenderWeek("16");
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

	public void setKalenderWeek(String week) {
		kw.setText(week);
	}

	public void setMonthDate(String monthDate) {
		month.setText(monthDate);
	}

	public void setDayDate(String dayDate) {
		day.setText(dayDate);
	}

	
	
	
}
