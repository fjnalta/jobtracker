package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class JobsWidget extends Composite
{

	private static JobsWidgetUiBinder uiBinder = GWT.create(JobsWidgetUiBinder.class);

	interface JobsWidgetUiBinder extends UiBinder<Widget, JobsWidget>
	{
		
	}
	
	@UiField
	ClearFix container;
	
	@UiField
	Select selectJob;
	
	public JobsWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));
		

//		Scheduler.get().scheduleDeferred(new ScheduledCommand()
//		{
//			
//			@Override
//			public void execute() {
//				// TODO Auto-generated method stub
//				selectJob.render();
//				
//				Select select = new Select();
//				select.setLiveSearch(true);
//				select.setLiveSearchPlaceholder("Suche");
//				select.setPlaceholder("Jobnummer");
//				
//				for(int i = 0; i < 10; i++)
//				{
//					Option option = new Option();
//					option.setText("TEST" + i);
//					
//					select.add(option);
//				}
//
//				container.add(select);
//				
//				
//			}
//		});
		//new RenderTimer().schedule(300);
	}
}
