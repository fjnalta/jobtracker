package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.html.ClearFix;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents a percentage in a donut diagram.
 * 
 * @author Max Blatt
 */
public class UtilizationDiagram extends Composite
{
	private static UtilizationDiagramUiBinder uiBinder =
			GWT.create(UtilizationDiagramUiBinder.class);

	interface UtilizationDiagramUiBinder extends UiBinder<Widget, UtilizationDiagram>
	{
		
	}

	private double utilization;
	
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
	
	
	/**
	 * Initializes a new instance of the {@link UtilizationDiagram} class.
	 */
	public UtilizationDiagram()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	
	/**
	 * Gets the percentage of the current {@link UtilizationDiagram}.
	 * 
	 * @return a double.
	 */
	public double getUtilization()
	{
		return utilization;
	}
	
	/**
	 * Sets the percentag of the current {@link UtilizationDiagram} and
	 * updates the graphics.
	 * 
	 * @param utilization the percentage the diagram should be filled.
	 * 0 means empty and 100 completely filled.
	 */
	public void setUtilization(double utilization)
	{
		if(utilization >= 100f) //full
		{
			//Fill all four quarters
			rotateQuarter(quarterOne, 0f);
			rotateQuarter(quarterTwo, 90f);
			rotateQuarter(quarterThree, 180f);
			rotateQuarter(quarterFour, 270f);
			
			//..and hide the final one.
			quarterFinal.setVisible(false);
		}
		else if(utilization >= 75f)
		{
			/* To prevent the final quarter from moving over the 0 degree
			 * point, it will be set to 270 degrees and the fourth quarter will
			 * be rotated. 
			 */
			
			rotateQuarter(quarterOne, 0f);
			rotateQuarter(quarterTwo, 90f);
			rotateQuarter(quarterThree, 180f);
			
			rotateQuarter(quarterFinal, 270f);
			
			rotateQuarterByPercent(quarterFour, utilization, -90f);
		}
		else if(utilization >= 50f)
		{
			//Fill first three quarters...
			rotateQuarter(quarterOne, 0f);
			rotateQuarter(quarterTwo, 90f);
			rotateQuarter(quarterThree, 180f);
			
			//...hide fourth...
			quarterFour.setVisible(false);
			
			//..and move the final one over the third.
			rotateQuarterByPercent(quarterFinal, utilization, 0f);
		}
		else if(utilization >= 25f)
		{
			//Fill first two quarters...
			rotateQuarter(quarterOne, 0f);
			rotateQuarter(quarterTwo, 90f);
			
			//...hide the other two...
			quarterThree.setVisible(false);
			quarterFour.setVisible(false);
			
			//..and move the final one over the second.
			rotateQuarterByPercent(quarterFinal, utilization, 0f);
		}
		else if(utilization > 0f)
		{
			//Fill first quarter...
			rotateQuarter(quarterOne, 0f);
			
			//...hide the other three...
			quarterTwo.setVisible(false);
			quarterThree.setVisible(false);
			quarterFour.setVisible(false);
			
			//...and move the final one over the first.
			rotateQuarterByPercent(quarterFinal, utilization, 0f);
		}
		else
		{
			//Hide all quarters.
			quarterOne.setVisible(false);
			quarterTwo.setVisible(false);
			quarterThree.setVisible(false);
			quarterFour.setVisible(false);
			
			quarterFinal.setVisible(false);
		}
		
		diagramInnerText.setText(
				NumberFormat.getFormat("###").format(utilization) + "%");
		
		this.utilization = utilization;
	}
	
	/**
	 * Calls {@link #rotateQuarter(UIObject, double)} with the
	 * corresponding rotation of the following percentage. 
	 * 
	 * @param quarter the {@link UIObject} that should be rotated.
	 * @param percent the percentage the diagram should be filled.
	 * @param degreeOffset the offset for the rotation in degrees.
	 */
	private static void rotateQuarterByPercent(
			UIObject quarter,
			double percent,
			double degreeOffset)
	{
		double filledDegrees = (360 * (percent * 0.01f)) + degreeOffset;
		
		rotateQuarter(quarter, filledDegrees);
	}
	
	/**
	 * Sets the {@code transform} CSS property of the following quarter to the
	 * following degrees.
	 * 
	 * @param quarter the {@link UIObject} that should be rotated.
	 * @param degrees the degrees the quarter should be rotated.
	 */
	private static void rotateQuarter(UIObject quarter, double degrees)
	{
		quarter.setVisible(true);
		
		String value = "rotate("
						+ NumberFormat.getFormat("###").format(degrees)
						+ "deg)"; 
		
		quarter.getElement().getStyle().setProperty(
				"transform",
				value);
	}
	
}
