package net.greenbeansit.jobtracker.client;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.Image;
import org.gwtbootstrap3.client.ui.NavbarBrand;
import org.gwtbootstrap3.client.ui.NavbarNav;
import org.gwtbootstrap3.extras.notify.client.ui.Notify;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.HomePage;
import net.greenbeansit.jobtracker.client.components.ManagerPage;
import net.greenbeansit.jobtracker.client.components.ProjectPage;

/**
 * Represents the main widget of the application.
 */
public class Application extends Composite
{
	private static ApplicationUiBinder uiBinder = GWT
			.create(ApplicationUiBinder.class);

	@UiTemplate("Application.ui.xml")
	interface ApplicationUiBinder extends UiBinder<Widget, Application>
	{

	}

	@UiField
	Container		mainContainer;

	@UiField
	AnchorListItem	navHome;

	@UiField
	AnchorListItem	navProject;
	
	@UiField
	AnchorListItem	navManager;

	@UiField
	NavbarNav		nav;
	
	@UiField
	NavbarBrand navbarBrand;

	public Application()
	{
		initWidget(uiBinder.createAndBindUi(this));

		//Add navbar logo
		navbarBrand.add(new Image("jobtracker/assets/images/navbar_logo.png"));
		
		History.addValueChangeHandler(new HistoryValueChangeHandler());

		History.fireCurrentHistoryState();

	}

	private class HistoryValueChangeHandler
			implements ValueChangeHandler<String>
	{

		@Override
		public void onValueChange(ValueChangeEvent<String> event)
		{
			deactivateAllNavButtons();

			switch (event.getValue())
			{
			case "project":
				navProject.setActive(true);

				removeActiveContentContainer();

				addWidgetAsContentPage(new ProjectPage());
				break;
				
			case "manager":
				navManager.setActive(true);
				
				removeActiveContentContainer();
				
				addWidgetAsContentPage(new ManagerPage());
				break;

			default: // home
				navHome.setActive(true);

				removeActiveContentContainer();

				addWidgetAsContentPage(new HomePage());
				break;
			}

		}

		private void deactivateAllNavButtons()
		{
			for (int i = 0; i < nav.getWidgetCount(); i++)
			{
				Widget widget = nav.getWidget(i);
				if (widget instanceof AnchorListItem)
					((AnchorListItem) widget).setActive(false);
			}
		}

		private void removeActiveContentContainer()
		{
			for (int i = 1; i < mainContainer.getWidgetCount(); i++)
				mainContainer.remove(i);

		}

		private void addWidgetAsContentPage(Widget widget)
		{
			mainContainer.add(widget);
		}
	}
}
