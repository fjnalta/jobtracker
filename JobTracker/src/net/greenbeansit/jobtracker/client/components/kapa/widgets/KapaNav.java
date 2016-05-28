package net.greenbeansit.jobtracker.client.components.kapa.widgets;

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
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.select.client.ui.OptGroup;
import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Shows the left Navigation in KapaPage
 *
 * @author Philipp
 */
public class KapaNav extends Composite implements LogicObservable {

    @UiField
    Select selectJob;

    @UiField
    OptGroup myJobsOptGroup,allJobsOptGroup;

    @UiField
    Button buttonUp, buttonDown, buttonSave;

    @UiField
    TextBox possibilityPercentage, textIdentifier;

    @UiField
    Slider mySlider;

    @UiHandler("mySlider")
    void onSlide(SlideEvent<Double> event)
    {
        possibilityPercentage.setText(event.getValue().toString());
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

    @UiHandler("buttonSave")
    public void savePseudoJob(final ClickEvent e){
        PseudoJob template = new PseudoJob();
        if(textIdentifier.getText().length()>0){
            template.setName(textIdentifier.getText());
            template.setAuthor(0);
            template.setId(0);
            //TODO - implement LogicHandler savePseudoJob
            handler.savePseudoJob(template);
        }else{
            NotifyHelper.errorMessage("Fill missing fields");
        }
    }

    interface KapaNavUiBinder extends UiBinder<Widget, KapaNav> {
    }

    private static KapaNavUiBinder uiBinder = GWT.create(KapaNavUiBinder.class);

    private List<Job> jobList = new ArrayList<Job>();
    private Job currentJob = null;

    public KapaNav() {
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

    private void initialize(){
        buttonDown.setIcon(IconType.ARROW_DOWN);
        buttonUp.setIcon(IconType.ARROW_UP);
        possibilityPercentage.setText(mySlider.getValue().toString());
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