package net.greenbeansit.jobtracker.client.components.widgets.calendar;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;

/**
 * This class extends from Button and contain a date
 * @author Yangus
 *
 */
public class UButton extends Button {
 
	Date date;
	public UButton(String txt, Date d) {
		super(txt);
		this.date = d;

	}
	

}
