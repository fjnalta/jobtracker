package net.greenbeansit.jobtracker.client.components.project.detail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.html.ClearFix;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
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
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.VAxis;

import net.greenbeansit.jobtracker.client.components.widgets.GraphWidget.GraphMode;
import net.greenbeansit.jobtracker.client.localization.HomePageConstants;
import net.greenbeansit.jobtracker.client.localization.ProjectDetailPageConstants;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.Job;

/**
 * Displays the budget of a {@link Job} in a graph.
 * 
 * @author Max Blatt
 */
public class JobBudgetWidget extends Composite implements OnDisplayEventListener
{

	private static JobBudgetWidgetUiBinder uiBinder = GWT
			.create(JobBudgetWidgetUiBinder.class);

	private static ProjectDetailPageConstants constants = GWT.create(ProjectDetailPageConstants.class);
	
	/**
	 * UiBinder for the {@link JobBudgetWidget}.
	 * 
	 * @author Max Blatt
	 */
	interface JobBudgetWidgetUiBinder extends UiBinder<Widget, JobBudgetWidget>
	{
	}

	@UiField
	ClearFix						budgetContent;

	@UiField
	Label							labelCurrentFocus;

	@UiField
	Button							buttonModeYear;

	@UiField
	Button							buttonModeMonth;

	@UiField
	Button							buttonModeWeek;

	@UiField
	Button							buttonNext;

	@UiField
	Button							buttonPrevious;

	private List<ActivityReport>	reportList;

	private LineChart				linechart;
	private Date					startDate;
	private Date					endDate;
	private int						maxBudget;

	private int						currentBudgetUsed;
	private int						currentBudgetStartFocus;
	private int						currentBudgetEndFocus;
	private List<Integer>			budgetSteps;

	private GraphMode				currentMode;

	/**
	 * Initializes a new instance of the {@link JobBudgetWidget}.
	 */
	public JobBudgetWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));

		Window.addResizeHandler(new ResizeHandler()
		{
			@Override
			public void onResize(ResizeEvent event)
			{
				if(linechart != null)
				{
					linechart.setWidth("100%");
					linechart.redraw();
				}
			}
		});
	}

	/**
	 * Initializes the widget.
	 * 
	 * @param jobNo
	 * @param posNo
	 */
	public void initialize(final Integer jobNo, final Integer posNo)
	{
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable()
		{
			@Override
			public void run()
			{
				// Create and attach the chart
				linechart = new LineChart();
				linechart.setWidth("100%");
				linechart.setHeight("400px");
				budgetContent.add(linechart);
				
				
				RestClient.build(new SuccessFunction<List<Integer>>()
				{
					@Override
					public void onSuccess(Method method, List<Integer> response)
					{
						budgetSteps = response;
						
						initializeGraph();
					}

					@Override
					public void onFailure(Method method, Throwable exception)
					{
						GWT.log(exception.toString());
						NotifyHelper.errorMessage(exception.toString());
					}
				}).getEmployeeService().getUsedBudgedYear(jobNo, posNo, 2016);
			}
		});
	}
	
	/**
	 * Initializes the graph.
	 */
	@SuppressWarnings("deprecation")
	private void initializeGraph()
	{
		maxBudget = 400000;
		currentBudgetUsed = 230000;
		currentBudgetEndFocus = currentBudgetUsed;
		currentBudgetStartFocus = currentBudgetUsed;

		budgetSteps = new ArrayList<Integer>();
		reportList = new ArrayList<ActivityReport>();

		for (int i = 0; i < 12; i++)
		{

			for (int a = 0; a < 5; a++)
			{
				ActivityReport temp = new ActivityReport();
				Date tempDate = new Date();
				tempDate.setYear(tempDate.getYear() - 1);
				tempDate.setMonth(tempDate.getMonth() + i);
				tempDate.setDate(tempDate.getDate() - a);
				temp.setDate(tempDate);
				temp.setStartTime(1);
				temp.setDuration(480);
				reportList.add(temp);
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
		endDate.setYear(endDate.getYear() + 1);

	}

	
	/**
	 * Method for resetting the graph widget
	 */
	private void reset()
	{
		budgetSteps.clear();
		currentBudgetEndFocus = currentBudgetUsed;
		currentBudgetStartFocus = currentBudgetUsed;
		startDate = new Date();
		startDate.setHours(0);
		startDate.setSeconds(0);
		startDate.setMinutes(0);
		endDate = new Date();
		endDate.setHours(0);
		endDate.setMinutes(0);
		endDate.setSeconds(0);
		endDate.setDate(endDate.getDate() + 1);
	}

	/**
	 * Method for the visualization of the week mode
	 */
	public void showWeek()
	{
		String[] weekDays = constants.weekDayNames();

		int[] values = new int[7];
		int[] budgetValues = new int[7];
		for (int i = 0; i < values.length; i++)
		{
			values[i] = 0;
			budgetValues[i] = currentBudgetEndFocus;
		}

		for (ActivityReport report : reportList)
		{
			Date tempDate = new Date();
			tempDate.setDate(this.startDate.getDate() + 7);
			tempDate.setHours(this.startDate.getHours());
			tempDate.setYear(this.startDate.getYear());
			tempDate.setMinutes(this.startDate.getMinutes());
			tempDate.setMonth(this.startDate.getMonth());
			tempDate.setSeconds(this.startDate.getSeconds());
			if (report.getDate().after(startDate)
					&& report.getDate().before(tempDate))
			{
				int currentWeekDay = report.getDate().getDay();
				if (currentWeekDay == 0)
				{
					currentWeekDay = 6;
				} else
				{
					currentWeekDay -= 1;
				}
				values[currentWeekDay] += report.getDuration();
			}
		}

		int budgetTemp = 0;
		for (int i = 0; i < values.length; i++)
		{
			budgetTemp += values[i];
		}
		budgetValues[0] = budgetValues[0] - budgetTemp;
		for (int i = 1; i < values.length; i++)
		{
			budgetValues[i] = budgetValues[i - 1] + values[i];
		}
		currentBudgetStartFocus = budgetValues[0];
		currentBudgetEndFocus = budgetValues[budgetValues.length - 1];

		updateFocusString();

		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Month	");
		dataTable.addColumn(ColumnType.NUMBER, "Project");
		dataTable.addRows(weekDays.length);
		for (int i = 0; i < weekDays.length; i++)
		{
			dataTable.setValue(i, 0, weekDays[i]);
		}
		for (int row = 0; row < budgetValues.length; row++)
		{
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

	/**
	 * Method for the visualization of the month mode
	 */
	public void showMonth()
	{
		int daysInMonth = endDate.getDate();
		int[] dayzz = new int[daysInMonth];
		for (int i = 0; i < dayzz.length; i++)
		{
			dayzz[i] = i + 1;
		}

		int[] values = new int[daysInMonth];
		int[] budgetValues = new int[daysInMonth];
		for (int i = 0; i < values.length; i++)
		{
			values[i] = 0;
			budgetValues[i] = currentBudgetEndFocus;
		}

		for (ActivityReport report : reportList)
		{
			if (report.getDate().after(startDate)
					&& report.getDate().before(endDate))
			{
				values[report.getDate().getDate() - 1] += report.getDuration();
			}
		}

		int budgetTemp = 0;
		for (int i = values.length - 2; i >= 0; i--)
		{
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
		for (int i = 0; i < dayzz.length; i++)
		{
			dataTable.setValue(i, 0, String.valueOf(dayzz[i]));
		}
		for (int row = 0; row < budgetValues.length; row++)
		{
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

	/**
	 * Method for the visualization of the year mode
	 */
	public void showYear()
	{
		int monthsToShow = endDate.getMonth()+1;
		//if(currentTime.getMonth())
		int[] months = new int[monthsToShow];
		for (int i = 0; i < months.length; i++)
		{
			months[i] = endDate.getMonth() - monthsToShow+2 + i;
		}

		int[] values = new int[monthsToShow];
		int[] budgetValues = new int[monthsToShow];
		for (int i = 0; i < values.length; i++)
		{
			values[i] = 0;
			budgetValues[i] = currentBudgetEndFocus;
		}

		for (ActivityReport report : reportList)
		{
			if (report.getDate().after(startDate)
					&& report.getDate().before(endDate))
			{
				values[Math.abs(report.getDate().getMonth() - 1)] += report
						.getDuration();
			}
		}

		int budgetTemp = 0;
		for (int i = values.length - 2; i >= 0; i--)
		{
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
		for (int i = 0; i < months.length; i++)
		{
			dataTable.setValue(i, 0, String.valueOf(months[i]));
		}
		for (int row = 0; row < budgetValues.length; row++)
		{
			dataTable.setValue(row, 1, budgetValues[row]);
		}

		// Set options
		LineChartOptions options = LineChartOptions.create();
		options.setBackgroundColor("white");
		options.setFontName("Tahoma");
		options.setTitle("Year view");
		options.setHAxis(HAxis.create("Year"));
		options.setVAxis(VAxis.create("€"));
		drawLineChart(dataTable, options);
	}

	/**
	 * Method for updating the focus string to the actual date
	 */
	private void updateFocusString()
	{
		String focusString = (startDate.getDate()) + "."
				+ (startDate.getMonth() + 1) + "."
				+ (startDate.getYear() + 1900) + " - " + (endDate.getDate())
				+ "." + (endDate.getMonth() + 1) + "."
				+ (endDate.getYear() + 1900);
		labelCurrentFocus.setText(focusString);
	}

	/**
	 * Method for drawing a line chart
	 * 
	 * @param table
	 *            a DataTable object with the data do draw
	 * @param options
	 *            a LineChartsOptions object with the options of the line chart
	 */
	private void drawLineChart(DataTable table, LineChartOptions options)
	{
		// Draw the chart
		linechart.draw(table, options);
	}

	@Override
	public void onDisplay()
	{
		linechart.redraw();
	}
	
	/**
	 * Event handling for the buttonPrevious, shows the previous time span
	 * 
	 * @param event
	 *            CLickEvent of the button
	 */
	@UiHandler("buttonPrevious")
	public void showPrevious(ClickEvent event)
	{
		budgetSteps.add(currentBudgetEndFocus - currentBudgetStartFocus);
		currentBudgetEndFocus = currentBudgetStartFocus;

		switch (currentMode)
		{
		case WEEK:
			startDate.setDate(startDate.getDate() - 7);
			this.endDate.setYear(this.startDate.getYear());
			this.endDate.setMonth(this.startDate.getMonth());
			this.endDate.setDate(this.startDate.getDate() + 6);
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

	/**
	 * event handling for buttonNext, shows the next time span
	 * @param event ClickEvent of the Button
	 */
	@UiHandler("buttonNext")
	public void showNext(ClickEvent event) {
		GWT.log("buttonNext");
		currentBudgetStartFocus = currentBudgetEndFocus;

		if(!budgetSteps.isEmpty())	{
			currentBudgetEndFocus += budgetSteps.get(budgetSteps.size() - 1);
			budgetSteps.remove(budgetSteps.size() - 1);
		}

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

	/**
	 * Event handling for the buttonModeWeek, switches the graph to week mode
	 * 
	 * @param event
	 *            ClickEvent of the button
	 */
	@UiHandler("buttonModeWeek")
	public void showWeekMode(ClickEvent event)
	{
		reset();
		buttonModeYear.removeStyleName("active");
		buttonModeMonth.removeStyleName("active");
		buttonModeWeek.addStyleName("active");
		currentMode = GraphMode.WEEK;
		startDate.setDate(startDate.getDate() - startDate.getDay() + 1);
		showWeek();
	}

	/**
	 * Event handling for the buttonModeMonth, switches the graph to month mode
	 * 
	 * @param event
	 *            ClickEvent of the button
	 */
	@UiHandler("buttonModeMonth")
	public void showMonthMode(ClickEvent event)
	{
		reset();
		buttonModeYear.removeStyleName("active");
		buttonModeMonth.addStyleName("active");
		buttonModeWeek.removeStyleName("active");
		currentMode = GraphMode.MONTH;
		startDate.setDate(startDate.getDate() - startDate.getDate() + 1);
		showMonth();
	}

	/**
	 * Event handling for the buttonModeYear, switches the graph to year mode
	 * 
	 * @param event
	 *            the Click event.
	 */
	@UiHandler("buttonModeYear")
	public void showYearMode(ClickEvent event)
	{
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

	

}
