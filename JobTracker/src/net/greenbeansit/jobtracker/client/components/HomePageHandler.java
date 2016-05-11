package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;

import net.greenbeansit.jobtracker.client.components.widgets.JobsWidget;
import net.greenbeansit.jobtracker.client.components.widgets.calendar.FullCalendarCustomize;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportTemplate;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;

public class HomePageHandler {
	
	private ActivityReport currentReport;
	private List<HomePageObservable> list = new ArrayList<>();
	private List<ActivityReport> currentReportsList = new ArrayList<ActivityReport>();
	private List<Job> jobList = new ArrayList<Job>();
	private List<ActivityReportTemplate> templateList = new ArrayList<ActivityReportTemplate>();
	private User currentUser;
	private HomePageHandler self = this;
	
	private Job currentJob;
	private ActivityReportTemplate currentTemplate;
	

	/**
	 * 
	 * @param w
	 */
	public void addObservable(HomePageObservable w) {

		for (HomePageObservable p : list) {
			if (p.getClass().equals(w.getClass()))
				return;
		}
		list.add(w);
	}

	
	/**
	 * 
	 */
	public void updateObservable(HomePageObservable obs) {
		for (HomePageObservable p : list) {
			if (p.getClass() == obs.getClass()) {

			} else {
				p.update();
			}
		}
	}

	/**
	 * 
	 * @param w
	 */
	public void deleteObservable(HomePageObservable w) {
		list.remove(w);
	}
	
	public void getInformations(){
		for(HomePageObservable o : list){
			o.notifyHandler();
		}
	}
	
	
	public void setCurrentReport(ActivityReport report){
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
	
	public void loadReportPeriod(Date from, Date to){
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
	
	public void saveReport(ActivityReport reportDummy){
		getInformations();
		ActivityReport tempReport = reportDummy;
		
		if(currentTemplate!=null&&currentJob!=null&&currentUser!=null){
			tempReport.setJobNr(currentJob.getJobNr());
			tempReport.setJobPosNr(currentJob.getPosNr());
			tempReport.setJobDesc(currentJob.getDesc());
			
			tempReport.setText(currentTemplate.getDescription());
			//TODO handle different JobTasks
			tempReport.setTaskId(0);
			
			tempReport.setAuthor(currentUser.getId());
		}else{
			//TODO error Handling
		}
		
		try{
			RestClient.build(new SuccessFunction<ActivityReport>() {
				@Override
				public void onSuccess(Method method, ActivityReport response) {
					
				}

				@Override
				public void onFailure(Method method, Throwable exception) {
					GWT.log(exception.getMessage());
				}
			
			}).getEmployeeService().createReport(currentUser.getId(), tempReport);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void saveTemplate(ActivityReport template){
		
	}
	
	public void loadTemplates(){
		
	}
	
	public void loadJobs(){
		
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
	}
	
	
	
	
}
