package net.greenbeansit.jobtracker.client.components.widgets;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.html.ClearFix;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.LineChart;
import com.googlecode.gwt.charts.client.corechart.LineChartOptions;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.googlecode.gwt.charts.client.corechart.PieChartOptions;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.VAxis;

import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.shared.ActivityReport;

/**
 * Widget for vizualization of the project budget
 *
 *@author Alexander Kirilyuk
 */
public class GraphWidget extends Composite implements LogicObservable {

	private static GraphWidgetUiBinder uiBinder = GWT.create(GraphWidgetUiBinder.class);

	interface GraphWidgetUiBinder extends UiBinder<Widget, GraphWidget> {
	}

	/**
	 * enum for the different view modes of the graph
	 */
	public static enum GraphMode {
		WEEK, MONTH, YEAR
	}

	/**
	 * standard constrcutor
	 */
	public GraphWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		initialize();
	}

	@UiField
	ClearFix budgetContent;

	@UiField
	ClearFix activityContent;

	@UiField
	Label labelBudget;

	@UiField
	Label labelBudgetLeft;

	@UiField
	Label labelCurrentFocus;

	@UiField
	Button buttonModeYear;

	@UiField
	Button buttonModeMonth;

	@UiField
	Button buttonModeWeek;

	@UiField
	Button buttonNext;

	@UiField
	Button buttonPrevious;

	private List<ActivityReport> reportList;

	private LineChart linechart;
	private PieChart piechart;
	private Date startDate;
	private Date endDate;
	private int maxBudget;

	private int currentBudgetUsed;
	private int currentBudgetStartFocus;
	private int currentBudgetEndFocus;
	private List<Integer> budgetSteps;

	private GraphMode currentMode;

	/**
	 *function for initializing the graph widget
	 */
	private void initialize() {

		maxBudget = 400000;
		currentBudgetUsed = 230000;
		currentBudgetEndFocus = currentBudgetUsed;
		currentBudgetStartFocus = currentBudgetUsed;

		budgetSteps = new ArrayList<Integer>();
		reportList = new ArrayList<ActivityReport>();
		
		for (int i = 0; i < 12; i++) {

			for (int a = 0; a < 5; a++) {
				ActivityReport temp = new ActivityReport();
				Date tempDate = new Date();
				tempDate.setYear(tempDate.getYear() - 1);
				tempDate.setMonth(tempDate.getMonth() + i);
				tempDate.setDate(tempDate.getDate() - a);
				temp.setDate(tempDate);
				temp.setStartTime(1);
				temp.setDuration(480);
				reportList.add(temp);
				GWT.log(tempDate.toGMTString());
			}
		}

		startDate = new Date();
		startDate.setHours(0);
		startDate.setMonth(0);
		startDate.setSeconds(0);
		startDate.setMinutes(0);
		startDate.setYear(startDate.getYear() - 1);
		endDate = new Date();
		endDate.setHours(0);
		endDate.setMinutes(0);
		endDate.setSeconds(0);
		endDate.setDate(endDate.getDate()+1);

		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				linechart = new LineChart();
				budgetContent.add(linechart);
				showWeekMode(null);
				piechart = new PieChart();
				activityContent.add(piechart);
				drawPieChart();
			}
		});
	}

	@UiHandler("buttonNext")
	public void showNext(ClickEvent event) {
		if (false){

		}else {
			currentBudgetStartFocus = currentBudgetEndFocus;
			currentBudgetEndFocus += budgetSteps.get(budgetSteps.size() - 1);
			budgetSteps.remove(budgetSteps.size() - 1);
			switch (currentMode) {
			case WEEK:
				startDate.setDate(startDate.getDate() + 7);
				endDate.setDate(endDate.getDate()+7);
				showWeek();
				break;
			case MONTH:
				startDate.setMonth(startDate.getMonth() + 1);
				endDate.setMonth(endDate.getMonth() + 2);
				endDate.setDate(0);
				if (endDate.getMonth() - startDate.getMonth() > 0) {
					endDate.setDate(0);
				}

				showMonth();
				break;
			case YEAR:
				startDate.setYear(startDate.getYear() + 1);
				endDate.setYear(endDate.getYear() + 1);
				showYear();
				break;
			default:
				break;
			}
		}
	}

	@UiHandler("buttonPrevious")
	public void showPrevious(ClickEvent event) {
		budgetSteps.add(currentBudgetEndFocus - currentBudgetStartFocus);
		currentBudgetEndFocus = currentBudgetStartFocus;

		switch (currentMode) {
		case WEEK:
			startDate.setDate(startDate.getDate() - 7);
			this.endDate.setYear(this.startDate.getYear());
			this.endDate.setMonth(this.startDate.getMonth());
			this.endDate.setDate(this.startDate.getDate()+6);
			GWT.log(endDate.toGMTString());
			showWeek();
			break;
		case MONTH:
			endDate.setMonth(startDate.getMonth());
			startDate.setMonth(startDate.getMonth() - 1);
			endDate.setDate(endDate.getDate() - endDate.getDate());
			endDate.setYear(startDate.getYear());
			showMonth();
			break;
		case YEAR:
			endDate.setYear(startDate.getYear());
			startDate.setYear(startDate.getYear() - 1);
			endDate.setMonth(0);
			endDate.setDate(0);
			showYear();
			break;
		default:
			break;
		}
	}

	@UiHandler("buttonModeWeek")
	public void showWeekMode(ClickEvent event) {
		reset();
		buttonModeYear.removeStyleName("active");
		buttonModeMonth.removeStyleName("active");
		buttonModeWeek.addStyleName("active");
		currentMode = GraphMode.WEEK;
		startDate.setDate(startDate.getDate() - startDate.getDay() + 1);
		showWeek();
	}

	@UiHandler("buttonModeMonth")
	public void showMonthMode(ClickEvent event) {
		reset();
		buttonModeYear.removeStyleName("active");
		buttonModeMonth.addStyleName("active");
		buttonModeWeek.removeStyleName("active");
		currentMode = GraphMode.MONTH;
		startDate.setDate(startDate.getDate() - startDate.getDate() + 1);
		showMonth();
	}

	@UiHandler("buttonModeYear")
	public void showYearMode(ClickEvent event) {
		reset();
		budgetSteps.clear();
		buttonModeYear.addStyleName("active");
		buttonModeMonth.removeStyleName("active");
		buttonModeWeek.removeStyleName("active");
		currentMode = GraphMode.YEAR;
		startDate.setMonth(0);
		startDate.setDate(1);
		endDate.setHours(endDate.getHours() + 1);
		showYear();
	}

	private void updateFocusString() {
		String focusString = (startDate.getDate()) + "." + (startDate.getMonth() + 1) + "."
				+ (startDate.getYear() + 1900) + " - " + (endDate.getDate()) + "." + (endDate.getMonth() + 1) + "."
				+ (endDate.getYear() + 1900);
		labelCurrentFocus.setText(focusString);
		labelBudgetLeft.setText("Budget left: " + String.valueOf(maxBudget-currentBudgetStartFocus));
		labelBudget.setText(String.valueOf(currentBudgetStartFocus)+ " von " + String.valueOf(maxBudget));
	}
	
	private void reset(){
		budgetSteps.clear();
		currentBudgetEndFocus=currentBudgetUsed;
		currentBudgetStartFocus=currentBudgetUsed;
		startDate = new Date();
		startDate.setHours(0);
		startDate.setSeconds(0);
		startDate.setMinutes(0);
		endDate = new Date();
		endDate.setHours(0);
		endDate.setMinutes(0);
		endDate.setSeconds(0);
		endDate.setDate(endDate.getDate()+1);
	}

	public void showWeek() {
		String[] weekDays = new String[] { "Mo", "Tu", "We", "Thu", "Fr", "Sa", "Sun" };

		
		int[] values = new int[7];
		int[] budgetValues = new int[7];
		for (int i = 0; i < values.length; i++) {
			values[i] = 0;
			budgetValues[i] = currentBudgetEndFocus;
		}

		for (ActivityReport report : reportList) {
			GWT.log("CurrentReport" + report.getDate().toGMTString());
			Date tempDate = new Date();
			tempDate.setDate(this.startDate.getDate()+7);
			tempDate.setHours(this.startDate.getHours());
			tempDate.setYear(this.startDate.getYear());
			tempDate.setMinutes(this.startDate.getMinutes());
			tempDate.setMonth(this.startDate.getMonth());
			tempDate.setSeconds(this.startDate.getSeconds());
			if (report.getDate().after(startDate) && report.getDate().before(tempDate)) {
				GWT.log("startDate:" + startDate.toGMTString() + "-" + report.getDate().toGMTString() + "-" + tempDate.toGMTString());
				int currentWeekDay = report.getDate().getDay();
				if (currentWeekDay == 0) {
					currentWeekDay = 6;
				} else {
					currentWeekDay -= 1;
				}
				values[currentWeekDay] += report.getDuration();
			}
		}
		
		int budgetTemp = 0;
		for (int i = 0; i<values.length; i++) {
			budgetTemp += values[i];
		}
		budgetValues[0] = budgetValues[0] - budgetTemp;
		for(int i = 1;i<values.length;i++){
			budgetValues[i] = budgetValues[i-1] + values[i];
		}
		currentBudgetStartFocus = budgetValues[0];
		currentBudgetEndFocus = budgetValues[budgetValues.length - 1];
	
		updateFocusString();

		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Month	");
		dataTable.addColumn(ColumnType.NUMBER, "Project");
		dataTable.addRows(weekDays.length);
		for (int i = 0; i < weekDays.length; i++) {
			dataTable.setValue(i, 0, weekDays[i]);
		}
		for (int row = 0; row < budgetValues.length; row++) {
			dataTable.setValue(row, 1, budgetValues[row]);
		}

		// Set options
		LineChartOptions options = LineChartOptions.create();
		options.setBackgroundColor("white");
		options.setFontName("Tahoma");
		options.setTitle("Week Report");
		options.setHAxis(HAxis.create("Day"));
		options.setVAxis(VAxis.create("€"));
		drawLineChart(dataTable, options);
	}

	public void showMonth() {
		int daysInMonth = endDate.getDate();
		int[] dayzz = new int[daysInMonth];
		for (int i = 0; i < dayzz.length; i++) {
			dayzz[i] = i + 1;
		}

		int[] values = new int[daysInMonth];
		int[] budgetValues = new int[daysInMonth];
		for (int i = 0; i < values.length; i++) {
			values[i] = 0;
			budgetValues[i] = currentBudgetEndFocus;
		}

		for (ActivityReport report : reportList) {
			if (report.getDate().after(startDate) && report.getDate().before(endDate)) {
				values[report.getDate().getDate() - 1] += report.getDuration();
			}
		}

		int budgetTemp = 0;
		for (int i = values.length - 2; i >= 0; i--) {
			budgetTemp += values[i + 1];
			budgetValues[i] -= budgetTemp;
		}

		currentBudgetStartFocus = budgetValues[0];
		currentBudgetEndFocus = budgetValues[budgetValues.length - 1];
		
		updateFocusString();

		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Days");
		dataTable.addColumn(ColumnType.NUMBER, "Project");
		dataTable.addRows(dayzz.length);
		for (int i = 0; i < dayzz.length; i++) {
			dataTable.setValue(i, 0, String.valueOf(dayzz[i]));
		}
		for (int row = 0; row < budgetValues.length; row++) {
			dataTable.setValue(row, 1, budgetValues[row]);
		}

		// Set options
		LineChartOptions options = LineChartOptions.create();
		options.setBackgroundColor("white");
		options.setFontName("Tahoma");
		options.setTitle("Month View");
		options.setHAxis(HAxis.create("Days"));
		options.setVAxis(VAxis.create("€"));
		drawLineChart(dataTable, options);

	}

	public void showYear() {
		int[] months = new int[12];
		for (int i = 0; i < months.length; i++) {
			months[i] = endDate.getMonth() - 12 + i;
		}

		int[] values = new int[12];
		int[] budgetValues = new int[12];
		for (int i = 0; i < values.length; i++) {
			values[i] = 0;
			budgetValues[i] = currentBudgetEndFocus;
		}

		for (ActivityReport report : reportList) {
			if (report.getDate().after(startDate) && report.getDate().before(endDate)) {
				values[Math.abs(report.getDate().getMonth() - 1)] += report.getDuration();
			}
		}

		int budgetTemp = 0;
		for (int i = values.length - 2; i >= 0; i--) {
			budgetTemp += values[i + 1];
			budgetValues[i] -= budgetTemp;
		}

		currentBudgetStartFocus = budgetValues[0];
		currentBudgetEndFocus = budgetValues[budgetValues.length - 1];
		
		updateFocusString();
		
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Year");
		dataTable.addColumn(ColumnType.NUMBER, "Project");
		dataTable.addRows(months.length);
		for (int i = 0; i < months.length; i++) {
			dataTable.setValue(i, 0, String.valueOf(months[i]));
		}
		for (int row = 0; row < budgetValues.length; row++) {
			dataTable.setValue(row, 1, budgetValues[row]);
		}

		// Set options
		LineChartOptions options = LineChartOptions.create();
		options.setBackgroundColor("white");
		options.setFontName("Tahoma");
		options.setTitle("Yearly Coffee Consumption by Country");
		options.setHAxis(HAxis.create("Year"));
		options.setVAxis(VAxis.create("€"));
		drawLineChart(dataTable, options);
	}

	private void drawLineChart(DataTable table, LineChartOptions options) {
		// Draw the chart
		linechart.draw(table, options);
	}

	private void drawPieChart() {
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Task");
		dataTable.addColumn(ColumnType.NUMBER, "Hours per Day");
		dataTable.addRows(5);
		dataTable.setValue(0, 0, "Task 1");
		dataTable.setValue(0, 1, 11);
		dataTable.setValue(1, 0, "Task 2");
		dataTable.setValue(1, 1, 7);
		dataTable.setValue(2, 0, "Task  3");
		dataTable.setValue(2, 1, 3);
		dataTable.setValue(3, 0, "Task 4");
		dataTable.setValue(3, 1, 2);
		dataTable.setValue(4, 0, "Task 5");
		dataTable.setValue(4, 1, 1);

		// Set options
		PieChartOptions options = PieChartOptions.create();

		// options.setColors(colors);
		options.setFontName("Tahoma");
		options.setIs3D(false);
		options.setBackgroundColor("white");
		options.setPieResidueSliceColor("#000000");
		options.setPieResidueSliceLabel("Others");
		options.setSliceVisibilityThreshold(0.1);
		options.setTitle("Project utilization");

		// Draw the chart
		piechart.draw(dataTable, options);

	}

	public void setReportList(List<ActivityReport> reports) {
		this.reportList = reports;
	}

	public void render() {
		initialize();
	}

	@Override
	public void updateObservable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyLogicHandler() {
		// TODO Auto-generated method stub

	}

	/*
	 * public void setLanguage(HashMap<String,String> languagePack){
	 * this.languagePack = languagePack; }
	 */
}
