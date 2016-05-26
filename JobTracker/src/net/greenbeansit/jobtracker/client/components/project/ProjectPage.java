package net.greenbeansit.jobtracker.client.components.project;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.client.ui.html.Span;
import org.gwtbootstrap3.extras.select.client.ui.MultipleSelect;
import org.gwtbootstrap3.extras.select.client.ui.Option;

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

import net.greenbeansit.jobtracker.client.components.project.ProjectPageHelperServiceImpl.Callback;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.Customer;
import net.greenbeansit.jobtracker.shared.Job;

public class ProjectPage extends Composite
{
	private static ProjectPageUiBinder uiBinder = GWT
			.create(ProjectPageUiBinder.class);

	interface ProjectPageUiBinder extends UiBinder<Widget, ProjectPage>
	{
	}

	interface ProjectPageStyle extends CssResource
	{
		String projectListItem();

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
	Span								columnHeaderNameArrow;

	@UiField
	Span								columnHeaderBudgetArrow;

	private ProjectPageHelperService	helperService;
	private ProjectPageSortMode			sortMode	= ProjectPageSortMode.NAME_UP;
	private List<Customer>				currentFilter;
	private List<Job>					currentJobList;

	public ProjectPage()
	{
		initWidget(uiBinder.createAndBindUi(this));

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

						fillJobList(helperService.filterJobs(filter));
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

	private void sortList()
	{
		currentJobList = helperService.sortJobs(currentJobList, sortMode);

		fillJobList(currentJobList);
	}

	private void fillJobList(List<Job> jobs)
	{

		// Insert list item widgets
		jobList.clear();
		for (Job job : jobs)
		{
			Anchor anchor = new Anchor();
			anchor.add(new ProjectBudgetBar(job));
			anchor.setHref("#project/job/" + job.getPosNr());
			anchor.getElement().addClassName(style.jobListItem());

			jobList.add(anchor);
		}
	}

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
