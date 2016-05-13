package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.extras.select.client.ui.MultipleSelect;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.widgets.UserListItem;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;

public class ManagerPage extends Composite
{
	private static ManagerPageUiBinder uiBinder = GWT
			.create(ManagerPageUiBinder.class);
	

	@UiField
	Row					employeeList;

	@UiField
	ManagerPageStyle	style;

	@UiField
	MultipleSelect		selectJob;

	private ManagerPageHelperService helperService;
	private SortMode sortMode = SortMode.ALPHABETICAL_UP;
	
	public ManagerPage()
	{
		initWidget(uiBinder.createAndBindUi(this));

		helperService = new ManagerPageHelperServiceDummyImpl();
		
		//Add handler
		selectJob.addValueChangeHandler(new ValueChangeHandler<List<String>>()
		{
			@Override
			public void onValueChange(ValueChangeEvent<List<String>> event)
			{
				List<Job> filter = new ArrayList<Job>(selectJob.getSelectedItems().size());
				
				for(Option option : selectJob.getSelectedItems())
					filter.add(((JobSelectOption)option).getJob());
				
				updateList(filter);
			}
		});
		
		for(Job job : helperService.getUserJobs())
			selectJob.add(new JobSelectOption(job));
		
		updateList(null);
	}
	
	private void updateList(List<Job> filter)
	{
		List<User> userList = helperService.getUser(sortMode, filter);

		//Insert list item widgets
		employeeList.clear();
		for (User user : userList)
		{
 			Anchor anchor = new Anchor();
 			anchor.add(new UserListItem(user));
 			anchor.setHref("#manager/employee/" + user.getId());
 			anchor.getElement().addClassName(style.employeeListItem());
			
 			employeeList.add(anchor);
 		}
	}


	/**
	 * Extends the class {@link Option} and helps to display a {@link Job} in
	 * a {@link Select} widget.
	 *  
	 * @author Max Blatt
	 */
	class JobSelectOption extends Option
	{
		private Job job;
		
		public JobSelectOption(Job job)
		{
			this.job = job;
			
			this.setText(job.getJobNr() + "|" + job.getPosNr() + "|" + job.getDesc());
		}
		
		
		public Job getJob()
		{
			return job;
		}
	}

	enum SortMode
	{
		ALPHABETICAL_UP,
		UTILIZATION_DOWN
	}
	
	interface ManagerPageUiBinder extends UiBinder<Widget, ManagerPage> { }

	interface ManagerPageStyle extends CssResource
	{
		String employeeListItem();
	}
	
	interface ManagerPageHelperService
	{
		/**
		 * Gets users which are employees of the current user which work on the following jobs and are sorted in the following way.
		 * 
		 * @param sortmode
		 * 		the {@link SortMode} that is used to sort the users.
		 * @param filter
		 * 		a List of {@link Job}s that is used as a filter. If the value is null, no filter will be applied.
		 * @return
		 * 		a List of {@link User}s. 
		 */
		List<User> getUser(SortMode sortmode, List<Job> filter);
		
		/**
		 * Gets all {@link Job}s which are associated to the employees of the current user.
		 * 
		 * @return
		 * 		a List of {@link Job}s.
		 */
		List<Job> getUserJobs();
	}

	/**
	 * Dummy implementation of {@link ManagerPageHelperService}.
	 * 
	 * @author Max Blatt
	 */
	class ManagerPageHelperServiceDummyImpl implements ManagerPageHelperService
	{
		private List<User> user;
		private List<Job> userJobs;
		
		
		public ManagerPageHelperServiceDummyImpl()
		{
			user = new ArrayList<User>();
			user.add(new User(0, "Walter", "von der Vogelweide", 50));
			user.add(new User(1, "Kurt", "Beck", 50));
			user.add(new User(2, "Rainer", "Brüderle", 50));
			user.add(new User(3, "Sun", "Tzu", 50));
			user.add(new User(4, "Albert", "Einstein", 50));
			user.add(new User(5, "Theodore", "Roosevelt", 50));
			user.add(new User(6, "Konrad", "Adenauer", 50));
			user.add(new User(7, "Richard", "Löwenherz", 50));
			
			userJobs = new ArrayList<Job>();
			userJobs.add(new Job(444, 555, 1, 24, "VW Intern", 25, 26));
			userJobs.add(new Job(440, 554, 1, 33, "Technik", 25, 26));
		}
		

		@Override
		public List<User> getUser(SortMode sortmode, List<Job> filter)
		{			
			Collections.sort(user, new Comparator<User>()
			{
				@Override
				public int compare(User o1, User o2)
				{
					int compareRes = o1.getSurname().compareTo(o2.getSurname());
					
					if(compareRes == 0)
						compareRes = o1.getName().compareTo(o2.getName());
					
					return compareRes;
				}
			});
			
			return user;
		}

		@Override
		public List<Job> getUserJobs()
		{
			return userJobs;
		}
		
	}
}
