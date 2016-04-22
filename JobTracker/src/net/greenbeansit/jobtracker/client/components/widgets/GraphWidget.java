package net.greenbeansit.jobtracker.client.components.widgets;

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

public class GraphWidget extends Composite{

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

	private LineChart linechart;
	private PieChart piechart;


	private void initialize() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				linechart = new LineChart();
				budgetContent.add(linechart);
				drawLineChart();
				piechart = new PieChart();
				activityContent.add(piechart);
				drawPieChart();
			}
		});
	}
	
	
	public void showWeek(){
		//TODO Datenauswahl für eine Woche
	}
	
	public void showMonth(){
		//TODO Datenauswahl für einen Monat
	}
	
	public void showYear(){
		//TODO Datenauswahl für ein Jahr
	}
	
	public void drawFromData(){
		//TODO von einer DatenKlasse chart zeichnen
	}
	
	private void drawLineChart() {
		String[] countries = new String[] { "Austria", "Bulgaria", "Denmark", "Greece" };
		int[] years = new int[] { 2003, 2004, 2005, 2006, 2007, 2008 };
		int[][] values = new int[][] { { 1336060, 1538156, 1576579, 1600652, 1968113, 1901067 },
				{ 400361, 366849, 440514, 434552, 393032, 517206 },
				{ 1001582, 1119450, 993360, 1004163, 979198, 916965 },
				{ 997974, 941795, 930593, 897127, 1080887, 1056036 } };

		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Year");
		for (int i = 0; i < countries.length; i++) {
			dataTable.addColumn(ColumnType.NUMBER, countries[i]);
		}
		dataTable.addRows(years.length);
		for (int i = 0; i < years.length; i++) {
			dataTable.setValue(i, 0, String.valueOf(years[i]));
		}
		for (int col = 0; col < values.length; col++) {
			for (int row = 0; row < values[col].length; row++) {
				dataTable.setValue(row, col + 1, values[col][row]);
			}
		}

		// Set options
		LineChartOptions options = LineChartOptions.create();
		options.setBackgroundColor("white");
		options.setFontName("Tahoma");
		options.setTitle("Yearly Coffee Consumption by Country");
		options.setHAxis(HAxis.create("Year"));
		options.setVAxis(VAxis.create("Cups"));

		// Draw the chart
		linechart.draw(dataTable, options);
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
