package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.html.ClearFix;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents a percentage in a ring diagram.
 * 
 * @author Max Blatt
 */
public class PercentageRingDiagram extends Composite
{
	private static PercentageRingDiagramUiBinder uiBinder =
			GWT.create(PercentageRingDiagramUiBinder.class);

	/**
	 * UiBinde for {@link PercentageRingDiagram}.
	 * 
	 * @author Max Blatt
	 */
	interface PercentageRingDiagramUiBinder 
		extends UiBinder<Widget, PercentageRingDiagram>
	{
		
	}

	private double percentage;
	
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
	
	@UiField
	ClearFix diagramInnerInner;
	
	@UiField
	ClearFix quarterSecondOne;
	
	@UiField
	ClearFix quarterSecondTwo;
	
	@UiField
	ClearFix quarterSecondThree;
	
	@UiField
	ClearFix quarterSecondFour;
	
	@UiField
	ClearFix quarterSecondFinal;
	
	
	/**
	 * Initializes a new instance of the {@link PercentageRingDiagram} class.
	 */
	public PercentageRingDiagram()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	
	/**
	 * Gets the percentage of the current {@link PercentageRingDiagram}.
	 * 
	 * @return a double.
	 */
	public double getPercentage()
	{
		return percentage;
	}
	
	/**
	 * Sets the percentage of the current {@link PercentageRingDiagram} and
	 * updates the graphics.
	 * 
	 * @param percentage the percentage to which the diagram should be filled.
	 * 0 means empty and 100 completely filled.
	 */
	public void setPercentage(double percentage)
	{
		if(percentage >= 200f)
		{
			setInnerRingPercentage(percentage - 100f); 
			setOuterRingPercentge(100f);
		}
		else if(percentage >= 175f)
		{
			setInnerRingPercentage(percentage - 100f);
			setOuterRingPercentge(100f);
		}
		else if(percentage >= 150f)
		{
			setInnerRingPercentage(percentage - 100f);
			setOuterRingPercentge(100f);
		}
		else if(percentage >= 125f)
		{
			setInnerRingPercentage(percentage - 100f);
			setOuterRingPercentge(100f);
		}
		else
		{
			//Hide inner ring
			quarterSecondOne.setVisible(false);
			quarterSecondTwo.setVisible(false);
			quarterSecondThree.setVisible(false);
			quarterSecondFour.setVisible(false);
			
			quarterSecondFinal.setVisible(false);
			
			setOuterRingPercentge(percentage);
		}
		
		diagramInnerText.setText(
				NumberFormat.getFormat("###").format(percentage) + "%");
		
		this.percentage = percentage;
	}
	
	/**
	 * Fills the inner ring.
	 * 
	 * @param percentage the percentage the ring should be filled.
	 */
	private void setInnerRingPercentage(double percentage)
	{
//		Window.alert("Inner: " + percentage);
		
		if(percentage >= 100f) //Full
		{
			//Fill all four quarters
			rotateQuarter(quarterSecondOne, 0f);
			rotateQuarter(quarterSecondTwo, 90f);
			rotateQuarter(quarterSecondThree, 180f);
			rotateQuarter(quarterSecondFour, 270f);
			
			//..and hide the final one.
			quarterSecondFinal.setVisible(false);
		}
		else if(percentage >= 75f)
		{
			/* To prevent the final quarter from moving over the 0 degree
			 * point, it will be set to 270 degrees and the fourth quarter will
			 * be rotated. 
			 */
			
			rotateQuarter(quarterSecondOne, 0f);
			rotateQuarter(quarterSecondTwo, 90f);
			rotateQuarter(quarterSecondThree, 180f);
			
			//..and hide the final one.
			quarterSecondFinal.setVisible(false);
			
			rotateQuarterToFillPercentage(quarterSecondFour, percentage, -90f);
		}
		else if(percentage >= 50f)
		{
			//Fill first three quarters...
			rotateQuarter(quarterSecondOne, 0f);
			rotateQuarter(quarterSecondTwo, 90f);
			rotateQuarter(quarterSecondThree, 180f);
			
			//...hide fourth...
			quarterSecondFour.setVisible(false);
			
			//..and move the final one over the third.
			rotateQuarterToFillPercentage(quarterSecondFinal, percentage, 0f);
		}
		else if(percentage >= 25f)
		{
			//Fill first two quarters...
			rotateQuarter(quarterSecondOne, 0f);
			rotateQuarter(quarterSecondTwo, 90f);
			
			//...hide the other two...
			quarterSecondThree.setVisible(false);
			quarterSecondFour.setVisible(false);
			
			//..and move the final one over the second.
			rotateQuarterToFillPercentage(quarterSecondFinal, percentage, 0f);
		}
		else if(percentage > 0f)
		{
			//Fill first quarter...
			rotateQuarter(quarterSecondOne, 0f);
			
			//...hide the other three...
			quarterSecondTwo.setVisible(false);
			quarterSecondThree.setVisible(false);
			quarterSecondFour.setVisible(false);
			
			//...and move the final one over the first.
			rotateQuarterToFillPercentage(quarterSecondFinal, percentage, 0f);
		}
		else
		{
			//Hide all quarters.
			quarterSecondOne.setVisible(false);
			quarterSecondTwo.setVisible(false);
			quarterSecondThree.setVisible(false);
			quarterSecondFour.setVisible(false);
			
			quarterSecondFinal.setVisible(false);
		}
	}
	
	/**
	 * Fills the outer ring.
	 * 
	 * @param percentage the percentage the ring should be filled.
	 */
	private void setOuterRingPercentge(double percentage)
	{
//		Window.alert("Outer: " + percentage);
		
		if(percentage >= 100f) //Full
		{
			//Fill all four quarters
			rotateQuarter(quarterOne, 0f);
			rotateQuarter(quarterTwo, 90f);
			rotateQuarter(quarterThree, 180f);
			rotateQuarter(quarterFour, 270f);
			
			//..and hide the final one.
			quarterFinal.setVisible(false);
		}
		else if(percentage >= 75f)
		{
			/* To prevent the final quarter from moving over the 0 degree
			 * point, it will be set to 270 degrees and the fourth quarter will
			 * be rotated. 
			 */
			
			rotateQuarter(quarterOne, 0f);
			rotateQuarter(quarterTwo, 90f);
			rotateQuarter(quarterThree, 180f);
			
			//..and hide the final one.
			quarterFinal.setVisible(false);
			
			rotateQuarterToFillPercentage(quarterFour, percentage, -90f);
		}
		else if(percentage >= 50f)
		{
			//Fill first three quarters...
			rotateQuarter(quarterOne, 0f);
			rotateQuarter(quarterTwo, 90f);
			rotateQuarter(quarterThree, 180f);
			
			//...hide fourth...
			quarterFour.setVisible(false);
			
			//..and move the final one over the third.
			rotateQuarterToFillPercentage(quarterFinal, percentage, 0f);
		}
		else if(percentage >= 25f)
		{
			//Fill first two quarters...
			rotateQuarter(quarterOne, 0f);
			rotateQuarter(quarterTwo, 90f);
			
			//...hide the other two...
			quarterThree.setVisible(false);
			quarterFour.setVisible(false);
			
			//..and move the final one over the second.
			rotateQuarterToFillPercentage(quarterFinal, percentage, 0f);
		}
		else if(percentage > 0f)
		{
			//Fill first quarter...
			rotateQuarter(quarterOne, 0f);
			
			//...hide the other three...
			quarterTwo.setVisible(false);
			quarterThree.setVisible(false);
			quarterFour.setVisible(false);
			
			//...and move the final one over the first.
			rotateQuarterToFillPercentage(quarterFinal, percentage, 0f);
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
	}
	
	/**
	 * Calls {@link #rotateQuarter(UIObject, double)} with the
	 * corresponding rotation of the following percentage. 
	 * 
	 * @param quarter the {@link UIObject} that should be rotated.
	 * @param percentage the percentage to which the diagram should be filled.
	 * @param degreeOffset the offset for the rotation in degrees.
	 */
	private static void rotateQuarterToFillPercentage(
			UIObject quarter,
			double percentage,
			double degreeOffset)
	{
		double filledDegrees = (360 * (percentage * 0.01f)) + degreeOffset;
		
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
