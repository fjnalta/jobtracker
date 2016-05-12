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

	public UserListItem(User user)
	{
		initWidget(uiBinder.createAndBindUi(this));

		spanName.setText(user.getName() + " " + user.getSurname());
		spanUtilization.setText("65 %");
		barUtilization.setPercent(65);
	}

}
