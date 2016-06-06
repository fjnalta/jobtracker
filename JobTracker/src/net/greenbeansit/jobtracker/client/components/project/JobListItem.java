package net.greenbeansit.jobtracker.client.components.project;

import org.gwtbootstrap3.client.ui.Anchor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.shared.Job;

/**
 * Wrapper around the {@link ProjectBudgetBar} that allows it to be used as
 * HTML anchor.
 *  
 * @author Max Blatt
 */
public class JobListItem extends Composite
{

	private static JobListItemUiBinder uiBinder = GWT
			.create(JobListItemUiBinder.class);

	/**
	 * UiBinder for {@link JobListItem}.
	 * 
	 * @author Max Blatt
	 */
	interface JobListItemUiBinder extends UiBinder<Widget, JobListItem>
	{
	}
	
	@UiField
	Anchor	anchor;

	
	/**
	 * Initializes a new instance of the {@link JobListItem} class.
	 * 
	 * @param job the {@link Job} that should be displayed.
	 */
	public JobListItem(Job job)
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		anchor.setHref("#project/job/" + job.getJobNr() + "/" + job.getPosNr());
		anchor.add(new ProjectBudgetBar(job));
	}

	
}
