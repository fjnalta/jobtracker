package net.greenbeansit.jobtracker.client.components.project.detail;

import org.gwtbootstrap3.client.ui.html.ClearFix;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.googlecode.gwt.charts.client.corechart.PieChartOptions;

import net.greenbeansit.jobtracker.client.localization.ProjectDetailPageConstants;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.JobTask;

/**
 * Displays the {@link JobTask}s of a {@link Job} in a diagram.
 * 
 * @author Max Blatt
 */
public class JobTaskWidget extends Composite implements OnDisplayEventListener
{

	private static JobTaskWidgetUiBinder		uiBinder	= GWT
			.create(JobTaskWidgetUiBinder.class);

	private static ProjectDetailPageConstants	constants	= GWT
			.create(ProjectDetailPageConstants.class);

	/**
	 * UiBinder for {@link JobTaskWidget}.
	 * 
	 * @author Max Blatt
	 */
	interface JobTaskWidgetUiBinder extends UiBinder<Widget, JobTaskWidget>
	{
	}

	@UiField
	ClearFix			activityContent;

	private PieChart	piechart;

	/**
	 * Initializes a new instance of the class {@link JobTaskWidget}.
	 */
	public JobTaskWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));

		Window.addResizeHandler(new ResizeHandler()
		{

			@Override
			public void onResize(ResizeEvent event)
			{
				if (piechart != null)
				{
					piechart.setWidth("100%");
					piechart.redraw();
				}
			}
		});

		initializeChart();
	}

	/**
	 * Initializes the Widget.
	 */
	private void initializeChart()
	{
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable()
		{
			@Override
			public void run()
			{
				piechart = new PieChart();
				piechart.setWidth("100%");
				piechart.setHeight("400px");
				activityContent.add(piechart);
				drawPieChart();
			}
		});
	}

	/**
	 * Draws the pie chart.
	 */
	private void drawPieChart()
	{
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, constants.jobTaskColumnName());
		dataTable.addColumn(ColumnType.NUMBER, constants.jobTaskColumnHoursPerDay());
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
		options.setTitle(constants.jobTaskTitle());

		// Draw the chart
		piechart.draw(dataTable, options);

	}

	@Override
	public void onDisplay()
	{
		piechart.redraw();
	}

}
