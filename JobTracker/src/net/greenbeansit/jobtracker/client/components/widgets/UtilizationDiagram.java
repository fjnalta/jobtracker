package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.html.ClearFix;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class UtilizationDiagram extends Composite
{
	private static UtilizationDiagramUiBinder uiBinder =
			GWT.create(UtilizationDiagramUiBinder.class);

	interface UtilizationDiagramUiBinder extends UiBinder<Widget, UtilizationDiagram>
	{
		
	}

	
	@UiField
	ClearFix diagramOuter;
	
	@UiField
	ClearFix diagramInner;
	
	@UiField
	ClearFix quarterOne;
	
	@UiField
	ClearFix quarterTwo;
	
	@UiField
	ClearFix quarterThree;
	
	@UiField
	ClearFix quarterFour;
	
	@UiField
	ClearFix quarterFinal;
	
	@UiField
	Heading diagramInnerText;
	
	
	public UtilizationDiagram()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	
	public void setUtilization(double percent)
	{
		if(percent >= 100f) //full
		{
			//Fill all four quarters
			rotateQuarter(quarterOne, 0f);
			rotateQuarter(quarterTwo, 90f);
			rotateQuarter(quarterThree, 180f);
			rotateQuarter(quarterFour, 270f);
			
			//..and hide the final one.
			quarterFinal.setVisible(false);
		}
		else if(percent >= 75f) //first three quarters full
		{
			//Fill all four quarters...
			rotateQuarter(quarterOne, 0f);
			rotateQuarter(quarterTwo, 90f);
			rotateQuarter(quarterThree, 180f);
			rotateQuarter(quarterFour, 270f);
			
			//...and move the final one over the fourth.
			rotateFinalQuarter(percent);
			
		}
		else if(percent >= 50f)
		{
			//Fill first three quarters...
			rotateQuarter(quarterOne, 0f);
			rotateQuarter(quarterTwo, 90f);
			rotateQuarter(quarterThree, 180f);
			
			//...hide fourth...
			quarterFour.setVisible(false);
			
			//..and move the final one over the third.
			rotateFinalQuarter(percent);
		}
		else if(percent >= 25f)
		{
			//Fill first two quarters...
			rotateQuarter(quarterOne, 0f);
			rotateQuarter(quarterTwo, 90f);
			
			//...hide the other two...
			quarterThree.setVisible(false);
			quarterFour.setVisible(false);
			
			//..and move the final one over the second.
			rotateFinalQuarter(percent);
		}
		else if(percent > 0f)
		{
			//Fill first quarter...
			rotateQuarter(quarterOne, 0f);
			
			//...hide the other three...
			quarterTwo.setVisible(false);
			quarterThree.setVisible(false);
			quarterFour.setVisible(false);
			
			//...and move the final one over the first.
			rotateFinalQuarter(percent);
		}
		else
		{
			//Hide all quarters.
			quarterOne.setVisible(false);
			quarterTwo.setVisible(false);
			quarterThree.setVisible(false);
			quarterFour.setVisible(false);
		}
		
		diagramInnerText.setText(
				NumberFormat.getFormat("###").format(percent) + "%");
	}
	
	private void rotateFinalQuarter(double percent)
	{
		//rotate 
		double filledDegrees = 360 * (percent * 0.01f);
		
		rotateQuarter(quarterFinal, filledDegrees);
	}
	
	private static void rotateQuarter(ClearFix quarter, double degrees)
	{
		quarter.setVisible(true);
		
		String value = "rotate(" + NumberFormat.getFormat("###").format(degrees) + "deg)"; 
		
		quarter.getElement().getStyle().setProperty(
				"transform",
				value);
	}
	
	private static double transformRotation(double diagramDegrees)
	{
		//add offset
		diagramDegrees += 90f;
		
		if(diagramDegrees >= 360f)
			return diagramDegrees - 360f;
		else
			return diagramDegrees;
		
	}
	
}
