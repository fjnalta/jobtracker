package net.greenbeansit.jobtracker.client.components.project.detail;

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

import net.greenbeansit.jobtracker.client.components.project.detail.ProjectDetailPageHelperServiceImpl.Callback;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.Job;


/**
 * Shows detailed information of a {@link Job} to the user.
 * 
 * @author Max Blatt
 */
public class ProjectDetailPage extends Composite
{

	private static ProjectDetailPageUiBinder uiBinder = GWT
			.create(ProjectDetailPageUiBinder.class);

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
	NavTabs				tabControl;

	@UiField
	TabListItem			tabBudget;

	@UiField
	TabListItem			tabTasks;

	@UiField
	TabListItem			tabEmployees;

	@UiField
	JobBudgetWidget		jobBudgetWidget;

	@UiField
	JobTaskWidget		jobTaskWidget;

	@UiField
	JobWorkerListWidget	jobWorkerListWidget;
	
	@UiField
	Heading titleProjectName;
	
	@UiField
	Span textProjectName;
	
	@UiField
	Span textCustomerName;
	
	@UiField
	Span textBudgetInfo;

	
	private ProjectDetailPageHelperService helperService;
	
	
	/**
	 * Initializes a new instance of the {@link ProjectDetailPage}.
	 * 
	 * @param jobId
	 *            the ID of the {@link Job} that should be displayed by the
	 *            page.
	 */
	public ProjectDetailPage(Integer jobId)
	{
		initWidget(uiBinder.createAndBindUi(this));

		//Add tab handlers
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
		
		
		helperService = new ProjectDetailPageHelperServiceImpl(jobId, new Callback()
		{
			@Override
			public void onSuccess()
			{
				textProjectName.setText(helperService.getJob().toString());
				titleProjectName.setText(textProjectName.getText());
				
				textCustomerName.setText(helperService.getCustomer().getName());
				textBudgetInfo.setText(helperService.getJob().getUsedBudget() + " / " + helperService.getJob().getMaxBudget());
				
			}
			
			@Override
			public void onFailure(Throwable error)
			{
				NotifyHelper.errorMessage(error.toString());
			}
		});
	}

}
