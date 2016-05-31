package net.greenbeansit.jobtracker.client.components.project;

import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.ProgressBar;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.ProgressBarType;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.shared.Job;

public class ProjectBudgetBar extends Composite
{

	private static ProjectBudgetBarUiBinder uiBinder = GWT
			.create(ProjectBudgetBarUiBinder.class);

	interface ProjectBudgetBarUiBinder
			extends UiBinder<Widget, ProjectBudgetBar>
	{

	}

	interface ProjectBudgetBarStyle extends CssResource
	{
		String iconLocked();

		String iconUnLocked();
	}

	@UiField
	Span				spanName;

	@UiField
	ProgressBar			barBudget;

	@UiField
	Icon				iconLocked;

	@UiField
	ProjectBudgetBarStyle	style;

	public ProjectBudgetBar(Job job)
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		spanName.setText(job.getJobNr() + "|" + job.getPosNr() + "|"
				+ job.getDesc());
		
		
		double percentage = ((double)job.getUsedBudget() / job.getMaxBudget()) * 100.0;
		
		barBudget.setPercent(percentage);
		barBudget.setText(job.getUsedBudget() + " / " + job.getMaxBudget() + "(" + percentage + "%)");
		
		if(percentage > 90)
			barBudget.setType(ProgressBarType.DANGER);
		else if(percentage > 75)
			barBudget.setType(ProgressBarType.WARNING);
		else
			barBudget.setType(ProgressBarType.SUCCESS);
		
		if(job.isLocked() == null || job.isLocked())
		{
			iconLocked.setType(IconType.LOCK);
			iconLocked.getElement().addClassName(style.iconLocked());
		}
		else
		{
			iconLocked.setType(IconType.UNLOCK);
			iconLocked.getElement().addClassName(style.iconUnLocked());
		}
	}
}
