package net.greenbeansit.jobtracker.client.components;


import org.fusesource.restygwt.client.Method;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.widgets.CalendarWidget;
import net.greenbeansit.jobtracker.client.components.widgets.JobsWidget;
import net.greenbeansit.jobtracker.client.components.widgets.UtilizationWidget;
import net.greenbeansit.jobtracker.client.components.widgets.WorkDiscriptionWidget;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportService;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplateService;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.JobID;
import net.greenbeansit.jobtracker.shared.JobService;


public class HomePage extends Composite {

	private static HomePageUiBinder uiBinder = GWT.create(HomePageUiBinder.class);

	interface HomePageUiBinder extends UiBinder<Widget, HomePage> {
	}

	@UiField
	JobsWidget jobsWidget;

	@UiField
	CalendarWidget calendarWidget;

	@UiField
	UtilizationWidget utilizationWidget;

	@UiField
	WorkDiscriptionWidget workDiscriptionWidget;
	
	private Job currentJob;
	private Job[] allJobs;
	
	private ActivityReportService activityReportService;
	private ActivityReportTemplateService activityReporttemplateService;
	private JobService jobService;

	public HomePage() {
		initWidget(uiBinder.createAndBindUi(this));
		initialize();

	}

	/**
	 * function called in constuctor on initializing the widgets and connect
	 * them with the homePage
	 */
	private void initialize() {
		jobsWidget.registerObserver(this);
		calendarWidget.registerObserver(this);
		utilizationWidget.registerObserver(this);
		workDiscriptionWidget.registerObserver(this);
		loadJobs(1L);
	}
	
	
	private void loadJobs(Long Id){
		RestClient.build(new SuccessFunction<JobService>() {
			@Override
			public void onSuccess(Method method, JobService response) {
				jobService = response;
				allJobs = response.getAllJobs();
				jobsWidget.addJobs(allJobs);
			}
		}).getEmployeeService().getJobService(Id);
	}
	
	private void loadEmploye(Long Id){
		//TODO load the Employee Object from backend using the EmployeeID
	}
	
	private ActivityReport[] loadActivityReports(Long ID){
		//TODO load all ActivityRepots for the current Employee
		return null;
	}
	
	private ActivityReportTemplate[] loadActivityReportTemplates(Long ID){
		//TODO load templates using the emploeee ID
		return null;
	}
	
	private void saveActivityReportTemplate(ActivityReportTemplate template){
		//TODO load 
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
		this.currentJob = jobsWidget.getSelectedJob();
		Window.alert(currentJob.getJobID().toString() + "ICH WURDE AUF DER HOMEPAGE AUSGEFÜHRT");
	}

}
