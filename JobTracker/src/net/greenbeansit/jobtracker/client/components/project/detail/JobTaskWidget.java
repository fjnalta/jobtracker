package net.greenbeansit.jobtracker.client.components.project.detail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.JobTask;

/**
 * Displays the {@link JobTask}s of a {@link Job} in a diagram.
 * 
 * @author Max Blatt
 */
public class JobTaskWidget extends Composite implements OnDisplayEventListener
{

	private static JobTaskWidgetUiBinder uiBinder = GWT
			.create(JobTaskWidgetUiBinder.class);

	/**
	 * UiBinder for {@link JobTaskWidget}.
	 * 
	 * @author Max Blatt
	 */
	interface JobTaskWidgetUiBinder
			extends UiBinder<Widget, JobTaskWidget>
	{
	}

	/**
	 * Initializes a new instance of the class {@link JobTaskWidget}.
	 */
	public JobTaskWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void onDisplay()
	{
		// TODO Auto-generated method stub
	}

}
