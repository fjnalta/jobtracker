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
 
	private Date date;
	private UButton self = this;
	
	public UButton(String txt, final Date d, final Date calcDate, final CalendarUtilizationWidget u) {
		super(txt);
		this.date = d;
		
		this.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final int diff =	CalendarUtil.getDaysBetween(calcDate, d);
				int month = calcDate.getMonth();
				CalendarUtil.addDaysToDate(calcDate, diff);
				
				changeDateToSunday(calcDate);
					
				if(month != calcDate.getMonth()){
					u.setCalculateUtilization(true);	
					u.handler.loadUtilization(calcDate.getYear()+1900, calcDate.getMonth() +1);
					
				}else{
					u.render();	
				}
				
				
				
				calendarHandler.calendar.goToDate(self.getDate());
			}
			
			@SuppressWarnings("deprecation")
			private void changeDateToSunday(Date d){
				while(d.getDay() > 0){
					CalendarUtil.addDaysToDate(d, -1);
				}
				
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
	@Override
	public void notifyHandler() {
		//IDLE
		
	}
}
