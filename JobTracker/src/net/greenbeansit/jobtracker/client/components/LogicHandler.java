package net.greenbeansit.jobtracker.client.components;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import net.greenbeansit.jobtracker.client.components.widgets.calendar.CalendarWidget;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
import net.greenbeansit.jobtracker.shared.*;
import org.fusesource.restygwt.client.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller class which provide CRUD funtionallity for the HomePage including
 * save new Reports, new ReportTemplates, and load Reports from backend. Also it coordinates 
 * and synchronizes all widgets on the HomePage
 *
 * The workflow: the widget call the function load<SOMETHING>. The load method then calls the updateObservable function
 * on sucess. The updateObservable function must implement the get<SOMETHING> function.
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
	private List<PseudoJob> pseudoJobList = new ArrayList<PseudoJob>();
	private List<Integer> utilizationList = new ArrayList<Integer>();
	private User currentUser;
	private LogicHandler self = this;


	private Job currentJob;
	private ActivityReportTemplate currentTemplate;
	private CalendarWidget calendar;

	/**
	 * Standart constructor of this class
	 */
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
		List<PseudoJob> pseudoJobreport = new ArrayList<PseudoJob>();
		
		this.jobList = temp;
		this.templateList = reporttemp;
		this.pseudoJobList = pseudoJobreport;
		this.currentUser = new User();
		this.currentUser.setId(2);
	}

	/**
	 * Add one ObservableClass to the logicHandler
	 * @param w the Observable to add
	 */
	public void addObservable(LogicObservable w) {
		list.add(w);
	}

	/**
	 * Update all registered observables
	 */
	public void updateAllObservables() {
		for (LogicObservable p : list) {
			p.updateObservable();
		}
	}

	/**
	 * update one single observable
	 * @param p the observable to update
     */
	public void updateObservable(LogicObservable p){
		p.updateObservable();
	}

	/**
	 * delete one observable from the observable list
	 * @param w observable to delete
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

	/**
	 * sets the current active activity Report this function is needed for showing the current selected report and the
	 * stored information in it on all widgets
	 * @param report report to be set as active
     */
	public void setCurrentReport(ActivityReport report) {
		this.currentReport = report;
		Job tempJob = new Job();
		tempJob.setJobNr(report.getJobNr());
		tempJob.setPosNr(report.getPosNr());
		for(Job j : jobList){
			if(j.equals(tempJob)){
				this.setCurrentJob(j);
			}
		}
		ActivityReportTemplate tempTemplate = new ActivityReportTemplate();
		tempTemplate.setAuthor(report.getAuthor());
		tempTemplate.setText(report.getText());
		tempTemplate.setTaskId(report.getTaskId());
		GWT.log("current selected report :" + report.toString());
		this.currentTemplate = tempTemplate;
		this.updateAllObservables();
	}

	/**
	 * function for getting the currently loaded reports
	 * @return List<ActivityReport> object with the current loaded reports
     */
	public List<ActivityReport> getCurrentReportsList() {
		return currentReportsList;
	}

	/**
	 * function for setting the currently loaded reports
	 * @param currentReportsList the List<ActivityReport> object with the reports
     */
	public void setCurrentReportsList(List<ActivityReport> currentReportsList) {
		this.currentReportsList = currentReportsList;
	}

	/**
	 * function for getting the currently loaded jobs
	 * @return List<Job> object with the current loaded jobs
     */
	public List<Job> getJobList() {
		return jobList;
	}

	/**
	 * function for setting the currently loaded jobs
	 * @param jobList List<Job> object with the current loaded jobs
     */
	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	/**
	 * function for getting the currently loaded templates
	 * @return List<ActivityReportTemplate> object with the currently loaded {@link ActivityReportTemplate}
     */
	public List<ActivityReportTemplate> getTemplateList() {
		return templateList;
	}

	/**
	 * function for setting the currently loaded templates
	 * @param templateList List<ActivityReportTemplate> object with the
	 *                       {@link ActivityReportTemplate} to be saved
     */
	public void setTemplateList(List<ActivityReportTemplate> templateList) {
		this.templateList = templateList;
	}

	/**
	 * function for getting the currently active user
	 * @return User object of the current user
     */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * set the current active user
	 * @param currentUser {@link User}User object of the current user
     */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * get the currently set {@link ActivityReport}
	 * @return currrent set {@link ActivityReport} object
     */
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
				NotifyHelper.successMessage("Reports loaded from backend");
				self.setCurrentReportsList(response);
				self.updateAllObservables();
				calendar.updateObservable();
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				NotifyHelper.errorMessage("FAILED: " + exception.getMessage());
				GWT.log(exception.getMessage());
			}

		}).getEmployeeService().getAllReports(currentUser.getId());
	}
	
	/**
	 * Function for loading a set of {@link ActivityReport} between the specified start and end time
	 * On success is calls {@link #updateAllObservables()}to update the widgets
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
				self.setCurrentReportsList(response);
				self.updateAllObservables();
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				NotifyHelper.errorMessage("FAILED " + exception.getMessage());
				GWT.log(exception.getMessage());
			}

		}).getEmployeeService().getReportPeriod(currentUser.getId(), fromString+"-"+toString);
	}
	/**
	 * Function for saving an ActivityReport to the backend. It first collects all needed information,
	 * then if everything is correct it will try to save it.
	 * On success is calls {@link #updateAllObservables()}to update the widgets
	 * @param reportDummy a {@link ActivityReport} object with set time parameters
	 */
	public void saveReport(ActivityReport reportDummy) {
		getInformations();
		ActivityReport tempReport = reportDummy;

		if (currentTemplate != null && currentJob != null && currentUser != null) {
			tempReport.setJobNr(currentJob.getJobNr());
			tempReport.setPosNr(currentJob.getPosNr());
			tempReport.setText(currentTemplate.getText());
			tempReport.setTaskId(currentTemplate.getTaskId());
			tempReport.setAuthor(currentUser.getId());
			try {
				RestClient.build(new SuccessFunction<ActivityReport>() {
					@Override
					public void onSuccess(Method method, ActivityReport response) {
						self.updateAllObservables();
						NotifyHelper.successMessage("Report saved");
					}

					@Override
					public void onFailure(Method method, Throwable exception) {
						NotifyHelper.errorMessage("FAILED" + exception.getMessage());
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
	 * On success is calls {@link #updateAllObservables()}to update the widgets
	 * and calls {@link #loadTemplates()} to load the new templates list from the backend
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
					GWT.log("Template saved");
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
	 * Trys to save a pseudoJob to the backend
	 * @param pseudoJob {@link PseudoJob} to save
	 */
	public void savePseudoJob(PseudoJob pseudoJob) {
		final PseudoJob temp = pseudoJob;
		updateAllObservables();
		try {
			RestClient.build(new SuccessFunction<PseudoJob>() {
				@Override
				public void onSuccess(Method method, PseudoJob response) {
					self.loadPseudoJobs();
					self.updateAllObservables();
					NotifyHelper.successMessage("PseudoJob saved successfully!");
				}

				@Override
				public void onFailure(Method method, Throwable exception) {
					NotifyHelper.errorMessage(exception.getMessage());
					GWT.log(exception.getMessage());
				}

			}).getEmployeeService().savePseudoJob(currentUser.getId(), pseudoJob);
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
					GWT.log("loaded templates");
					self.setTemplateList(response);
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
	 * Try to load all {@link PseudoJob} from the backend
	 * On success is calls {@link #updateAllObservables()}to update the widgets
	 */
	public void loadPseudoJobs() {
		this.updateAllObservables();
		try {
			RestClient.build(new SuccessFunction<List<PseudoJob>>() {
				@Override
				public void onSuccess(Method method, List<PseudoJob> response) {
					GWT.log("loaded all pseudoJobs successfully");
					self.setPseudoJobList(response);
					self.updateAllObservables();
				}

				@Override
				public void onFailure(Method method, Throwable exception) {
					NotifyHelper.errorMessage(exception.getMessage());
					GWT.log(exception.getMessage());
				}

			}).getEmployeeService().getAllPseudoJobs(currentUser.getId());
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
					GWT.log("loaded all jobs successfully");
					self.setJobList(response);
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
	 * Method for loading All Users from the Backend
	 */
	public void loadUsers(){
		try {
			RestClient.build(new SuccessFunction<List<User>>() {
				@Override
				public void onSuccess(Method method, List<User> response) {
					self.setUserList(response);
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
		for(Job j : selectedJobs) {
			try {
				RestClient.build(new SuccessFunction<List<User>>() {
					@Override
					public void onSuccess(Method method, List<User> response) {
						self.setUserList(response);
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

	/**
	 * Method for loading the utilization for a specified month from the backend
	 * @param year year of the utilization to show
	 * @param month month of the utilization to show
     */
	public void loadUtilization(int year, int month){
		try {
			RestClient.build(new SuccessFunction<List<Integer>>() {
				@Override
				public void onSuccess(Method method, List<Integer> response) {
					self.setUtilizationList(response);
					self.updateAllObservables();
				}

				@Override
				public void onFailure(Method method, Throwable exception) {
					NotifyHelper.errorMessage(exception.getMessage());
					GWT.log(exception.getMessage());
				}

			}).getEmployeeService().getUtilizationDays(currentUser.getId(),year,month);
		} catch (Exception e) {
			GWT.log(e.getMessage());
		}
	}

	/**
	 * Function for
	 * @param day Date objet with the date of the day, the function should load the reports for
	 * @return the list with the reports for a day
     */
	public List<ActivityReport> getReportsForDay(Date day){
		List<ActivityReport> reportList = new ArrayList<ActivityReport>();
		for(ActivityReport p : currentReportsList){
			if(p.getDate().getDate()==day.getDate()&&p.getDate().getYear()==day.getYear()&&p.getDate().getMonth()==day.getMonth()){
				reportList.add(p);
				GWT.log("getReportsForDay: added " + p.getJobNr() + p.getText() + p.getDate().toString());
			}
		}
		return reportList;
	}

	/**
	 * function for returning the actual list of the utilization in integer values
	 * @return a List<Integer> obejct with the utilization values
     */
	public List<Integer> getUtilizationList(){
		return this.utilizationList;
	}

	/**
	 * function for setting the current utilization list
	 * @param utilizationList a List<Integer> object with the current utilization
     */
	public void setUtilizationList(List<Integer> utilizationList) {
		this.utilizationList = utilizationList;
	}

	/**
	 * get the currently loaded userList
	 * @return List<User>
     */
	public List<User> getUsers(){
		return this.userList;
	}

	/**
	 * get the current selected {@link Job}
	 * @return currently selected {@link Job} object
     */
	public Job getCurrentJob() {
		return currentJob;
	}

	/**
	 * set the current selected {@link Job}
	 * @param currentJob {@link Job} object to be set
     */
	public void setCurrentJob(Job currentJob) {
		this.currentJob = currentJob;
	}

	/**
	 * get the current selected {@link ActivityReportTemplate}
	 * @return current {@link ActivityReportTemplate}
     */
	public ActivityReportTemplate getCurrentTemplate() {
		return currentTemplate;
	}

	/**
	 * function for setting the current selected {@link ActivityReportTemplate}
	 * @param currentTemplate {@link ActivityReportTemplate} object to be set
     */
	public void setCurrentTemplate(ActivityReportTemplate currentTemplate) {
		this.currentTemplate = currentTemplate;
	}

	/**
	 * return the actual selectedJobs
	 * @return List<Job> with the current selected Jobs
     */
	public List<Job> getSelectedJobs() {
		return selectedJobs;
	}

	/**
	 * set the current selected Jobs
	 * @param selectedJobs List<Job> with the selected jobs
     */
	public void setSelectedJobs(List<Job> selectedJobs) {
		this.selectedJobs = selectedJobs;
	}

	/**
	 * gets the calendar widget corresponding to the logic handler
	 * @return {@link CalendarWidget} object
     */
	public CalendarWidget getCalendar() {
		return calendar;
	}

	/**
	 * sets the calendar widget for this handler
	 * @param calendar {@link CalendarWidget} object to be adressed
     */
	public void setCalendar(CalendarWidget calendar) {
		this.calendar = calendar;
	}

	/**
	 * get the loaded userlist
	 * @return List<User> object with all loaded {@link User}
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * set the users list
	 * @param userList List<User> object to set
     */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * get the current pseudojobs list
	 * @return List<PseudoJobs> object with currently loaded pseudoJobs
     */
	public List<PseudoJob> getPseudoJobList() {
		return pseudoJobList;
	}

	/**
	 * set the pseudoJobsList
	 * @param pseudoJobList List<PseudoJob> the pseudoJobsList to save
     */
	public void setPseudoJobList(List<PseudoJob> pseudoJobList) {
		this.pseudoJobList = pseudoJobList;
	}


}
