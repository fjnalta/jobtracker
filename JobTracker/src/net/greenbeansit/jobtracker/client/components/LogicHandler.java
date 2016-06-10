package net.greenbeansit.jobtracker.client.components;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import net.greenbeansit.jobtracker.client.components.kapa.widgets.CapacityCalendarWidget;
import net.greenbeansit.jobtracker.client.components.widgets.UtilizationWidget;
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
 * Controller class which provide CRUD functionality for the HomePage including
 * save new Reports, new ReportTemplates, and load Reports from backend. Also it coordinates 
 * and synchronizes all widgets on the HomePage
 *
 * The workflow: the widget call the function load<SOMETHING>. The load method then calls the updateObservable function
 * on success. The updateObservable function must implement the get<SOMETHING> function.
 * 
 * @author Alexander Kirilyuk
 *
 */
public class LogicHandler {

	private ActivityReport currentReport;
	private ActivityReportTemplate currentTemplate;

	private List<LogicObservable> list = new ArrayList<>();

	private List<JobTask> jobTasksList = new ArrayList<JobTask>();
	private List<ActivityReport> currentReportsList = new ArrayList<ActivityReport>();
	private List<ActivityReportTemplate> templateList = new ArrayList<ActivityReportTemplate>();
	private List<UtilizationWeek> utilizationWeekList = new ArrayList<UtilizationWeek>();
	private List<Job> jobList = new ArrayList<Job>();
	private List<PseudoJob> pseudoJobList = new ArrayList<PseudoJob>();

	private List<Integer> utilizationList = new ArrayList<Integer>();
	private User currentUser;

	private Job currentJob;
	private PseudoJob currentPJob;
	private UtilizationWeek currentUtilizationWeek;


	private UtilizationWeek tempUtilizationWeek;

	private CapacityCalendarWidget capacityCalendar;
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
		List<UtilizationWeek> utilizationWeekreports = new ArrayList<UtilizationWeek>();
		List<JobTask> jobTasksTmp = new ArrayList<JobTask>();
		
		this.jobTasksList = jobTasksTmp;
		this.jobList = temp;
		this.templateList = reporttemp;
		this.pseudoJobList = pseudoJobreport;
		this.utilizationWeekList = utilizationWeekreports;

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
	 * sets the current active activity Report this function is needed for showing the current selected report and the
	 * stored information in it on all widgets
	 * @param report report to be set as active
	 */
	public void setCurrentReport(UtilizationWeek report) {
		UtilizationWeek tempUtilizationWeek = new UtilizationWeek();
		tempUtilizationWeek.setText(report.getText());
		for(UtilizationWeek j : utilizationWeekList){
			if(j.getText().equals(tempUtilizationWeek.getText())){
				this.setCurrentUtilizationWeek(j);
			}
		}
		this.currentUtilizationWeek = report;
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
	 * get the current capacity report list
	 * @return List<UtilizationWeek> object
     */
	public List<UtilizationWeek> getCurrentUtilizationWeekList(){
		return utilizationWeekList;
	}

	public void setCurrentUtilizationWeekList(List<UtilizationWeek> utilizationWeekList) {
		this.utilizationWeekList = utilizationWeekList;
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
	 * get the currently set {@link UtilizationWeek}
	 * @return currrent set {@link UtilizationWeek} object
	 */
	public UtilizationWeek getCurrentUtilizationWeek() {
		return currentUtilizationWeek;
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
				LogicHandler.this.setCurrentReportsList(response);
				LogicHandler.this.updateAllObservables();
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
	 * Function for loading all Utilization Weeks for the current user
	 */
	public void loadAllUtilizationWeeks(){
		RestClient.build(new SuccessFunction<List<UtilizationWeek>>() {
			@Override
			public void onSuccess(Method method, List<UtilizationWeek> response) {
				NotifyHelper.successMessage("Reports loaded from backend");
				LogicHandler.this.setCurrentUtilizationWeekList(response);
				LogicHandler.this.updateAllObservables();
				calendar.updateObservable();
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				NotifyHelper.errorMessage("FAILED: " + exception.getMessage());
				GWT.log(exception.getMessage());
			}

		}).getEmployeeService().getAllUtilizationWeeks(currentUser.getId());
	}

	/**
	 * Function for loading all Jobtasks for the current user
	 */
	public void getAllJobTasks(){
		RestClient.build(new SuccessFunction<List<JobTask>>() {

			@Override
			public void onSuccess(Method method, List<JobTask> response) {
				LogicHandler.this.setJobTasksList(response);
				LogicHandler.this.updateAllObservables();
				calendar.updateObservable();
				NotifyHelper.successMessage("JobTask´s loaded from backend");
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				NotifyHelper.errorMessage("FAILED: " + exception.getMessage());
				GWT.log(exception.getMessage());
			}

		}).getEmployeeService().getJobTasks(LogicHandler.this.getCurrentUser().getId());
	}
	
	/**
	 * Method to delete an {@link ActivityReport} from the Calendar and Backend.
	 * @param authorId the Author ID.
	 * @param reportId the Report ID.
     */
	public void deleteReport(Integer authorId, Integer reportId){
		final Integer reportID = reportId;
		RestClient.build(new SuccessFunction<List<ActivityReport>>() {
			@Override
			public void onSuccess(Method method, List<ActivityReport> response) {
				NotifyHelper.successMessage("Report deleted");
				for(ActivityReport report : currentReportsList){
					if(report.getId()==reportID){
						currentReportsList.remove(report);
					}
				}
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				NotifyHelper.errorMessage("FAILED: " + exception.getMessage());
				GWT.log(exception.getMessage());
			}
		}).getEmployeeService().deleteReport(currentReport.getAuthor(),reportId);
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
				LogicHandler.this.setCurrentReportsList(response);
				LogicHandler.this.updateAllObservables();
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
		final ActivityReport tempReport = reportDummy;

		if (currentTemplate != null && currentJob != null && currentUser != null) {
			tempReport.setId(0);
			tempReport.setJobNr(currentJob.getJobNr());
			tempReport.setPosNr(currentJob.getPosNr());
			tempReport.setText(currentTemplate.getText());
			tempReport.setTaskId(currentTemplate.getTaskId());
			tempReport.setAuthor(currentUser.getId());
			try {
				RestClient.build(new SuccessFunction<ActivityReport>() {
					@Override
					public void onSuccess(Method method, ActivityReport response) {
						LogicHandler.this.updateAllObservables();
						NotifyHelper.successMessage("Report saved");
					}

					@Override
					public void onFailure(Method method, Throwable exception) {
						NotifyHelper.errorMessage("FAILED" + exception.getMessage());
						GWT.log(exception.getMessage());
						calendar.addReportsToSave(tempReport);
					}

				}).getEmployeeService().saveReport(currentUser.getId(), tempReport);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

		}
	}

	/**
	 * Function for saving an UtilizationWeek to the backend. It first collects all needed information,
	 * then if everything is correct it will try to save it.
	 * On success is calls {@link #updateAllObservables()}to update the widgets
	 * @param report
	 * 			the {@link UtilizationWeek}.
	 */
	public void saveUtilizationWeek(UtilizationWeek report) {
		if (report != null && currentUser != null && tempUtilizationWeek!=null) {
			final UtilizationWeek tempReport = report;
			tempReport.setName(tempUtilizationWeek.getName());
			GWT.log(tempUtilizationWeek.getName());
			tempReport.setText(tempUtilizationWeek.getText());
			tempReport.setAuthor(currentUser.getId());
			tempReport.setPseudoJobId(tempUtilizationWeek.getPseudoJobId());
			tempReport.setPossibility(tempUtilizationWeek.getPossibility());
			try {
				RestClient.build(new SuccessFunction<UtilizationWeek>() {
					@Override
					public void onSuccess(Method method, UtilizationWeek response) {
						GWT.log(tempReport.toString());
						LogicHandler.this.updateAllObservables();
						NotifyHelper.successMessage("Report saved");
					}

					@Override
					public void onFailure(Method method, Throwable exception) {
						NotifyHelper.errorMessage("FAILED" + exception.getMessage());
						GWT.log(exception.getMessage());
					}

				}).getEmployeeService().saveUtilizationWeek(currentUser.getId(), tempReport);
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
					LogicHandler.this.loadTemplates();
					LogicHandler.this.updateAllObservables();
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
					LogicHandler.this.loadPseudoJobs();
					LogicHandler.this.updateAllObservables();
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
					LogicHandler.this.setTemplateList(response);
					LogicHandler.this.updateAllObservables();

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
		try {
			RestClient.build(new SuccessFunction<List<PseudoJob>>() {
				@Override
				public void onSuccess(Method method, List<PseudoJob> response) {
					GWT.log("loaded all pseudoJobs successfully");
					LogicHandler.this.setPseudoJobList(response);
					LogicHandler.this.updateAllObservables();
				}

				@Override
				public void onFailure(Method method, Throwable exception) {
					NotifyHelper.errorMessage(exception.getMessage());
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
					LogicHandler.this.setJobList(response);
					LogicHandler.this.updateAllObservables();
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
	 * Method for loading the utilization for a specified month from the backend
	 * @param year year of the utilization to show
	 * @param month month of the utilization to show
     */
	public void loadUtilization(int year, int month){
		try {
			RestClient.build(new SuccessFunction<List<Integer>>() {
				@Override
				public void onSuccess(Method method, List<Integer> response) {
					LogicHandler.this.setUtilizationList(response);
					LogicHandler.this.updateAllObservables();
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
	 * Method for loading the utilization for a specific month in the given year
	 * @param year the Year.
     */
	public void loadUtilizationYear(int year){
		try {
			RestClient.build(new SuccessFunction<List<Integer>>() {
				@Override
				public void onSuccess(Method method, List<Integer> response) {
					LogicHandler.this.setUtilizationList(response);
					LogicHandler.this.updateAllObservables();
					GWT.log("utilization loaded");
				}

				@Override
				public void onFailure(Method method, Throwable exception) {
					NotifyHelper.errorMessage(exception.getMessage());
					GWT.log(exception.getMessage());
				}

			}).getEmployeeService().getUtilizationMonths(currentUser.getId(),year);
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
	 * get the current selected {@link Job}
	 * @return currently selected {@link Job} object
     */
	public Job getCurrentJob() {
		return currentJob;
	}

	/**
	 * get the current {@link PseudoJob}
	 * @return {@link PseudoJob} object
     */
	public PseudoJob getCurrentPseudoJob(){
		return currentPJob;
	}

	/**
	 * set the current {@link PseudoJob}
	 * @param currentPJob {@link PseudoJob} object
     */
	public void setCurrentPJob(PseudoJob currentPJob) {
		this.currentPJob = currentPJob;
	}

	/**
	 * set the current selected {@link Job}
	 * @param currentJob {@link Job} object to be set
     */
	public void setCurrentJob(Job currentJob) {
		this.currentJob = currentJob;
	}

	/**
	 * set the current selected {@link Job}
	 * @param currentJob {@link Job} object to be set
	 */
	public void setCurrentUtilizationWeek(UtilizationWeek currentJob) {
		this.currentUtilizationWeek = currentJob;
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
	 * set the CapazityCalendarWidget
	 * @param calendar the {@link CapacityCalendarWidget}
     */
	public void setCapacityCalendar(CapacityCalendarWidget calendar) {
		this.capacityCalendar = calendar;
	}

	/**
	 * get the CapacityCalendarWidget
	 * @return the {@link CapacityCalendarWidget}
     */
	public CapacityCalendarWidget getCapacityCalendar(){
		return this.capacityCalendar;
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
	/**
	 * get the {@link JobTask} {@link List}
	 * @return {@link List} object with the currently loaded JobTask.
	 */
	public List<JobTask> getJobTasksList() {
		return jobTasksList;
	}
	
	/**
	 * set the {@link JobTask} {@link List}
	 * @param jobTasksList {@link List} object with the {@link JobTask}s to save
	 */
	public void setJobTasksList(List<JobTask> jobTasksList) {
		this.jobTasksList = jobTasksList;
	}

	/**
	 * Get's the temporary {@link UtilizationWeek}.
	 * @return the {@link UtilizationWeek}
     */
	public UtilizationWeek getTempUtilizationWeek() {
		return tempUtilizationWeek;
	}

	/**
	 * Set's the temporary {@link UtilizationWeek}
	 * @param tempUtilizationWeek the {@link UtilizationWeek}.
     */
	public void setTempUtilizationWeek(UtilizationWeek tempUtilizationWeek) {
//		GWT.log("LogicHandler: TempUtilizationName: " + tempUtilizationWeek.getText());
		this.tempUtilizationWeek = tempUtilizationWeek;
	}
}
