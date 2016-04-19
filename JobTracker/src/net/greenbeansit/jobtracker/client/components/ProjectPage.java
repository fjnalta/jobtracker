package net.greenbeansit.jobtracker.client.components;

import org.gwtbootstrap3.client.ui.Row;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.widgets.ProjectBudgetBar;

public class ProjectPage extends Composite
{
	private static ProjectPageUiBinder uiBinder = GWT.create(ProjectPageUiBinder.class);

	interface ProjectPageUiBinder extends UiBinder<Widget, ProjectPage>
	{
		
	}

	
	@UiField
	Row containerBudgetDiagrams;
	
	
	public ProjectPage()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		containerBudgetDiagrams.add(new ProjectBudgetBar(500, 1000, "Projekt X"));
		containerBudgetDiagrams.add(new ProjectBudgetBar(920, 1000, "Projekt Y"));
		containerBudgetDiagrams.add(new ProjectBudgetBar(800, 1000, "Projekt Z"));
	}

}
