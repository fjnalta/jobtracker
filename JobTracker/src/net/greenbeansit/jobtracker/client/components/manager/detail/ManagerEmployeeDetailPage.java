package net.greenbeansit.jobtracker.client.components.manager.detail;

import org.gwtbootstrap3.client.shared.event.TabShowEvent;
import org.gwtbootstrap3.client.shared.event.TabShowHandler;
import org.gwtbootstrap3.client.ui.NavTabs;
import org.gwtbootstrap3.client.ui.TabListItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ManagerEmployeeDetailPage extends Composite
{

	private static ManagerEmployeeDetailPageUiBinder	uiBinder	= GWT
			.create(ManagerEmployeeDetailPageUiBinder.class);

	@UiField
	ActivityReportCalendar								reportCalendar;

	@UiField
	CapacityCalendar									capacityCalendar;

	@UiField
	NavTabs												tabControl;

	@UiField
	TabListItem											tabProject;

	@UiField
	TabListItem											tabReport;

	@UiField
	TabListItem											tabCapacity;

	public ManagerEmployeeDetailPage(Integer userId)
	{
		initWidget(uiBinder.createAndBindUi(this));

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
	}

	interface ManagerEmployeeDetailPageUiBinder
			extends UiBinder<Widget, ManagerEmployeeDetailPage>
	{
	}
}
