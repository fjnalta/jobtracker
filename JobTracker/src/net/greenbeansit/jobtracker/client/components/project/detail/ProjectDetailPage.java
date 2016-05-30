package net.greenbeansit.jobtracker.client.components.project.detail;

import org.gwtbootstrap3.client.ui.NavTabs;
import org.gwtbootstrap3.client.ui.TabListItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ProjectDetailPage extends Composite
{

	private static ProjectDetailPageUiBinder uiBinder = GWT
			.create(ProjectDetailPageUiBinder.class);

	interface ProjectDetailPageUiBinder
			extends UiBinder<Widget, ProjectDetailPage>
	{

	}

	@UiField
	NavTabs		tabControl;

	@UiField
	TabListItem	tabBudget;

	@UiField
	TabListItem	tabTasks;

	@UiField
	TabListItem	tabEmployees;

	public ProjectDetailPage(Integer jobId)
	{
		initWidget(uiBinder.createAndBindUi(this));

		// tabReport.addShowHandler(new TabShowHandler()
		// {
		// @Override
		// public void onShow(TabShowEvent event)
		// {
		// reportCalendar.onDisplayed();
		// }
		// });
		//
		// tabCapacity.addShowHandler(new TabShowHandler()
		// {
		// @Override
		// public void onShow(TabShowEvent event)
		// {
		// capacityCalendar.onDisplayed();
		// }
		// });
	}

}
