package net.greenbeansit.jobtracker.client.components.widgets;

import com.google.gwt.canvas.dom.client.Context2d;

import org.gwtbootstrap3.client.ui.html.ClearFix;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.gauge.Gauge;

public class UtilizationWidget extends Composite {

	private static UtilizationWidgetUiBinder uiBinder = GWT.create(UtilizationWidgetUiBinder.class);

	interface UtilizationWidgetUiBinder extends UiBinder<Widget, UtilizationWidget> {
	}

	
	@UiField
	ClearFix container;
	
	@UiField
	PercentageRingDiagram utilizationDiagram;
	
	
	public UtilizationWidget()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		utilizationDiagram.setPercentage(80);
	}
}
