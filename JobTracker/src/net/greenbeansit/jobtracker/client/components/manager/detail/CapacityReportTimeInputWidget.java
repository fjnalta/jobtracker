package net.greenbeansit.jobtracker.client.components.manager.detail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CapacityReportTimeInputWidget extends Composite
{

	private static CapacityReportTimeInputWidgetUiBinder uiBinder = GWT
			.create(CapacityReportTimeInputWidgetUiBinder.class);

	interface CapacityReportTimeInputWidgetUiBinder
			extends UiBinder<Widget, CapacityReportTimeInputWidget>
	{
	}

	public CapacityReportTimeInputWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

}
