package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.client.ui.LinkedGroup;
import org.gwtbootstrap3.client.ui.LinkedGroupItem;
import org.gwtbootstrap3.client.ui.TabPane;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class JobsWidget extends Composite
{

	private static JobsWidgetUiBinder uiBinder = GWT.create(JobsWidgetUiBinder.class);

	interface JobsWidgetUiBinder extends UiBinder<Widget, JobsWidget> {
	}
	
	@UiField
	TabPane tabFavJobsContent;
	@UiField
	LinkedGroup tabAllJobsContent;

	public JobsWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
		LinkedGroupItem job1 = new LinkedGroupItem("Job1", "#home");
		LinkedGroupItem job2 = new LinkedGroupItem("Job2", "#home");
		LinkedGroupItem job3 = new LinkedGroupItem("Job3", "#home");
		LinkedGroupItem job4 = new LinkedGroupItem("Job4", "#home");
		LinkedGroupItem job5 = new LinkedGroupItem("Job5", "#home");
		
		LinkedGroupItem favjob1 = new LinkedGroupItem("FavJob1", "#home");
		LinkedGroupItem favjob2 = new LinkedGroupItem("FavJob2", "#home");
		LinkedGroupItem favjob3 = new LinkedGroupItem("FavJob3", "#home");
		
		tabAllJobsContent.add(job1);
		tabAllJobsContent.add(job2);
		tabAllJobsContent.add(job3);
		tabAllJobsContent.add(job4);
		tabAllJobsContent.add(job5);
		
		tabFavJobsContent.add(favjob1);
		tabFavJobsContent.add(favjob2);
		tabFavJobsContent.add(favjob3);
	}
}
