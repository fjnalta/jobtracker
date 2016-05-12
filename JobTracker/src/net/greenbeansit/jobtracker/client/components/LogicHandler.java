package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;

import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;
/**
 * Controller class which provide CRUD funtionallity for the HomePage including
 * save new Reports, new ReportTemplates, and load Reports from backend. Also it coordinates 
 * and synchronizes all widgets on the HomePage
 * 
 * @author Alexander Kirilyuk
 *
 */
public class LogicHandler {

	private ActivityReport currentReport;
	private List<LogicObservable> list = new ArrayList<>();
	private List<ActivityReport> currentReportsList = new ArrayList<ActivityReport>();
	private List<Job> jobList = new ArrayList<Job>();
	private List<ActivityReportTemplate> templateList = new ArrayList<ActivityReportTemplate>();
	private User currentUser;
	private LogicHandler self = this;

	private Job currentJob;
	private ActivityReportTemplate currentTemplate;

	
	public LogicHandler(){
		initialize();
	}
	/**
	 * function to call on first start, this will load all needet data from the 
	 * backend
	 */
	private void initialize() {
		//this.currentJob = new Job(0,0,0,0,"",0,0);
		List<Job> temp = new ArrayList<Job>();
		temp.add(new Job(1,2,3,4,"5",6,7));
		temp.add(new Job(1,2,3,4,"5",6,7));
		temp.add(new Job(1,2,3,4,"5",6,7));
		temp.add(new Job(1,2,3,4,"5",6,7));
		
		List<ActivityReportTemplate> reporttemp = new ArrayList<ActivityReportTemplate>();
		reporttemp.add(new ActivityReportTemplate("temp","temp2",1L));
		reporttemp.add(new ActivityReportTemplate("temp","temp2",1L));
		reporttemp.add(new ActivityReportTemplate("temp","temp2",1L));
		reporttemp.add(new ActivityReportTemplate("temp","temp2",1L));
		
		this.jobList = temp;
		this.templateList = reporttemp;
	}

	/**
	 * 
	 * @param w
	 */
	public void addObservable(LogicObservable w) {
		list.add(w);
	}

	/**
	 * 
	 */
	public void updateAllObservables() {
		for (LogicObservable p : list) {
			p.update();
		}
	}
	
	public void updateObservable(LogicObservable p){
		p.update();
	}

	/**
	 * 
	 * @param w
	 */
	public void deleteObservable(LogicObservable w) {
		list.remove(w);
	}
	
	/**
	 * function for getting information of all widgets 
	 * the widgets which implement this function should set the 
	 * information they store on the handler object
	 */
	public void getInformations() {
		for (LogicObservable o : list) {
			o.notifyHandler();
		}
	}

	public void setCurrentReport(ActivityReport report) {
		this.currentReport = report;
	}

	public List<ActivityReport> getCurrentReportsList() {
		return currentReportsList;
	}

	public void setCurrentReportsList(List<ActivityReport> currentReportsList) {
		this.currentReportsList = currentReportsList;
	}

	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	public List<ActivityReportTemplate> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<ActivityReportTemplate> templateList) {
		this.templateList = templateList;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public ActivityReport getCurrentReport() {
		return currentReport;
	}
	
	/**
	 * Function for loading a set of {@link ActivityReport} between the specified start and end time
	 * @param from Date object which specifies the start time
	 * @param to Date object which specifiels the end time
	 */
	public void loadReportPeriod(Date from, Date to) {
		DateTimeFormat formatTime = DateTimeFormat.getFormat("dd.MM.yyyy");
		String fromString = formatTime.format(from);
		String toString = formatTime.format(to);

		RestClient.build(new SuccessFunction<List<ActivityReport>>() {
			@Override
			public void onSuccess(Method method, List<ActivityReport> response) {
				self.currentReportsList = response;
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				GWT.log(exception.getMessage());
			}

		}).getEmployeeService().getReportPeriod(currentUser.getId(), fromString, toString);
	}
	/**
	 * Function for saving an ActivityReport to the backend. It first collects all needed information,
	 * then if everything is correct it will try to save it.
	 * @param reportDummy a {@link ActivityReport} object with set time parameters
	 */
	public void saveReport(ActivityReport reportDummy) {
		getInformations();
		ActivityReport tempReport = reportDummy;

		if (currentTemplate != null && currentJob != null && currentUser != null) {
			tempReport.setJobNr(currentJob.getJobNr());
			tempReport.setJobPosNr(currentJob.getPosNr());
			tempReport.setJobDesc(currentJob.getDesc());

			tempReport.setText(currentTemplate.getDescription());
			// TODO handle different JobTasks
			tempReport.setTaskId(0);

			tempReport.setAuthor(currentUser.getId());
			
			try {
				RestClient.build(new SuccessFunction<ActivityReport>() {
					@Override
					public void onSuccess(Method method, ActivityReport response) {

					}

					@Override
					public void onFailure(Method method, Throwable exception) {
						GWT.log(exception.getMessage());
					}

				}).getEmployeeService().createReport(currentUser.getId(), tempReport);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			// TODO error Handling
		}
	}
	/**
	 * Trys to save a template to the backend
	 * @param template {@link ActivityReportTemplate} to save
	 */
	public void saveTemplate(ActivityReportTemplate template) {
		templateList.add(template);
		updateAllObservables();
		try {
			RestClient.build(new SuccessFunction<ActivityReportTemplate>() {
				@Override
				public void onSuccess(Method method, ActivityReportTemplate response) {
				}

				@Override
				public void onFailure(Method method, Throwable exception) {
					//TODO error handling
					GWT.log(exception.getMessage());
				}

			}).getEmployeeService().saveReportTemplate(currentUser.getId(), template);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Try to load all {@link ActivityReportTemplate} from the backend
	 * On success is calls {@link #updateAllObservables()}to update the widgets 
	 */
	
	public void loadTemplates() {
		//only for dummy implementation
		this.updateAllObservables();
		
		try {
			RestClient.build(new SuccessFunction<ActivityReport>() {
				@Override
				public void onSuccess(Method method, ActivityReport response) {
					self.updateAllObservables();
				}

				@Override
				public void onFailure(Method method, Throwable exception) {
					//TODO error handling
					GWT.log(exception.getMessage());
				}

			}).getEmployeeService().getAllReportTemplates(currentUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Try to load all {@link Job} from the backend
	 * on sucess call the {@link #updateAllObservables()} to update the widgets
	 */
	public void loadJobs() {
		//only for dummy implementation
		this.updateAllObservables();
		
		try {
			RestClient.build(new SuccessFunction<List<Job>>() {
				@Override
				public void onSuccess(Method method, List<Job> response) {
						self.jobList = response;
						self.updateAllObservables();
				}

				@Override
				public void onFailure(Method method, Throwable exception) {
					//TODO error handling
					GWT.log(exception.getMessage());
				}

			}).getEmployeeService().getAllJobs(currentUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Job getCurrentJob() {
		return currentJob;
	}

	public void setCurrentJob(Job currentJob) {
		this.currentJob = currentJob;
	}

	public ActivityReportTemplate getCurrentTemplate() {
		return currentTemplate;
	}

	public void setCurrentTemplate(ActivityReportTemplate currentTemplate) {
		this.currentTemplate = currentTemplate;
		this.updateAllObservables();
	}

}
