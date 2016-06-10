package net.greenbeansit.jobtracker.client.components.project.detail;

import org.gwtbootstrap3.client.ui.Heading;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.shared.User;

/**
 * This Class represents a List Item for the {@link JobWorkerListItem}
 * @author Max Blatt
 */
public class JobWorkerListItem extends Composite
{

	private static JobWorkerListItemUiBinder uiBinder = GWT
			.create(JobWorkerListItemUiBinder.class);

	interface JobWorkerListItemUiBinder
			extends UiBinder<Widget, JobWorkerListItem>
	{
	}

	@UiField
	Heading headingWorkerName;

	/**
	 * Initializes a new Instance of JobWorkerListItem
	 * @param user the User.
     */
	public JobWorkerListItem(User user)
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		headingWorkerName.setText(user.getName() + ", " + user.getSurname());
	}

}
