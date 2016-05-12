package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Row;

import com.google.gwt.cell.client.ButtonCellBase.DefaultAppearance.Style;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.widgets.UserListItem;
import net.greenbeansit.jobtracker.shared.User;

public class ManagerPage extends Composite
{

	private static ManagerPageUiBinder uiBinder = GWT
			.create(ManagerPageUiBinder.class);

	interface ManagerPageUiBinder extends UiBinder<Widget, ManagerPage>
	{
	}
	
	interface ManagerPageStyle extends CssResource
	{
		String employeeListItem();
	}

	@UiField
	Row employeeList;
	
	@UiField
	ManagerPageStyle style;
	
	List<User> userlist;
	
	public ManagerPage()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		userlist = new ArrayList<User>();
		
		userlist.add(new User(0, "Walter", "von der Vogelweide"));
		userlist.add(new User(0, "Peter", "Tauber"));
		userlist.add(new User(0, "Kurt", "Beck"));
		userlist.add(new User(0, "Rudolf", "Scharping"));
		userlist.add(new User(0, "Peter", "Altmeier"));
		userlist.add(new User(0, "Rainer", "Brüderle"));
		
		//Fill the display list
		for(User user : userlist)
		{
			Anchor anchor = new Anchor();
			anchor.add(new UserListItem(user));
			anchor.setHref("#manager_detail/" + user.getId());
			anchor.getElement().addClassName(style.employeeListItem());
			
			employeeList.add(anchor);
		}
	}

}
