package net.greenbeansit.jobtracker.client.components.project.detail;

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

import net.greenbeansit.jobtracker.client.components.project.detail.ProjectDetailPageHelperServiceImpl.Callback;
import net.greenbeansit.jobtracker.client.localization.ProjectDetailPageConstants;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.Job;

/**
 * Shows detailed information of a {@link Job} to the user.
 * 
 * @author Max Blatt
 */
public class ProjectDetailPage extends Composite
{

	private static ProjectDetailPageUiBinder	uiBinder	= GWT
			.create(ProjectDetailPageUiBinder.class);

	private static ProjectDetailPageConstants	constants	= GWT
			.create(ProjectDetailPageConstants.class);

	/**
	 * UiBinder for {@link ProjectDetailPage}.
	 * 
	 * @author Max Blatt
	 */
	interface ProjectDetailPageUiBinder
			extends UiBinder<Widget, ProjectDetailPage>
	{
	}

	@UiField
	NavTabs									tabControl;

	@UiField
	TabListItem								tabBudget;

	@UiField
	TabListItem								tabTasks;

	@UiField
	TabListItem								tabEmployees;

	@UiField
	JobBudgetWidget							jobBudgetWidget;

	@UiField
	JobTaskWidget							jobTaskWidget;

	@UiField
	JobWorkerListWidget						jobWorkerListWidget;

	@UiField
	Heading									titleProjectName;

	@UiField
	Span									textProjectName, textCustomerName,
			textBudgetInfo, labelProjectName, labelCustomerName,
			labelBudgetInfo;
	@UiField
	Anchor anchorBackward;

	private ProjectDetailPageHelperService	helperService;

	/**
	 * Initializes a new instance of the {@link ProjectDetailPage}.
	 * 
	 * @param jobNo
	 *            the ID of the {@link Job} that should be displayed by the
	 *            page.
	 * @param posNo
	 *            the Position Number.
	 */
	public ProjectDetailPage(Integer jobNo, Integer posNo)
	{
		initWidget(uiBinder.createAndBindUi(this));

		tabEmployees.setText(constants.tabEmployees());
		tabTasks.setText(constants.tabTasks());
		tabBudget.setText(constants.tabBudget());
		labelProjectName.setText(constants.labelProjectName());
		labelCustomerName.setText(constants.labelCustomerName());
		labelBudgetInfo.setText(constants.labelBudgetInfo());
		anchorBackward.setText(constants.anchorBackward());

		// Add tab handlers
		tabBudget.addShowHandler(new TabShowHandler()
		{
			@Override
			public void onShow(TabShowEvent event)
			{
				jobBudgetWidget.onDisplay();
			}
		});

		tabTasks.addShowHandler(new TabShowHandler()
		{
			@Override
			public void onShow(TabShowEvent event)
			{
				jobTaskWidget.onDisplay();
			}
		});

		tabTasks.addShowHandler(new TabShowHandler()
		{
			@Override
			public void onShow(TabShowEvent event)
			{
				jobWorkerListWidget.onDisplay();
			}
		});

		helperService = new ProjectDetailPageHelperServiceImpl(jobNo, posNo,
				new Callback()
				{
					@Override
					public void onSuccess()
					{
						textProjectName
								.setText(helperService.getJob().toString());
						titleProjectName.setText(textProjectName.getText());

						textCustomerName
								.setText(helperService.getCustomer().getName());
						textBudgetInfo.setText(helperService.getJob()
								.getUsedBudget() + " / "
								+ helperService.getJob().getMaxBudget());

						jobWorkerListWidget
								.fillWorkerList(helperService.getWorker());
					}

					@Override
					public void onFailure(Throwable error)
					{
						NotifyHelper.errorMessage(error.toString());
					}
				});

		jobBudgetWidget.initialize(jobNo, posNo);
		jobTaskWidget.initializeChart(jobNo, posNo);
	}

}
