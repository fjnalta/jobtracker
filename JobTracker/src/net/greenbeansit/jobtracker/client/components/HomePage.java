package net.greenbeansit.jobtracker.client.components;

import org.gwtbootstrap3.client.ui.Column;

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

import net.greenbeansit.jobtracker.client.components.widgets.CalendarActivity;
import net.greenbeansit.jobtracker.client.components.widgets.JobsActivity;
import net.greenbeansit.jobtracker.client.components.widgets.UtilizationActivity;
import net.greenbeansit.jobtracker.client.components.widgets.WorkActivity;

public class HomePage extends Composite{

	private static HomePageUiBinder uiBinder = GWT.create(HomePageUiBinder.class);

	interface HomePageUiBinder extends UiBinder<Widget, HomePage> {
	}
	
	@UiField
	Column first_column;
	
	@UiField
	Column second_column;

	public HomePage() {
		initWidget(uiBinder.createAndBindUi(this));
		first_column.add(new JobsActivity());
		first_column.add(new WorkActivity());
		first_column.add(new UtilizationActivity());
		second_column.add(new CalendarActivity());
	}

}
