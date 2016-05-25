package net.greenbeansit.jobtracker.client.components.project;

import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.extras.select.client.ui.MultipleSelect;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ProjectPage extends Composite
{
	private static ProjectPageUiBinder uiBinder = GWT.create(ProjectPageUiBinder.class);

	interface ProjectPageUiBinder extends UiBinder<Widget, ProjectPage>
	{	
	}
	
	interface ProjectPageStyle extends CssResource
	{
		String projectListItem();
	}

	
	private static final String			ARROW_UP	= "\u25B2";
	private static final String			ARROW_DOWN	= "\u25BC";

	@UiField
	Row									employeeList;

	@UiField
	ProjectPageStyle					style;

	@UiField
	MultipleSelect						selectCustomer;

	@UiField
	Heading								columnHeaderNameArrow;

	@UiField
	Heading								columnHeaderBudgetArrow;

	@UiField
	ClearFix							columnHeaderName;

	@UiField
	ClearFix							columnHeaderBudget;
	
	
	public ProjectPage()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		
	}
	
	enum SortMode
	{
		
	}

}
