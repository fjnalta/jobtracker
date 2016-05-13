package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.extras.select.client.ui.MultipleSelect;
import org.gwtbootstrap3.extras.select.client.ui.Option;

import com.google.gwt.cell.client.ButtonCellBase.DefaultAppearance.Style;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.widgets.SelectJobOption;
import net.greenbeansit.jobtracker.client.components.widgets.UserListItem;
import net.greenbeansit.jobtracker.shared.Job;
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
	
	@UiField
	MultipleSelect selectJob;
	
	List<Job> jobList = new ArrayList<Job>();
	List<User> userList = new ArrayList<User>();
	List<Job> selectedJobs = new ArrayList<Job>();

	public ManagerPage() {
		userList = new ArrayList<User>();
		initWidget(uiBinder.createAndBindUi(this));

		handler.addObservable(this);
		handler.loadUsers();
		handler.loadJobs();
		selectJob.addValueChangeHandler(new ValueChangeHandler<List<String>>(){

			@Override
			public void onValueChange(ValueChangeEvent<List<String>> arg0) {
				getSelectedJobs();
				notifyHandler();
			}
			
		});

	}
	
	private void getSelectedJobs(){
		selectedJobs.clear();
		for(Option j : selectJob.getSelectedItems()){
			selectedJobs.add(((SelectJobOption)j).getJob());
		}
	}
	
	private void loadJobsToSelect(){
		this.jobList.clear();
		selectJob.clear();
		this.jobList = handler.getJobList();
		for(Job j : this.jobList){
			selectJob.add(new SelectJobOption(j));
		}
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
		handler.setSelectedJobs(this.selectedJobs);
	}

}
