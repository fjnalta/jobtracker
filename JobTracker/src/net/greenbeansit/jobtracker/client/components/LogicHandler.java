package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;

import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
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
	private List<User> userList = new ArrayList<User>();
	private List<Job> selectedJobs = new ArrayList<Job>();
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
		List<Job> temp = new ArrayList<Job>();
		List<ActivityReportTemplate> reporttemp = new ArrayList<ActivityReportTemplate>();
		
		this.jobList = temp;
		this.templateList = reporttemp;
		this.currentUser = new User();
		this.currentUser.setId(2);
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
			p.updateObservable();
		}
	}
	
	public void updateObservable(LogicObservable p){
		p.updateObservable();
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
			o.notifyLogicHandler();
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
	 * Function for loading all Reports for the current user
	 */
	public void loadAllReports(){
		RestClient.build(new SuccessFunction<List<ActivityReport>>() {
			@Override
			public void onSuccess(Method method, List<ActivityReport> response) {
				self.currentReportsList = response;
				self.updateAllObservables();
				NotifyHelper.successMessage(response.toString());
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				NotifyHelper.errorMessage(exception.getMessage());
				GWT.log(exception.getMessage());
			}

		}).getEmployeeService().getAllReports(currentUser.getId());
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
				NotifyHelper.errorMessage(exception.getMessage());
				GWT.log(exception.getMessage());
			}

		}).getEmployeeService().getReportPeriod(currentUser.getId(), fromString+"-"+toString);
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
			tempReport.setPosNr(currentJob.getPosNr());
			tempReport.setText(currentJob.getDesc());

			tempReport.setText(currentTemplate.getText());
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
						NotifyHelper.errorMessage(exception.getMessage());
						GWT.log(exception.getMessage());
					}

				}).getEmployeeService().saveReport(currentUser.getId(), tempReport);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			NotifyHelper.errorMessage("Please fill in the missing fields");
		}
	}
	/**
	 * Trys to save a template to the backend
	 * @param template {@link ActivityReportTemplate} to save
	 */
	public void saveTemplate(ActivityReportTemplate template) {
		final ActivityReportTemplate temp = template;
		updateAllObservables();
		try {
			RestClient.build(new SuccessFunction<ActivityReportTemplate>() {
				@Override
				public void onSuccess(Method method, ActivityReportTemplate response) {
					self.loadTemplates();
					self.updateAllObservables();
					NotifyHelper.successMessage("Template saved successfully!");
				}

				@Override
				public void onFailure(Method method, Throwable exception) {
					NotifyHelper.errorMessage(exception.getMessage());
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
		this.updateAllObservables();
		try {
			RestClient.build(new SuccessFunction<List<ActivityReportTemplate>>() {
				@Override
				public void onSuccess(Method method, List<ActivityReportTemplate> response) {		
					self.templateList = response;
					self.updateAllObservables();
				}

				@Override
				public void onFailure(Method method, Throwable exception) {
					NotifyHelper.errorMessage(exception.getMessage());
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
		try {
			RestClient.build(new SuccessFunction<List<Job>>() {
				@Override
				public void onSuccess(Method method, List<Job> response) {
					GWT.log(String.valueOf(response.size()));
					self.jobList = response;
					self.updateAllObservables();
				}

				@Override
				public void onFailure(Method method, Throwable exception) {
					NotifyHelper.errorMessage(exception.getMessage());
					GWT.log(exception.getMessage());
//					Window.alert(exception.getMessage());
				}

			}).getEmployeeService().getAllJobs(currentUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Mehtod for loading All Users from the Backend
	 */
	public void loadUsers(){
		try {
			RestClient.build(new SuccessFunction<List<User>>() {
				@Override
				public void onSuccess(Method method, List<User> response) {
					self.userList = response;
					self.updateAllObservables();
				}

				@Override
				public void onFailure(Method method, Throwable exception) {
					NotifyHelper.errorMessage(exception.getMessage());
					GWT.log(exception.getMessage());
				}

			}).getEmployeeService().getAllUser();
		} catch (Exception e) {
			GWT.log(e.getMessage());
		}
	}
	
	/**
	 * Load all Users based on the selected Jobs
	 */
	public void loadUsersForSelectedJobs(){
		for(Job j : selectedJobs){
			try {
				RestClient.build(new SuccessFunction<List<User>>() {
					@Override
					public void onSuccess(Method method, List<User> response) {
						self.userList = response;
						self.updateAllObservables();
					}

					@Override
					public void onFailure(Method method, Throwable exception) {
						NotifyHelper.errorMessage(exception.getMessage());
						GWT.log(exception.getMessage());
					}

				}).getEmployeeService().getUsersToJob(j.getJobNr(), j.getPosNr());
			} catch (Exception e) {
				GWT.log(e.getMessage());
			}
		}
		
	}
	
	public List<User> getUsers(){
		return this.userList;
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
	public List<Job> getSelectedJobs() {
		return selectedJobs;
	}
	public void setSelectedJobs(List<Job> selectedJobs) {
		this.selectedJobs = selectedJobs;
	}

}
