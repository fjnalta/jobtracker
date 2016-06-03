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

/**
 * This Class Creates the Timeline Buttons on the Homepage.
 * @author Jonathan Brenner
 */
public class CalendarTimeLineButtons extends Composite implements CalendarObserver{

	private static CalendarTimeLineButtonsUiBinder uiBinder = GWT.create(CalendarTimeLineButtonsUiBinder.class);
	
	
	interface CalendarTimeLineButtonsUiBinder extends UiBinder<Widget, CalendarTimeLineButtons> {
	}

	/**
	 * Initializes a new Instance of {@link CalendarTimeLineButtons}
	 */
	public CalendarTimeLineButtons() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button buttonWeek;

	@UiField
	Button buttonMonth;

	/**
	 * Initializes a new Instance of {@link CalendarTimeLineButtons}
	 * @param firstName the FirstName.
     */
	public CalendarTimeLineButtons(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}

	/**
	 * Changes the Calendar view to monthly.
	 * @param event the ClickEvent.
     */
	@UiHandler("buttonMonth")
	public void onClickButtonMonth(ClickEvent event){
		calendarHandler.calendar.setView(ViewOption.month);
	}

	/**
	 * Changes the Calendar view to weekly.
	 * @param event the ClickEvent.
     */
	@UiHandler("buttonWeek")
	public void onClickButtonWeek(ClickEvent event){
		calendarHandler.calendar.setView(ViewOption.agendaWeek);
		//handler.calendar.render();
	}

	@Override
	public void update() {
		
	}

	@Override
	public void notifyHandler() {
		
	}

}
