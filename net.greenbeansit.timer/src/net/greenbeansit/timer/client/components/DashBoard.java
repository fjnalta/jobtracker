package net.greenbeansit.timer.client.components;

import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Row;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.timer.client.components.widgets.CalendarActivity;
import net.greenbeansit.timer.client.components.widgets.CapazityActivity;
import net.greenbeansit.timer.client.components.widgets.DashBoardWidget;
import net.greenbeansit.timer.client.components.widgets.JobsActivity;
import net.greenbeansit.timer.client.components.widgets.UtilizationActivity;

public class DashBoard extends Composite {
	private static DashBoardUiBinder uiBinder = GWT.create(DashBoardUiBinder.class);

	interface DashBoardUiBinder extends UiBinder<Widget, DashBoard> {

	}

	@UiField
	Row first_row;
	
	@UiField 
	Row second_row;
	
	@UiField
	Row third_row;
	
	@UiField
	Column calender_column;

	public DashBoard() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void addWidget(String name) {

		switch (name) {
		case "home":
			first_row.add(new DashBoardWidget(new JobsActivity()));
			second_row.add(new DashBoardWidget(new JobsActivity()));
			third_row.add(new DashBoardWidget(new CalendarActivity()));
			calender_column.add(new DashBoardWidget(new CalendarActivity()));
			break;
			
		case "calendar":
			//widgetRow.add(new DashBoardWidget(new CalendarActivity()));
			break;
			
		case "utilization":
			//widgetRow.add(new DashBoardWidget(new UtilizationActivity()));
			break;
			
		case "capazity":
			//widgetRow.add(new DashBoardWidget(new CapazityActivity()));
			break;

		case "jobs":
			//widgetRow.add(new DashBoardWidget(new JobsActivity()));
			break;
		}
	}
	/*
	private boolean checkForWidget(String name) {
		boolean isThere = false;
		for (int i = 0; i < widgetRow.getWidgetCount(); i++) {
			if (widgetRow.getWidget(i).getTitle() == name)
				//System.out.println(widgetRow.getWidget(i).getTitle());
				isThere = true;
		}
		return isThere;
	}*/
}
