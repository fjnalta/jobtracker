package net.greenbeansit.jobtracker.client.components;

import org.gwtbootstrap3.client.ui.Row;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.widgets.JobsWidget;
import net.greenbeansit.jobtracker.client.components.widgets.UtilizationWidget;
import net.greenbeansit.jobtracker.client.components.widgets.WorkDescriptionWidget;
import net.greenbeansit.jobtracker.client.components.widgets.calendar.CalendarWidget;
import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;

/**
 * Allows the user to create {@link ActivityReport}s.
 * 
 * @author Max Blatt
 */
public class HomePage extends Composite
{

	private static HomePageUiBinder uiBinder = GWT
			.create(HomePageUiBinder.class);

	/**
	 * UiBinder for the {@link HomePage}.
	 * 
	 * @author Max Blatt
	 */
	interface HomePageUiBinder extends UiBinder<Widget, HomePage>
	{
	}

	
	@UiField
	Row						rowJobs;
	JobsWidget				jobsWidget;

	@UiField
	CalendarWidget			calendarWidget;

	@UiField
	UtilizationWidget		utilizationWidget;

	@UiField
	Row						rowWork;
	WorkDescriptionWidget workWidget;

	private Job				currentJob;
	private Job[]			allJobs;

	private User			user;
	
	private int				currentUserId;

	/**
	 * Initializes a new instance of the {@link HomePage} class.
	 */
	public HomePage()
	{
		this.currentUserId = 1;
		initWidget(uiBinder.createAndBindUi(this));
		initialize();
	}

	/**
	 * Initializes the widgets.
	 */
	private void initialize()
	{
		jobsWidget = new JobsWidget();
		rowJobs.add(jobsWidget);

		workWidget = new WorkDescriptionWidget();
		rowWork.add(workWidget);
	}

	/**
	 * Gets the ID of the current user.
	 * 
	 * @return an integer value.
	 */
	public int getCurrentUserId()
	{
		return currentUserId;
	}

	/**
	 * Sets the ID of the current user.
	 * 
	 * @param currentUserId the new ID.
	 */
	public void setCurrentUserId(Integer currentUserId)
	{
		this.currentUserId = currentUserId;
	}

}
