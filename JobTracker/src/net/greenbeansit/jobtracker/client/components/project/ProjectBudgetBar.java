package net.greenbeansit.jobtracker.client.components.project;

import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.ProgressBar;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.ProgressBarType;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.shared.Job;

/**
 * Helps to display a {@link Job} in the list of the {@link ProjectPage}.
 * 
 * @author Max Blatt
 */
public class ProjectBudgetBar extends Composite
{

	private static ProjectBudgetBarUiBinder uiBinder = GWT
			.create(ProjectBudgetBarUiBinder.class);

	/**
	 * UiBinder for {@link ProjectBudgetBar}.
	 * 
	 * @author Max Blatt
	 */
	interface ProjectBudgetBarUiBinder
			extends UiBinder<Widget, ProjectBudgetBar>
	{
	}

	/**
	 * Gets access to the inline styles defined in the ui.xml.
	 * 
	 * @author Max Blatt
	 */
	interface ProjectBudgetBarStyle extends CssResource
	{
		/**
		 * Gets the name of the iconLocked style.
		 * 
		 * @return a String.
		 */
		String iconLocked();

		/**
		 * Gets the name of the iconUnLocked style.
		 * 
		 * @return a String.
		 */
		String iconUnLocked();
	}

	@UiField
	Span				spanName, spanBudget;

	@UiField
	ProgressBar			barBudget;

	@UiField
	Icon				iconLocked;

	@UiField
	ProjectBudgetBarStyle	style;

	
	/**
	 * Initializes a new instance of the {@link ProjectBudgetBar} class.
	 * 
	 * @param job the {@link Job} that should be displayed.
	 */
	public ProjectBudgetBar(Job job)
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		spanName.setText(job.getJobNr() + "|" + job.getPosNr() + "|"
				+ job.getDesc());
		
		
		double percentage = ((double)job.getUsedBudget() / job.getMaxBudget()) * 100.0;
		
		
		spanBudget.setText(job.getUsedBudget() + " / " + job.getMaxBudget() + " (" + NumberFormat.getFormat("0.00").format(percentage) + " %)");
		
		if(percentage >= 100)
		{
			barBudget.setType(ProgressBarType.DANGER);
			
			barBudget.setPercent(100);
		}
		else
		{
			if(percentage > 90)
				barBudget.setType(ProgressBarType.WARNING);
			else
				barBudget.setType(ProgressBarType.SUCCESS);
			
			barBudget.setPercent(percentage);
		}
		
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
