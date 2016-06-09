package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.datepicker.client.CalendarUtil;

import net.greenbeansit.jobtracker.client.components.CalendarObserver;

/**
 * This class extends from Button and contain a date
 * @author ahmed
 *
 */
public class UButton extends Button implements CalendarObserver{
 
	private Date 						date;

	/**
	 * Initializes a new Instance of UButton
	 * @param txt the Text
	 * @param d the Date
	 * @param calcDate the calculated Date
     * @param calUtil the CalendarUtilizationWidget
     */
	public UButton(String txt, final Date d, final Date calcDate, final CalendarUtilizationWidget calUtil) {
		super(txt);
		
		this.date = d;
		
		this.addClickHandler(new ClickHandler() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(ClickEvent event) {
				
				final int diff = CalendarUtil.getDaysBetween(calcDate, d);
				
				int month = calcDate.getMonth();
				
				CalendarUtil.addDaysToDate(calcDate, diff);
				
				changeDateToMonday(calcDate);
					
				if(month != calcDate.getMonth()){
				
					calUtil.setCalculateUtilization(true);	
					
					calUtil.handler.loadUtilization(calcDate.getYear()+1900, calcDate.getMonth() +1);
				}else{
					
					calUtil.render();	
				}
				
				calendarHandler.calendar.goToDate(UButton.this.getDate());
				notifyHandler();
			}

		});
		
	}
	/**
	 * Return the Date of the day
	 * @return date object
	 */
	public final Date getDate(){
		return date;
	}
	
	@Override
	public void update() {
		//IDLE
		
	}
	
	/**
	 * Changes the Date to Monday
	 * @param d the Date.
     */
	@SuppressWarnings("deprecation")
	private void changeDateToMonday(Date d){
		
		while(d.getDay() > 1 || d.getDay() == 0){
			
			CalendarUtil.addDaysToDate(d, -1);
		}
		
	}
	
	@Override
	public void notifyHandler() {
		changeDateToMonday(date);
		calendarHandler.setDisplayMonth( this.date.getMonth() + 1 );
		
		calendarHandler.updateObserver(this);
		
	}
}
