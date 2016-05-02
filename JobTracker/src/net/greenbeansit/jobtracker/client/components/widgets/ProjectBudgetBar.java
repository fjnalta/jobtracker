package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.ProgressBar;
import org.gwtbootstrap3.client.ui.constants.ProgressBarType;

import com.google.gwt.core.client.GWT;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.ProjectPage;

public class ProjectBudgetBar extends Composite implements ObservableOnProjectPage
{

	private static ProjectBudgetBarUiBinder uiBinder =
			GWT.create(ProjectBudgetBarUiBinder.class);

	interface ProjectBudgetBarUiBinder extends UiBinder<Widget, ProjectBudgetBar>
	{
		
	}

	@UiField
	Heading headingName;
	
	@UiField
	ProgressBar budgetBar;
	
//	public ProjectBudgetBar()
//	{
//		initWidget(uiBinder.createAndBindUi(this));
//	}
	
	public ProjectBudgetBar(int consumedBudget, int totalBudget, String projectName)
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		headingName.setText(projectName);
		
		
		double percentage = ((double)consumedBudget / totalBudget) * 100.0;
		
		budgetBar.setPercent(percentage);
		
		if(percentage > 90)
			budgetBar.setType(ProgressBarType.DANGER);
		else if(percentage > 75)
			budgetBar.setType(ProgressBarType.WARNING);
		else
			budgetBar.setType(ProgressBarType.SUCCESS);
		
		budgetBar.setText(consumedBudget + "/" + totalBudget);
		//budgetBar.setText(percentage + "");
	}

	@Override
	public void registerObserver(ProjectPage projectPage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(ProjectPage projectPage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificate() {
		// TODO Auto-generated method stub
		
	}
	
	
}