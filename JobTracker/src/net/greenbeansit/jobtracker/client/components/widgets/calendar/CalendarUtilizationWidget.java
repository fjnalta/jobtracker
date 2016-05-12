package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.IconType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;

import net.greenbeansit.jobtracker.client.components.CalendarObserver;

/**
 * Shows the Utilization per Day of the month above the Calendar
 * 
 * @author ahmed
 *
 */
public class CalendarUtilizationWidget extends Composite implements CalendarObserver {

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
		calendarHandler.calendar.previous();
	}

	@UiHandler("rightButton")
	public void clickHandlerRightButton(ClickEvent e) {
		createNewTimeline(1);
		calendarHandler.calendar.next();
	}

	// Path for the css File
	final String suffixPath = "net-greenbeansit-jobtracker-client-components-widgets-calendar-CalendarUtilizationWidget_CalendarUtilizationWidgetUiBinderImpl_GenCss_style-";

	Date currentDate;

	public CalendarUtilizationWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		currentDate = new Date();
		calendarHandler.addObserver(this);
		leftButton.setIcon(IconType.ARROW_LEFT);
		rightButton.setIcon(IconType.ARROW_RIGHT);

		createNewTimeline(0);
	}

	/**
	 * This method create a new TimeLine.
	 * 
	 * @param newMonth
	 *            the month which is choosen, e.g. 1 means the next month, -1
	 *            the previous month
	 */
	@SuppressWarnings("deprecation")
	public void createNewTimeline(int newMonth) {
		table.removeAllRows();

		CalendarUtil.addMonthsToDate(currentDate, newMonth);
		CalendarUtil.setToFirstDayOfMonth(currentDate);
		int monthNumber = currentDate.getMonth();
		int dayNumberColumn = 0;
		Date date = new Date();

		while (monthNumber == currentDate.getMonth()) {
			table.setWidget(0, dayNumberColumn, getBarChart(null));

			Button tmp = new Button("" + (dayNumberColumn + 1) + " ");

			if (CalendarUtil.isSameDate(date, currentDate)) {
				tmp.setStyleName(this.suffixPath + "button-Day-current");
				table.setWidget(1, dayNumberColumn, tmp);
			} else {
				tmp.setStyleName(this.suffixPath + "button-Day");
				table.setWidget(1, dayNumberColumn, tmp);
			}

			table.setText(2, dayNumberColumn, "" + this.getDayName(currentDate.getDay()));
			CalendarUtil.addDaysToDate(currentDate, 1);

			dayNumberColumn++;
		}
		// set to the old month!!!
		CalendarUtil.addMonthsToDate(currentDate, -1);
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

	/**
	 * Get the BarChart for a day
	 * 
	 * @param height
	 *            set the heigh of the Bar
	 * @param style
	 *            add css attribute so the widget
	 * @return an new BarChart
	 */
	private VerticalPanel getBarChart(Date date) {
		VerticalPanel vp = new VerticalPanel();

		double rnd = getBarChartHeight(date);

		vp.setHeight(rnd + "px");

		if (rnd < 40) {
			vp.setStyleName(this.suffixPath + "barChart", true);
		} else {
			vp.setStyleName(this.suffixPath + "barChartHeight", true);
		}
		return vp;
	}

	/**
	 * get the barChart height of a day 
	 * @param date
	 * @return
	 */
	private double getBarChartHeight(Date date) {

		return Math.random() * 50;
	}

	@Override
	public void update() {

	}

	@Override
	public void notifyHandler() {
		calendarHandler.updateObserver(this);
	}

}
