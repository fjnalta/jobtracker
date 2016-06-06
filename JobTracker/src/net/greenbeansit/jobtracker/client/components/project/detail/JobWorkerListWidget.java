package net.greenbeansit.jobtracker.client.components.project.detail;

import java.util.List;

import org.gwtbootstrap3.client.ui.html.ClearFix;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;

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

	
	@UiField
	ClearFix employeeContainer;
	
	
	/**
	 * Initializes a new instance of the {@link JobWorkerListWidget}.
	 */
	public JobWorkerListWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void fillWorkerList(List<User> worker)
	{
		for(User user : worker)
		{
			employeeContainer.add(new JobWorkerListItem(user));
		}
	}

	@Override
	public void onDisplay()
	{
		//...
	}

}
