package net.greenbeansit.jobtracker.client.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.widgets.CalendarWidget;
import net.greenbeansit.jobtracker.client.components.widgets.JobsWidget;
import net.greenbeansit.jobtracker.client.components.widgets.UtilizationWidget;
import net.greenbeansit.jobtracker.client.components.widgets.WorkDiscriptionWidget;
import net.greenbeansit.jobtracker.shared.Job;

public class HomePage extends Composite {

	private static HomePageUiBinder uiBinder = GWT.create(HomePageUiBinder.class);

	interface HomePageUiBinder extends UiBinder<Widget, HomePage> {
	}

	@UiField
	JobsWidget jobsWidget;

	@UiField
	CalendarWidget calendarWidget;

	@UiField
	UtilizationWidget utilizationWidget;

	@UiField
	WorkDiscriptionWidget workDiscriptionWidget;

	public HomePage() {
		initWidget(uiBinder.createAndBindUi(this));

		initialize();

	}

	/**
	 * function called in constuctor on initializing the widgets and connect
	 * them with the homePage
	 */
	private void initialize() {
		jobsWidget.registerObserver(this);
		calendarWidget.registerObserver(this);
		utilizationWidget.registerObserver(this);
		workDiscriptionWidget.registerObserver(this);
	}

	/**
	 * Method for booking a new ActivityReport The method gets the data from
	 * calendarWidget,JobsWidget and WorkDiscriptionWidget then it sends it to
	 * the backend
	 */
	public void bookActivityReport() {
		Job jobToSave = jobsWidget.getSelectedJob();
		// ActivityReport newReport = calendarWidget.getNewActivityReport();

	}

	/**
	 * update Widgets based on selected Job
	 */
	public void update(Job selectedJob) {

	}

	/**
	 * 
	 */

}
