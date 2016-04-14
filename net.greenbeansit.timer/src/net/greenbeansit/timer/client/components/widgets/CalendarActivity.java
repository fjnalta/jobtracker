package net.greenbeansit.timer.client.components.widgets;

import org.gwtbootstrap3.extras.fullcalendar.client.ui.FullCalendar;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class CalendarActivity extends DashBoardActivity
{

	private static CalendarWidgetUiBinder uiBinder = GWT.create(CalendarWidgetUiBinder.class);

	interface CalendarWidgetUiBinder extends UiBinder<Widget, CalendarActivity>
	{
		
	}

	@UiField
	HTMLPanel container;
	
	FullCalendar calendar;
	
	public CalendarActivity() 
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		calendar = new FullCalendar("some_unique_id", ViewOption.agendaWeek, false);
		
		container.add(calendar);
	}


}
