package net.greenbeansit.jobtracker.client;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.Image;
import org.gwtbootstrap3.client.ui.NavbarBrand;
import org.gwtbootstrap3.client.ui.NavbarNav;

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
import net.greenbeansit.jobtracker.client.components.KapaPage;
import net.greenbeansit.jobtracker.client.components.manager.ManagerPage;
import net.greenbeansit.jobtracker.client.components.manager.detail.ManagerEmployeeDetailPage;
import net.greenbeansit.jobtracker.client.components.project.ProjectPage;
import net.greenbeansit.jobtracker.client.components.project.detail.ProjectDetailPage;

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
	AnchorListItem	navKapa;

	@UiField
	NavbarNav		nav;

	@UiField
	NavbarBrand		navbarBrand;

	public Application()
	{
		initWidget(uiBinder.createAndBindUi(this));

		// Add navbar logo
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

			String[] addressSplit = event.getValue().split("/");

			switch (addressSplit[0])
			{
			case "project":
				loadProjectPage(addressSplit);
				break;

			case "manager":
				loadManagerPage(addressSplit);
				break;

			case "kapa":
				loadKapaPage(addressSplit);
				break;

			default: // home
				loadHomePage(addressSplit);
				break;
			}

		}

		private void loadProjectPage(String[] addressSplit)
		{
			Composite page;

			if (addressSplit.length == 3 && addressSplit[1] == "job")
			{
				Integer jobId;

				try
				{
					jobId = Integer.parseInt(addressSplit[2]);

				} catch (Exception ex)
				{
					jobId = null;
				}

				if (jobId != null)
					page = new ProjectDetailPage(jobId);
				else
					page = new ProjectPage();
			} else
				page = new ProjectPage();
			
			
			navProject.setActive(true);

			removeActiveContentContainer();

			addWidgetAsContentPage(page);
		}

		private void loadManagerPage(String[] addressSplit)
		{
			Composite page;

			if (addressSplit.length == 3 && addressSplit[1] == "employee")
			{
				Integer userId;

				try
				{
					userId = Integer.parseInt(addressSplit[2]);

				} catch (Exception ex)
				{
					userId = null;
				}

				if (userId != null)
					page = new ManagerEmployeeDetailPage(userId);
				else
					page = new ManagerPage();
			} else
				page = new ManagerPage();

			navManager.setActive(true);

			removeActiveContentContainer();

			addWidgetAsContentPage(page);
		}

		private void loadKapaPage(String[] addressSplit)
		{
			navKapa.setActive(true);
			removeActiveContentContainer();
			addWidgetAsContentPage(new KapaPage());
		}

		private void loadHomePage(String[] addressSplit)
		{
			navHome.setActive(true);

			removeActiveContentContainer();

			addWidgetAsContentPage(new HomePage());
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
