package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.client.ui.ProgressBar;
import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.shared.User;

public class UserListItem extends Composite
{

	private static UserListItemUiBinder uiBinder = GWT
			.create(UserListItemUiBinder.class);

	interface UserListItemUiBinder extends UiBinder<Widget, UserListItem>
	{
	}

	@UiField
	ClearFix	container;

	@UiField
	Span		spanName;

	@UiField
	Span		spanUtilization;

	@UiField
	ProgressBar	barUtilization;
	
	private User user;

	public UserListItem(User user)
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		this.user = user;
		spanName.setText(user.getSurname() + ", " + user.getName());
		
		if(user.getUtilization() != null)
			displayUtilization(user.getUtilization());
		else
			displayUtilization(0);
	}
	
	private void displayUtilization(int utilization)
	{
		spanUtilization.setText(utilization + " %");
		barUtilization.setPercent(utilization);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
