package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.client.ui.html.ClearFix;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import net.greenbeansit.jobtracker.client.components.LogicObservable;

/**
 * Utilization ring widget
 * 
 * @author Max Blatt
 */
public class UtilizationWidget extends Composite implements LogicObservable
{

	private static UtilizationWidgetUiBinder uiBinder = GWT
			.create(UtilizationWidgetUiBinder.class);

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
	Span textRemainingVacationDays;

	/**
	 * standard constructor
	 */
	public UtilizationWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));

		utilizationDiagram.setPercentage(120);
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
	 */
	public void setRemainingVacationDays(Integer remainingVacationDays)
	{
		if(remainingVacationDays == null)
			remainingVacationDays = 0;
		
		textRemainingVacationDays.setText(remainingVacationDays + "");
	}
	
	/**
	 * Sets the percentage of the ring diagram.
	 * 
	 * @param utilizationPercent
	 */
	public void setUtilization(Double utilizationPercent)
	{
		if(utilizationPercent == null)
			utilizationPercent = 0d;
		
		utilizationDiagram.setPercentage(utilizationPercent);
	}
}
