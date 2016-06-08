package net.greenbeansit.jobtracker.client.components.project;

import java.util.ArrayList;
import java.util.List;

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
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.project.ProjectPageHelperServiceImpl.Callback;
import net.greenbeansit.jobtracker.client.localization.ProjectPageConstants;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.Customer;
import net.greenbeansit.jobtracker.shared.Job;

/**
 * Displays a list of all {@link Job}s in which the current user is the project
 * leader.
 * 
 * @author Max Blatt
 */
public class ProjectPage extends Composite
{
	private static ProjectPageUiBinder	uiBinder	= GWT
			.create(ProjectPageUiBinder.class);

	private static ProjectPageConstants	constants	= GWT
			.create(ProjectPageConstants.class);

	/**
	 * UiBinder for the {@link ProjectPage}.
	 * 
	 * @author Max Blatt
	 */
	interface ProjectPageUiBinder extends UiBinder<Widget, ProjectPage>
	{
	}

	/**
	 * Provides access to the inline styles defined in the ui.xml.
	 * 
	 * @author Max Blatt
	 */
	interface ProjectPageStyle extends CssResource
	{
		/**
		 * Gets the name of the jobListItem style.
		 * 
		 * @return a String.
		 */
		String jobListItem();
	}

	private static final String			ARROW_UP	= "\u25B2";
	private static final String			ARROW_DOWN	= "\u25BC";

	@UiField
	MultipleSelect						selectCustomer;

	@UiField
	Row									jobList;

	@UiField
	ProjectPageStyle					style;

	@UiField
	ClearFix							columnHeaderName;

	@UiField
	ClearFix							columnHeaderBudget;

	@UiField
	ClearFix							columnHeaderLocked;

	@UiField
	Heading								columnHeaderNameTitle,
			columnHeaderNameArrow, columnHeaderBudgetTitle,
			columnHeaderBudgetArrow, columnHeaderLockedArrow, headingTitle, headingFilter;

	private ProjectPageHelperService	helperService;
	private ProjectPageSortMode			sortMode;
	private List<Customer>				currentFilter;
	private List<Job>					currentJobList;

	/**
	 * Initializes a new instance of the ProjectPage and loads the required data
	 * from the server.
	 */
	public ProjectPage()
	{
		initWidget(uiBinder.createAndBindUi(this));

		columnHeaderNameTitle.setText(constants.columnHeaderName());
		columnHeaderBudgetTitle.setText(constants.columnHeaderBudget());
		headingTitle.setText(constants.title());
		headingFilter.setText(constants.headingFilter());
		selectCustomer.setPlaceholder(constants.selectCustomerPlaceHolder());
		selectCustomer.setLiveSearchPlaceholder(constants.selectCustomerLiveSearchPlaceHolder());
		
		// initial sort mode
		sortMode = ProjectPageSortMode.NAME_UP;
		columnHeaderNameArrow.setText(ARROW_UP);

		// Add handler
		selectCustomer
				.addValueChangeHandler(new ValueChangeHandler<List<String>>()
				{
					@Override
					public void onValueChange(
							ValueChangeEvent<List<String>> event)
					{
						List<Customer> filter = new ArrayList<Customer>(
								selectCustomer.getSelectedItems().size());

						for (Option option : selectCustomer.getSelectedItems())
							filter.add(((CustomerOption) option).getCustomer());

						currentFilter = filter;

						currentJobList = helperService.filterAndSortJobs(filter,
								sortMode);

						fillJobList(currentJobList);
					}
				});

		// Register click events
		columnHeaderName.addDomHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				if (sortMode == ProjectPageSortMode.NAME_UP)
				{
					sortMode = ProjectPageSortMode.NAME_DOWN;
					sortList();

					columnHeaderNameArrow.setText(ARROW_DOWN);
				} else
				{
					sortMode = ProjectPageSortMode.NAME_UP;
					sortList();

					columnHeaderNameArrow.setText(ARROW_UP);
				}

				columnHeaderBudgetArrow.setText("");
				columnHeaderLockedArrow.setText("");
			}
		}, ClickEvent.getType());

		columnHeaderBudget.addDomHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				if (sortMode == ProjectPageSortMode.USED_BUDGET_PERCENT_UP)
				{
					sortMode = ProjectPageSortMode.USED_BUDGET_PERCENT_DOWN;
					sortList();

					columnHeaderBudgetArrow.setText(ARROW_DOWN);
				} else
				{
					sortMode = ProjectPageSortMode.USED_BUDGET_PERCENT_UP;
					sortList();

					columnHeaderBudgetArrow.setText(ARROW_UP);
				}

				columnHeaderNameArrow.setText("");
				columnHeaderLockedArrow.setText("");
			}
		}, ClickEvent.getType());

		columnHeaderLocked.addDomHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				if (sortMode == ProjectPageSortMode.LOCKED_UP)
				{
					sortMode = ProjectPageSortMode.LOCKED_DOWN;
					sortList();

					columnHeaderLockedArrow.setText(ARROW_DOWN);
				} else
				{
					sortMode = ProjectPageSortMode.LOCKED_UP;
					sortList();

					columnHeaderLockedArrow.setText(ARROW_UP);
				}

				columnHeaderNameArrow.setText("");
				columnHeaderBudgetArrow.setText("");
			}
		}, ClickEvent.getType());

		helperService = new ProjectPageHelperServiceImpl(new Callback()
		{
			@Override
			public void onSuccess()
			{
				fillJobList(helperService.filterAndSortJobs(null, sortMode));
				fillCustomerList(helperService.getCustomers());
			}

			@Override
			public void onFailure(Throwable error)
			{
				NotifyHelper.errorMessage(error.toString());
			}
		});
	}

	/**
	 * Sorts the current {@link Job}s by the current {@link ProjectPageSortMode}
	 * and refills the list using {@link ProjectPage#fillJobList(List)}.
	 */
	private void sortList()
	{
		currentJobList = helperService.sortJobs(currentJobList, sortMode);

		fillJobList(currentJobList);
	}

	/**
	 * Clears the list container and refills it with {@link JobListItem}s based
	 * on the following list of {@link Job}s.
	 * 
	 * @param jobs
	 *            the list of {@link Job}s that should be displayed.
	 */
	private void fillJobList(List<Job> jobs)
	{
		// Insert list item widgets
		jobList.clear();
		for (Job job : jobs)
		{
			JobListItem item = new JobListItem(job);
			item.getElement().addClassName(style.jobListItem());

			jobList.add(item);
		}
	}

	/**
	 * Fills the filter {@link Select} with the following {@link Customer}s.
	 * 
	 * @param customers
	 *            a list of {@link Customer}s.
	 */
	private void fillCustomerList(List<Customer> customers)
	{
		selectCustomer.clear();

		for (Customer customer : customers)
		{
			selectCustomer.add(new CustomerOption(customer));
		}

		selectCustomer.refresh();
	}
}
