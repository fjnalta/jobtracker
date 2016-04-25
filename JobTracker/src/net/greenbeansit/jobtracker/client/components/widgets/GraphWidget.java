package net.greenbeansit.jobtracker.client.components.widgets;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.html.ClearFix;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.Selection;
import com.googlecode.gwt.charts.client.corechart.LineChart;
import com.googlecode.gwt.charts.client.corechart.LineChartOptions;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.googlecode.gwt.charts.client.corechart.PieChartOptions;
import com.googlecode.gwt.charts.client.event.ReadyEvent;
import com.googlecode.gwt.charts.client.event.ReadyHandler;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.VAxis;

import net.greenbeansit.jobtracker.controller.Controller;
import net.greenbeansit.jobtracker.controller.ControllerInterface;
import net.greenbeansit.jobtracker.shared.Activity;
import net.greenbeansit.jobtracker.shared.Job;

public class GraphWidget extends Composite {

	private static GraphWidgetUiBinder uiBinder = GWT.create(GraphWidgetUiBinder.class);

	interface GraphWidgetUiBinder extends UiBinder<Widget, GraphWidget> {
	}

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
	
	private ControllerInterface controller;

	

	private LineChart linechart;
	private PieChart piechart;
	private Job currentJob;
	private int[][] currentFocus;

	private void initialize() {
		currentFocus = new int[][] { { 17, 4, 2016 }, { 24, 4, 2016 } };

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
	
	public ControllerInterface getController() {
		return controller;
	}

	public void setController(ControllerInterface controller) {
		this.controller = controller;
	}

	private void loadData() {
	}

	private void setJob(Job currentJob) {
		this.currentJob = currentJob;
	}

	public void showPreviousWeek() {
		// TODO Datenanzeige für die Woche vor der aktuell angezeigten Woche
	}

	public void showNextWeek() {
		// TODO Datenanzeigen für die Woche nach der aktuell angezeigten Woche
	}

	public void showPreviousMonth() {
		currentFocus[0][1] = currentFocus[0][1] - 1;
		currentFocus[1][1] = currentFocus[1][1] - 1;
	}

	public void showNextMonth() {
		currentFocus[0][1] = currentFocus[0][1] + 1;
		currentFocus[1][1] = currentFocus[1][1] + 1;
	}

	public void showPreviousYear() {
		currentFocus[0][2] = currentFocus[0][2] - 1;
		currentFocus[1][2] = currentFocus[1][2] - 1;
	}

	public void showNextYear() {
		currentFocus[0][2] = currentFocus[0][2] + 1;
		currentFocus[1][2] = currentFocus[1][2] + 1;
	}

	public void showWeek() {
	}

	public void showMonth() {
	}
	
	public void showYear() {
		currentFocus[0][2] = currentFocus[1][2] -1;
		currentFocus[0][1] = currentFocus[1][1];
		currentFocus[0][0] = currentFocus[1][0];
		//creating dummy data
		
		int[] months = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
		
		Controller controller = new Controller();
		
		
		
		int[] values = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		//values[0] = temp.getActivities().size();
		/*
		for (Activity e : controller.getJob(String.valueOf(0)).getActivities()) {
			if (e.getYear() == currentFocus[0][2] || e.getYear() == currentFocus[1][2]) {
						values[e.getMonth()] += e.getWorkedTime();
			}
		}
		*/
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
		drawLineChart(dataTable,options);
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

}
