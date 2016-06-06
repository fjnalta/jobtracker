package net.greenbeansit.jobtracker.client.components.project.detail;

import org.gwtbootstrap3.client.ui.Heading;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.shared.User;

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
	
	
	public JobWorkerListItem(User user)
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		headingWorkerName.setText(user.getName() + ", " + user.getSurname());
	}

}
