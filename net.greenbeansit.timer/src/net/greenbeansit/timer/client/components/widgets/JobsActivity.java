package net.greenbeansit.timer.client.components.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class JobsActivity extends DashBoardActivity
{

	private static JobsActivityUiBinder uiBinder = GWT.create(JobsActivityUiBinder.class);

	interface JobsActivityUiBinder extends UiBinder<Widget, JobsActivity> {
	}

	public JobsActivity() {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
