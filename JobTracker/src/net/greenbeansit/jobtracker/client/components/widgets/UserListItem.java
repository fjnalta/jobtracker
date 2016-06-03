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

/**
 * This class describes a UserListItem
 *
 * @author Maximilian Blatt & Philipp Minges
 */
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

	/**
	 * Initializes a new Object of UserListItem
	 * @param user the User.
     */
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

	/**
	 * This method displays the Utilization
	 * @param utilization the Utilization.
     */
	private void displayUtilization(int utilization)
	{
		spanUtilization.setText(utilization + " %");
		barUtilization.setPercent(utilization);
	}

	/**
	 * Gets the User
	 * @return the user.
     */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the User
	 * @param user the User to be set.
     */
	public void setUser(User user) {
		this.user = user;
	}

}
