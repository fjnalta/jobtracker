package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.gwtbootstrap3.client.ui.Row;

import com.google.gwt.core.client.GWT;
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
		jobsWidget.registerObserver(this);
		
		workWidget = new WorkDiscriptionWidget();
		workWidget.registerObserver(this);
		rowWork.add(workWidget);
		
		utilizationWidget.registerObserver(this);
		
		loadJobs(this.currentUserId);
		loadTemplates(this.currentUserId);
		loadUser(this.currentUserId);
	}

	private void loadJobs(Integer Id) {
		
		RestClient.build(new SuccessFunction<List<Job>>() {
			@Override
			public void onSuccess(Method method, List<Job> response) {

				jobsWidget.removeFromParent();
				jobsWidget = new JobsWidget();
				jobsWidget.registerObserver(self);
				jobsWidget.addJobs(response);
				rowJobs.add(jobsWidget);
				
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				//do error handling here
				
			}
		
		}).getEmployeeService().getAllJobs(Id);
		
	}
	
	private void loadTemplates(Integer Id){
		/*
		RestClient.build(new SuccessFunction<List<ActivityReportTemplate>>() {
			@Override
			public void onSuccess(Method method, List<ActivityReportTemplate> response) {

				workWidget.removeFromParent();
				workWidget = new WorkDiscriptionWidget();
				workWidget.registerObserver(self);
				workWidget.addTemplates(response);
				rowWork.add(workWidget);
				
			}
		}).getEmployeeService().getAllReportTemplates(Id);*/
		
	}
	
	private void loadUser(Integer Id) {
		/*
		RestClient.build(new SuccessFunction<User>() {
			@Override
			public void onSuccess(Method method, User response) {

				self.user = response;
				
			}
		}).getEmployeeService().getEmployee(Id);*/
	}
		
	private ActivityReport[] loadActivityReports(Long ID) {
		// TODO load all ActivityRepots for the current Employee
		return null;
	}

	private void saveActivityReportTemplate(ActivityReportTemplate template) {
		// TODO load
	}

	/**
	 * Method for booking a new ActivityReport The method gets the data from
	 * calendarWidget,JobsWidget and WorkDiscriptionWidget then it sends it to
	 * the backend
	 */
	public void bookActivityReport() {
		Job jobToSave = jobsWidget.getSelectedJob();
		// ActivityReport newReport = calendarWidget.getNewActivityReport();

	}

	/**
	 * updates and synchronizes all Widgets
	 */
	public void update() {
		loadJobs(this.currentUserId);
		loadTemplates(this.currentUserId);
	}

	public int getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(Integer currentUserId) {
		this.currentUserId = currentUserId;
	}

	public void saveTemplate() {
		ActivityReportTemplate template = workWidget.getTemplateToSave();
		/*
		RestClient.build(new SuccessFunction<ActivityReportTemplate>() {
			@Override
			public void onSuccess(Method method, ActivityReportTemplate response) {
				self.update();
			}
		}).getEmployeeService().saveReportTemplate(user.getId(), template);*/
		
	}

}
