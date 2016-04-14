package net.greenbeansit.timer.client.components;

import org.gwtbootstrap3.client.ui.Row;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.timer.client.components.widgets.CalendarActivity;
import net.greenbeansit.timer.client.components.widgets.DashBoardWidget;
import net.greenbeansit.timer.client.components.widgets.JobsActivity;

public class DashBoard extends Composite
{
	private static DashBoardUiBinder uiBinder = GWT.create(DashBoardUiBinder.class);

	interface DashBoardUiBinder extends UiBinder<Widget, DashBoard>
	{
		
	}


	@UiField
	Row widgetRow;
	
	public DashBoard()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void addWidget(String name)
	{
		switch(name){
		
		case "calendar":
			widgetRow.add(new DashBoardWidget(new CalendarActivity()));
			break;
			
		case "jobs":
			widgetRow.add(new DashBoardWidget(new JobsActivity()));
			break;
		}
	}
}
