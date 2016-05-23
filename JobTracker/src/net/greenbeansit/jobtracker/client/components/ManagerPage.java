package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.fusesource.restygwt.client.Method;
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.widgets.UserListItem;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;
import net.greenbeansit.jobtracker.shared.rest.services.RestService.ManagerPageRestServiceResponse;

public class ManagerPage extends Composite
{
	private static ManagerPageUiBinder	uiBinder	= GWT
			.create(ManagerPageUiBinder.class);

	@UiField
	Row									employeeList;

	@UiField
	ManagerPageStyle					style;

	@UiField
	MultipleSelect						selectJob;

	private ManagerPageHelperService	helperService;
	private SortMode					sortMode	= SortMode.ALPHABETICAL_UP;

	public ManagerPage()
	{
		initWidget(uiBinder.createAndBindUi(this));

		helperService = new ManagerPageHelperServiceImpl();

		// Add handler
		selectJob.addValueChangeHandler(new ValueChangeHandler<List<String>>()
		{
			@Override
			public void onValueChange(ValueChangeEvent<List<String>> event)
			{
				List<Job> filter = new ArrayList<Job>(
						selectJob.getSelectedItems().size());

				for (Option option : selectJob.getSelectedItems())
					filter.add(((JobSelectOption) option).getJob());

				updateList(filter);
			}
		});

		for (Job job : helperService.getUserJobs())
			selectJob.add(new JobSelectOption(job));

		updateList(null);
	}

	private void updateList(List<Job> filter)
	{
		List<User> userList = helperService.getUser(sortMode, filter);

		// Insert list item widgets
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
	 * Extends the class {@link Option} and helps to display a {@link Job} in a
	 * {@link Select} widget.
	 * 
	 * @author Max Blatt
	 */
	class JobSelectOption extends Option
	{
		private Job job;

		public JobSelectOption(Job job)
		{
			this.job = job;

			this.setText(job.getJobNr() + "|" + job.getPosNr() + "|"
					+ job.getDesc());
		}

		public Job getJob()
		{
			return job;
		}
	}

	enum SortMode
	{
		ALPHABETICAL_UP, UTILIZATION_DOWN
	}

	interface ManagerPageUiBinder extends UiBinder<Widget, ManagerPage>
	{
	}

	interface ManagerPageStyle extends CssResource
	{
		String employeeListItem();
	}

	interface ManagerPageHelperService
	{
		/**
		 * Gets users which are employees of the current user which work on the
		 * following jobs and are sorted in the following way.
		 * 
		 * @param sortmode
		 *            the {@link SortMode} that is used to sort the users.
		 * @param filter
		 *            a List of {@link Job}s that is used as a filter. If the
		 *            value is null, no filter will be applied.
		 * @return a List of {@link User}s.
		 */
		List<User> getUser(SortMode sortmode, List<Job> filter);

		/**
		 * Gets all {@link Job}s which are associated to the employees of the
		 * current user.
		 * 
		 * @return a List of {@link Job}s.
		 */
		List<Job> getUserJobs();

		/**
		 * Gets the ID of the current {@link User}.
		 * 
		 * @return an integer object.
		 */
		Integer getUserId();
	}

	/**
	 * Dummy implementation of {@link ManagerPageHelperService}.
	 * 
	 * @author Max Blatt
	 */
	class ManagerPageHelperServiceImpl implements ManagerPageHelperService
	{
		private List<User>	user = new ArrayList<User>();
		private List<Job>	userJobs = new ArrayList<Job>();

		public ManagerPageHelperServiceImpl()
		{
			loadServerData();
		}

		private void loadServerData()
		{
			RestClient.build(
					new SuccessFunction<ManagerPageRestServiceResponse>()
					{

						@Override
						public void onSuccess(Method method,
								ManagerPageRestServiceResponse response)
						{
							userJobs = response.getJobs();
							if(userJobs == null)
								userJobs = new ArrayList<Job>();
							
							user = response.getEmployees();
							if(user == null)
								user = new ArrayList<User>();
						}

						@Override
						public void onFailure(Method method,
								Throwable exception)
						{
							GWT.log(exception.getMessage());
							exception.printStackTrace();
						}
					}).getEmployeeService().getEmployees(getUserId());
		}

		@Override
		public List<User> getUser(SortMode sortmode, List<Job> filter)
		{
			sortUser(sortmode);

			return applyFilter(filter);
		}

		private void sortUser(SortMode sortmode)
		{
			// sort
			switch (sortmode)
			{
			case ALPHABETICAL_UP:
				Collections.sort(user, new Comparator<User>()
				{
					@Override
					public int compare(User o1, User o2)
					{
						int compareRes = o1.getSurname()
								.compareTo(o2.getSurname());

						if (compareRes == 0)
							compareRes = o1.getName().compareTo(o2.getName());

						return compareRes;
					}
				});
				break;
			default: // UTILZATION_DOWN
				Collections.sort(user, new Comparator<User>()
				{
					@Override
					public int compare(User o1, User o2)
					{
						int compareRes = Integer.compare(o1.getId(),
								o2.getId());

						if (compareRes == 0)
						{
							compareRes = o1.getSurname()
									.compareTo(o2.getSurname());

							if (compareRes == 0)
							{
								compareRes = o1.getName()
										.compareTo(o2.getName());
							}
						}

						return compareRes;
					}
				});
				break;
			}
		}

		private List<User> applyFilter(List<Job> filter)
		{
			List<User> filteredUser = new ArrayList<User>();
			for (User employee : user)
			{
				// TODO: apply filter
				filteredUser.add(employee);
			}

			return filteredUser;
		}

		@Override
		public List<Job> getUserJobs()
		{
			return userJobs;
		}

		@Override
		public Integer getUserId()
		{
			return 0; // TODO: Get real user ID.
		}

	}
}
