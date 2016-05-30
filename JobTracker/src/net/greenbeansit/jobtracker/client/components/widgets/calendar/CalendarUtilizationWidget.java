package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.constants.IconType;

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

/**
 * Shows the Utilization per Day of the month above the Calendar
 * 
 * @author ahmed
 *
 */
public class CalendarUtilizationWidget extends Composite implements CalendarObserver,LogicObservable {

	private static CalendarUtilizationWidgetUiBinder uiBinder = GWT.create(CalendarUtilizationWidgetUiBinder.class);

	interface CalendarUtilizationWidgetUiBinder extends UiBinder<Widget, CalendarUtilizationWidget> {
	}

	// Path for the css File
	private final String SUFFIXPATH = "net-greenbeansit-jobtracker-client-components-widgets-calendar-CalendarUtilizationWidget_CalendarUtilizationWidgetUiBinderImpl_GenCss_style-";

	private Date tmpDate;
	private Date calcDate;
	boolean calcUtilization = false;
	int calcMonth;
	List<VerticalPanel> list;

	@UiField
	FlexTable table;

	@UiField
	Button leftButton;

	@UiField
	Button rightButton;

	@UiHandler("leftButton")
	public void clickHandlerLeftButton(ClickEvent e) {
		createNewTimeline(-7);
		calendarHandler.calendar.previous();
	}

	@UiHandler("rightButton")
	public void clickHandlerRightButton(ClickEvent e) {
		createNewTimeline(7);
		calendarHandler.calendar.next();
	}

	@SuppressWarnings("deprecation")
	public CalendarUtilizationWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		this.tmpDate = new Date();
		this.calcDate = getFirstDayOfWeek();
		this.calcMonth = -1;
		calendarHandler.addObserver(this);
		this.leftButton.setIcon(IconType.ARROW_LEFT);
		this.rightButton.setIcon(IconType.ARROW_RIGHT);
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
	private void createNewTimeline(int days) {
		this.table.removeAllRows();

		CalendarUtil.addDaysToDate(calcDate, days);
		this.tmpDate = CalendarUtil.copyDate(calcDate);
		CalendarUtil.setToFirstDayOfMonth(this.tmpDate);

		Date date = new Date();
		int monthNumber = calcDate.getMonth();
		int dayNumberColumn = 0;
		int element = 0;

		
		if (this.calcMonth != this.calcDate.getMonth()) {
			this.calcUtilization = true;
			this.calcMonth = this.calcDate.getMonth();
			this.list = createBarChartList();
		} else {
			this.calcUtilization = false;
		}

		while (monthNumber == this.tmpDate.getMonth()) {

			createFirstRow(dayNumberColumn, element++);
			createSecondRow(dayNumberColumn, date);
			createThirdRow(dayNumberColumn++);
			CalendarUtil.addDaysToDate(this.tmpDate, 1);
			
		}
	}

	private void createFirstRow(int dayNumberColumn,int element) {
		this.table.setWidget(0, dayNumberColumn, list.get(element));
	}

	private void createSecondRow(int dayNumberColumn, Date date) {
		UButton tmpBtn = new UButton((dayNumberColumn + 1) + "", CalendarUtil.copyDate(this.tmpDate));
		
		tmpBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				// TODO Calendar Jumo to the Day
			}
		});
		
		if (CalendarUtil.isSameDate(date, this.tmpDate)) {
			tmpBtn.setStyleName(this.SUFFIXPATH + "button-Day-current");
			this.table.setWidget(1, dayNumberColumn, tmpBtn);
		} else {
			tmpBtn.setStyleName(this.SUFFIXPATH + "button-Day");
			this.table.setWidget(1, dayNumberColumn, tmpBtn);
		}
	}
	
	@SuppressWarnings("deprecation")
	private void createThirdRow(int dayNumberColumn) {
		
		Label lbl = new Label("" + this.getDayName(tmpDate.getDay()));
		if (this.calcDate.getDate() <= (dayNumberColumn + 1)
				&& (this.calcDate.getDate() + 6) >= (dayNumberColumn + 1)) {
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
	private VerticalPanel getBarChart(int number) {
		VerticalPanel vp = new VerticalPanel();

		vp.setHeight((double)number + "px");

		if (number < 100) {
			vp.setStyleName(this.SUFFIXPATH + "barChart", true);
		} else {
			vp.setStyleName(this.SUFFIXPATH + "barChartHeight", true);
		}
		return vp;
	}

	private List<VerticalPanel> createBarChartList() {

		//List<Integer> utilizationList = handler.get();
		
		List<VerticalPanel> list = new ArrayList<VerticalPanel>();

		for (int element = 0; element <= 32; element++) {

		//	list.add(getBarChart(utilizationList.get(index)));

		}

		return list;
	}



	/**
	 * Get an Date, which is set to the first day of the current week (Sunday)
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private Date getFirstDayOfWeek() {
		Date tmp = new Date();
		while (tmp.getDay() > 0) {
			CalendarUtil.addDaysToDate(tmp, -1);
		}
		return tmp;
	}

	@Override
	public void update() {

	}

	@Override
	public void notifyHandler() {
		calendarHandler.updateObserver(this);

	}

	@Override
	public void updateObservable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyLogicHandler() {
		// TODO Auto-generated method stub
		
	}

}
