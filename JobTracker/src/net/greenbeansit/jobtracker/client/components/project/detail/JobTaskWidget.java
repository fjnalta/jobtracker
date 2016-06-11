package net.greenbeansit.jobtracker.client.components.project.detail;

import java.util.List;

import org.fusesource.restygwt.client.Method;
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
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
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

//		initializeChart();
	}

	/**
	 * Initializes the Widget.
	 */
	public void initializeChart(final Integer jobNo, final Integer posNo)
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
//				drawPieChart();
				
				RestClient.build(new SuccessFunction<List<JobTask>>()
				{
					@Override
					public void onSuccess(Method method, List<JobTask> response)
					{
						drawPieChart(response);
					}

					@Override
					public void onFailure(Method method, Throwable exception)
					{
						GWT.log(exception.toString());
						NotifyHelper.errorMessage(exception.toString());
					}
				}).getEmployeeService().getJobTasks(jobNo, posNo);
			}
		});
	}

	/**
	 * Draws the pie chart.
	 */
	private void drawPieChart(List<JobTask> list)
	{
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, constants.jobTaskColumnName());
		dataTable.addColumn(ColumnType.NUMBER, constants.jobTaskColumnHoursPerDay());
		
		for(JobTask task : list)
		{
			int rowIndex = dataTable.addRow();
			
			dataTable.setCell(rowIndex, 0, task.getName());
			dataTable.setCell(rowIndex, 1, 20);
		}
		
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
