package net.greenbeansit.jobtracker.client.components.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class UtilizationDiagram extends Composite
{
	private static UtilizationDiagramUiBinder uiBinder =
			GWT.create(UtilizationDiagramUiBinder.class);

	interface UtilizationDiagramUiBinder extends UiBinder<Widget, UtilizationDiagram>
	{
		
	}

	
	
	public UtilizationDiagram()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		createDiagram();
	}

	private void createDiagram()
	{
		double[] utilizations = getUtilizationData();
		
		for(int i = 0; i < utilizations.length; i++)
		{
			
		}
	}
	
	private double[] getUtilizationData()
	{
		double[] utilizations = new double[31];
		for(int i = 0; i < utilizations.length; i++)
			utilizations[i] = 70;
		
		return utilizations;
	}
}
