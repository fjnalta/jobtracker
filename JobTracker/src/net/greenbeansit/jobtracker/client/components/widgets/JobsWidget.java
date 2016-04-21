package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.extras.select.client.ui.Select;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class JobsWidget extends Composite
{

	private static JobsWidgetUiBinder uiBinder = GWT.create(JobsWidgetUiBinder.class);

	interface JobsWidgetUiBinder extends UiBinder<Widget, JobsWidget>
	{
		
	}
	
	
	@UiField
	Select selectJob;
	
	public JobsWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		new RenderTimer().schedule(300);
	}
	
	private class RenderTimer extends Timer
	{
		@Override
		public void run()
		{
			selectJob.render();
		}
		
	}
}
