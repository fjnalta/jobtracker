package net.greenbeansit.jobtracker.client.components.widgets;

import java.util.List;

import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.extras.select.client.ui.OptGroup;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.HomePage;
import net.greenbeansit.jobtracker.client.components.HomePageObservable;
import net.greenbeansit.jobtracker.shared.Job;

public class JobsWidget extends Composite implements HomePageObservable {

	private static JobsWidgetUiBinder uiBinder = GWT.create(JobsWidgetUiBinder.class);

	interface JobsWidgetUiBinder extends UiBinder<Widget, JobsWidget> {

	}

	@UiField
	ClearFix container;

	@UiField
	Select selectJob;

	@UiField
	OptGroup favoriteJobsOptGroup;

	@UiField
	OptGroup allJobsOptGroup;

	private HomePage homePage;
	private Job selectedJob;

	public JobsWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		selectJob.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				selectedJob = ((SelectJobOption)selectJob.getSelectedItem()).getJob();
			}
		});
	}
	
	public Job getSelectedJob() {
		return selectedJob;
	}

	public void addJobs(List<Job> jobList) {
		for (Job currentJob : jobList) {
			SelectJobOption tempOption = new SelectJobOption(currentJob);
			allJobsOptGroup.add(tempOption);

		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void notifyHandler() {
		// TODO Auto-generated method stub
	}
}
