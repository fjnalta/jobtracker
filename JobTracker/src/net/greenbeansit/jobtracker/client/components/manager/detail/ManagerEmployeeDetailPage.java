package net.greenbeansit.jobtracker.client.components.manager.detail;

import org.gwtbootstrap3.client.shared.event.TabShowEvent;
import org.gwtbootstrap3.client.shared.event.TabShowHandler;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.NavTabs;
import org.gwtbootstrap3.client.ui.TabListItem;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.manager.detail.ManagerEmployeeDetailPageHelperServiceImpl.Callback;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;

/**
 * Shows detailed information about an employee to the user.
 * 
 * @author Max Blatt
 */
public class ManagerEmployeeDetailPage extends Composite
{

	private static ManagerEmployeeDetailPageUiBinder uiBinder = GWT
			.create(ManagerEmployeeDetailPageUiBinder.class);

	/**
	 * UiBinder for the {@link ManagerEmployeeDetailPage}.
	 * 
	 * @author Max Blatt
	 */
	interface ManagerEmployeeDetailPageUiBinder
			extends UiBinder<Widget, ManagerEmployeeDetailPage>
	{
	}

	@UiField
	ActivityReportCalendar							reportCalendar;

	@UiField
	CapacityCalendar								capacityCalendar;

	@UiField
	NavTabs											tabControl;

	@UiField
	TabListItem										tabProject;

	@UiField
	TabListItem										tabReport;

	@UiField
	TabListItem										tabCapacity;

	@UiField
	Span											textEmployeeName;

	@UiField
	Span											textEmployeeSurName;

	@UiField
	Heading											titleEmployeeName;

	@UiField
	JobChart										jobChart;

	private ManagerEmployeeDetailPageHelperService	helperService;

	/**
	 * Initializes a new instance of the {@link ManagerEmployeeDetailPage}
	 * class.
	 * 
	 * @param userId
	 *            the ID of the employee that should be displayed.
	 */
	public ManagerEmployeeDetailPage(Integer userId)
	{
		initWidget(uiBinder.createAndBindUi(this));

		tabProject.addShowHandler(new TabShowHandler()
		{
			@Override
			public void onShow(TabShowEvent event)
			{
				jobChart.onDisplay();
			}
		});

		tabReport.addShowHandler(new TabShowHandler()
		{
			@Override
			public void onShow(TabShowEvent event)
			{
				reportCalendar.onDisplayed();
			}
		});

		tabCapacity.addShowHandler(new TabShowHandler()
		{
			@Override
			public void onShow(TabShowEvent event)
			{
				capacityCalendar.onDisplayed();
			}
		});

		helperService = new ManagerEmployeeDetailPageHelperServiceImpl(userId,
				new Callback()
				{
					@Override
					public void onSuccess()
					{
						textEmployeeName.setText(
								helperService.getEmployee().getSurname());
						textEmployeeSurName
								.setText(helperService.getEmployee().getName());

						titleEmployeeName.setText(helperService.getEmployee()
								.getName() + " "
								+ helperService.getEmployee().getSurname());

						jobChart.fillPieChart(helperService.getJobs());
					}

					@Override
					public void onFailure(Throwable error)
					{
						NotifyHelper.errorMessage(error.toString());
					}
				});
	}
}
