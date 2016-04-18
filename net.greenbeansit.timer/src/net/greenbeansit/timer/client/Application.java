package net.greenbeansit.timer.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.timer.client.components.HomePage;
import net.greenbeansit.timer.client.components.NavigationBar;
import net.greenbeansit.timer.client.components.NavigationBar.OnClickListener;

/**
 * Represents the main widget of the application.
 */
class Application extends Composite
{
	private static ApplicationUiBinder uiBinder = 
			GWT.create(ApplicationUiBinder.class);
	
	@UiTemplate("Application.ui.xml") 
	interface ApplicationUiBinder extends UiBinder<Widget, Application>
	{
		
	}
	
	@UiField 
	NavigationBar navigationBar;
	
	@UiField
	HomePage dashboard;
	
	
	public Application()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		navigationBar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(String uiField) {
				
			}
		});
		
	}
	
	
}
