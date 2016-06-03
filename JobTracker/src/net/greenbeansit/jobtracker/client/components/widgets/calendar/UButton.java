package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;

/**
 * This class extends from Button and contain a date
 * @author ahmed
 *
 */
public class UButton extends Button {
 
	private Date date;

	/**
	 * Initializes a new Instance of UButton
	 * @param txt the Text.
	 * @param d the Date.
     */
	public UButton(String txt, Date d) {
		super(txt);
		this.date = d;
		
	}
	/**
	 * Return the Date of the day
	 * @return date object
	 */
	public Date getDate(){
		return date;
	}
}
