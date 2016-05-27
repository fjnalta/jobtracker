package net.greenbeansit.jobtracker.client.components.project;

import org.gwtbootstrap3.client.ui.Anchor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.shared.Job;

public class JobListItem extends Composite
{

	private static JobListItemUiBinder uiBinder = GWT
			.create(JobListItemUiBinder.class);

	interface JobListItemUiBinder extends UiBinder<Widget, JobListItem>
	{
	}
	
	@UiField
	Anchor	anchor;

	public JobListItem(Job job)
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		anchor.setHref("#project/job/" + job.getPosNr());
		anchor.add(new ProjectBudgetBar(job));
	}

	
}
