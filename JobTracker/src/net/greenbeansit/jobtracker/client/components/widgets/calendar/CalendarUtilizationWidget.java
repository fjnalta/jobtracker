package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Text;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
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
		createNewTimeline(-7);
		calendarHandler.calendar.previous();
	}

	@UiHandler("rightButton")
	public void clickHandlerRightButton(ClickEvent e) {
		createNewTimeline(7);
		calendarHandler.calendar.next();
	}

	// Path for the css File
	private final String suffixPath = "net-greenbeansit-jobtracker-client-components-widgets-calendar-CalendarUtilizationWidget_CalendarUtilizationWidgetUiBinderImpl_GenCss_style-";

	private Date tmpDate;
	private Date calcDate;
	boolean calcUtilization = false;
	int calcMonth;
	List<VerticalPanel> list;

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
		table.removeAllRows();
		
		CalendarUtil.addDaysToDate(calcDate, days);
		tmpDate = CalendarUtil.copyDate(calcDate);
		CalendarUtil.setToFirstDayOfMonth(tmpDate);			
		Date date = new Date();
		
		//int dayNumber = calcDate.getDate();
		int monthNumber = calcDate.getMonth();
		int dayNumberColumn = 0;
		
		if(this.calcMonth != this.calcDate.getMonth()){
			this.calcUtilization = true;
			this.calcMonth = this.calcDate.getMonth();
			this.list = createBarChartList();
		}else{
			this.calcUtilization = false;
		}
		int element = 0;
		while (monthNumber == tmpDate.getMonth()) {

			table.setWidget(0, dayNumberColumn, list.get(element++));				

			Button tmp = new Button("" + (dayNumberColumn + 1) + " ");

			if (CalendarUtil.isSameDate(date, tmpDate)) {
				tmp.setStyleName(this.suffixPath + "button-Day-current");
				table.setWidget(1, dayNumberColumn, tmp);
			} else {
				tmp.setStyleName(this.suffixPath + "button-Day");
				table.setWidget(1, dayNumberColumn, tmp);
			}

			if(calcDate.getDate() <=  (dayNumberColumn+1) && (calcDate.getDate()+6) >=  (dayNumberColumn+1)){
				Label lbl = new Label("" + this.getDayName(tmpDate.getDay()));
				lbl.setStyleName(this.suffixPath + "label-week");
				table.setWidget(2, dayNumberColumn, lbl);
			}else{
				table.setText(2, dayNumberColumn, "" + this.getDayName(tmpDate.getDay()));
			}
			CalendarUtil.addDaysToDate(tmpDate, 1);

			dayNumberColumn++;
		}
		// set to the old month!!!
		CalendarUtil.addMonthsToDate(tmpDate, -1);
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
	private List<VerticalPanel> createBarChartList(){
		
		List<VerticalPanel> list = new ArrayList<VerticalPanel>();
		
		for (int element = 0; element <= 32; element++) {
			
			list.add(getBarChart(null));
		}
		
		return list;
	}

	/**
	 * get the barChart height of a day
	 * 
	 * @param date
	 * @return
	 */
	private double getBarChartHeight(Date date) {

		return Math.random() * 50;
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

}
