package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.CalendarObserver;

public class CalendarTimeLineButtons extends Composite implements CalendarObserver{

	private static CalendarTimeLineButtonsUiBinder uiBinder = GWT.create(CalendarTimeLineButtonsUiBinder.class);
	
	
	interface CalendarTimeLineButtonsUiBinder extends UiBinder<Widget, CalendarTimeLineButtons> {
	}

	public CalendarTimeLineButtons() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button buttonWeek;

	@UiField
	Button buttonMonth;

	@UiField
	Button buttonYear;

	public CalendarTimeLineButtons(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	
	@UiHandler("buttonMonth")
	public void onClickButtonMonth(ClickEvent event){
		handler.calendar.setView(ViewOption.month);
		//handler.calendar.render();
	}

	@UiHandler("buttonWeek")
	public void onClickButtonWeek(ClickEvent event){
		handler.calendar.setView(ViewOption.agendaWeek);
		//handler.calendar.render();
	}

	@Override
	public void update() {
		
	}

	@Override
	public void notifyHandler() {
		
	}

}
