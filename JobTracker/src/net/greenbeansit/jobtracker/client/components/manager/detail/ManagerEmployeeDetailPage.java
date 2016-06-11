package net.greenbeansit.jobtracker.client.components.manager.detail;

import org.gwtbootstrap3.client.shared.event.TabShowEvent;
import org.gwtbootstrap3.client.shared.event.TabShowHandler;
import org.gwtbootstrap3.client.ui.Anchor;
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
import net.greenbeansit.jobtracker.client.components.widgets.UtilizationWidget;
import net.greenbeansit.jobtracker.client.localization.ManagerEmployeeDetailPageConstants;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;

/**
 * Shows detailed information about an employee to the user.
 * 
 * @author Max Blatt
 */
public class ManagerEmployeeDetailPage extends Composite
{

	private static ManagerEmployeeDetailPageUiBinder	uiBinder	= GWT
			.create(ManagerEmployeeDetailPageUiBinder.class);

	private static ManagerEmployeeDetailPageConstants	constants	= GWT
			.create(ManagerEmployeeDetailPageConstants.class);

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
	Span											textEmployeeSurName,
			labelName, labelSurName;

	@UiField
	Heading											titleEmployeeName,
			infoFieldHeading;

	@UiField
	JobChart										jobChart;

	@UiField
	UtilizationWidget								utilizationWidget;

	@UiField
	Anchor											anchorBackward;
	
	@UiField
	CapacityReportDescriptionWidget capacityReportDescriptionWidget;

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

		labelName.setText(constants.labelName());
		labelSurName.setText(constants.labelSurName());
		anchorBackward.setText(constants.anchorBackward());
		tabProject.setText(constants.tabProject());
		tabReport.setText(constants.tabReport());
		tabCapacity.setText(constants.tabCapacity());
		infoFieldHeading.setText(constants.infoFieldHeading());
		
		reportCalendar.loadServerReports(userId);

		tabProject.addShowHandler(new TabShowHandler()
		{
			@Override
			public void onShow(TabShowEvent event)
			{
				jobChart.onDisplay();
				
				capacityReportDescriptionWidget.setVisible(false);
			}
		});

		tabReport.addShowHandler(new TabShowHandler()
		{
			@Override
			public void onShow(TabShowEvent event)
			{
				reportCalendar.onDisplayed();
				
				capacityReportDescriptionWidget.setVisible(false);
			}
		});

		tabCapacity.addShowHandler(new TabShowHandler()
		{
			@Override
			public void onShow(TabShowEvent event)
			{
				capacityCalendar.onDisplayed();
				
				capacityReportDescriptionWidget.setVisible(true);
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

						utilizationWidget.setRemainingVacationDays(helperService
								.getEmployee().getRemainingVacationDays());

//						utilizationWidget.setUtilization((double)helperService.getEmployee().getUtilization());
						utilizationWidget.setUtilization(157d);
					}

					@Override
					public void onFailure(Throwable error)
					{
						NotifyHelper.errorMessage(error.toString());
					}
				});
	
		capacityReportDescriptionWidget.setVisible(false);
	}
}
