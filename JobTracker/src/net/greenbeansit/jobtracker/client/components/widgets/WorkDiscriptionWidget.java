package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.extras.select.client.ui.Select;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.HomePage;

public class WorkDiscriptionWidget extends Composite implements ObservableOnHomePage
{

	private static WorkDiscriptionWidgetUiBinder uiBinder = 
			GWT.create(WorkDiscriptionWidgetUiBinder.class);

	interface WorkDiscriptionWidgetUiBinder extends UiBinder<Widget, WorkDiscriptionWidget>
	{
		
	}
	
	public WorkDiscriptionWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		
	}


	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
	}


	@Override
	public void registerObserver(HomePage homePage) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeObserver(HomePage homePage) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void notificate() {
		// TODO Auto-generated method stub
		
	}
}
