package net.greenbeansit.jobtracker.client.components.kapa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import net.greenbeansit.jobtracker.client.components.LogicObservable;
import net.greenbeansit.jobtracker.client.components.widgets.SelectJobOption;
import net.greenbeansit.jobtracker.client.utils.rest.NotifyHelper;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.PseudoJob;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.FullCalendar;
import org.gwtbootstrap3.extras.fullcalendar.client.ui.ViewOption;
import org.gwtbootstrap3.extras.select.client.ui.OptGroup;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philipp Minges on 23.05.16.
 */
public class KapaPage extends Composite implements LogicObservable {

    @UiField
    OptGroup myJobsOptGroup,allJobsOptGroup;

    @UiField
    Select selectJob;

    @UiField
    Button buttonUp, buttonDown, buttonSave;

    @UiField
    TextBox possibilityPercentage, textIdentifier;

    @UiField
    Slider mySlider;

    @UiField
    Row content;

    @UiField
    Heading dateHeading;

    @UiHandler("mySlider")
    void onSlide(SlideEvent<Double> event)
    {
        possibilityPercentage.setText(event.getValue().toString());
    }

    @UiHandler("buttonSave")
    public void savePseudoJob(final ClickEvent e){
        PseudoJob template = new PseudoJob();
        if(textIdentifier.getText().length()>0){
            template.setName(textIdentifier.getText());
            //TODO implement LogicHandler savePseudoJob
            //handler.savePseudoJob(template);
        }else{
            NotifyHelper.errorMessage("Fill missing fields");
        }
    }

    @UiHandler("buttonUp")
    void onClickUp(ClickEvent e) {
        if (mySlider.getValue() < 76)
            mySlider.setValue(mySlider.getValue() + 25);
        //refresh percentage TextBox
        possibilityPercentage.setText(mySlider.getValue().toString());
    }

    @UiHandler("buttonDown")
    void onClickDown(ClickEvent e) {
        if (mySlider.getValue() > 24)
            mySlider.setValue(mySlider.getValue() - 25);
        possibilityPercentage.setText(mySlider.getValue().toString());
    }

    private List<Job> jobList = new ArrayList<Job>();
    private List<PseudoJob> pseudoJobList = new ArrayList<PseudoJob>();
    private Job currentJob = null;

    interface KapaPageUiBinder extends UiBinder<Widget, KapaPage> {
    }

    private static KapaPageUiBinder uiBinder = GWT.create(KapaPageUiBinder.class);

    FullCalendar fc = new FullCalendar("kapaCalendar", ViewOption.month, false);

    public KapaPage() {
        initWidget(uiBinder.createAndBindUi(this));
        initialize();

        handler.addObservable(this);
        handler.updateObservable(this);
        selectJob.addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                currentJob = ((SelectJobOption) selectJob.getSelectedItem()).getJob();
                notifyLogicHandler();
            }
        });
        handler.loadJobs();
    }

    private void initialize() {
        //set Buttons
        buttonDown.setIcon(IconType.ARROW_DOWN);
        buttonUp.setIcon(IconType.ARROW_UP);
        //set Slider
        possibilityPercentage.setText(mySlider.getValue().toString());
        //load calendar
        content.add(fc);
    }

    private void addJobs(List<Job> jobList) {
        for (Job currentJob : jobList) {
            SelectJobOption tempOption = new SelectJobOption(currentJob);
            allJobsOptGroup.add(tempOption);
        }
    }

    @Override
    public void updateObservable() {
        allJobsOptGroup.clear();
        this.jobList = handler.getJobList();
        addJobs(this.jobList);
        currentJob = handler.getCurrentJob();
        if (currentJob != null) {
            for (Option opt : selectJob.getItems()) {
                ((SelectJobOption) opt).setSelected(false);
                if (((SelectJobOption) opt).getJob().equals(currentJob)) {
                    opt.setSelected(true);
                }
            }
        }
        selectJob.refresh();
    }

    @Override
    public void notifyLogicHandler() {
        if(currentJob != null){
            handler.setCurrentJob(currentJob);
        }
        else{
            NotifyHelper.errorMessage("Please select job");
        }
    }
}