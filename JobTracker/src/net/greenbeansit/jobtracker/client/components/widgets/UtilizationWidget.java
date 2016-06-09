package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.client.localization.HomePageConstants;

/**
 * Utilization ring widget
 * 
 * @author Max Blatt
 */
public class UtilizationWidget extends Composite implements LogicObservable
{

	private static UtilizationWidgetUiBinder	uiBinder	= GWT
			.create(UtilizationWidgetUiBinder.class);

	private static HomePageConstants			constants	= GWT
			.create(HomePageConstants.class);

	/**
	 * UiBinder for {@link UtilizationWidget}
	 */
	interface UtilizationWidgetUiBinder
			extends UiBinder<Widget, UtilizationWidget>
	{
	}

	@UiField
	ClearFix				container;

	@UiField
	PercentageRingDiagram	utilizationDiagram;

	@UiField
	Span					textRemainingVacationDays;

	@UiField
	Heading					headingRemainingVacationDays;

	@UiField
	Heading					headingUtilization;

	/**
	 * standard constructor
	 */
	public UtilizationWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));

		utilizationDiagram.setPercentage(120);

		headingRemainingVacationDays
				.setText(constants.headingRemainingVacationDays());
		headingUtilization.setText(constants.headingUtilization());
	}

	@Override
	public void updateObservable()
	{

	}

	@Override
	public void notifyLogicHandler()
	{

	}

	/**
	 * Sets the display number of the remaining vacation days.
	 * 
	 * @param remainingVacationDays
	 * 				the remaining vacation days.
	 */
	public void setRemainingVacationDays(Integer remainingVacationDays)
	{
		if (remainingVacationDays == null)
			remainingVacationDays = 0;

		textRemainingVacationDays.setText(remainingVacationDays + "");
	}

	/**
	 * Sets the percentage of the ring diagram.
	 * 
	 * @param utilizationPercent
	 * 				the utilization percent.
	 */
	public void setUtilization(Double utilizationPercent)
	{
		if (utilizationPercent == null)
			utilizationPercent = 0d;

		utilizationDiagram.setPercentage(utilizationPercent);
	}
}
