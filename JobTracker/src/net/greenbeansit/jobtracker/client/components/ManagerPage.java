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

public class ManagerPage extends Composite implements LogicObservable {

	private static ManagerPageUiBinder uiBinder = GWT.create(ManagerPageUiBinder.class);

	interface ManagerPageUiBinder extends UiBinder<Widget, ManagerPage> {
	}

	interface ManagerPageStyle extends CssResource {
		String employeeListItem();
	}

	@UiField
	Row employeeList;

	@UiField
	ManagerPageStyle style;

	List<User> userList;

	public ManagerPage() {
		userList = new ArrayList<User>();
		initWidget(uiBinder.createAndBindUi(this));

		handler.addObservable(this);
		handler.loadUsers();

		this.update();
	}

	@Override
	public void update() {
		userList.clear();
		userList = handler.getUsers();
		if(userList!=null){
			for (User user : userList) {
				Anchor anchor = new Anchor();
				anchor.add(new UserListItem(user));
				anchor.setHref("#manager_detail/" + user.getId());
				anchor.getElement().addClassName(style.employeeListItem());

				employeeList.add(anchor);
			}
		}	
	}

	@Override
	public void notifyHandler() {
		// TODO Auto-generated method stub

	}

}
