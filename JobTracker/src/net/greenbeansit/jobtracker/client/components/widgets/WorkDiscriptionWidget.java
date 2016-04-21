package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.extras.select.client.ui.Select;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class WorkDiscriptionWidget extends Composite
{

	private static WorkDiscriptionWidgetUiBinder uiBinder = 
			GWT.create(WorkDiscriptionWidgetUiBinder.class);

	interface WorkDiscriptionWidgetUiBinder extends UiBinder<Widget, WorkDiscriptionWidget>
	{
		
	}

	@UiField
	Select selectTemplate;
	
	public WorkDiscriptionWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		new RenderTimer().schedule(500);
	}


	private class RenderTimer extends Timer
	{
		@Override
		public void run()
		{
			selectTemplate.render();
		}
		
	}
}
