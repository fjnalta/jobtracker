package net.greenbeansit.jobtracker.client.components.project.detail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.shared.Job;

/**
 * Displays the budget of a {@link Job} in a graph.
 * 
 * @author Max Blatt
 */
public class JobBudgetWidget extends Composite implements OnDisplayEventListener
{

	private static JobBudgetWidgetUiBinder uiBinder = GWT
			.create(JobBudgetWidgetUiBinder.class);

	/**
	 * UiBinder for the {@link JobBudgetWidget}.
	 * 
	 * @author Max Blatt
	 */
	interface JobBudgetWidgetUiBinder extends UiBinder<Widget, JobBudgetWidget>
	{
	}

	
	/**
	 * Initializes a new instance of the {@link JobBudgetWidget}.
	 */
	public JobBudgetWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void onDisplay()
	{
		// TODO Auto-generated method stub
	}

}
