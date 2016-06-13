package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;

import net.greenbeansit.jobtracker.client.components.CalendarObserver;
import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.client.localization.HomePageConstants;

/**
 * Shows the Utilization per Day of the month above the Calendar
 * 
 * @author ahmed
 *
 */
public class CalendarUtilizationWidget extends Composite implements CalendarObserver, LogicObservable {

	private static CalendarUtilizationWidgetUiBinder uiBinder = GWT.create(CalendarUtilizationWidgetUiBinder.class);

	private static HomePageConstants constants = GWT.create(HomePageConstants.class);

	interface CalendarUtilizationWidgetUiBinder extends UiBinder<Widget, CalendarUtilizationWidget> {
	}

	// Path for the css File
	private final String 						SUFFIXPATH = "net-greenbeansit-jobtracker-client-components-widgets-calendar-CalendarUtilizationWidget_CalendarUtilizationWidgetUiBinderImpl_GenCss_style-";
	// This date iterate to every day of month
	private Date 								iteratorDate;
	// This date is needed for the current week view
	private Date 								currentWeekFirstDayDate;
	// Utilization should new calculate if it's yes
	private ViewOption 							view;
	// Is needed for change the Calendar time line
	private int 								changeDay, changeMonth;

	private List<VerticalPanel> 				list;

	private List<Integer> 						utilizationList;

	private boolean 							calculateUtilization;

	@UiField
	FlexTable 									table;

	@UiField
	Button 										leftButton;

	@UiField
	Button 										rightButton;

	/**
	 * When this button is clicked, the timeline will change to 1 week or month
	 * previous
	 * 
	 * @param e
	 *            the ClickEvent.
	 */
	@SuppressWarnings("deprecation")
	@UiHandler("leftButton")
	public void clickHandlerLeftButton(ClickEvent e) {

		if (view.toString().equals("agendaWeek")) {

			this.setTime(-7, 0);

			CalendarUtil.addDaysToDate(this.currentWeekFirstDayDate, this.changeDay);

		} else {

			this.setTime(0, -1);

			configMonthView();
		}

		setCalculateUtilization(true);

		handler.loadUtilization(this.currentWeekFirstDayDate.getYear() + 1900,
				this.currentWeekFirstDayDate.getMonth() + 1);

		calendarHandler.calendar.previous();
		
		notifyHandler();
	}

	/**
	 * When this button is clicked, the timeline will change to 1 week or month
	 * next
	 * 
	 * @param e
	 *            the ClickEvent.
	 */
	@SuppressWarnings("deprecation")
	@UiHandler("rightButton")
	public void clickHandlerRightButton(ClickEvent e) {

		if (view.toString().equals("agendaWeek")) {

			this.setTime(7, 0);

			CalendarUtil.addDaysToDate(this.currentWeekFirstDayDate, this.changeDay);

		} else {

			this.setTime(0, 1);

			configMonthView();
		}

		setCalculateUtilization(true);

		handler.loadUtilization(this.currentWeekFirstDayDate.getYear() + 1900,
				this.currentWeekFirstDayDate.getMonth() + 1);

		calendarHandler.calendar.next();
		notifyHandler();
	}

	/**
	 * Change the timeline in the month view
	 */
	private void configMonthView() {

		CalendarUtil.addMonthsToDate(this.currentWeekFirstDayDate, this.changeMonth);

		CalendarUtil.setToFirstDayOfMonth(this.currentWeekFirstDayDate);

		this.getFirstDayOfWeek();
	}

	/**
	 * Returns the calculateUtilization state
	 * 
	 * @param calculateUtilization
	 *            true or false
	 */
	public void setCalculateUtilization(boolean calculateUtilization) {
		this.calculateUtilization = calculateUtilization;
	}

	/**
	 * Contructor
	 */
	@SuppressWarnings("deprecation")
	public CalendarUtilizationWidget() {
		initWidget(uiBinder.createAndBindUi(this));

		handler.addObservable(this);

		calendarHandler.addObserver(this);

		this.setCalculateUtilization(true);

		this.currentWeekFirstDayDate = getFirstDayOfActualWeek();

		this.view = ViewOption.agendaWeek;

		this.leftButton.setIcon(IconType.ARROW_LEFT);

		this.rightButton.setIcon(IconType.ARROW_RIGHT);

		this.setTime(0, 0);

		
		
		handler.loadUtilization(currentWeekFirstDayDate.getYear() + 1900, currentWeekFirstDayDate.getMonth() + 1);

	}

	/**
	 * Set the time for creating the table.
	 * 
	 * @param day
	 *            the Day
	 * @param month
	 *            the Month
	 */
	private void setTime(int day, int month) {
		this.changeDay = day;

		this.changeMonth = month;
	}

	/**
	 * This method create a new TimeLine. There are three rows The first show
	 * the utilization from the day or month The second the day number of the
	 * month or the month The third show the day name and the current week /
	 * month
	 * 
	 */
	@SuppressWarnings("deprecation")
	private void createNewTimeline() {
		this.table.removeAllRows();

		this.iteratorDate = CalendarUtil.copyDate(currentWeekFirstDayDate);

		CalendarUtil.setToFirstDayOfMonth(this.iteratorDate);

		int monthNumber = currentWeekFirstDayDate.getMonth();

		int dayNumberColumn = 0;

		int element = 0;

		while (monthNumber == this.iteratorDate.getMonth()) {

			createFirstRow(dayNumberColumn, element++);

			createSecondRow(dayNumberColumn);

			createThirdRow(dayNumberColumn++);

			CalendarUtil.addDaysToDate(this.iteratorDate, 1);
		}
	}

	/**
	 * Create the first Row from the Table
	 * 
	 * @param dayNumberColumn
	 *            the current column
	 * @param element
	 *            the element.
	 */
	private void createFirstRow(int dayNumberColumn, int element) {

		this.table.getRowFormatter().addStyleName(0,this.SUFFIXPATH + "tableHeight" );
		this.table.setWidget(0, dayNumberColumn, list.get(element));
	}

	/**
	 * Create the second Row from the Table
	 * 
	 * @param dayNumberColumn
	 *            the current column
	 */
	private void createSecondRow(int dayNumberColumn) {
		final UButton dayBtn = new UButton((dayNumberColumn + 1) + "", CalendarUtil.copyDate(this.iteratorDate),
				this.currentWeekFirstDayDate, this);

		Date date = new Date();
		if (CalendarUtil.isSameDate(date, this.iteratorDate)) {

			dayBtn.setStyleName(this.SUFFIXPATH + "button-Day-current");

			this.table.setWidget(1, dayNumberColumn, dayBtn);

		} else {

			dayBtn.setStyleName(this.SUFFIXPATH + "button-Day");

			this.table.setWidget(1, dayNumberColumn, dayBtn);

		}
	}

	/**
	 * Create the third Row from the Table
	 * 
	 * @param dayNumberColumn
	 *            the current column
	 */
	@SuppressWarnings("deprecation")
	private void createThirdRow(int dayNumberColumn) {

		// this.table.removeRow(3);

		Label lbl = new Label("" + this.getDayName(iteratorDate.getDay()));

		if (this.currentWeekFirstDayDate.getDate() <= (dayNumberColumn + 1)
				&& (this.currentWeekFirstDayDate.getDate() + 6) >= (dayNumberColumn + 1)) {

			lbl.setStyleName(this.SUFFIXPATH + "label-week");

			this.table.setWidget(2, dayNumberColumn, lbl);

		} else {

			lbl.setStyleName(this.SUFFIXPATH + "label-not-week");

			this.table.setWidget(2, dayNumberColumn, lbl);
		}
	}

	/**
	 * Get the name of the day by the number. 0: Sunday... 6: Saturday
	 * 
	 * @param day
	 *            the Day
	 * @return the Name of the Day.
	 */
	private String getDayName(int day) {

		if (day >= 0 && day < 7)
			return constants.dayNames()[day];
		else
			return "";
	}

	/**
	 * Get the BarChart for a day
	 * 
	 * @param number
	 *            set the height of the Bar
	 * @return an new BarChart
	 */
	private VerticalPanel getBarChart(double number) {

		VerticalPanel inner = new VerticalPanel();
		int maxHeigh = 70;
		
		// For empty Days
		if (number < 1) {
			number = 7;
		}
		if (number > maxHeigh) {
			number = maxHeigh;
		}
		
		inner.setHeight(this.getHeightInPercent(maxHeigh, number) + "px");

		inner.setStyleName(this.SUFFIXPATH + "barChart", true);

		return inner;
	}
	
	/**
	* Get percent for the given parameters
	 * @param max stand for 100%
	 * @param number from this will calculate the percent of max
	 * @return 
	 */
	private double getHeightInPercent(double max, double number){
		
		return ( ( max / 100) * number ) ;
	}

	/**
	 * Update the List with the new Utilization
	 *
	 * @param u
	 *            the Utilization List
	 * @return the Bar chart List
	 */
	private List<VerticalPanel> createBarChartList(List<Integer> u) {

		List<VerticalPanel> list = new ArrayList<VerticalPanel>();

		int n = u.size();

		for (int element = 1; element < n; element++) {

			list.add(getBarChart(Math.abs(u.get(element).intValue())));

		}

		return list;
	}

	/**
	 * Get an Date, which is set to the first day of the current week (Sunday)
	 * 
	 * @return the first Day.
	 */
	@SuppressWarnings("deprecation")
	private Date getFirstDayOfActualWeek() {

		Date tmp = new Date();

		while (tmp.getDay() > 1 || tmp.getDay() == 0) {

			CalendarUtil.addDaysToDate(tmp, -1);

		}

		return tmp;
	}
	
	/**
	 * Gets the first day of the week which is pointing by currentWeekFirstDayDate
	 * 
	 * @return the first Day of the week.
	 */
	@SuppressWarnings("deprecation")
	private Date getFirstDayOfWeek() {

		Date tmp = CalendarUtil.copyDate(this.currentWeekFirstDayDate);

		while (tmp.getDay() > 1 || tmp.getDay() == 0) {

			CalendarUtil.addDaysToDate(tmp, -1);

		}

		return tmp;
	}
	/**
	 * Creates a new TimeLine.
	 */
	public void render() {

		createNewTimeline();

		setTime(0, 0);
	}

	@Override
	public void update() {

		this.view = calendarHandler.calendar.getCurrentView();

		// this.setTime(0, 0);
		// handler.loadUtilization(this.currentWeekFirstDayDate.getYear() +
		// 1900, this.currentWeekFirstDayDate.getMonth() + 1);
	}

	@Override
	public void notifyHandler() {

		calendarHandler.setDisplayMonth(this.currentWeekFirstDayDate.getMonth());
		//GWT.log("___"+this.currentWeekFirstDayDate.getMonth()+1);
		
		calendarHandler.setDisplayYear(this.currentWeekFirstDayDate.getYear());
		//GWT.log("___"+this.currentWeekFirstDayDate.getYear());
		calendarHandler.updateObserver(this);

	}

	@Override
	public void updateObservable() {

		this.utilizationList = handler.getUtilizationList();

		if (utilizationList.size() != 0) {

			list = createBarChartList(utilizationList);

			this.setCalculateUtilization(false);

			render();
		}

	}

	@Override
	public void notifyLogicHandler() {
	}
}