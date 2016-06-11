package net.greenbeansit.jobtracker.client.components.manager.detail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.project.detail.OnDisplayEventListener;

public class CapacityReportDescriptionWidget extends Composite implements OnDisplayEventListener
{

	private static CapacityReportDescriptionWidgetUiBinder uiBinder = GWT
			.create(CapacityReportDescriptionWidgetUiBinder.class);

	interface CapacityReportDescriptionWidgetUiBinder
			extends UiBinder<Widget, CapacityReportDescriptionWidget>
	{
	}

	public CapacityReportDescriptionWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void onDisplay()
	{
		// ...
	}

}
