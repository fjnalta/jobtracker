package net.greenbeansit.jobtracker.client.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.extras.select.client.ui.MultipleSelect;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.widgets.UserListItem;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient;
import net.greenbeansit.jobtracker.client.utils.rest.RestClient.SuccessFunction;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;
import net.greenbeansit.jobtracker.shared.rest.services.RestService.JobID;
import net.greenbeansit.jobtracker.shared.rest.services.RestService.ManagerPageRestServiceResponse;

public class ManagerPage extends Composite
{
	private static ManagerPageUiBinder	uiBinder	= GWT
			.create(ManagerPageUiBinder.class);

	private static final String			ARROW_UP	= "\u25B2";
	private static final String			ARROW_DOWN	= "\u25BC";

	@UiField
	Row									employeeList;

	@UiField
	ManagerPageStyle					style;

	@UiField
	MultipleSelect						selectJob;

	@UiField
	Heading								columnHeaderNameArrow;

	@UiField
	Heading								columnHeaderUtilizationArrow;

	@UiField
	ClearFix							columnHeaderName;

	@UiField
	ClearFix							columnHeaderUtilization;

	private ManagerPageHelperService	helperService;
	private SortMode					sortMode	= SortMode.ALPHABETICAL_UP;

	private List<Job>					currentFilter;
	private List<User>					currentUserList;

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

				currentFilter = filter;

				fillEmployeeList(helperService.filterUser(filter));
			}
		});

		// Register click events
		columnHeaderName.addDomHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				if (sortMode == SortMode.ALPHABETICAL_UP)
				{
					sortMode = SortMode.ALPHABETICAL_DOWN;
					sortList();

					columnHeaderNameArrow.setText(ARROW_DOWN);
				} else
				{
					sortMode = SortMode.ALPHABETICAL_UP;
					sortList();

					columnHeaderNameArrow.setText(ARROW_UP);
				}

				columnHeaderUtilizationArrow.setText("");
			}
		}, ClickEvent.getType());

		columnHeaderUtilization.addDomHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				if (sortMode == SortMode.UTILIZATION_UP)
				{
					sortMode = SortMode.UTILIZATION_DOWN;
					sortList();

					columnHeaderUtilizationArrow.setText(ARROW_DOWN);
				} else
				{
					sortMode = SortMode.UTILIZATION_UP;
					sortList();
					
					columnHeaderUtilizationArrow.setText(ARROW_UP);
				}
				
				columnHeaderNameArrow.setText("");
			}
		}, ClickEvent.getType());
	}

	private void sortList()
	{
		currentUserList = helperService.sortUser(currentUserList, sortMode);

		fillEmployeeList(currentUserList);
	}

	private void fillEmployeeList(List<User> employees)
	{
		// Insert list item widgets
		employeeList.clear();
		for (User user : employees)
		{
			Anchor anchor = new Anchor();
			anchor.add(new UserListItem(user));
			anchor.setHref("#manager/employee/" + user.getId());
			anchor.getElement().addClassName(style.employeeListItem());

			employeeList.add(anchor);
		}
	}

	private void fillJobList(List<Job> jobs)
	{
		selectJob.clear();

		for (Job job : jobs)
			selectJob.add(new JobSelectOption(job));

		selectJob.refresh();
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
		ALPHABETICAL_UP, ALPHABETICAL_DOWN, UTILIZATION_UP, UTILIZATION_DOWN
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
		List<User> sortUser(List<User> user, SortMode sortmode);

		List<User> filterUser(List<Job> filter);

		List<User> filterAndSortUser(List<Job> filter, SortMode sortmode);

		/**
		 * Gets the ID of the current {@link User}.
		 * 
		 * @return an integer object.
		 */
		Integer getUserId();

		boolean isDataLoaded();
	}

	/**
	 * Dummy implementation of {@link ManagerPageHelperService}.
	 * 
	 * @author Max Blatt
	 */
	class ManagerPageHelperServiceImpl implements ManagerPageHelperService
	{
		private List<User>	cachedUser	= new ArrayList<User>();
		private List<Job>	cachedJobs	= new ArrayList<Job>();

		private boolean		dataLoaded	= false;

		public ManagerPageHelperServiceImpl()
		{
			loadServerData();
		}

		private void loadServerData()
		{
			RestClient
					.build(new SuccessFunction<ManagerPageRestServiceResponse>()
					{
						@Override
						public void onSuccess(Method method,
								ManagerPageRestServiceResponse response)
						{
							cachedJobs = response.getJobs();
							if (cachedJobs == null)
								cachedJobs = new ArrayList<Job>();

							cachedUser = response.getEmployees();
							if (cachedUser == null)
								cachedUser = new ArrayList<User>();

							fillEmployeeList(filterAndSortUser(null, sortMode));
							fillJobList(cachedJobs);

							dataLoaded = true;
						}

						@Override
						public void onFailure(Method method,
								Throwable exception)
						{
							NotifyHelper.errorMessage(exception.toString());
							GWT.log(exception.getMessage());
							exception.printStackTrace();
						}
					}).getEmployeeService().getEmployees(getUserId());
		}

		@Override
		public Integer getUserId()
		{
			return 1; // TODO: Get real user ID.
		}

		@Override
		public List<User> sortUser(List<User> user, SortMode sortmode)
		{
			if (user == null)
				user = cachedUser;

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
			case ALPHABETICAL_DOWN:
				Collections.sort(user, new Comparator<User>()
				{
					@Override
					public int compare(User o1, User o2)
					{
						int compareRes = o1.getSurname()
								.compareTo(o2.getSurname()) * -1;

						if (compareRes == 0)
							compareRes = o1.getName().compareTo(o2.getName()) * -1;

						return compareRes;
					}
				});
				break;
			case UTILIZATION_UP:
				Collections.sort(user, new Comparator<User>()
				{
					@Override
					public int compare(User o1, User o2)
					{
						int compareRes = Integer.compare(o1.getId(),
								o2.getId()) * -1;

						if (compareRes == 0)
						{
							compareRes = o1.getSurname()
									.compareTo(o2.getSurname()) * -1;

							if (compareRes == 0)
							{
								compareRes = o1.getName()
										.compareTo(o2.getName()) * -1;
							}
						}

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

			return user;
		}

		@Override
		public List<User> filterUser(List<Job> filter)
		{
			if (filter != null && filter.size() > 0)
			{
				List<User> filteredUser = new ArrayList<User>();
				for (User userInstance : cachedUser)
				{
					if (matchesFilter(userInstance, filter))
						filteredUser.add(userInstance);
				}

				return filteredUser;
			} else
				return cachedUser;
		}

		private boolean matchesFilter(User employee, List<Job> filter)
		{
			if (employee.getAssignedJobs() == null)
				employee.setAssignedJobs(new ArrayList<JobID>());

			for (Job filterJob : filter)
			{
				for (JobID assignedJobId : employee.getAssignedJobs())
				{
					if (assignedJobId.matchesJob(filterJob))
						return true;
				}
			}

			return false;
		}

		@Override
		public List<User> filterAndSortUser(List<Job> filter, SortMode sortmode)
		{
			return sortUser(filterUser(filter), sortmode);
		}

		@Override
		public boolean isDataLoaded()
		{
			return dataLoaded;
		}

	}
}
