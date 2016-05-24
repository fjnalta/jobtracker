package net.greenbeansit.jobtracker.client.components;

import org.gwtbootstrap3.client.ui.html.ClearFix;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.googlecode.gwt.charts.client.corechart.PieChartOptions;

public class ManagerEmployeeDetailPage extends Composite
{

	private static ManagerEmployeeDetailPageUiBinder	uiBinder	= GWT
			.create(ManagerEmployeeDetailPageUiBinder.class);

	@UiField
	ClearFix											activityChartContainer;

	PieChart piechart;
	
	public ManagerEmployeeDetailPage(Integer userId)
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		
	}

	@Override
	protected void onLoad()
	{
		initActivityChart();
		super.onLoad();
	}

	private void initActivityChart()
	{
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable()
		{
			@Override
			public void run()
			{
				piechart = new PieChart();
				activityChartContainer.add(piechart);
				
				// Prepare the data
				DataTable dataTable = DataTable.create();
				dataTable.addColumn(ColumnType.STRING, "Name");
				dataTable.addColumn(ColumnType.NUMBER, "Hours per Day");
				dataTable.addRows(5);
				dataTable.setValue(0, 0, "VW Intern");
				dataTable.setValue(0, 1, 11);
				dataTable.setValue(1, 0, "Porsche Support");
				dataTable.setValue(1, 1, 7);
				dataTable.setValue(2, 0, "Porsche Entwicklung");
				dataTable.setValue(2, 1, 11);
				dataTable.setValue(3, 0, "HeidCement Entwicklung");
				dataTable.setValue(3, 1, 6);
				dataTable.setValue(4, 0, "HeidCement Support");
				dataTable.setValue(4, 1, 11);

				// Set options
				PieChartOptions options = PieChartOptions.create();

				// options.setColors(colors);
				options.setFontName("Tahoma");
				options.setIs3D(false);
				options.setBackgroundColor("white");
				options.setPieResidueSliceColor("#000000");
				options.setPieResidueSliceLabel("Andere");
				options.setSliceVisibilityThreshold(0.1);
				options.setWidth(500);
				options.setHeight(500);
//				options.setTitle("");

				// Draw the chart
				piechart.draw(dataTable, options);
			}
		});
	}

	interface ManagerEmployeeDetailPageUiBinder
			extends UiBinder<Widget, ManagerEmployeeDetailPage>
	{
	}
}
