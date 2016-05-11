package net.greenbeansit.jobtracker.client.components.widgets;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.html.ClearFix;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.util.collect.HashMap;
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

import net.greenbeansit.jobtracker.client.components.ProjectPage;
import net.greenbeansit.jobtracker.shared.ActivityReport;

public class GraphWidget extends Composite{

	private static GraphWidgetUiBinder uiBinder = GWT.create(GraphWidgetUiBinder.class);

	interface GraphWidgetUiBinder extends UiBinder<Widget, GraphWidget> {
	}

	public static enum GraphMode {
		WEEK, MONTH, YEAR
	}

	public GraphWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		// initialize();
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
	Button buttonModeYear;

	@UiField
	Button buttonModeMonth;

	@UiField
	Button buttonModeWeek;

	private List<ActivityReport> reportList;

	private LineChart linechart;
	private PieChart piechart;
	private Date startDate;
	private Date endDate;
	//private HashMap<String,String> languagePack;

	private GraphMode currentMode;

	private void initialize() {
		/*if(this.languagePack==null){
			languagePack.put("jan", "January");
			languagePack.put("feb", "February");
			languagePack.put("mar", "March");
			languagePack.put("jan", "Januar");
			languagePack.put("jan", "Januar");
			languagePack.put("jan", "Januar");
			languagePack.put("jan", "Januar");
			languagePack.put("jan", "Januar");
			languagePack.put("jan", "Januar");
			languagePack.put("jan", "Januar");
			
			
		}
		*/
		startDate = new Date();
		startDate.setYear(startDate.getYear() - 1);
		endDate = new Date();
		endDate.setHours(endDate.getHours() + 1);

		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				linechart = new LineChart();
				budgetContent.add(linechart);
				showYear();
				piechart = new PieChart();
				activityContent.add(piechart);
				drawPieChart();
			}
		});
	}

	@UiHandler("buttonNext")
	public void showNext(ClickEvent event) {
		switch (currentMode) {
		case WEEK:
			startDate.setDate(startDate.getDate() + 7);
			endDate.setDate(startDate.getDate() + 7);
			break;
		case MONTH:
			startDate.setMonth(startDate.getMonth() + 1);
			endDate.setMonth(endDate.getMonth() + 1);
			break;
		case YEAR:
			startDate.setYear(startDate.getYear() + 1);
			endDate.setYear(endDate.getYear() + 1);
			break;
		default:
			break;
		}
	}

	@UiHandler("buttonPrevious")
	public void showPrevious(ClickEvent event) {
		switch (currentMode) {
		case WEEK:
			startDate.setDate(startDate.getDate() - 7);
			endDate.setDate(startDate.getDate() - 7);
			break;
		case MONTH:
			startDate.setMonth(startDate.getMonth() - 1);
			endDate.setMonth(endDate.getMonth() - 1);
			break;
		case YEAR:
			startDate.setYear(startDate.getYear() - 1);
			endDate.setYear(endDate.getYear() - 1);
			break;
		default:
			break;
		}
	}

	@UiHandler("buttonModeWeek")
	public void showWeekMode(ClickEvent event) {
		buttonModeYear.removeStyleName("active");
		buttonModeMonth.removeStyleName("active");
		buttonModeWeek.addStyleName("active");
		showWeek();
	}

	@UiHandler("buttonModeMonth")
	public void showMonthMode(ClickEvent event) {
		buttonModeYear.removeStyleName("active");
		buttonModeMonth.addStyleName("active");
		buttonModeWeek.removeStyleName("active");
		showMonth();
	}

	@UiHandler("buttonModeYear")
	public void showYearMode(ClickEvent event) {
		buttonModeYear.addStyleName("active");
		buttonModeMonth.removeStyleName("active");
		buttonModeWeek.removeStyleName("active");
		showYear();
	}

	public void showWeek() {
		currentMode = GraphMode.WEEK;
	}

	public void showMonth() {
		currentMode = GraphMode.MONTH;
		startDate = new Date();
		startDate.setMonth(startDate.getMonth() - 1);
	}

	public void showYear() {
		currentMode = GraphMode.YEAR;
		startDate = new Date();
		startDate.setYear(startDate.getYear() - 1);

		int[] months = new int[12];
		for (int i = 0; i < months.length; i++) {
			months[i] = endDate.getMonth() - 12 + i;
		}

		int[] values = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		// load relevant data in value array
		for (ActivityReport report : reportList) {
			if (report.getDate().after(startDate) && report.getDate().before(endDate)) {

				values[Math.abs(report.getDate().getMonth() - 1)] += report.getDuration();
			}
		}

		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Year");
		dataTable.addColumn(ColumnType.NUMBER, "LAWL");
		dataTable.addRows(months.length);
		for (int i = 0; i < months.length; i++) {
			dataTable.setValue(i, 0, String.valueOf(months[i]));
		}
		for (int row = 0; row < values.length; row++) {
			dataTable.setValue(row, 1, values[row]);
		}

		// Set options
		LineChartOptions options = LineChartOptions.create();
		options.setBackgroundColor("white");
		options.setFontName("Tahoma");
		options.setTitle("Yearly Coffee Consumption by Country");
		options.setHAxis(HAxis.create("Year"));
		options.setVAxis(VAxis.create("€"));
		drawLineChart(dataTable, options);
		linechart.draw(dataTable, options);
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
		dataTable.setValue(0, 0, "Work");
		dataTable.setValue(0, 1, 11);
		dataTable.setValue(1, 0, "Sleep");
		dataTable.setValue(1, 1, 7);
		dataTable.setValue(2, 0, "Watch TV");
		dataTable.setValue(2, 1, 3);
		dataTable.setValue(3, 0, "Eat");
		dataTable.setValue(3, 1, 2);
		dataTable.setValue(4, 0, "Commute");
		dataTable.setValue(4, 1, 1);

		// Set options
		PieChartOptions options = PieChartOptions.create();
		options.setBackgroundColor("#f0f0f0");

		// options.setColors(colors);
		options.setFontName("Tahoma");
		options.setIs3D(false);
		options.setPieResidueSliceColor("#000000");
		options.setPieResidueSliceLabel("Others");
		options.setSliceVisibilityThreshold(0.1);
		options.setTitle("So, how was your day?");

		// Draw the chart
		piechart.draw(dataTable, options);

	}

	public void setReportList(List<ActivityReport> reports) {
		this.reportList = reports;
	}

	public void render() {
		initialize();
	}

	
	/*
	public void setLanguage(HashMap<String,String> languagePack){
		this.languagePack = languagePack;
	}
*/
}
