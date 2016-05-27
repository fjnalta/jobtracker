package net.greenbeansit.jobtracker.client.components.project.detail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ProjectDetailPage extends Composite
{

	private static ProjectDetailPageUiBinder uiBinder = GWT
			.create(ProjectDetailPageUiBinder.class);

	interface ProjectDetailPageUiBinder
			extends UiBinder<Widget, ProjectDetailPage>
	{
	}

	public ProjectDetailPage(Integer jobId)
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

}
