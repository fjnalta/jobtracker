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

	/**
	 * sets the current active activity Report
	 * @param report report to be set as active
     */
	public void setCurrentReport(ActivityReport report) {
		this.currentReport = report;
		Job tempJob = new Job();
		tempJob.setJobNr(report.getJobNr());
		tempJob.setPosNr(report.getPosNr());
		for(Job j : jobList){
			if(j.equals(tempJob)){
				this.currentJob = j;
				GWT.log("WTF" + j.getJobNr() +" " + j.getPosNr());
			}
		}
		ActivityReportTemplate tempTemplate = new ActivityReportTemplate();
		GWT.log(report.getAuthor()+" ");
		tempTemplate.setAuthor(report.getAuthor());
		GWT.log(report.getText());
		tempTemplate.setText(report.getText());
		GWT.log(report.getTaskId() + " ");
		tempTemplate.setTaskId(report.getTaskId());
		this.currentTemplate = tempTemplate;
		this.updateAllObservables();
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
				NotifyHelper.successMessage("Reports loaded from backend");
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
	 * @param reportDummy a {@link ActivityReport} object with set time parameters
	 */
	public void saveReport(ActivityReport reportDummy) {
		getInformations();
		ActivityReport tempReport = reportDummy;

		if (currentTemplate != null && currentJob != null && currentUser != null) {
			tempReport.setJobNr(currentJob.getJobNr());
			tempReport.setPosNr(currentJob.getPosNr());
			tempReport.setText(currentTemplate.getText());
			// TODO handle different JobTasks
			tempReport.setTaskId(currentTemplate.getTaskId());

			tempReport.setAuthor(currentUser.getId());
			
			try {
				RestClient.build(new SuccessFunction<ActivityReport>() {
					@Override
					public void onSuccess(Method method, ActivityReport response) {
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

	//TODO - does not work
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
					self.templateList = response;
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
	 * Try to load all {@link PseudoJob} from the backend
	 * On success is calls {@link #updateAllObservables()}to update the widgets
	 */

	public void loadPseudoJobs() {
		this.updateAllObservables();
		try {
			RestClient.build(new SuccessFunction<List<PseudoJob>>() {
				@Override
				public void onSuccess(Method method, List<PseudoJob> response) {
					self.pseudoJobList = response;
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
	 * Method for loading All Users from the Backend
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
		for(Job j : selectedJobs) {
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

	/**
	 * Mehtod for loading the utilization for a specified month from the backend
	 * @param year
	 * @param month
     */
	public void loadUtilization(int year, int month){
		try {
			RestClient.build(new SuccessFunction<List<Integer>>() {
				@Override
				public void onSuccess(Method method, List<Integer> response) {
					self.utilizationList = response;
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

	public List<Integer> getUtilizationList(){
		return this.utilizationList;
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
	public CalendarWidget getCalendar() {
		return calendar;
	}
	public void setCalendar(CalendarWidget calendar) {
		this.calendar = calendar;
	}

}
