package net.greenbeansit.timer.client.components;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.NavPills;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class NavigationBar extends Composite
{
	private static NavigationBarUiBinder uiBinder = 
			GWT.create(NavigationBarUiBinder.class);
	
	public interface OnClickListener
	{
		void onClick(String uiField);
	}
	
	private OnClickListener onClickListener;
	
	@UiField
	AnchorListItem homeNavButton;
	
	@UiField
	AnchorListItem calendarNavButton;
	
	@UiField
	AnchorListItem jobsNavButton;
	
	@UiField
	AnchorListItem utilizationNavButton;
	
	@UiField
	AnchorListItem capazityNavButton;
	
	@UiField
	AnchorListItem budgetNavButton;
	
	@UiField
	NavPills navPills;

	@UiTemplate("NavigationBar.ui.xml") 
	interface NavigationBarUiBinder extends UiBinder<Widget, NavigationBar>
	{
		
	}
	
	public NavigationBar()
	{
		initWidget(uiBinder.createAndBindUi(this));
		homeNavButton.setActive(true);
		addOnClickHandlers();
	}
	
	public OnClickListener getOnClickListener()
	{
		return onClickListener;
	}


	public void setOnClickListener(OnClickListener onClickListener)
	{
		this.onClickListener = onClickListener;
	}
	
	
	
	@UiHandler("homeNavButton")
	void onClickHome(ClickEvent e)
	{
		if(onClickListener != null)
			onClickListener.onClick("home");
	}
	
	@UiHandler("calendarNavButton")
	void onClickCalendar(ClickEvent e)
	{
		if(onClickListener != null)
			onClickListener.onClick("calendar");
	}
	
	@UiHandler("jobsNavButton")
	void onClickJobs(ClickEvent e)
	{
		if(onClickListener != null)
			onClickListener.onClick("jobs");
	}
	
	@UiHandler("utilizationNavButton")
	void onClickUtilization(ClickEvent e)
	{
		if(onClickListener != null)
			onClickListener.onClick("utilization");
	}
	
	@UiHandler("capazityNavButton")
	void onClickCapazity(ClickEvent e)
	{
		if(onClickListener != null)
			onClickListener.onClick("capazity");
	}
	
	@UiHandler("budgetNavButton")
	void onClickBudget(ClickEvent e)
	{
		if(onClickListener != null)
			onClickListener.onClick("budget");
	}
	
	
	
	private void addOnClickHandlers(){
		
		ClickHandler clickHandler = new ClickHandler()
		{
			
			@Override
			public void onClick(ClickEvent event)
			{
				
				for(int i = 0; i< navPills.getWidgetCount();i++)
					((AnchorListItem)navPills.getWidget(i)).setActive(false);
				
				
				((AnchorListItem)event.getSource()).setActive(true);
			}
		};
		
		for(int index = 0; index< navPills.getWidgetCount();index++)
		{	
			navPills.getWidget(index).addDomHandler(
					clickHandler,
					ClickEvent.getType());
			
		}
	}
}
