package net.greenbeansit.jobtracker.client.components.manager;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.client.ui.Anchor;
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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.manager.ManagerPageHelperServiceImpl.Callback;
import net.greenbeansit.jobtracker.client.components.widgets.UserListItem;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;

public class ManagerPage extends Composite
{
	interface ManagerPageUiBinder extends UiBinder<Widget, ManagerPage>
	{
	}

	interface ManagerPageStyle extends CssResource
	{
		String employeeListItem();
	}

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
	private ManagerPageSortMode			sortMode	= ManagerPageSortMode.ALPHABETICAL_UP;

	private List<Job>					currentFilter;
	private List<User>					currentUserList;

	
	public ManagerPage()
	{
		initWidget(uiBinder.createAndBindUi(this));

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
				if (sortMode == ManagerPageSortMode.ALPHABETICAL_UP)
				{
					sortMode = ManagerPageSortMode.ALPHABETICAL_DOWN;
					sortList();

					columnHeaderNameArrow.setText(ARROW_DOWN);
				} else
				{
					sortMode = ManagerPageSortMode.ALPHABETICAL_UP;
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
				if (sortMode == ManagerPageSortMode.UTILIZATION_UP)
				{
					sortMode = ManagerPageSortMode.UTILIZATION_DOWN;
					sortList();

					columnHeaderUtilizationArrow.setText(ARROW_DOWN);
				} else
				{
					sortMode = ManagerPageSortMode.UTILIZATION_UP;
					sortList();

					columnHeaderUtilizationArrow.setText(ARROW_UP);
				}

				columnHeaderNameArrow.setText("");
			}
		}, ClickEvent.getType());

		
		helperService = new ManagerPageHelperServiceImpl(new Callback()
		{
			@Override
			public void onSuccess()
			{
				fillEmployeeList(
						helperService.filterAndSortUser(null, sortMode));
				fillJobList(helperService.getJobs());
			}

			@Override
			public void onFailure(Throwable error)
			{
				NotifyHelper.errorMessage(error.toString());
			}
		});
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
}