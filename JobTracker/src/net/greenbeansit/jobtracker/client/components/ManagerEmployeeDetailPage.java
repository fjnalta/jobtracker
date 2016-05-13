package net.greenbeansit.jobtracker.client.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ManagerEmployeeDetailPage extends Composite
{

	private static ManagerEmployeeDetailPageUiBinder uiBinder = GWT
			.create(ManagerEmployeeDetailPageUiBinder.class);

	public ManagerEmployeeDetailPage(Integer userId)
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		
	}

	
	
	interface ManagerEmployeeDetailPageUiBinder
			extends UiBinder<Widget, ManagerEmployeeDetailPage> { }
}
