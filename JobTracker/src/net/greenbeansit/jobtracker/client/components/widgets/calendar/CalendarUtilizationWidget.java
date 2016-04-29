package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.constants.IconType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;

/**
 * Shows the Utilization per Day of the month above the Calendar
 * 
 * @author ahmed
 *
 */
public class CalendarUtilizationWidget extends Composite {

	private static CalendarUtilizationWidgetUiBinder uiBinder = GWT.create(CalendarUtilizationWidgetUiBinder.class);

	interface CalendarUtilizationWidgetUiBinder extends UiBinder<Widget, CalendarUtilizationWidget> {
	}

	@UiField
	FlexTable table;

	@UiField
	Button leftButton;

	@UiField
	Button rightButton;

	@UiHandler("leftButton")
	public void clickHandlerLeftButton(ClickEvent e) {
		createNewTimeline(-1);

	}

	@UiHandler("rightButton")
	public void clickHandlerRightButton(ClickEvent e) {
		createNewTimeline(1);
	}

	Date date;

	public CalendarUtilizationWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		date = new Date();
		// table.setStylePrimaryName("fontColor");

		leftButton.setIcon(IconType.ARROW_LEFT);
		rightButton.setIcon(IconType.ARROW_RIGHT);
		createNewTimeline(0);
	}

	/**
	 * This method create a new TimeLine.
	 * 
	 * @param month
	 *            the month which is choosen, e.g. 1 means the next month, -1
	 *            previous month
	 */
	@SuppressWarnings("deprecation")
	public void createNewTimeline(int month) {

		CalendarUtil.addMonthsToDate(date, month);
		CalendarUtil.setToFirstDayOfMonth(date);

		int monthNumber = date.getMonth();
		int dayNumber = 0;
		table.removeAllRows();
		Date today = new Date();
		while (monthNumber == date.getMonth()) {
			table.setText(0, dayNumber, ("" + (dayNumber + 1)));

			if (CalendarUtil.isSameDate(today, date)) {

				table.setCellPadding(0);
				table.setText(1, dayNumber, "<" + this.getDayName(date.getDay()) + ">");
			} else {
				//table.setStyleName(" fontColor");
				table.setText(1, dayNumber, "" + this.getDayName(date.getDay()));
			}
			CalendarUtil.addDaysToDate(date, 1);

			dayNumber++;
		}
		//set to the old month!!!
		CalendarUtil.addMonthsToDate(date, -1);
	}

	/**
	 * Get the name of the day by the number. 0: Sunday... 6: Saturday
	 * 
	 * @param day
	 * @return
	 */
	private String getDayName(int day) {

		String dayName = "";

		switch (day) {
		case 0:
			dayName = "So";
			break;
		case 1:
			dayName = "Mo";
			break;
		case 2:
			dayName = "Di";
			break;
		case 3:
			dayName = "Mi";
			break;
		case 4:
			dayName = "Do";
			break;
		case 5:
			dayName = "Fr";
			break;
		case 6:
			dayName = "Sa";
			break;
		}

		return dayName;
	}

}
