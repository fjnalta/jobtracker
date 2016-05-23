package net.greenbeansit.jobtracker.client.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideEvent;

/**
 * Created by Philipp Minges on 23.05.16.
 */
public class KapaPage extends Composite {
    interface KapaPageUiBinder extends UiBinder<Widget, KapaPage>
    {
    }

    private static KapaPageUiBinder uiBinder = GWT.create(KapaPageUiBinder.class);

    @UiField
    Button buttonUp;

    @UiField
    Button buttonDown;

    @UiField
    TextBox possibilityPercentage;

    @UiField
    Slider mySlider;

    @UiHandler("mySlider")
    void onSlide(SlideEvent<Double> event)
    {
        possibilityPercentage.setText(event.getValue().toString());

    }

    @UiHandler("buttonUp")
    void onClickUp(ClickEvent e)
    {
        if (mySlider.getValue() < 100)
            mySlider.setValue(mySlider.getValue() + 1);
        //refresh percentage TextBox
        possibilityPercentage.setText(mySlider.getValue().toString());
    }

    @UiHandler("buttonDown")
    void onClickDown(ClickEvent e)
    {
        if (mySlider.getValue() > 0)
            mySlider.setValue(mySlider.getValue() - 1);
        possibilityPercentage.setText(mySlider.getValue().toString());
    }

    public KapaPage()
    {
        initWidget(uiBinder.createAndBindUi(this));
        initialize();
    }

    private void initialize()
    {
        //set Buttons
        buttonDown.setIcon(IconType.ARROW_DOWN);
        buttonUp.setIcon(IconType.ARROW_UP);
        //set Slider
        possibilityPercentage.setText(mySlider.getValue().toString());
    }


}