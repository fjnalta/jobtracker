package net.greenbeansit.jobtracker.client.components.project;

import org.gwtbootstrap3.client.ui.ProgressBar;
import org.gwtbootstrap3.client.ui.constants.ProgressBarType;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.shared.Job;

public class ProjectBudgetBar extends Composite
{

	private static ProjectBudgetBarUiBinder uiBinder =
			GWT.create(ProjectBudgetBarUiBinder.class);

	interface ProjectBudgetBarUiBinder extends UiBinder<Widget, ProjectBudgetBar>
	{
		
	}

	@UiField
	Span spanName;
	
	@UiField
	ProgressBar barBudget;
	
//	public ProjectBudgetBar()
//	{
//		initWidget(uiBinder.createAndBindUi(this));
//	}
	
	public ProjectBudgetBar(Job job)
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		spanName.setText(job.getJobNr() + "|" + job.getPosNr() + "|"
				+ job.getDesc());
		
		
		double percentage = ((double)job.getUsedBudget() / job.getMaxBudget()) * 100.0;
		
		barBudget.setPercent(percentage);
		
		if(percentage > 90)
			barBudget.setType(ProgressBarType.DANGER);
		else if(percentage > 75)
			barBudget.setType(ProgressBarType.WARNING);
		else
			barBudget.setType(ProgressBarType.SUCCESS);
	}
}

