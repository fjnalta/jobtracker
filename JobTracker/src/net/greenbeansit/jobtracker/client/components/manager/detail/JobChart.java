package net.greenbeansit.jobtracker.client.components.manager.detail;

import java.util.List;

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
import com.googlecode.gwt.charts.client.event.ReadyEvent;
import com.googlecode.gwt.charts.client.event.ReadyHandler;

import net.greenbeansit.jobtracker.client.components.project.detail.OnDisplayEventListener;
import net.greenbeansit.jobtracker.shared.Job;

/**
 * Displays the {@link Job}s of an employee.
 * 
 * @author Max Blatt
 */
public class JobChart extends Composite implements OnDisplayEventListener
{

	private static JobChartUiBinder uiBinder = GWT
			.create(JobChartUiBinder.class);

	/**
	 * UiBinder for the {@link JobChartUiBinder}.
	 * 
	 * @author Max Blatt
	 */
	interface JobChartUiBinder extends UiBinder<Widget, JobChart>
	{
	}

	@UiField
	ClearFix	activityChartContainer;

	PieChart	piechart;

	/**
	 * Initializes a new instance of the {@link JobChart} class.
	 */
	public JobChart()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		Window.addResizeHandler(new ResizeHandler()
		{
			@Override
			public void onResize(ResizeEvent event)
			{
				if(piechart != null)
				{
					piechart.setWidth("100%");
					piechart.redraw();
				}
			}
		});
	}
	
	/**
	 * Fills the pie chart.
	 * 
	 * @param jobs a list of {@link Job}s.
	 */
	public void fillPieChart(final List<Job> jobs)
	{
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable()
		{
			@Override
			public void run()
			{
				piechart = new PieChart();
				activityChartContainer.add(piechart);

				DataTable dataTable = DataTable.create();
				dataTable.addColumn(ColumnType.STRING, "Name");
				dataTable.addColumn(ColumnType.NUMBER, "Hours per Week");
				
				for(Job job : jobs)
				{
					if(job != null)
					{
						int rowIndex = dataTable.addRow();
						dataTable.setValue(rowIndex, 0, job.toString());
						dataTable.setValue(rowIndex, 1, 20);
					}
				}
				
				
				// Set options
				PieChartOptions options = PieChartOptions.create();

				// options.setColors(colors);
				options.setFontName("Tahoma");
				options.setIs3D(false);
				options.setBackgroundColor("white");
				options.setPieResidueSliceColor("#000000");
				options.setPieResidueSliceLabel("Andere");
				options.setSliceVisibilityThreshold(0.1);
				
				piechart.setWidth("100%");
				piechart.setHeight("500px");
				
				piechart.draw(dataTable, options);
				
				piechart.addReadyHandler(new ReadyHandler()
				{

					@Override
					public void onReady(ReadyEvent event)
					{

					}
				});
			}
		});
	}

	@Override
	public void onDisplay()
	{
		if(piechart != null)
		{
			piechart.setWidth("100%");
			piechart.redraw();
		}
	}
}
