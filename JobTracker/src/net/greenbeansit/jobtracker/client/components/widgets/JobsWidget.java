package net.greenbeansit.jobtracker.client.components.widgets;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.extras.select.client.ui.OptGroup;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.Job;

public class JobsWidget extends Composite implements LogicObservable {

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

	private Job currentJob = null;
	private List<Job> jobList = new ArrayList<Job>();

	public JobsWidget() {
		initWidget(uiBinder.createAndBindUi(this));

		handler.addObservable(this);
		handler.updateObservable(this);
		selectJob.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				currentJob = ((SelectJobOption) selectJob.getSelectedItem()).getJob();
				notifyLogicHandler();
			}
		});
		handler.loadJobs();
	}

	private void addJobs(List<Job> jobList) {
		for (Job currentJob : jobList) {
			SelectJobOption tempOption = new SelectJobOption(currentJob);
			allJobsOptGroup.add(tempOption);
		}
	}

	@Override
	public void updateObservable() {
		allJobsOptGroup.clear();
		this.jobList = handler.getJobList();
		addJobs(this.jobList);
		currentJob = handler.getCurrentJob();
		if (currentJob != null) {

			for (Option opt : selectJob.getItems()) {
				((SelectJobOption) opt).setSelected(false);
				if (((SelectJobOption) opt).getJob().equals(currentJob)) {
					opt.setSelected(true);
				}
			}
		}
		selectJob.refresh();

	}

	@Override
	public void notifyLogicHandler() {
		if(currentJob != null){
			handler.setCurrentJob(currentJob);
		}
		else{
			Job temp = new Job(null,null,null,null,null,null,null,null);
			handler.setCurrentJob(temp);
		}
	}
}
