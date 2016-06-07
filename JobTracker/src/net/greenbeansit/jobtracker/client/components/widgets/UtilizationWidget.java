package net.greenbeansit.jobtracker.client.components.widgets;

import com.google.gwt.canvas.dom.client.Context2d;

import org.gwtbootstrap3.client.ui.html.ClearFix;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.gauge.Gauge;

import net.greenbeansit.jobtracker.client.components.HomePage;
import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.client.components.project.ProjectPage;

/**
 * Utilization ring widget
 * @author Max Blatt
 */
public class UtilizationWidget extends Composite implements LogicObservable{

	private static UtilizationWidgetUiBinder uiBinder = GWT.create(UtilizationWidgetUiBinder.class);

	/**
	 * UiBinder for {@link UtilizationWidget}
	 */
	interface UtilizationWidgetUiBinder extends UiBinder<Widget, UtilizationWidget> {
	}

	
	@UiField
	ClearFix container;
	
	@UiField
	PercentageRingDiagram utilizationDiagram;

	/**
	 * standard constructor
	 */
	public UtilizationWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		utilizationDiagram.setPercentage(180);
	}


	@Override
	public void updateObservable() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void notifyLogicHandler() {
		// TODO Auto-generated method stub
		
	}

	
}
