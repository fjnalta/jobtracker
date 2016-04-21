package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.client.ui.LinkedGroup;
import org.gwtbootstrap3.client.ui.LinkedGroupItem;
import org.gwtbootstrap3.client.ui.TabPane;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class JobsActivity extends Composite
{

	private static JobsActivityUiBinder uiBinder = GWT.create(JobsActivityUiBinder.class);

	interface JobsActivityUiBinder extends UiBinder<Widget, JobsActivity> {
	}

	public JobsActivity() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	
	@UiField
	TabPane tabFavJobsContent;
	@UiField
	LinkedGroup tabAllJobsContent;
	
	//TODO - Load Job List from external data
	@UiHandler("tabAllJobs")
	public void onClickTabAllJobs(ClickEvent event){		
		LinkedGroupItem job1 = new LinkedGroupItem("Job1", "#home");
		LinkedGroupItem job2 = new LinkedGroupItem("Job2", "#home");
		LinkedGroupItem job3 = new LinkedGroupItem("Job3", "#home");
		LinkedGroupItem job4 = new LinkedGroupItem("Job4", "#home");
		LinkedGroupItem job5 = new LinkedGroupItem("Job5", "#home");
		
		tabAllJobsContent.add(job1);
		tabAllJobsContent.add(job2);
		tabAllJobsContent.add(job3);
		tabAllJobsContent.add(job4);
		tabAllJobsContent.add(job5);
	}
	
	@UiHandler("tabFavJobs")
	public void onClickTabFavJobs(ClickEvent event){
		LinkedGroupItem favjob1 = new LinkedGroupItem("FavJob1", "#home");
		LinkedGroupItem favjob2 = new LinkedGroupItem("FavJob2", "#home");
		LinkedGroupItem favjob3 = new LinkedGroupItem("FavJob3", "#home");
		
		tabFavJobsContent.add(favjob1);
		tabFavJobsContent.add(favjob2);
		tabFavJobsContent.add(favjob3);
	}

}
