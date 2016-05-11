package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.gwtbootstrap3.client.ui.Row;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.widgets.calendar.CalendarWidget;
import net.greenbeansit.jobtracker.client.components.widgets.JobsWidget;
import net.greenbeansit.jobtracker.client.components.widgets.UtilizationWidget;
import net.greenbeansit.jobtracker.client.components.widgets.WorkDiscriptionWidget;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;

public class HomePage extends Composite {

	private static HomePageUiBinder uiBinder = GWT.create(HomePageUiBinder.class);

	interface HomePageUiBinder extends UiBinder<Widget, HomePage> {
	}

	private HomePage self = this;
	
	private int currentUserId;
	
	@UiField
	Row rowJobs;
	JobsWidget jobsWidget;

	@UiField
	CalendarWidget calendarWidget;

	@UiField
	UtilizationWidget utilizationWidget;

	@UiField
	Row rowWork;
	WorkDiscriptionWidget workWidget;

	private Job currentJob;
	private Job[] allJobs;

	private User user;

	public HomePage() {
		this.currentUserId = 1;
		
		initWidget(uiBinder.createAndBindUi(this));
		initialize();
	}

	/**
	 * function called in constuctor on initializing the widgets and connect
	 * them with the homePage
	 */
	private void initialize() {
		jobsWidget = new JobsWidget();
		rowJobs.add(jobsWidget);
		
		workWidget = new WorkDiscriptionWidget();
		rowWork.add(workWidget);
	}


	public int getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(Integer currentUserId) {
		this.currentUserId = currentUserId;
	}

}
