package net.greenbeansit.jobtracker.client.components.project.detail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.shared.Job;

/**
 * Displays a list of the user who work at a {@link Job}.
 * 
 * @author Max Blatt
 */
public class JobWorkerListWidget extends Composite implements OnDisplayEventListener
{

	private static JobWorkerListWidgetUiBinder uiBinder = GWT
			.create(JobWorkerListWidgetUiBinder.class);

	/**
	 * UiBinder for {@link JobWorkerListWidget}.
	 * 
	 * @author Max Blatt
	 */
	interface JobWorkerListWidgetUiBinder
			extends UiBinder<Widget, JobWorkerListWidget>
	{
	}

	
	/**
	 * Initializes a new instance of the {@link JobWorkerListWidget}.
	 */
	public JobWorkerListWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void onDisplay()
	{
		// TODO Auto-generated method stub
	}

}
